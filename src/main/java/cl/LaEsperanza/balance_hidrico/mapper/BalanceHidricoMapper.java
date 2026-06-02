package cl.LaEsperanza.balance_hidrico.mapper;

import cl.LaEsperanza.balance_hidrico.dto.BalanceHidricoDTO;
import cl.LaEsperanza.balance_hidrico.model.BalanceHidricoModel;

public class BalanceHidricoMapper {
    public static BalanceHidricoModel toBalance(BalanceHidricoDTO request) {
        BalanceHidricoModel balance = new BalanceHidricoModel();
        balance.setFecha(request.getFecha());
        balance.setAguaBombeadaM3(request.getAguaBombeadaM3());
        balance.setAguaFacturadaM3(request.getAguaFacturadaM3());
        balance.setAlertaPerdida(request.isAlertaPerdida());
        return balance;
    }
}
