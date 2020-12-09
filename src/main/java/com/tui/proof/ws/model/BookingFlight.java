package com.tui.proof.ws.model;


import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Author: Janty
 */

@Data
@Entity
@Table(name="bookingFlight")
public class BookingFlight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer bookingId;
    Integer flightNumber;
    Timestamp createdOn;
    @ManyToOne
    Booking booking;
}
