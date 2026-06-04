package com.apr.BalanceHidrico_Service.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "balances_hidricos")
public class BalanceHidrico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 7)
    private String periodo; // yyyy-MM

    @Column(name = "agua_producida_m3", nullable = false)
    private Double aguaProducidaM3;

    @Column(name = "agua_facturada_m3", nullable = false)
    private Double aguaFacturadaM3;

    @Column(name = "perdida_m3")
    private Double perdidaM3;

    @Column(name = "porcentaje_perdida")
    private Double porcentajePerdida;

    @Column(nullable = false)
    private Boolean alerta;

    @Column(name = "fecha_calculo")
    private LocalDate fechaCalculo;

    @PrePersist
    @PreUpdate
    protected void onCalculate() {
        if (this.fechaCalculo == null) {
            this.fechaCalculo = LocalDate.now();
        }
        if (this.aguaProducidaM3 != null && this.aguaFacturadaM3 != null) {
            this.perdidaM3 = this.aguaProducidaM3 - this.aguaFacturadaM3;
            if (this.aguaProducidaM3 > 0) {
                this.porcentajePerdida = (this.perdidaM3 / this.aguaProducidaM3) * 100.0;
            } else {
                this.porcentajePerdida = 0.0;
            }
        } else {
            this.perdidaM3 = 0.0;
            this.porcentajePerdida = 0.0;
        }
        this.alerta = this.porcentajePerdida > 25.0;
    }

    public BalanceHidrico() {
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
