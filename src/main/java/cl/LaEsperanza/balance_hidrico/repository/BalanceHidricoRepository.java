package cl.LaEsperanza.balance_hidrico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.LaEsperanza.balance_hidrico.model.BalanceHidricoModel;

@Repository
public interface BalanceHidricoRepository extends JpaRepository<BalanceHidricoModel, Integer> {
}