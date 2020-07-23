package com.example.sqlite_expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sqlite_expensemanager.Classes.DBTools;

public class DeleteActivity extends AppCompatActivity {


    DBTools dbTools;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        dbTools=new DBTools((getApplicationContext()));

        editText=findViewById(R.id.idEdit);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void DeleteData(View view) {
        final String id=editText.getText().toString();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dbTools.deleteEXPENSES(id);
                Toast.makeText(getApplicationContext(), "Expense Deleted", Toast.LENGTH_SHORT).show();
            }
        },5000);

    }
}
