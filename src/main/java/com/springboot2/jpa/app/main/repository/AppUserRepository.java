package com.springboot2.jpa.app.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springboot2.jpa.app.main.model.AppUser;

/**
 * 
 * @author Naveen
 *
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
}
