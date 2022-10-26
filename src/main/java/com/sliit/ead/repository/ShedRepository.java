package com.sliit.ead.repository;

import com.sliit.ead.model.Shed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Wikramasinghe R.J.P
 * @IT_number IT19151052
 */
@Repository
public interface ShedRepository extends MongoRepository<Shed, String> {
    Shed findByAddress(String address);
    List<Shed> findShedsByAddress(String address);
}
