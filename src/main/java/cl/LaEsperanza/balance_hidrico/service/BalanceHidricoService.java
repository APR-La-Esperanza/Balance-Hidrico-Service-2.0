package cl.LaEsperanza.BalanceHidrico.Service;

import cl.LaEsperanza.BalanceHidrico.Repository.BalanceHidricoRepository;
import cl.LaEsperanza.BalanceHidrico.model.BalanceHidrico;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BalanceHidricoService {

    @Autowired
    private BalanceHidricoRepository balanceHidricoRepository;

    private static final Logger log = LoggerFactory.getLogger(BalanceHidricoService.class);

    public BalanceHidrico saveBalance(BalanceHidrico balance) {
        log.info("Registrando balance hidrico para fecha: {}", balance.getFecha());
        double porcentaje = ((balance.getAguaBombeadaM3() - balance.getAguaFacturadaM3()) / balance.getAguaBombeadaM3()) * 100;
        balance.setAlertaPerdida(porcentaje > 25);
        BalanceHidrico saved = balanceHidricoRepository.save(balance);
        log.info("Balance hidrico registrado con ID: {}", saved.getId());
        return saved;
    }

    public List<BalanceHidrico> getAllBalances() {
        log.info("Obteniendo todos los balances hidricos.");
        return balanceHidricoRepository.findAll();
    }
}