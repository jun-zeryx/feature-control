package com.moneylion.assessment.repository;

import com.moneylion.assessment.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

}
