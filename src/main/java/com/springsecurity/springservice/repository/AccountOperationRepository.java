package com.springsecurity.springservice.repository;

import com.springsecurity.springservice.entity.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {
}
