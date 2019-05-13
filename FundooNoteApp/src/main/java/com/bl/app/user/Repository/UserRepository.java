package com.bl.app.user.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bl.app.user.model.User;
import com.google.common.base.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByEmailAndPassword(String email, String password);

	Optional<User> findByResetToken(String resetToken);
	
	 User findByEmail(String email);

	User findById(int id);


}
