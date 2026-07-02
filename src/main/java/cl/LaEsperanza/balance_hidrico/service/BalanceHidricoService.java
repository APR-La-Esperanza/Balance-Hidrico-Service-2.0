package cl.LaEsperanza.balance_hidrico.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.LaEsperanza.balance_hidrico.model.BalanceHidricoModel;
import cl.LaEsperanza.balance_hidrico.repository.BalanceHidricoRepository;

@Service
public class BalanceHidricoService {

    @Autowired
    private BalanceHidricoRepository balanceHidricoRepository;

    private static final Logger log = LoggerFactory.getLogger(BalanceHidricoService.class);

    public BalanceHidricoModel saveBalance(BalanceHidricoModel balance) {
        log.info("Registrando balance hidrico para fecha: {}", balance.getFecha());
        double porcentaje = ((balance.getAguaBombeadaM3() - balance.getAguaFacturadaM3()) / balance.getAguaBombeadaM3()) * 100;
        balance.setAlertaPerdida(porcentaje > 25);
        BalanceHidricoModel saved = balanceHidricoRepository.save(balance);
        log.info("Balance hidrico registrado con ID: {}", saved.getId());
        return saved;
    }

    public List<BalanceHidricoModel> getAllBalances() {
        log.info("Obteniendo todos los balances hidricos.");
        return balanceHidricoRepository.findAll();
    }
}