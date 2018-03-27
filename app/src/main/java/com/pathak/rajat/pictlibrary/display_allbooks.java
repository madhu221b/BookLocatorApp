package com.pathak.rajat.pictlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class display_allbooks extends AppCompatActivity
{
    ListView listView;
    SearchView searchView;
    ArrayAdapter<String> arrayAdapter;
    String booksearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_allbooks);

        listView =(ListView)findViewById(R.id.listview);
        searchView = (SearchView)findViewById(R.id.searchview);

        searchView.setQueryHint("ENTER BOOK NAME");
        searchView.setIconified(false);

        BackGroundTask bg = new BackGroundTask(this);
        bg.execute("book_name");


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
                String book_info = book[0] + " " + book[1];
                BackGround2 bg2 = new BackGround2(display_allbooks.this);
                bg2.execute(book[0],book_info);


            }
        });
    }
}
