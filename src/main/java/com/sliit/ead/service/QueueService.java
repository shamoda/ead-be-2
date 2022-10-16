package com.sliit.ead.service;

import com.sliit.ead.model.Queue;
import com.sliit.ead.model.Shed;
import com.sliit.ead.repository.QueueRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author S.M. Jayasekara
 * @IT_number IT19161648
 */
@Service
public class QueueService {
    private final QueueRepository repository;
    private final ShedService shedService;
    private final MongoTemplate mongoTemplate;

    public QueueService(QueueRepository repository, ShedService shedService, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.shedService = shedService;
        this.mongoTemplate = mongoTemplate;
    }

    public Queue insertQueue(Queue queue) {
        shedService.queueOperation(queue.getRegNo(), queue.getFuelType(), "increment");
        queue.setArrivedTime(LocalDateTime.now());
        return repository.save(queue);
    }

    public Queue exitQueue(String id) {
        Queue queue = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), Queue.class);
        queue.setDepartTime(LocalDateTime.now());
        mongoTemplate.save(queue);
        shedService.queueOperation(queue.getRegNo(), queue.getFuelType(), "decrement");
        return queue;
    }
}
