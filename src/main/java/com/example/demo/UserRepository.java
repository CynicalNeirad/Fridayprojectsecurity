package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{

    User findByUsername(String username);

    User findByPassword(String password);

    Long countByUsername(String username);

    Long countByPassword(String password);
}