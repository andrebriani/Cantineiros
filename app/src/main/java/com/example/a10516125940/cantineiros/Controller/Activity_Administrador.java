package com.example.a10516125940.cantineiros.Controller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a10516125940.cantineiros.Model.Produto;
import com.example.a10516125940.cantineiros.R;

import java.util.ArrayList;

public class Activity_Administrador extends AppCompatActivity {

    private Button botaoAdicionar;
    private EditText campoNomeProduto;
    private EditText campoPrecoProduto;
    private ListView listViewProdutos;

    private AlertDialog.Builder dialogo;

    private ArrayList<Produto> listaProdutos = new ArrayList<>();

    private int indiceItemEscolhido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__administrador);

        listViewProdutos = findViewById(R.id.listView_produtos);
        campoNomeProduto = findViewById(R.id.editText_Produto);
        campoPrecoProduto = findViewById(R.id.editText_Preco);
        botaoAdicionar = findViewById(R.id.botaoAdicionar);

        adicionarValoresTeste();

        inserirElementosListaInterface();

        dialogo = new AlertDialog.Builder(Activity_Administrador.this);
        dialogo.setTitle("Excluir?");
        dialogo.setMessage("Realmente quer excluír?");
        dialogo.setCancelable(true);
        dialogo.setIcon(android.R.drawable.ic_delete);

        dialogo.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Activity_Administrador.this, "Cancelando ...", Toast.LENGTH_LONG).show();
            }
        });

        dialogo.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                removerProduto( listaProdutos.get(indiceItemEscolhido) );
                Toast.makeText(Activity_Administrador.this, "Pronto", Toast.LENGTH_LONG).show();
            }
        });

        listViewProdutos.setLongClickable(true);
        listViewProdutos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                indiceItemEscolhido = position;
                dialogo.create().show();
                return true;
            }
        });

        botaoAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomeProduto = campoNomeProduto.getText().toString();
                String preco = campoPrecoProduto.getText().toString();

                if(nomeProduto.isEmpty() || preco.isEmpty()){
                    Toast.makeText(Activity_Administrador.this, "Não deixe campos vazios", Toast.LENGTH_LONG).show();
                }else{
                    double precoProduto = Double.parseDouble(campoPrecoProduto.getText().toString());

                    Produto p = new Produto(nomeProduto, precoProduto);

                    inserirProduto(p);

                    inserirElementosListaInterface();

                    campoNomeProduto.setText("");
                    campoPrecoProduto.setText("");
                }
            }
        });
    }

    private void adicionarValoresTeste(){
        listaProdutos.add(new Produto("Pão", 10));
        listaProdutos.add(new Produto("Bolo", 20));
        listaProdutos.add(new Produto("Sanduiche", 30));
        listaProdutos.add(new Produto("Suco", 40));
        listaProdutos.add(new Produto("Bolacha", 50));
        listaProdutos.add(new Produto("Biscoito", 60));
    }

    private void inserirProduto(Produto p){
        listaProdutos.add(p);
        inserirElementosListaInterface();
    }

    private void removerProduto(Produto p){
        listaProdutos.remove(p);
        inserirElementosListaInterface();
    }

    private void inserirElementosListaInterface(){
        adicionarValoresTeste();

        if(listaProdutos.isEmpty()){
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, new String[]{"Vazio"}
            );

            listViewProdutos.setAdapter(adaptador);
        }else{
            ArrayList<String> listaDado = new ArrayList<>(listaProdutos.size());

            for(Produto p: listaProdutos){
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
