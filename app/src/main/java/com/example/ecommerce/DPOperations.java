package com.example.ecommerce;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DPOperations extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "E_COMMERCE";
    private SQLiteDatabase database;

    public DPOperations(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CreateScheme(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Customers");
        onCreate(db);
    }

    private SQLiteDatabase CreateScheme(SQLiteDatabase db){
        db.execSQL("create table Customers(CustID integer primary key, CustName text not null, Username text not null," +
                "Password text not null)");
        return db;
    }

    public Cursor userLogin(String username, String password){
        database = getReadableDatabase();
        String [] args = {username, password};
        Cursor cursor = database.rawQuery("select CustID, CustName from Customers where Username=? and Password =?", args);

        if(cursor!=null)
            cursor.moveToFirst();

        database.close();
        return cursor;
    }

    public void userSignUp(User user){
        ContentValues values = new ContentValues();
        values.put("CustName", user.getName());
        values.put("Username", user.getUsername());
        values.put("Password", user.getPassword());
        database = getWritableDatabase();
        database.insert("Customers",null, values);
        database.close();
    }
}
