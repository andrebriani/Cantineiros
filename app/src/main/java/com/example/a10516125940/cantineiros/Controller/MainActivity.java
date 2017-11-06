package com.example.a10516125940.cantineiros.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a10516125940.cantineiros.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private Button botaoEntrar;
    private TextView textView_Esqueceu_senha;
    private TextView criarNovoUsuario;
    private EditText campoNome;
    private EditText campoSenha;

    private FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fba = FirebaseAuth.getInstance();

        //fba.signOut();

        if(fba.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this, Activity_Comprador_Principal.class));
        }

        botaoEntrar = findViewById(R.id.botaoEntrar);
        textView_Esqueceu_senha = findViewById(R.id.textView_Esqueceu_Senha);
        criarNovoUsuario = findViewById(R.id.textViewCriarUsuario);
        campoNome = findViewById(R.id.editText_Usuario);
        campoSenha = findViewById(R.id.editText_senha);

        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fba.signInWithEmailAndPassword(campoNome.getText().toString(), campoSenha.getText().toString())
                    .addOnCompleteListener(MainActivity.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(MainActivity.this, Activity_Comprador_Principal.class));
                                    }else{
                                        Toast.makeText(MainActivity.this, "Erro ao realizar o login", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

            }
        });

        textView_Esqueceu_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Não implementado ainda, birrl", Toast.LENGTH_LONG).show();
            }
        });

        criarNovoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CriarUsuario.class));
            }
        });
    }
}
