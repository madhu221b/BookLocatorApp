package com.pathak.rajat.pictlibrary;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BackGroundTask extends AsyncTask<String,Void,String[]>
{
    String[] data;
    String line =null;
    String result;
    Context ctx;
    ProgressDialog progressDialog;
    public ArrayList<String> book_names = new ArrayList<String>();


    public  BackGroundTask(Context ctx)
    {
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
    protected String[] doInBackground(String... params) {

         String JSON_URL = "http://smarty07.000webhostapp.com/LIBRARY/"+params[0]+".php";

        try {
            URL url = new URL(JSON_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream is = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            while((line=br.readLine())!=null)
                sb.append(line+"\n");
            result= sb.toString();

            //PARSE DATA

            JSONArray ja = new JSONArray(result);
            JSONObject jo = null;
            data = new String[ja.length()];

            for(int i=0;i<ja.length();i++)
            {
                jo = ja.getJSONObject(i);
                data[i] = jo.getString("BOOK_NAME")+"-BY "+jo.getString("AUTHOR");

            }


            is.close();
            br.close();
            httpURLConnection.disconnect();

            return data;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String[] data) {


        for(int i=0;i<data.length;i++)
            book_names.add(data[i]);

        progressDialog.dismiss();

    }
}
