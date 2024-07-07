package com.example.newapppadres.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bdUsuarios extends SQLiteOpenHelper {
    public static final String BDNAME = "login.bd";

    public bdUsuarios(Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyBD) {
        MyBD.execSQL("CREATE TABLE Usuario(Nombre TEXT, Email TEXT PRIMARY KEY, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyBD, int oldVersion, int newVersion) {
        MyBD.execSQL("DROP TABLE IF EXISTS Usuario");
        onCreate(MyBD);
    }

    public Boolean insertData(String Nombre, String Email, String password) {
        SQLiteDatabase MyBD = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Nombre", Nombre);
        contentValues.put("Email", Email);
        contentValues.put("password", password);
        long result = MyBD.insert("Usuario", null, contentValues);
        return result != -1;
    }

    public Boolean ComprobarEmail(String Email) {
        SQLiteDatabase MyBD = this.getReadableDatabase();
        Cursor cursor = MyBD.rawQuery("SELECT * FROM Usuario WHERE Email = ?", new String[]{Email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    public Boolean CompromarEmailPass(String Email, String password) {
        SQLiteDatabase MyBD = this.getReadableDatabase();
        Cursor cursor = MyBD.rawQuery("SELECT * FROM Usuario WHERE Email = ? AND password = ?", new String[]{Email, password});
        boolean correct = cursor.getCount() > 0;
        cursor.close();
        return correct;
    }

    public Cursor ActualizaNombreEmail(String Email) {
        SQLiteDatabase MyBD = this.getReadableDatabase();
        return MyBD.rawQuery("SELECT Nombre, Email FROM Usuario WHERE Email = ?", new String[]{Email});
    }
}
