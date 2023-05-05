package com.example.supporthireapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UserData extends SQLiteOpenHelper {
    Context ctx;
    private static final String DATABASE_NAME = "userdata.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "User";
    private static final String COLUMN_UID = "UID";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_EMAIL = "Email";

    public UserData(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table User ( UID integer primary key, Username text , Password text, Email text );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "drop table if exists "+ TABLE_NAME;
        db.execSQL(query);
    }

    public Boolean insertData(String username, String password, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME,username);
        cv.put(COLUMN_PASSWORD,password);
        cv.put(COLUMN_EMAIL,email);

        long result = db.insert(TABLE_NAME,null,cv);
        if (result != -1){
            return true;
        }
        else {
            return false;
        }
    }
    public Boolean checkUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from User where Username=?", new String[]{username});
        if (cursor.getCount()==1){
            return false;
        }
        else {
            return true;
        }

    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from User where Username=? and Password=?", new String[]{username,password});
        if (cursor.getCount()==1){
            return true;
        }
        else {
            return false;
        }

    }

    public Cursor AllAccount(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+TABLE_NAME;
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    public Boolean deleteData(String uid){
        SQLiteDatabase db = this.getReadableDatabase();
        long result = db.delete(TABLE_NAME,"uid=?",new String[]{uid});
        if (result != -1){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkPassAndEmail(String email,String password,String uid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_NAME+" where (Email = ? or Password = ?) and UID = ?", new String[]{email,password,uid});
        if (cursor.getCount()==1){
            return true;
        }
        else {
            return false;
        }
    }

    public Cursor checkUID(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select UID from "+TABLE_NAME+" where Username=?", new String[]{username});
        return cursor;
    }

    public Boolean updatePass( String password, String UID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PASSWORD, password);
        long result = db.update(TABLE_NAME, cv, "UID = ?",new String[]{UID});

        if (result != -1){
            return true;
        }
        else {
            return false;
        }

    }
    public Boolean updateEmail(String email, String UID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL,email);
        long result = db.update(TABLE_NAME, cv, "UID = ?",new String[]{UID});

        if (result != -1){
            return true;
        }
        else {
            return false;
        }
    }
}
