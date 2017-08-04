package com.example.liuyueyue.asynctask;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

public class ProgressBarTest extends Activity {

private ProgressBar progressBar;
    private MyAsyncTask mTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progressbar);
        progressBar = (ProgressBar) findViewById(R.id.pg);
        mTask = new MyAsyncTask();
        mTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mTask != null&&mTask.getStatus() == AsyncTask.Status.RUNNING){
            //cancel方法只是将对应的AsyncTask标记为cancel状态，并不是真正的取消线程的执行
            mTask.cancel(true);
        }
    }

    class MyAsyncTask extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... params) {
            //模拟进度更新
            for(int i =0;i<100;i++){
                //判断线程是否执行中
                if(isCancelled()){
                    break;
                }
                publishProgress(i);
                try {
                    //增加睡眠时间
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //判断线程是否执行中
            if(isCancelled()){
                return;
            }
            //获取进度更新值
            progressBar.setProgress(values[0]);
        }
    }

}
