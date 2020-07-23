package com.example.sqlite_expensemanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ShowAddActivity(View view) {
        Intent intent=new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    public void ShowEditActivity(View view) {
        Intent intent=new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    public void ShowDeleteActivity(View view) {
        Intent intent=new Intent(this, DeleteActivity.class);
        startActivity(intent);
    }

    public void ShowShowAllActivity(View view) {
        Intent intent=new Intent(this, ShowAllActivity.class);
        startActivity(intent);
    }
}
