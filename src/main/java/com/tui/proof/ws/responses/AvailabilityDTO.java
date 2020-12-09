package com.tui.proof.ws.responses;


import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Author: Janty
 */

@Data
@ApiModel(value = "availability", description = "availability")
@JsonTypeName("availability")
public class AvailabilityDTO {
    public static final String TYPE = "application/vnd.availability.v1+json";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer availabilityId;
    @NotNull
    Integer flightNumber;
    @NotNull
    @Size(max = 20)
    String companyName;
    @NotNull
    Date date;
    @NotNull
    Time hour;
    @NotNull
    Double price;
    @NotNull
    Timestamp createdOn;

}
