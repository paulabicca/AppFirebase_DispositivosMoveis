package com.example.conexaofirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AtualizarActivity extends AppCompatActivity {

    private EditText etAtualizaAnotacao;
    private Button btnAtualizar;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar);

        etAtualizaAnotacao = (EditText) findViewById(R.id.etAtualizaAnotacao);
        btnAtualizar = (Button) findViewById(R.id.btnAtualizar);

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizar();

            }
        });





    }

    private void atualizar(){



    }

}
