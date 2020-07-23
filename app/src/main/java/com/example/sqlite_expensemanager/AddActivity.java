package com.example.sqlite_expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.sqlite_expensemanager.Classes.DBTools;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.HashMap;

public class AddActivity extends AppCompatActivity {


    MaterialButton materialButton;

    DatePickerDialog datePickerDialog;

    DBTools dbTools;
    TextView datetxt;
    EditText amountEdit;

    String spinnerItem,amountstr,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        materialButton=findViewById(R.id.AddButton);

        Spinner spinner = findViewById(R.id.spinner);

        datetxt=findViewById(R.id.eText);
        amountEdit=findViewById(R.id.amount);

        dbTools=new DBTools((getApplicationContext()));


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItem = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datetxt.getText().toString()=="" && amountEdit.getText().toString()=="" && spinnerItem=="") {
                    Toast.makeText(AddActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    amountstr=amountEdit.getText().toString();
                    AddData();

                }
            }
        });

    }

    public void SelectDate(View view) {

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        datePickerDialog = new DatePickerDialog(AddActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        datetxt.setText("Selected date is : "+dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        int m=monthOfYear+1;
                        date=dayOfMonth+"/"+m+"/"+year;
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    public void AddData() {


        HashMap<String, String> expenses = new HashMap<>();
        expenses.put("amount", amountstr);
        expenses.put("type", spinnerItem);
        expenses.put("date", date);
        dbTools.Insert(expenses);

        Toast.makeText(this, "Expense Stored", Toast.LENGTH_SHORT).show();
        this.finish();
    }
}
