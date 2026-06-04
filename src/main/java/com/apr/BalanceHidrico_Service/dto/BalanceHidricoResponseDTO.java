package com.apr.BalanceHidrico_Service.dto;

import java.time.LocalDate;

public class BalanceHidricoResponseDTO {

    private Long id;
    private String periodo;
    private Double aguaProducidaM3;
    private Double aguaFacturadaM3;
    private Double perdidaM3;
    private Double porcentajePerdida;
    private Boolean alerta;
    private LocalDate fechaCalculo;

    public BalanceHidricoResponseDTO() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    public Double getAguaProducidaM3() { return aguaProducidaM3; }
    public void setAguaProducidaM3(Double aguaProducidaM3) { this.aguaProducidaM3 = aguaProducidaM3; }
    public Double getAguaFacturadaM3() { return aguaFacturadaM3; }
    public void setAguaFacturadaM3(Double aguaFacturadaM3) { this.aguaFacturadaM3 = aguaFacturadaM3; }
    public Double getPerdidaM3() { return perdidaM3; }
    public void setPerdidaM3(Double perdidaM3) { this.perdidaM3 = perdidaM3; }
    public Double getPorcentajePerdida() { return porcentajePerdida; }
    public void setPorcentajePerdida(Double porcentajePerdida) { this.porcentajePerdida = porcentajePerdida; }
    public Boolean getAlerta() { return alerta; }
    public void setAlerta(Boolean alerta) { this.alerta = alerta; }
    public LocalDate getFechaCalculo() { return fechaCalculo; }
    public void setFechaCalculo(LocalDate fechaCalculo) { this.fechaCalculo = fechaCalculo; }
}
