package com.tui.proof.ws.model;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


/**
 * Author: Janty
 */

@Data
@Entity
@Table(name="availability")
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer availabilityId;
    Integer flightNumber;
    @NotBlank
    String companyName;
    @NotBlank
    Date date;
    @NotBlank
    Time hour;
    @NotBlank
    Double price;
    @NotBlank
    Timestamp createdOn;

}
