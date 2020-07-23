package com.example.sqlite_expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
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

public class EditActivity extends AppCompatActivity {

    MaterialButton materialButton;

    DatePickerDialog datePickerDialog;

    DBTools dbTools;
    TextView datetxt;
    EditText amountEdit,idEdit;

    String spinnerItem,amountstr,date,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        materialButton=findViewById(R.id.EditButton);

        Spinner spinner = findViewById(R.id.Editspinner);
        spinnerItem = spinner.getSelectedItem().toString();

        datetxt=findViewById(R.id.EditeText);
        amountEdit=findViewById(R.id.Editamount);
        idEdit=findViewById(R.id.IDText);

        dbTools=new DBTools((getApplicationContext()));



        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(datetxt.getText().toString()=="" && amountEdit.getText().toString()=="" && spinnerItem=="" && idEdit.getText().toString()=="") {
                    Toast.makeText(EditActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    amountstr=amountEdit.getText().toString();
                    id=idEdit.getText().toString();
                    EditData();
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
        datePickerDialog = new DatePickerDialog(EditActivity.this,
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

    public void EditData() {

        HashMap<String, String> expenses = new HashMap<>();
        expenses.put("amount", amountstr);
        expenses.put("type", spinnerItem);
        expenses.put("date", date);
        dbTools.updateEXPENSE(expenses,id);

        Toast.makeText(this, "Expense Edited", Toast.LENGTH_SHORT).show();
    }
}
