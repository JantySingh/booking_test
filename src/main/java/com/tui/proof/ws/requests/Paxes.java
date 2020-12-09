package com.tui.proof.ws.requests;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * Author: Janty
 */

@Data
public class Paxes {
    @NotNull
    @Min(0)
    @Max(10)
    Integer infants ;
    @NotNull
    @Min(0)
    @Max(10)
    Integer children ;
    @NotNull
    @Min(0)
    @Max(10)
    Integer adults ;
}
