package com.example.a10516125940.cantineiros.Controller;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a10516125940.cantineiros.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CriarUsuario extends AppCompatActivity {

    private Button botaoCadastrar;
    private EditText editTextUsuario;
    private EditText editTextSenha;
    private EditText editTextSenhaNovamente;

    private FirebaseAuth fba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_usuario);

        fba = FirebaseAuth.getInstance();

        botaoCadastrar = findViewById(R.id.botaoCadastrar);
        editTextUsuario = findViewById(R.id.editTextUsuario);
        editTextSenha = findViewById(R.id.editTextSenha);
        editTextSenhaNovamente = findViewById(R.id.editTextSenhaNovamente);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String se, se2;
                se = editTextSenha.getText().toString();
                se2 = editTextSenhaNovamente.getText().toString();

                if( !se.equalsIgnoreCase(se2) ) {

                    Toast.makeText(CriarUsuario.this,
                            "As duas senhas digitadas não conferem.\n" + editTextSenha.getText().toString()
                            + "\n" + editTextSenhaNovamente.getText().toString()
                            , Toast.LENGTH_LONG).show();

                }else if(editTextUsuario.getText().toString().isEmpty() || editTextSenha.getText().toString().isEmpty()
                        || editTextSenhaNovamente.getText().toString().isEmpty()){

                    Toast.makeText(CriarUsuario.this, "Não deixe campos em vazio", Toast.LENGTH_LONG).show();

                }else{

                    fba.createUserWithEmailAndPassword(editTextUsuario.getText().toString(),
                            editTextSenha.getText().toString() )
                                .addOnCompleteListener(CriarUsuario.this,
                                        new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if(task.isSuccessful()){
                                                    startActivity(new Intent(CriarUsuario.this, Activity_Comprador_Principal.class));
                                                }else{
                                                    Toast.makeText(CriarUsuario.this,
                                                            "Ocorreu um erro, usuário não foi criado", Toast.LENGTH_LONG)
                                                    .show();
                                                    Log.i("Login", task.getException().getMessage() );
                                                }
                                            }
                                        });

                }
            }
        });
    }
}
