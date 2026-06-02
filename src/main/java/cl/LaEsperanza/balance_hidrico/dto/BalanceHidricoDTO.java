package cl.LaEsperanza.balance_hidrico.dto;

public class BalanceHidricoDTO {
    private int id;
    private String fecha;
    private double aguaBombeadaM3;
    private double aguaFacturadaM3;
    private boolean alertaPerdida;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }
    public double getAguaBombeadaM3() { return aguaBombeadaM3; }
    public void setAguaBombeadaM3(double aguaBombeadaM3) { this.aguaBombeadaM3 = aguaBombeadaM3; }
    public double getAguaFacturadaM3() { return aguaFacturadaM3; }
    public void setAguaFacturadaM3(double aguaFacturadaM3) { this.aguaFacturadaM3 = aguaFacturadaM3; }
    public boolean isAlertaPerdida() { return alertaPerdida; }
    public void setAlertaPerdida(boolean alertaPerdida) { this.alertaPerdida = alertaPerdida; }
}