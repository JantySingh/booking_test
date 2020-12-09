package com.tui.proof.ws.requests;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.annotations.ApiModel;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;


/**
 * Author: Janty
 */

@Data
@ApiModel(value = "availabilityRequest", description = "availabilityRequest")
@JsonTypeName("availabilityRequest")
public class AvailabilityRequestDTO {
    @NotNull
    @Size(max = 40)
    String origin;
    @NotNull
    @Size(max = 40)
    String destination;
    @NotNull
    Date dateFrom;
    @NotNull
    Date dateTo;
    @NotNull
    Paxes paxes;
}
