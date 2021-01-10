package com.SpringJWTDemo.SpringJWTDemo.repository;

import com.SpringJWTDemo.SpringJWTDemo.model.UserDtl;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserDtl, Integer> {

    Optional<UserDtl> findByUserName(String username);

    Iterable<UserDtl> findAllByRole(String role);
}
