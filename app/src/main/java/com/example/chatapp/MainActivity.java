package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private List<Someone> someoneList=new ArrayList<>();
    private void initSomeone(){
            Someone xa=new Someone("xm",R.drawable.head1);
            someoneList.add(xa);
            Someone xb=new Someone("xb",R.drawable.head2);
            someoneList.add(xb);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_message:
                    return true;
                case R.id.navigation_mail_list:
                  SomeoneAdapter adapter=new SomeoneAdapter(MainActivity.this,R.layout.someone_item,someoneList);
                  ListView listView=(ListView)findViewById(R.id.main_list_view);
                  listView.setAdapter(adapter);
                  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                      @Override
                      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                          Someone someone=someoneList.get(position);
                          Intent intent=new Intent(MainActivity.this,ChatActivity.class);
                          startActivity(intent);
                      }
                  });
                    return true;
                case R.id.navigation_settings:
                    mTextMessage.setText("多余功能");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        initSomeone();
        getMenuInflater().inflate(R.menu.memu,menu);
        return true;
    }

}
