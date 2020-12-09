package com.tui.proof.ws.controller;

import com.tui.proof.ws.BookingServiceException;
import com.tui.proof.ws.model.Availability;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Holder;
import com.tui.proof.ws.repository.BookingFlightRepository;
import com.tui.proof.ws.repository.BookingRepository;
import com.tui.proof.ws.repository.FlightRepository;
import com.tui.proof.ws.repository.HolderRepository;
import com.tui.proof.ws.requests.AvailabilityRequestDTO;
import com.tui.proof.ws.responses.AvailabilityDTO;
import com.tui.proof.ws.service.BookingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.http.HttpHeaders.CACHE_CONTROL;

@Log4j2
@RestController
@RequestMapping(value = "/booking-service")
@Api( value = "Booking Entity",  tags = {"Booking Entity"} )
public class BookingController {

  @Autowired
  BookingService bookingService ;


  /**
   *
   * @param availabilityRequestDTO
   * @param binding
   * @return
   */
  @ApiOperation(
          tags = "Check Flight Availability",
          value = "Check Availability.",
          notes = "Check Availability"
  )
  @ApiResponses(
          value = {
                  @ApiResponse(code = 201, message = "Created - The request was successful, we created a new resource and the response body contains the representation."),
                  @ApiResponse(code = 400, message = "Bad Request - The data given in the POST failed validation. Inspect the response body for details."),
                  @ApiResponse(code = 404, message = "Not Found"),
                  @ApiResponse(code = 408, message = "Request Timeout"),
                  @ApiResponse(code = 500, message = "Internal Server Error - We couldn't delete the resource. Please try again."),
                  @ApiResponse(code = 502, message = "Service Unavailable")
          }
  )
  @PostMapping(path ="/checkFlightAvailability", produces = AvailabilityDTO.TYPE)
  public ResponseEntity<List<Availability>> checkFlightAvailability(@Valid @RequestBody AvailabilityRequestDTO availabilityRequestDTO , BindingResult binding) {
      if (binding.hasErrors()) {
        log.warn("Conflict", binding.getAllErrors());
        return ResponseEntity.badRequest().build();
      }

      List<Availability> availabilityList ;
      try{
        availabilityList = bookingService.checkFlightAvailability(availabilityRequestDTO);
      }catch (BookingServiceException e){
        return ResponseEntity.status(e.getHttpStatus()).build();
      }
      catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
      return ResponseEntity.status(HttpStatus.CREATED).header(CACHE_CONTROL, "store, private, max-age=15").body(availabilityList);

  }


  /**
   *
   * @param holder
   * @param binding
   * @return
   * @throws Exception
   */
  @ApiOperation(
          tags = "Create Booking",
          value = "Create Booking.",
          notes = "Create Booking"
  )
  @ApiResponses(
          value = {
                  @ApiResponse(code = 201, message = "Created - The request was successful, we created a new resource and the response body contains the representation."),
                  @ApiResponse(code = 400, message = "Bad Request - The data given in the POST failed validation. Inspect the response body for details."),
                  @ApiResponse(code = 404, message = "Not Found"),
                  @ApiResponse(code = 408, message = "Request Timeout"),
                  @ApiResponse(code = 500, message = "Internal Server Error - We couldn't delete the resource. Please try again."),
                  @ApiResponse(code = 502, message = "Service Unavailable")
          }
  )
  @PostMapping("/createBooking")
  public ResponseEntity<Booking> createBooking(@RequestBody Holder holder , BindingResult binding){
    if (binding.hasErrors()) {
      log.warn("Conflict", binding.getAllErrors());
      return ResponseEntity.badRequest().build();
    }
    Booking booking ;
    try{
        booking = bookingService.createBooking(holder);
      }
        catch (Exception e) {
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
      return ResponseEntity.status(HttpStatus.CREATED).body(booking);

  }

  /**
   *
   * @param bookingId
   * @param flight
   * @param binding
   * @return
   */
  @ApiOperation(
          tags = "For add flight to booking",
          value = "For add flight to booking.",
          notes = "For add flight to booking"
  )
  @ApiResponses(
          value = {
                  @ApiResponse(code = 201, message = "Created - The request was successful, we created a new resource and the response body contains the representation."),
                  @ApiResponse(code = 400, message = "Bad Request - The data given in the POST failed validation. Inspect the response body for details."),
                  @ApiResponse(code = 404, message = "Not Found"),
                  @ApiResponse(code = 408, message = "Request Timeout"),
                  @ApiResponse(code = 500, message = "Internal Server Error - We couldn't delete the resource. Please try again."),
                  @ApiResponse(code = 502, message = "Service Unavailable")
          }
  )
  @PostMapping("/addFlightToBooking")
  public ResponseEntity<Booking> addFlightToBooking(@RequestParam Integer bookingId, @RequestBody Flight flight, BindingResult binding){
    if (binding.hasErrors()) {
      log.warn("Conflict", binding.getAllErrors());
      return ResponseEntity.badRequest().build();
    }
    Booking booking ;
    try{
      booking = bookingService.addFlightToBooking(bookingId, flight);
    }
    catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(booking);
  }

  /**
   *
   * @param bookingId
   * @param flightId
   * @return
   * @throws Exception
   */
  @ApiOperation(value="for delete flight from booking")
  @DeleteMapping("/deleteFlightFromBooking")
  public ResponseEntity deleteFlightFromBooking(@RequestParam Integer bookingId, @RequestParam Integer flightId) throws Exception {
    try{
      bookingService.deleteByFlightNumber(bookingId, flightId);
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
  }

  /**
   *
   * @return
   * @throws Exception
   */
  @ApiOperation(value="for get all bookings")
  @GetMapping("/getBooking")
  public ResponseEntity<List<Booking>> getBooking() throws Exception {
    List<Booking> bookingList ;
    try{
      bookingList = bookingService.getBooking();
    }catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(bookingList);
  }

  /**
   *
   * @param id
   * @return
   * @throws Exception
   */
  @ApiOperation(value="for delete booking")
  @DeleteMapping("/deleteBooking")
  public ResponseEntity deleteBooking(@RequestParam Integer id) throws Exception {
    try{
      bookingService.deleteById(id);
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return ResponseEntity.status(HttpStatus.ACCEPTED).body("");

  }

  /**
   *
   * @param bookingId
   * @return
   * @throws Exception
   */
  @ApiOperation(value="for confirm booking")
  @PutMapping("/confirmBooking")
  public ResponseEntity<Booking> confirmBooking(@RequestParam Integer bookingId) throws Exception {
    Booking booking ;
    try {
      booking= bookingService.confirmBooking(bookingId);
    }
    catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
      }
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(booking);
  }

  /**
   *
   * @param id
   * @return
   * @throws Exception
   */
  @ApiOperation(value="for get bookings by id")
  @GetMapping("/getBookingById")
  public ResponseEntity<Booking> getBookingById(Integer id) throws Exception {
    Booking booking ;
    try {
      booking= bookingService.getBookingById(id);
    }
    catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    return ResponseEntity.status(HttpStatus.OK).body(booking);
  }
}
