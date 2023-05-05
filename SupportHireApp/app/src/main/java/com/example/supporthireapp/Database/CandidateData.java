package com.example.supporthireapp.Database;

import static com.google.android.material.transition.platform.MaterialSharedAxis.Y;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.supporthireapp.Activity.MainActivity;
import com.google.android.material.tabs.TabLayout;

public class CandidateData extends SQLiteOpenHelper {
    Context ctx;
    private final static String DATABASE_NAME = "candidateData.db";
    private final static Integer VERSION = 1;
    private final static String TABLE_NAME = "Candidate_Info";
    private final static String COLUMN_FULLNAME = "FullName";
    private final static String COLUMN_DATE = "Date_of_birth";
    private final static String COLUMN_ADDRESS = "Address";
    private final static String COLUMN_SKILL = "Skills";
    private final static String COLUMN_EXPERIENCE = "Experience";
    private final static String COLUMN_FIELD = "Field";
    private final static String COLUMN_Foreign_Key = "Uid";

    public CandidateData(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "create table "+TABLE_NAME +" ( STT integer primary key autoincrement, FullName text , Date_of_birth date, Address text, Skills text, Experience text, Field text, Uid text )";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);

    }

    public Boolean insertData(String fullname,String date,String address,String skill,String experience,String field, String uid){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FULLNAME, fullname);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_ADDRESS,address);
        cv.put(COLUMN_SKILL,skill);
        cv.put(COLUMN_EXPERIENCE,experience);
        cv.put(COLUMN_FIELD,field);
        cv.put(COLUMN_Foreign_Key,uid);

        long result = db.insert(TABLE_NAME,null,cv);
        if (result != -1){
            return true;
        }
        else {
            return false;
        }
    }

    public Cursor DataDetail(String uid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery("select * from "+TABLE_NAME+" where Uid = ?",new String[]{uid});
        }
        return cursor;
    }

    public Cursor EachData(String stt){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery("select STT,FullName,strftime(\"%Y\",date(\"now\"))-strftime(Date_of_birth) as Age from "+TABLE_NAME+" where STT = ?", new String[]{stt});
        }
        return cursor;
    }

    public Cursor Fetch(String Name, String Skill,String uid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Candidate_Info where (FullName like ? or Skills like ?) and Uid = ?",new String[]{Name,Skill,uid} );
        return cursor;

    }

    public Cursor Skill(String uid){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select Skills from Candidate_Info where Uid = ? ", new String[]{uid});
        return cursor;
    }

}
