package com.example.liuyueyue.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import java.net.URLConnection;


public class ImageTest extends AppCompatActivity {
    private ImageView imageView;
    private ProgressBar progressBar;
    private static String URL =
            "http://pic.7y7.com/Uploads/Former/20155/20150521121931389_600_0.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagetest);
        imageView = (ImageView) findViewById(R.id.image);
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        //设置传递进去的参数
        new MyAsycTask().execute(URL);

    }
    class MyAsycTask extends AsyncTask<String,Void,Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //获取传递进来的参数
            String url = params[0];
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;//用于获取数据的输入流
            try {
                connection = new URL(url).openConnection();
                is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                //设置图片加载时间（阻碍作用）
               Thread.sleep(2000);
                //通过decodeStream方法解析输入流
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            }catch (IOException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //将Bitmap作为返回值
            return bitmap;
        }
    }
}
