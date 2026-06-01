package cl.LaEsperanza.balance_hidrico.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "BalanceHidrico")
public class BalanceHidricoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "fecha", nullable = false, length = 20)
    private String fecha;

    @Column(name = "agua_bombeada_m3", nullable = false)
    private double aguaBombeadaM3;

    @Column(name = "agua_facturada_m3", nullable = false)
    private double aguaFacturadaM3;

    @Column(name = "alerta_perdida", nullable = false)
    private boolean alertaPerdida;
}