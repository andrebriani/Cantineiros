package com.example.a10516125940.cantineiros.Model;

import java.util.ArrayList;

/**
 * Created by 10516125940 on 31/10/2017.
 */

public class Pedido {

    private String data;
    private String nomeCliente;
    private boolean feito;
    private boolean entregue;
    private ArrayList<Produto> produtos = new ArrayList<>();

    public Pedido(String data) {
        this.data = data;
        entregue = false;
    }

    public Pedido(String data, ArrayList<Produto> produtos) {
        this.data = data;
        this.produtos = produtos;
        entregue = false;
    }

    public double calcularTotal(){
        double valor = 0;
        for(Produto p: produtos){
            valor += p.getPreco();
        }
        valor = Double.parseDouble(String.format("%.2f", valor).replace(",","."));
        return valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public boolean isFeito() {
        return feito;
    }

    public void setFeito(boolean feito) {
        this.feito = feito;
    }

    public String mostrarParaCliente(){
        String s = "";

        s += "Data: " + data + "\n";
        s += "Entregue: ";
        if(entregue){
            s += "sim";
        }else{
            s += "não";
        }
        s += "\nProdutos:\n";

        for(Produto p: produtos){
            s += "   " + p.toString();
        }

        s += "Valor Total: R$ " + calcularTotal();
        return s;
    }

    public String mostrarParaAdministrador(){
        return "Nome Cliente: " + nomeCliente + "\n" + mostrarParaCliente();
    }
}
