package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import com.example.myapplication.transactions.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;

public class ItemCardapioActivity extends AppCompatActivity {
    EditText editNome;
    EditText editPreco;

    boolean edit;
    int idItemEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_cardapio);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();

        actionbar.setTitle("Item Cardápio");

        editNome = findViewById( R.id.editNome );
        editPreco = findViewById( R.id.editPreco );
        edit = false;

        if( getIntent().getExtras() != null ){
            String nome = ( String )getIntent().getExtras().get( "nome" );
            String preco = ( String )getIntent().getExtras().get( "preco" );
            idItemEditar = (int)getIntent().getExtras().get( "id" );
            editNome.setText( nome );
            editPreco.setText( preco );
            edit = true;
        }
    }

    public void cancelar( View view ){
        setResult( Constants.RESULT_CANCEL );
        setResult( Constants.RESULT_CANCEL );
        finish();
    }

    public void adicionar( View view ){
        Intent intent = new Intent();

        String nome = editNome.getText().toString();
        String preco = editPreco.getText().toString();

        intent.putExtra( "nome", nome );
        intent.putExtra( "preco", preco );
        if(edit) {
            intent.putExtra("id", idItemEditar);
        }
        setResult( Constants.RESULT_ADD, intent );
        finish();
    }

}