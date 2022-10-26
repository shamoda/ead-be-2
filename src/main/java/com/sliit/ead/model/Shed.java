package com.sliit.ead.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

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
public class Shed {
    @Id
    private String regNo;
    private String name;
    private String address;
    private LocalDateTime petrolArrivalTime;
    private LocalDateTime petrolFinishTime;
    private int petrolQueueLength;
    private boolean petrolAvailable;
    private LocalDateTime dieselArrivalTime;
    private LocalDateTime dieselFinishTime;
    private int dieselQueueLength;
    private boolean dieselAvailable;
    private String password;
}
