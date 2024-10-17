package com.hydrogenhr.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydrogenhr.persistence.entity.UserApproval;

public interface UserApprovalRepository extends JpaRepository<UserApproval, Long> {

}
