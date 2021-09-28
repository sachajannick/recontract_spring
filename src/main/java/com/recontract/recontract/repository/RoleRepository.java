package com.recontract.recontract.repository;

import com.recontract.recontract.domain.ERole;
import com.recontract.recontract.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
