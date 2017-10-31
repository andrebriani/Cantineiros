package com.example.a10516125940.cantineiros.Model;

import java.util.ArrayList;

/**
 * Created by 10516125940 on 31/10/2017.
 */

public class Comprador extends Usuario {

    private ArrayList<Pedido> listaPedidos = new ArrayList<>();

    public Comprador() {
        super();
    }

    public Comprador(String nome, String senha) {
        super(nome, senha);
    }

    public Comprador(String nome, String senha, ArrayList<Pedido> listaCompra) {
        super(nome, senha);
        this.listaPedidos = listaCompra;
    }

    public void addPedido(Pedido p){
        listaPedidos.add(p);
    }

    public boolean removePedido(Pedido p){
        return listaPedidos.remove(p);
    }
}
