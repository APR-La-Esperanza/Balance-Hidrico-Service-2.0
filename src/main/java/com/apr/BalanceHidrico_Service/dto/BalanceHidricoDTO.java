package com.apr.BalanceHidrico_Service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class BalanceHidricoDTO {

    @NotBlank(message = "El periodo es obligatorio")
    @Pattern(regexp = "^\\d{4}-\\d{2}$", message = "El periodo debe tener el formato yyyy-MM")
    private String periodo;

    @NotNull(message = "El agua producida es obligatoria")
    private Double aguaProducidaM3;

    @NotNull(message = "El agua facturada es obligatoria")
    private Double aguaFacturadaM3;

    private LocalDate fechaCalculo;

    public BalanceHidricoDTO() {
    }

    public String getPeriodo() { return periodo; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }
    public Double getAguaProducidaM3() { return aguaProducidaM3; }
    public void setAguaProducidaM3(Double aguaProducidaM3) { this.aguaProducidaM3 = aguaProducidaM3; }
    public Double getAguaFacturadaM3() { return aguaFacturadaM3; }
    public void setAguaFacturadaM3(Double aguaFacturadaM3) { this.aguaFacturadaM3 = aguaFacturadaM3; }
    public LocalDate getFechaCalculo() { return fechaCalculo; }
    public void setFechaCalculo(LocalDate fechaCalculo) { this.fechaCalculo = fechaCalculo; }
}
