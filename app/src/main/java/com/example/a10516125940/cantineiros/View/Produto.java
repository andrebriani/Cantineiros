package com.example.a10516125940.cantineiros.View;

/**
 * Created by 10516125940 on 31/10/2017.
 */

class Produto {

    private String nome;
    private double preco;

    public Produto(){}

    public Produto(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return getNome() + " R$ " + getPreco();
    }
}
