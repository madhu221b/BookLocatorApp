package com.pathak.rajat.pictlibrary;

/**
 * Created by pawar on 4/10/17.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.UnicodeSetSpanner;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by pawar on 3/10/17.
 */

public class FetchBook extends AsyncTask<String,Void,String> {

    private TextView mdesc, mpub, mpagecount;
    Context ctx;
    ProgressDialog progressDialog;

    public FetchBook(TextView desc, TextView pub, TextView pagecount, Context ctx)

    {
        this.mdesc = desc;
        this.mpub = pub;
        this.mpagecount = pagecount;
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
    protected String doInBackground(String... strings) {
        return NetworkUtils.getBookInfo(strings[0]);
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {

            JSONArray itemsArray = jsonObject.getJSONArray("items");

            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject book = itemsArray.getJSONObject(i);
                String desc = null;
                String pub = null;
                String pagecount = null;
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try {

                    desc = volumeInfo.getString("description");
                    pub = volumeInfo.getString("publisher");
                    pagecount = volumeInfo.getString("pageCount");


                } catch (Exception e) {
                    e.printStackTrace();

                }

                if (desc != null && pub != null && pagecount != null) {
                    mdesc.setText(desc);
                    mpub.setText(pub);
                    mpagecount.setText(pagecount);
                    //  new DownloadImageTask(mimage).execute(image_url);

                    progressDialog.dismiss();
                    return;
                }
            }

            //   mtitle.setText("No result found");
            //  mauthor.setText(" ");

        } catch (Exception e) {
            //mtitle.setText("No result found");
            //mauthor.setText(" ");
            e.printStackTrace();
        }

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView mImage;

        public DownloadImageTask(ImageView image) {
            this.mImage = image;
        }
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {

                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            mImage.setImageBitmap(result);
        }
    }






}
