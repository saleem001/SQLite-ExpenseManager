package com.example.sqlite_expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.sqlite_expensemanager.Classes.DBTools;

import java.util.ArrayList;
import java.util.HashMap;

public class ShowAllActivity extends AppCompatActivity {

    DBTools dbTools;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        dbTools=new DBTools((getApplicationContext()));

        listView=findViewById(R.id.list);

        EditData();

    }

    public void EditData() {

        ArrayList<HashMap<String,String>> expenseList=dbTools.getAllEXPENSES();
        ListAdapter listAdapter=new SimpleAdapter(this,expenseList,R.layout.showexpenses,new String[]{"id","amount","type","date"},new int[]{R.id.idtext,R.id.amounttext,R.id.typetext,R.id.datetext});
        listView.setAdapter(listAdapter);

        Toast.makeText(this, "Expense Edited", Toast.LENGTH_SHORT).show();
    }

    public void hideActivity(View view) {
        this.finish();
    }
}
