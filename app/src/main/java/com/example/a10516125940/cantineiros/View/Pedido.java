package com.example.a10516125940.cantineiros.View;

import java.util.ArrayList;

/**
 * Created by 10516125940 on 31/10/2017.
 */

public class Pedido {

    private String data;
    private ArrayList<Produto> produtos = new ArrayList<>();

    public Pedido(String data) {
        this.data = data;
    }

    public Pedido(String data, ArrayList<Produto> produtos) {
        this.data = data;
        this.produtos = produtos;
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
}
