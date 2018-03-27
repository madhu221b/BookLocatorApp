package com.pathak.rajat.pictlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class BOOK_DESC extends AppCompatActivity {

    TextView desc;
    TextView pub,pagecount;

    String bookname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book__desc);

        Bundle bundle = getIntent().getExtras();
        bookname = bundle.getString("bookname");

        desc = (TextView)findViewById(R.id.desc);
        pub = (TextView)findViewById(R.id.pub);
        pagecount = (TextView)findViewById(R.id.pagecount);

        desc.setMovementMethod(new ScrollingMovementMethod());

        new FetchBook(desc,pub,pagecount,this).execute(bookname);

    }
}
