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
 * @author S.M. Jayasekara
 * @IT_number IT19161648
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

    public Shed insertShed(Shed shed) {
        return repository.save(shed);
    }

    public List<Shed> getShedByAddress(String address) {
        return repository.findShedsByAddress(address);
    }

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

    public Shed getShortestQueueByAddressAndType(String address, String type) {
        List<Shed> sheds = getShedByAddress(address);
        Shed tmpShed = sheds.get(0);
        for (Shed shed : sheds) {
            if (type.equalsIgnoreCase("petrol") && shed.getPetrolQueueLength() < tmpShed.getPetrolQueueLength()) {
                tmpShed = shed;
            }
            else if (type.equalsIgnoreCase("diesel") && shed.getDieselQueueLength() < tmpShed.getDieselQueueLength()) {
                tmpShed = shed;
            }
        }
        return tmpShed;
    }

    public Shed updatePetrolArrived(String regNo) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        shed.setPetrolArrivalTime(LocalDateTime.now());
        shed.setPetrolAvailable(true);
        shed.setPetrolFinishTime(null);
        mongoTemplate.save(shed);
        return shed;
    }

    public Shed updatePetrolFinished(String regNo) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        shed.setPetrolArrivalTime(null);
        shed.setPetrolAvailable(false);
        shed.setPetrolFinishTime(LocalDateTime.now());
        mongoTemplate.save(shed);
        return shed;
    }

    public Shed updateDieselArrived(String regNo) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        shed.setDieselArrivalTime(LocalDateTime.now());
        shed.setDieselAvailable(true);
        shed.setDieselFinishTime(null);
        mongoTemplate.save(shed);
        return shed;
    }

    public Shed updateDieselFinished(String regNo) {
        Shed shed = mongoTemplate.findOne(Query.query(Criteria.where("_id").is(regNo)), Shed.class);
        shed.setDieselArrivalTime(null);
        shed.setDieselAvailable(false);
        shed.setDieselFinishTime(LocalDateTime.now());
        mongoTemplate.save(shed);
        return shed;
    }

    public Shed getShedById(String regNo) {
        return repository.findById(regNo).get();
    }

    public Shed queueOperation(String regNo, String fuelType, String operation) {
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
