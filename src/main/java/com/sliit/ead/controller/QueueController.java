package com.sliit.ead.controller;

import com.sliit.ead.model.Queue;
import com.sliit.ead.service.QueueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author S.M. Jayasekara
 * @IT_number IT19161648
 */
@RestController
@RequestMapping("/api/v1/queue")
@CrossOrigin(origins = "*")
public class QueueController {
    private final QueueService service;

    public QueueController(QueueService service) {
        this.service = service;
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insertQueue(@RequestBody Queue queue) {
        queue.setId(String.valueOf(UUID.randomUUID()));
        return new ResponseEntity<>(service.insertQueue(queue), HttpStatus.CREATED);
    }

    @PostMapping("/exit/{id}")
    public ResponseEntity<?> existQueue(@PathVariable String id) {
        return new ResponseEntity<>(service.exitQueue(id), HttpStatus.OK);
    }
}
