package com.sliit.ead.service;

import com.sliit.ead.model.Queue;
import com.sliit.ead.repository.QueueRepository;
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

    public QueueService(QueueRepository repository, ShedService shedService) {
        this.repository = repository;
        this.shedService = shedService;
    }

    public Queue insertQueue(Queue queue) {
        shedService.queueOperation(queue.getRegNo(), queue.getFuelType(), "increment");
        queue.setArrivedTime(LocalDateTime.now());
        return repository.save(queue);
    }
}
