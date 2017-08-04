package com.example.liuyueyue.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MyAsyncTask extends AsyncTask<Void,Void,Void> {

    @Override
    protected Void doInBackground(Void... params) {
        publishProgress();
        Log.d("xys","doInBackground");
        return null;

    }

    @Override
    protected void onPreExecute() {
        Log.d("xys","onPreExecute");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.d("xys","onPostExecute");
        super.onPostExecute(aVoid);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        Log.d("xys","onProgressUpdate");
        super.onProgressUpdate(values);
    }
}
