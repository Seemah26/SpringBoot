package com.bl.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bl.app.model.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

	List<UserInfo> findByIdAndPassword(int id, String password);

	Optional<UserInfo> findAllById(int id);

	Optional<UserInfo> findById(int id);

	boolean deleteById(int varifiedUserId);

	UserInfo findByEmail(String email);

}