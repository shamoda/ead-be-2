package com.sliit.ead.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author Weerasinghe S.S.
 * @IT_number IT19204680
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document
public class Queue {
    @Id
    private String id;
    private String regNo;
    private String nic;
    private String fuelType;
    private LocalDateTime arrivedTime;
    private LocalDateTime departTime;
    private Boolean leftEarly = false;
}
