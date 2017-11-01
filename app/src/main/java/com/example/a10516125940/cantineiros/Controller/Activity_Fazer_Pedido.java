package com.example.a10516125940.cantineiros.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a10516125940.cantineiros.Model.Pedido;
import com.example.a10516125940.cantineiros.Model.Produto;
import com.example.a10516125940.cantineiros.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Activity_Fazer_Pedido extends AppCompatActivity {

    private ListView listViewPedido;
    private ListView listViewProdutos;
    private Button botaoFinalizar;
    private Button botaoVoltar;

    private AlertDialog.Builder dialogoExcluir;
    private AlertDialog.Builder dialogoAdicionar;

    private ArrayList<Produto> listaProdutos;
    private ArrayList<Produto> listaProdutoDoCliente;

    private int indiceProdutoNoPedidoEscolhido;
    private int indiceProdutoEscolhido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__fazer__pedido);

        listaProdutoDoCliente = new ArrayList<>();
        listaProdutos = new ArrayList<>();

        botaoFinalizar = findViewById(R.id.botaoFinalizar);
        listViewPedido = findViewById(R.id.listViewPedido);
        listViewProdutos = findViewById(R.id.listViewProdutos);
        botaoVoltar = findViewById(R.id.botaoVoltar);

        inserirProdutoNaListaViewProdutos();
        inserirProdutosNaListaViewPedido();

        dialogoExcluir = new AlertDialog.Builder(Activity_Fazer_Pedido.this);
        dialogoExcluir.setTitle("Excluir?");
        dialogoExcluir.setMessage("Realmente quer excluir esse produto do seu pedido?");
        dialogoExcluir.setCancelable(true);
        dialogoExcluir.setIcon(android.R.drawable.ic_delete);

        dialogoExcluir.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogoExcluir.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listaProdutoDoCliente.remove( listaProdutoDoCliente.get(indiceProdutoNoPedidoEscolhido));
                inserirProdutosNaListaViewPedido();
            }
        });

        dialogoAdicionar = new AlertDialog.Builder(Activity_Fazer_Pedido.this);
        dialogoAdicionar.setTitle("Adicionar?");
        dialogoAdicionar.setMessage("Realmente quer adicionar esse produto do seu pedido?");
        dialogoAdicionar.setCancelable(true);
        dialogoAdicionar.setIcon(android.R.drawable.ic_delete);

        listViewPedido.setLongClickable(true);
        listViewPedido.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                indiceProdutoNoPedidoEscolhido = position;
                dialogoExcluir.create().show();
                return true;
            }
        });

        dialogoAdicionar.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogoAdicionar.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                inserirProdutosNoPedido( listaProdutos.get(indiceProdutoEscolhido) );
                inserirProdutosNaListaViewPedido();
            }
        });

        listViewProdutos.setLongClickable(true);
        listViewProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                indiceProdutoEscolhido = position;
                dialogoAdicionar.create().show();
                return true;
            }
        });

        botaoFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarPedido();
            }
        });

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Fazer_Pedido.this, Activity_Comprador_Principal.class));
            }
        });

    }

    private void realizarPedido(){
        if(listaProdutoDoCliente.isEmpty()){
            Toast.makeText(Activity_Fazer_Pedido.this, "Selecione pelo menos" +
                    " um produto para poder fazer a reserva", Toast.LENGTH_LONG).show();
        }else{
            String data = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
            Pedido p = new Pedido(data, listaProdutoDoCliente);


            ControllerCentral.realizarPedido(p);

            Toast.makeText(Activity_Fazer_Pedido.this, "Pronto", Toast.LENGTH_LONG).show();

            listaProdutoDoCliente = new ArrayList<>();
            inserirProdutosNaListaViewPedido();
        }
    }

    private void inserirProdutosNoPedido(Produto p){
            listaProdutoDoCliente.add(p);
    }

    private void inserirProdutosNaListaViewPedido() {
        if (listaProdutoDoCliente.isEmpty()) {
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, new String[]{"Vazio"}
            );

            listViewPedido.setAdapter(adaptador);
        } else {
            ArrayList<String> listaDado = new ArrayList<>(listaProdutoDoCliente.size());

            for (Produto p : listaProdutoDoCliente) {
                listaDado.add(p.getNome() + " R$ " + p.getPreco());
            }

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, listaDado
            );

            listViewPedido.setAdapter(adaptador);
        }
    }

    private void inserirProdutoNaListaViewProdutos() {
        listaProdutos.add(new Produto("Pão", 10));
        listaProdutos.add(new Produto("Bolo", 20));
        listaProdutos.add(new Produto("Sanduiche", 30));
        listaProdutos.add(new Produto("Suco", 40));
        listaProdutos.add(new Produto("Bolacha", 50));
        listaProdutos.add(new Produto("Biscoito", 60));

        if (listaProdutos.isEmpty()) {
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, new String[]{"Vazio"}
            );

            listViewProdutos.setAdapter(adaptador);
        } else {
            ArrayList<String> listaDado = new ArrayList<>(listaProdutos.size());

            for (Produto p : listaProdutos) {
                listaDado.add(p.getNome() + " R$ " + p.getPreco());
            }

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, listaDado
            );

            listViewProdutos.setAdapter(adaptador);
        }
    }
}