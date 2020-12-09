package com.tui.proof.ws.model;

import lombok.*;
import java.sql.Timestamp;
import java.util.Set;
import javax.persistence.*;


/**
 * Author: Janty
 */

@Data
@Entity
@Table(name="booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer bookingId;
    Integer holderId;
    Boolean isConfirmed;
    Timestamp createdOn;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="booking_id")
    Set<Flight> flightSet;

}
