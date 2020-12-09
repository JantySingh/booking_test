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

@Data@Entity
@Table(name="flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer flightId;
    Integer flightNumber;
    @NotBlank
    String companyName;
    @NotBlank
    Date date;
    @NotBlank
    Time hour;
    Double price;
    @NotBlank
    String origin;
    @NotBlank
    String destination;
    Date dateFrom;
    Date dateTo;
    Integer infants;
    Integer children;
    Integer adults;
    Timestamp createdOn;

}
