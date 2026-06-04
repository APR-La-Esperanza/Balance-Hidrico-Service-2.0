package com.apr.BalanceHidrico_Service.repository;

import com.apr.BalanceHidrico_Service.model.BalanceHidrico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceHidricoRepository extends JpaRepository<BalanceHidrico, Long> {
    Optional<BalanceHidrico> findByPeriodo(String periodo);
}
