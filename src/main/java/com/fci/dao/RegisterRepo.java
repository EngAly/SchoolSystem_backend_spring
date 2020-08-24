package com.fci.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fci.models.User;

@Repository
public interface RegisterRepo extends JpaRepository<User, Long> {

//it return single user only because username is unique
	User findByUsername(String name);

	Page<User> findByUsernameContainingIgnoreCase(String name, Pageable pageable);

}
