package com.apr.BalanceHidrico_Service.mapper;

import com.apr.BalanceHidrico_Service.dto.BalanceHidricoDTO;
import com.apr.BalanceHidrico_Service.dto.BalanceHidricoResponseDTO;
import com.apr.BalanceHidrico_Service.model.BalanceHidrico;

public class BalanceHidricoMapper {

    public static BalanceHidrico toEntity(BalanceHidricoDTO dto) {
        if (dto == null) return null;
        BalanceHidrico balance = new BalanceHidrico();
        balance.setPeriodo(dto.getPeriodo());
        balance.setAguaProducidaM3(dto.getAguaProducidaM3());
        balance.setAguaFacturadaM3(dto.getAguaFacturadaM3());
        if (dto.getFechaCalculo() != null) balance.setFechaCalculo(dto.getFechaCalculo());
        return balance;
    }

    public static BalanceHidricoResponseDTO toResponseDTO(BalanceHidrico balance) {
        if (balance == null) return null;
        BalanceHidricoResponseDTO dto = new BalanceHidricoResponseDTO();
        dto.setId(balance.getId());
        dto.setPeriodo(balance.getPeriodo());
        dto.setAguaProducidaM3(balance.getAguaProducidaM3());
        dto.setAguaFacturadaM3(balance.getAguaFacturadaM3());
        dto.setPerdidaM3(balance.getPerdidaM3());
        dto.setPorcentajePerdida(balance.getPorcentajePerdida());
        dto.setAlerta(balance.getAlerta());
        dto.setFechaCalculo(balance.getFechaCalculo());
        return dto;
    }
}
