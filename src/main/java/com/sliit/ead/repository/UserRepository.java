package com.sliit.ead.repository;

import com.sliit.ead.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Wikramasinghe R.J.P
 * @IT_number IT19151052
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
