package com.sliit.ead.controller;

import com.sliit.ead.model.Shed;
import com.sliit.ead.service.ShedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Wikramasinghe R.J.P
 * @IT_number IT19151052
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
        return new ResponseEntity<>(service.createShed(shed), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("regNo") String regNo, @RequestParam("password") String password) {
        return new ResponseEntity<>(service.login(regNo, password), HttpStatus.OK);
    }

    @GetMapping("/{address}")
    public ResponseEntity<?> getShedsByAddress(@PathVariable String address) {
        return new ResponseEntity<>(service.getByAddress(address), HttpStatus.OK);
    }

    @GetMapping("/{address}/{type}")
    public ResponseEntity<?> getShortestQueueByAddressAndType(@PathVariable String address, @PathVariable String type) {
        return new ResponseEntity<>(service.shortestQueueByAddressAndType(address, type), HttpStatus.OK);
    }

    @GetMapping("/get/{regNo}")
    public ResponseEntity<?> getShedById(@PathVariable String regNo) {
        return new ResponseEntity<>(service.shedById(regNo), HttpStatus.OK);
    }

    @GetMapping("/petrol-arrived/{regNo}")
    public ResponseEntity<?> updatePetrolArrived(@PathVariable String regNo) {
        return new ResponseEntity<>(service.petrolArrived(regNo), HttpStatus.OK);
    }

    @GetMapping("/petrol-finished/{regNo}")
    public ResponseEntity<?> updatePetrolFinished(@PathVariable String regNo) {
        return new ResponseEntity<>(service.petrolFinished(regNo), HttpStatus.OK);
    }

    @GetMapping("/diesel-arrived/{regNo}")
    public ResponseEntity<?> updateDieselArrived(@PathVariable String regNo) {
        return new ResponseEntity<>(service.dieselArrived(regNo), HttpStatus.OK);
    }

    @GetMapping("/diesel-finished/{regNo}")
    public ResponseEntity<?> updateDieselFinished(@PathVariable String regNo) {
        return new ResponseEntity<>(service.dieselFinished(regNo), HttpStatus.OK);
    }
}
