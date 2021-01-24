package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.transactions.Constants;
import com.example.myapplication.transactions.ItemCardapio;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int selected;
    ArrayList<ItemCardapio> cardapio;
    ArrayAdapter adapter;

    ListView listViewCardapio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selected = -1;
        cardapio = new ArrayList<ItemCardapio>();
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cardapio);

        listViewCardapio = (ListView) findViewById(R.id.listViewCardapio);


        listViewCardapio.setAdapter(adapter);
        listViewCardapio.setSelector(android.R.color.holo_blue_light);

        listViewCardapio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Toast.makeText(MainActivity.this, "" + cardapio.get(position).toString(), Toast.LENGTH_SHORT).show();
                selected = position;
            }
        });
    }

    public void clickBotaoAdd(View view){
        Intent intent = new Intent( this, ItemCardapioActivity.class );
        startActivityForResult( intent, Constants.REQUEST_ADD );
    }
    public void clickBotaoEdit(View view) {
        Intent intent = new Intent( this, ItemCardapioActivity.class );
        ItemCardapio itemCardapio = cardapio.get(selected);
        intent.putExtra( "id", itemCardapio.getId());
        intent.putExtra( "nome", itemCardapio.getNome());
        intent.putExtra( "preco", String.valueOf(itemCardapio.getPreco()));
        startActivityForResult( intent, Constants.REQUEST_EDIT );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode == Constants.REQUEST_ADD && resultCode == Constants.RESULT_ADD ){
            String nome = ( String )data.getExtras().get("nome");
            String preco = ( String ) data.getExtras().get("preco");
            ItemCardapio itemCardapio = new ItemCardapio( nome, new Float(preco) );
            cardapio.add( itemCardapio );
            adapter.notifyDataSetChanged();

        }else if( requestCode == Constants.REQUEST_EDIT && resultCode == Constants.RESULT_ADD ){
            String nome = ( String )data.getExtras().get("nome");
            String preco = ( String ) data.getExtras().get("preco");
            int idEdit = (int)data.getExtras().get( "id" );

            for( ItemCardapio itemCardapio: cardapio ){
                if( itemCardapio.getId() == idEdit ){
                    itemCardapio.setNome(nome);
                    itemCardapio.setPreco(new Float(preco));
                }
            }
            adapter.notifyDataSetChanged();
        } else if( resultCode == Constants.RESULT_CANCEL ){
            Toast.makeText( this,"Cancelado",
                    Toast.LENGTH_SHORT).show();
        }

    }
}