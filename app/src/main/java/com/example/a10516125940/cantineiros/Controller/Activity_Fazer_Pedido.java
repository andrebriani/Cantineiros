package com.example.a10516125940.cantineiros.Controller;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.a10516125940.cantineiros.Model.Pedido;
import com.example.a10516125940.cantineiros.Model.Produto;
import com.example.a10516125940.cantineiros.R;

import java.util.ArrayList;

public class Activity_Fazer_Pedido extends AppCompatActivity {

    private ListView listViewPedido;
    private ListView listViewProdutos;
    private Button botaoFinalizar;

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

        inserirProdutoNaListaViewProdutos();
        inserirProdutosNaListaViewPedido();

        botaoFinalizar = findViewById(R.id.botaoPedir);
        listViewPedido = findViewById(R.id.listViewPedido);
        listViewProdutos = findViewById(R.id.listViewProdutos);

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
        dialogoAdicionar.setTitle("Excluir?");
        dialogoAdicionar.setMessage("Realmente quer excluir esse produto do seu pedido?");
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
                listaProdutoDoCliente.add( listaProdutos.get(indiceProdutoNoPedidoEscolhido) );
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

            }
        });

    }

    private void realizarPedido(Pedido p){

    }

    private void inserirProdutosNoPedido(Produto p){

    }

    private void inserirProdutosNaListaViewPedido() {
        if (listaProdutoDoCliente.isEmpty()) {
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, new String[]{"Vazio"}
            );

            listViewPedido.setAdapter(adaptador);
        } else {
            ArrayList<String> listaDado = new ArrayList<>(listaProdutos.size());

            for (Produto p : listaProdutoDoCliente) {
                listaDado.add(p.getNome() + " R$ " + p.getPreco());
            }

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, listaDado
            );

            listViewProdutos.setAdapter(adaptador);
        }
    }

    private void inserirProdutoNaListaViewProdutos() {
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
