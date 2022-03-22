package com.typechecker.typesystem.repository;

import com.typechecker.typesystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface UserRepository extends JpaRepository<User,Integer>{
}