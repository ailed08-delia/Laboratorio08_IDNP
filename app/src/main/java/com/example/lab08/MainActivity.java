package com.example.lab08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;

import com.example.lab08.adapter.RecyclerAdapter;
import com.example.lab08.model.ItemList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.RecyclerItemClick, SearchView.OnQueryTextListener {
    private RecyclerView rvLista;
    private SearchView svSearch;
    private RecyclerAdapter adapter;
    private List<ItemList> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initValues();
        initListener();
    }
    private void initViews(){

        rvLista = findViewById(R.id.rvLista);
        svSearch=findViewById(R.id.svSearch);
    }
    private void initValues(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvLista.setLayoutManager(manager);

        items = getItems();
        adapter = new RecyclerAdapter(items,this);
        rvLista.setAdapter(adapter);


    }

    private void initListener(){

        svSearch.setOnQueryTextListener(this);
    }
    private List<ItemList> getItems(){
        List<ItemList> itemLists = new ArrayList<>();
        itemLists.add(new ItemList("Gabriela Paredes",974363821, "gabriela@gmailcom",R.drawable.chica1));
        itemLists.add(new ItemList("Julio Serpa", 958989454, "Julio@gmail.com",R.drawable.chico2));
        itemLists.add(new ItemList("Melissa Paredes", 940800317, "melissa@gmail.com",R.drawable.chica2));
        itemLists.add(new ItemList("Emanuel Quispe", 958903456,"enma_45@gmail.com",R.drawable.chico3));
        itemLists.add(new ItemList("Rosalinda Mamani", 96784356, "rosa_linda@gmail.com",R.drawable.chica1));
        itemLists.add(new ItemList("Enrique Portillo", 908765430,"enrique@gmail.com",R.drawable.chico3));
        return itemLists;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }



    @Override
    public void itemClick(ItemList item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemDetail", (Serializable) item);
        startActivity(intent);
    }
}