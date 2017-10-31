package com.example.a10516125940.cantineiros.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.a10516125940.cantineiros.Model.Produto;
import com.example.a10516125940.cantineiros.R;

public class Activity_Fazer_Pedido extends AppCompatActivity {

    private ListView listViewPedido;
    private ListView listViewProdutos;
    private Button botaoFinalizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__fazer__pedido);

        botaoFinalizar = findViewById(R.id.botaoPedir);
        listViewPedido = findViewById(R.id.listViewPedido);
        listViewProdutos = findViewById(R.id.listViewProdutos);
    }

    private void inserirProdutosPedido(Produto p){

    }

    private void inserirProdutos(){

    }
}
