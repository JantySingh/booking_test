package com.tui.proof.ws.model;

import lombok.*;

import javax.persistence.*;

/**
 * Author: Janty
 */

@Data
@Entity
@Table(name="holder")
public class Holder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer holderId ;
    String name;
    String lastName;
    String address;
    String postalCode;
    String country;
    String email;
    String telephones;

}
