package com.sliit.ead.repository;

import com.sliit.ead.model.Queue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Weerasinghe S.S.
 * @IT_number IT19204680
 */
@Repository
public interface QueueRepository extends MongoRepository<Queue, String> {
    List<Queue> findQueuesByRegNoAndFuelType(String regNo, String fuelType);
}
