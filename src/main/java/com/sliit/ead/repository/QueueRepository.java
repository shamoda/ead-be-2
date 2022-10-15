package com.sliit.ead.repository;

import com.sliit.ead.model.Queue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author S.M. Jayasekara
 * @IT_number IT19161648
 */
@Repository
public interface QueueRepository extends MongoRepository<Queue, String> {
}
