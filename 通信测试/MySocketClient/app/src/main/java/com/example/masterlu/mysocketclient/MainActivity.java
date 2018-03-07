package com.example.masterlu.mysocketclient;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    EditText ip;
    EditText editText;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip=(EditText) findViewById(R.id.ip);
        editText =(EditText) findViewById(R.id.edit);
        text = (TextView) findViewById(R.id.text);

        findViewById(R.id.connent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connent();
            }
        });
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send();
            }
        });
    }
    Socket socket =null;
    BufferedWriter bw=null;
    BufferedReader br=null;
    public void connent(){
        try {
            socket =new Socket(ip.getText().toString(),10086);
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            AsyncTask<Void,String ,Void> read =new AsyncTask<Void, String, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {

                    try {

                        socket =new Socket(ip.getText().toString(),10086);
                        br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        publishProgress("@success");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        String line;
                        while((line=br.readLine())!=null){
                            publishProgress(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }
                @Override
                protected void onProgressUpdate(String... values){
                    if(values[0].equals("@success")) {
                        Toast.makeText(MainActivity.this, "链接成功", Toast.LENGTH_SHORT).show();
                    }
                    text.append("reply"+values[0]+"\n");
                    super.onProgressUpdate(values);
                }


            };
read.execute();

        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "无法建立链接", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
    public void send(){
        try {
            text.append(editText.getText().toString()+"\n");
            bw.write(editText.getText().toString()+"\n");
            bw.flush();
            editText.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
