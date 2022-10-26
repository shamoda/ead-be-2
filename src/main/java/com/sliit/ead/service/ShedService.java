package com.sliit.ead.service;

import com.sliit.ead.model.Shed;
import com.sliit.ead.repository.ShedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Wikramasinghe R.J.P
 * @IT_number IT19151052
 */
@Service
public class ShedService {
    private final ShedRepository repository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ShedService(ShedRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    // Shed create method
    public Shed createShed(Shed shed) {
        return repository.save(shed);
    }

    // Sheds by given address
    public List<Shed> getByAddress(String address) {
        return repository.findShedsByAddress(address);
    }

    // Shed login
    public Shed login(String regNo, String password) {
        Shed shed = repository.findById(regNo).get();
        if (shed != null) {
            if (shed.getPassword().equalsIgnoreCase(password)) {
                return shed;
            }
            return null;
        }
        return null;
    }

    // Shortest queue for the given Fuel type and Address
    public Shed shortestQueueByAddressAndType(String address, String type) {
        List<Shed> sheds = getByAddress(address);
        Shed tmpShed = sheds.get(0);
        int i = 0;
        for (Shed shed : sheds) {
            if (type.equalsIgnoreCase("petrol") && shed.getPetrolQueueLength() <= tmpShed.getPetrolQueueLength()) {
                if (!shed.isPetrolAvailable())
                    continue;
                tmpShed = shed;
                i++;
            }
            else if (type.equalsIgnoreCase("diesel") && shed.getDieselQueueLength() <= tmpShed.getDieselQueueLength()) {
                if (!shed.isDieselAvailable())
                    continue;
                tmpShed = shed;
                i++;
            }
        }

        if (i == 0)
            return null;
        return tmpShed;
    }

    // petrol arrival date and time update
    public Shed petrolArrived(String regNo) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        shed.setPetrolArrivalTime(LocalDateTime.now());
        shed.setPetrolAvailable(true);
        shed.setPetrolFinishTime(null);
        mongoTemplate.save(shed);
        return shed;
    }

    // petrol finished date and time update
    public Shed petrolFinished(String regNo) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        shed.setPetrolArrivalTime(null);
        shed.setPetrolAvailable(false);
        shed.setPetrolFinishTime(LocalDateTime.now());
        mongoTemplate.save(shed);
        return shed;
    }

    // diesel arrival date and time update
    public Shed dieselArrived(String regNo) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        shed.setDieselArrivalTime(LocalDateTime.now());
        shed.setDieselAvailable(true);
        shed.setDieselFinishTime(null);
        mongoTemplate.save(shed);
        return shed;
    }

    // diesel finished date and time update
    public Shed dieselFinished(String regNo) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        shed.setDieselArrivalTime(null);
        shed.setDieselAvailable(false);
        shed.setDieselFinishTime(LocalDateTime.now());
        mongoTemplate.save(shed);
        return shed;
    }

    // shed for by ID
    public Shed shedById(String regNo) {
        return repository.findById(regNo).get();
    }

    // update queue length according to the regNo, Fuel type and Operation
    public Shed queueOp(String regNo, String fuelType, String operation) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        if (fuelType.equalsIgnoreCase("petrol")) {
            int tempLen;
            if (operation.equalsIgnoreCase("increment")) {
                assert shed != null;
                tempLen = shed.getPetrolQueueLength() + 1;
            } else {
                assert shed != null;
                tempLen = shed.getPetrolQueueLength() - 1;
            }
            shed.setPetrolQueueLength(tempLen);
            mongoTemplate.save(shed);
        } else {
            int tempLen;
            if (operation.equalsIgnoreCase("increment")) {
                assert shed != null;
                tempLen = shed.getDieselQueueLength() + 1;
            } else {
                assert shed != null;
                tempLen = shed.getDieselQueueLength() - 1;
            }
            shed.setPetrolQueueLength(tempLen);
            mongoTemplate.save(shed);
        }
        return shed;
    }

}
