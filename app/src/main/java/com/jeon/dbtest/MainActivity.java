package com.jeon.dbtest;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
SQLiteDatabase db;
    SQLiteOpenHelper helper;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.dbtext);

 DBManager db = DBManager.getInstance();
        db.addCategory(1,"efesef");
        db.addCategory(2,"teswqw");
      db.addCategory(3,"tesq2esdas");
        db.addSubCategory(1,"sdfe",1);
        db.addSubCategory(2,"sdfe",3);
        db.addSubCategory(3,"sdfe",4);

       Map<Integer,String> i= DBManager.getInstance().getMainCategories();
//todo 이거 데이터 ㄱ클래스에서 대분류를 가져온다 . 그리고 대분류를 중심으로 쿼리를 알아서 처리해서 데이터클래스 세팅한 다음에 메소드에서 세팅.


        Toast.makeText(MainActivity.this, ""+DBManager.getInstance().getSubCategories(1)[0], Toast.LENGTH_SHORT).show();



    }
}
