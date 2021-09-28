package com.recontract.recontract.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.recontract.recontract.domain.Search;

@Repository
public interface SearchRepository extends JpaRepository<Search, Long> {
}
