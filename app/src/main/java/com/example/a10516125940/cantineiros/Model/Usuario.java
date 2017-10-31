package com.example.a10516125940.cantineiros.Model;

import java.util.ArrayList;

/**
 * Created by 10516125940 on 31/10/2017.
 */

public class Usuario {

    private String nome;
    private String senha;
    ArrayList<Pedido> listaPedidos = new ArrayList<>();


    public Usuario() {
    }

    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ArrayList<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(ArrayList<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public void addPedido(Pedido p){
        listaPedidos.add(p);
    }

    public boolean removePedido(Pedido p){
        return listaPedidos.remove(p);
    }
}
