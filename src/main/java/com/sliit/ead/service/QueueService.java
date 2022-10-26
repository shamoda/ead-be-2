package com.sliit.ead.service;

import com.sliit.ead.model.Queue;
import com.sliit.ead.repository.QueueRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @author Weerasinghe S.S.
 * @IT_number IT19204680
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

    // The queue insert function
    public Queue enterQueue(Queue queue) {
        shedService.queueOp(queue.getRegNo(), queue.getFuelType(), "increment");
        queue.setArrivedTime(LocalDateTime.now());
        return repository.save(queue);
    }

    // The queue exist function
    public Queue exitQueue(String id) {
        Queue queue = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), Queue.class);
        queue.setDepartTime(LocalDateTime.now());
        mongoTemplate.save(queue);
        shedService.queueOp(queue.getRegNo(), queue.getFuelType(), "decrement");
        return queue;
    }

    // The average waiting time for the given Fuel type and regNo
    public long averageWaitingTimeByRegNoAndFuelType(String regNo, String type) {
        List<Queue> queues = repository.findQueuesByRegNoAndFuelType(regNo, type);
        long count = 0;
        long totWait = 0;
        for (Queue queue : queues) {
            if (queue.getArrivedTime().isAfter(LocalDateTime.now().minusHours(1)) && queue.getDepartTime() != null && !queue.getLeftEarly()) {
                long wait = ChronoUnit.MINUTES.between(queue.getArrivedTime(), queue.getDepartTime());
                totWait = totWait + wait;
                count++;
            }
        }
        if (count != 0)
            return totWait/count;
        return 0;
    }
}
