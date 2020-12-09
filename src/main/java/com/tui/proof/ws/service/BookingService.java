package com.tui.proof.ws.service;

import com.tui.proof.ws.BookingServiceException;
import com.tui.proof.ws.event.BookingEvent;
import com.tui.proof.ws.model.Availability;
import com.tui.proof.ws.model.Booking;
import com.tui.proof.ws.model.Flight;
import com.tui.proof.ws.model.Holder;
import com.tui.proof.ws.repository.*;
import com.tui.proof.ws.requests.AvailabilityRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Janty
 */

@Service
@Log4j2
public class BookingService {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    BookingFlightRepository bookingFlightRepository;
    @Autowired
    HolderRepository holderRepository;
    @Autowired
    AvailabilityRepository availabilityRepository ;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    /**
     *
     * @param availabilityRequestDTO
     * @return
     * @throws BookingServiceException
     */
    public List<Availability> checkFlightAvailability(AvailabilityRequestDTO availabilityRequestDTO) throws BookingServiceException {
        List<Flight> flightList=flightRepository.getAvailableFlights(availabilityRequestDTO.getOrigin(), availabilityRequestDTO.getDestination(), availabilityRequestDTO.getDateFrom(),
                availabilityRequestDTO.getDateTo()
               // , availabilityRequestDTO.getPaxes().getAdults(), availabilityRequestDTO.getPaxes().getChildren(),availabilityRequestDTO.getPaxes().getInfants()
        );
        List<Availability> availabilityList= new ArrayList();

        flightList.stream().forEach(flight -> {
            Availability availability = new Availability();
            availability.setFlightNumber(flight.getFlightNumber());
            availability.setDate(flight.getDate());
            availability.setHour(flight.getHour());
            availability.setPrice(flight.getPrice());
            availabilityList.add(availability);
        });

        return availabilityRepository.saveAll(availabilityList);
    }

    /**
     *
     * @param holder
     * @return
     * @throws Exception
     */
    public Booking createBooking(Holder holder) throws Exception {
        try{
            Holder holder1=holderRepository.save(holder);
            Integer holderId=holder1.getHolderId();

            Booking booking = new Booking();
            booking.setIsConfirmed(false);
            booking.setHolderId(holderId);
            booking.setCreatedOn(new Timestamp(System.currentTimeMillis()));
            Booking booking1=bookingRepository.save(booking);
            return booking1;
        }catch (Exception e){
            throw new BookingServiceException(HttpStatus.CONFLICT, e);
        }
    }

    /**
     *
     * @param bookingId
     * @return
     * @throws Exception
     */

    public Booking confirmBooking(Integer bookingId) throws Exception {
        Booking booking ;
        try{
            booking=getBookingById(bookingId);
            booking.setIsConfirmed(true);
            booking = bookingRepository.save(booking);

            //Asyc Booking process
            processBooking(booking);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return booking ;
    }

    @Async
    private void processBooking(Booking booking){
        eventPublisher.publishEvent(new BookingEvent(booking.getBookingId().toString(),  booking, "Confirmed"));

    }

    /**
     *
     * @param bookingId
     * @param flightId
     * @throws BookingServiceException
     */
    public void deleteByFlightNumber(Integer bookingId, Integer flightId) throws BookingServiceException{
        try{
            bookingFlightRepository.deleteByFlightNumber(bookingId, flightId);
        }catch (Exception e){
            throw new BookingServiceException(HttpStatus.CONFLICT, e);
        }
    }

    /**
     *
     * @param bookingId
     * @throws Exception
     */
    public void deleteById(Integer bookingId) throws Exception  {
       Booking booking=getBookingById(bookingId);
        bookingRepository.delete(booking);

    }


    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Booking getBookingById(Integer id) throws Exception {
        try{
            return bookingRepository.findById(id).get();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    /**
     *
     * @param bookingId
     * @param flight
     * @return
     */
    public Booking addFlightToBooking(Integer bookingId, Flight flight) {
        return null ;
    }


    /**
     *
     * @return
     */
    public List<Booking> getBooking() {
        return null ;
    }

}
