package com.sliit.ead.service;

import com.sliit.ead.model.Shed;
import com.sliit.ead.repository.ShedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author S.M. Jayasekara
 * @IT_number IT19161648
 */
@Service
public class ShedService {
    private final ShedRepository repository;

    @Autowired
    public ShedService(ShedRepository repository) {
        this.repository = repository;
    }

    public Shed insertShed(Shed shed) {
        return repository.save(shed);
    }

    public List<Shed> getShedByAddress(String address) {
        return repository.findShedsByAddress(address);
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

}
