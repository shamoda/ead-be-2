package com.sliit.ead.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author S.M. Jayasekara
 * @IT_number IT19161648
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
    private LocalDateTime dieselArrivalTime;
    private LocalDateTime dieselFinishTime;
    private int dieselQueueLength;
}
