package com.pathak.rajat.pictlibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackGround2 extends AsyncTask<String, Void, String>
{
    String result;
    Context ctx;
    int stack,shelf;
    ProgressDialog progressDialog;
    String booksearch;
    String book_info;
    //  public final String JSON_URL = "http://192.168.43.215/LIBRARY/book_search.php";
    public final String JSON_URL = "http://smarty07.000webhostapp.com/LIBRARY/book_search.php";


    public BackGround2(Context ctx) {
        this.ctx = ctx;
        progressDialog = new ProgressDialog(ctx);
    }

    @Override
    protected void onPreExecute() {
        progressDialog.setTitle("BOOK LOCATOR");
        progressDialog.setMessage("PLEASE WAIT..");
        progressDialog.show();
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

         booksearch = params[0];
        book_info = params[1];
        String line = null;
        try {
            URL url = new URL(JSON_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
            String data = URLEncoder.encode("BOOK_NAME", "UTF-8") + "=" + URLEncoder.encode(booksearch, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();

            httpURLConnection.setRequestMethod("GET");
            //  httpURLConnection.setDoInput(true);
            InputStream IS = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
            StringBuilder sb = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null)
                sb.append(line+"\n");
            result = sb.toString().trim();

            IS.close();
            bufferedReader.close();
            httpURLConnection.disconnect();
            return result;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        parse_activity(s);
        progressDialog.dismiss();
    }


    private void parse_activity(String result) {
        try {
            JSONArray ja = new JSONArray(result);
            JSONObject jo = ja.getJSONObject(0);


            stack = jo.getInt("STACK_NO");
            shelf = jo.getInt("SHELF_NO");

      //      Toast.makeText(ctx,"STACK:"+stack+","+"SHELF:"+shelf,Toast.LENGTH_LONG).show();
            Intent i = new Intent(ctx, MapTopView.class);
            i.putExtra("bookname",book_info);
            i.putExtra("stack", stack);
            i.putExtra("shelf",shelf);
            ctx.startActivity(i);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
