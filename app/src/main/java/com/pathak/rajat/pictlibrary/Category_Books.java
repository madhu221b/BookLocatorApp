package com.pathak.rajat.pictlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Category_Books extends AppCompatActivity
{
    ListView listView;
    SearchView searchView;
    String category;
    ArrayAdapter<String> arrayAdapter;
    String booksearch;
    String book_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__books);

        Intent i = getIntent();

        category = i.getStringExtra("category");

        listView =(ListView)findViewById(R.id.listview);
        searchView = (SearchView)findViewById(R.id.searchview);

        searchView.setQueryHint("ENTER BOOK NAME");
        searchView.setIconified(false);


        BackGroundTask bg = new BackGroundTask(this);
        bg.execute(category);


        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,bg.book_names);
        listView.setAdapter(arrayAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toUpperCase();
                arrayAdapter.getFilter().filter(newText);

                return false;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                searchView.setQuery(arrayAdapter.getItem(i),false);
                booksearch = arrayAdapter.getItem(i);

                String []book = booksearch.split("-BY",2);
                book_info = book[0]+" "+book[1];
                BackGround2 bg2 = new BackGround2(Category_Books.this);
                bg2.execute(book[0],book_info);

            }
        });

    }
}
