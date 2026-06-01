package cl.LaEsperanza.BalanceHidrico.Repository;

import cl.LaEsperanza.BalanceHidrico.model.BalanceHidrico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceHidricoRepository extends JpaRepository<BalanceHidrico, Integer> {
}