package com.sliit.ead.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Wikramasinghe R.J.P
 * @IT_number IT19151052
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document
public class User {
    @Id
    private String nic;
    private String name;
    private String address;
    private String vehicleNo;
    private String fuelType;
    private String password;
}
