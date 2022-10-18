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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("regNo") String regNo, @RequestParam("password") String password) {
        return new ResponseEntity<>(service.login(regNo, password), HttpStatus.OK);
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

    @GetMapping("/petrol-arrived/{regNo}")
    public ResponseEntity<?> updatePetrolArrived(@PathVariable String regNo) {
        return new ResponseEntity<>(service.updatePetrolArrived(regNo), HttpStatus.OK);
    }

    @GetMapping("/petrol-finished/{regNo}")
    public ResponseEntity<?> updatePetrolFinished(@PathVariable String regNo) {
        return new ResponseEntity<>(service.updatePetrolFinished(regNo), HttpStatus.OK);
    }

    @GetMapping("/diesel-arrived/{regNo}")
    public ResponseEntity<?> updateDieselArrived(@PathVariable String regNo) {
        return new ResponseEntity<>(service.updateDieselArrived(regNo), HttpStatus.OK);
    }

    @GetMapping("/diesel-finished/{regNo}")
    public ResponseEntity<?> updateDieselFinished(@PathVariable String regNo) {
        return new ResponseEntity<>(service.updateDieselFinished(regNo), HttpStatus.OK);
    }
}
