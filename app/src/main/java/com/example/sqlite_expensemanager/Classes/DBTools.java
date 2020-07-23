package com.example.sqlite_expensemanager.Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.HashMap;

public class DBTools extends SQLiteOpenHelper {

    SQLiteDatabase objWrite=getWritableDatabase();
    SQLiteDatabase objRead=getReadableDatabase();
    public DBTools(Context context) {
        super(context, "ExpenseDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sqlCreateTable="CREATE TABLE EXPENSES ("+"id INTEGER PRIMARY KEY AUTOINCREMENT,"
        +"amount TEXT,"
        +"type TEXT,"
        +"date TEXT)";
        db.execSQL(sqlCreateTable);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void Insert(HashMap<String,String> contact)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("amount",contact.get("amount"));
        contentValues.put("type",contact.get("type"));
        contentValues.put("date",contact.get("date"));
        objWrite.insert("EXPENSES",null,contentValues);
        objWrite.close();
    }
    public ArrayList<HashMap<String,String>> getAllEXPENSES()
    {
        ArrayList<HashMap<String,String>> expenseList=new ArrayList<>();
        String Query="SELECT * FROM EXPENSES";
        Cursor cursor=objRead.rawQuery(Query,null);
        if (cursor.moveToNext()) {
                do {
                    HashMap<String, String> expenses = new HashMap<>();
                    expenses.put("id", cursor.getString(0));
                    expenses.put("amount", cursor.getString(1));
                    expenses.put("type", cursor.getString(2));
                    expenses.put("date", cursor.getString(3));
                    expenseList.add(expenses);
                }while(cursor.moveToNext()==true);
        }
        objRead.close();
        return expenseList;
    }
    public void deleteEXPENSES(String id)
    {
        objWrite.delete("EXPENSES","id="+id,null);
        objWrite.close();
    }
    public void updateEXPENSE(HashMap<String,String> contact,String id)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("amount",contact.get("amount"));
        contentValues.put("type",contact.get("type"));
        contentValues.put("date",contact.get("date"));
        objWrite.update("EXPENSES",contentValues,"id="+id,null);
        objWrite.close();
    }
}
