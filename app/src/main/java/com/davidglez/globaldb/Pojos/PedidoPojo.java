package com.davidglez.globaldb.Pojos;

public class PedidoPojo {

    private String nombre_cliente;
    private String apellido_cliente;
    private String plato_entrada;
    private String plato_principal;
    private String postre;
    private String bebida;
    private String nota_extra;
    private String nombre_mesero;
    private String apellido_mesero;
    private String fecha;
    private String mesa;
    private int comensales;
    private float monto;


    public PedidoPojo(){}

    public PedidoPojo(String nombre_cliente, String apellido_cliente, String plato_entrada, String plato_principal,
                      String postre, String bebida, String nota_extra, String nombre_mesero, String apellido_mesero,
                      String fecha, int comensales, String mesa, float monto) {
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.plato_entrada = plato_entrada;
        this.plato_principal = plato_principal;
        this.postre = postre;
        this.bebida = bebida;
        this.nota_extra = nota_extra;
        this.nombre_mesero = nombre_mesero;
        this.apellido_mesero = apellido_mesero;
        this.fecha = fecha;
        this.comensales = comensales;
        this.mesa = mesa;
        this.monto = monto;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public String getPlato_entrada() {
        return plato_entrada;
    }

    public void setPlato_entrada(String plato_entrada) {
        this.plato_entrada = plato_entrada;
    }

    public String getPlato_principal() {
        return plato_principal;
    }

    public void setPlato_principal(String plato_principal) {
        this.plato_principal = plato_principal;
    }

    public String getPostre() {
        return postre;
    }

    public void setPostre(String postre) {
        this.postre = postre;
    }

    public String getBebida() {
        return bebida;
    }

    public void setBebida(String bebida) {
        this.bebida = bebida;
    }

    public String getNota_extra() {
        return nota_extra;
    }

    public void setNota_extra(String nota_extra) {
        this.nota_extra = nota_extra;
    }

    public String getNombre_mesero() {
        return nombre_mesero;
    }

    public void setNombre_mesero(String nombre_mesero) {
        this.nombre_mesero = nombre_mesero;
    }

    public String getApellido_mesero() {
        return apellido_mesero;
    }

    public void setApellido_mesero(String apellido_mesero) {
        this.apellido_mesero = apellido_mesero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getComensales() {
        return comensales;
    }

    public void setComensales(int comensales) {
        this.comensales = comensales;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
}
