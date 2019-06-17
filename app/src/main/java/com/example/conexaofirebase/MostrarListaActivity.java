package com.example.conexaofirebase;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class MostrarListaActivity extends AppCompatActivity {

    ListView lvLista;
    List<Anotacao> lista;
    NotaListAdapter adapter;

    private DatabaseReference databaseAnotacao;
    private ChildEventListener childEventListener;

    @Override
    protected void onStart() {
        super.onStart();

        lista.clear();


        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Anotacao nota = new Anotacao();
                nota.setNome(dataSnapshot.child("nome").getValue(String.class) );
                lista.add(nota);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        databaseAnotacao.addChildEventListener( childEventListener );
    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseAnotacao.removeEventListener( childEventListener );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_lista);
        lvLista = (ListView) findViewById(R.id.lvAnotacoes);
        lista = new ArrayList<>();
        carregarLista();
        databaseAnotacao = FirebaseDatabase.getInstance().getReference("Anotacao");

        //excluir
        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(MostrarListaActivity.this);
                alerta.setTitle("Exclusão Anotação");
                alerta.setMessage("Confirmar exclusão da anotação ?");
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseAnotacao = FirebaseDatabase.getInstance().getReference("Anotacao");
                        databaseAnotacao.removeValue();
                        Toast.makeText(getApplicationContext(), "Deletado com sucesso", Toast.LENGTH_LONG).show();

                    }
                });
                alerta.setNeutralButton("Cancelar", null);
                alerta.show();
                return true;
            }
        });
    }
    private void carregarLista(){
        adapter = new NotaListAdapter(this, lista);
        lvLista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}