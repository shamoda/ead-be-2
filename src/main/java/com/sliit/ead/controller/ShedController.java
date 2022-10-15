package com.sliit.ead.controller;

import com.sliit.ead.model.Shed;
import com.sliit.ead.service.ShedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author S.M. Jayasekara
 * @IT_number IT19161648
 */
@RestController
@RequestMapping("/api/v1/shed")
@CrossOrigin(origins = "*")
public class ShedController {
    private final ShedService service;

    @Autowired
    public ShedController(ShedService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<?> createShed(@RequestBody Shed shed) {
        return new ResponseEntity<>(service.insertShed(shed), HttpStatus.CREATED);
    }

    @GetMapping("/{address}")
    public ResponseEntity<?> getShedsByAddress(@PathVariable String address) {
        return new ResponseEntity<>(service.getShedByAddress(address), HttpStatus.OK);
    }

    @GetMapping("/{address}/{type}")
    public ResponseEntity<?> getShortestQueueByAddressAndType(@PathVariable String address, @PathVariable String type) {
        return new ResponseEntity<>(service.getShortestQueueByAddressAndType(address, type), HttpStatus.OK);
    }

    @GetMapping("/get/{regNo}")
    public ResponseEntity<?> getShedById(@PathVariable String regNo) {
        return new ResponseEntity<>(service.getShedById(regNo), HttpStatus.OK);
    }
}
