package com.example.surya.cbsv2;

/**
 * Created by Surya on 09/01/2017.
 */

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PinjamKelasDB extends SQLiteOpenHelper{


    public static final String DATABASE_NAME = "pinjamKelas.db";
    public static final String TABLE_NAME1 = "data_user";
    public static final String COL1_1 = "IDUSER";
    public static final String COL1_2 = "USERNAME";
    public static final String COL1_3 = "PASSWORD";
    public static final String TABLE_NAME2 = "data_kelas";
    public static final String COL2_1 = "IDPINJAM";
    public static final String COL2_2 = "NAMARUANG";
    public static final String COL2_3 = "TANGGALPINJAM";
    public static final String COL2_4 = "JAMPINJAM";
    public static final String COL2_5 = "TANGGALKEMBALI";
    public static final String COL2_6 = "JAMKEMBALI";
    public static final String TABLE_NAME3 = "list_kelas";
    public static final String COL3_1 = "IDRUANG";
    public static final String COL3_2 = "NAMARUANG";

    public PinjamKelasDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME1 + "(IDUSER INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASSWORD TEXT)");
        db.execSQL("create table " + TABLE_NAME2 + "(IDPINJAM INTEGER PRIMARY KEY AUTOINCREMENT, NAMARUANG TEXT, " +
                "TANGGALPINJAM TEXT, JAMPINJAM TEXT, TANGGALKEMBALI TEXT, JAMKEMBALI TEXT)");
        db.execSQL("create table " + TABLE_NAME3 + "(IDRUANG INTEGER PRIMARY KEY AUTOINCREMENT, NAMARUANG TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME1);
        db.execSQL("drop table if exists " + TABLE_NAME2);
        db.execSQL("drop table if exists " + TABLE_NAME3);
        onCreate(db);
    }

    public boolean insertDataUser(String uname, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1_2, uname);
        contentValues.put(COL1_3, pass);
        long result = db.insert(TABLE_NAME1, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataKelas(String ruang, String tglPinjam, String jamPinjam, String tglKembali, String jamKembali){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_2, ruang);
        contentValues.put(COL2_3, tglPinjam);
        contentValues.put(COL2_4, jamPinjam);
        contentValues.put(COL2_5, tglKembali);
        contentValues.put(COL2_6, jamKembali);
        long result = db.insert(TABLE_NAME2, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertListKelas(String ruang){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3_2, ruang);
        long result = db.insert(TABLE_NAME3, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getListKelas(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor listKelas = db.rawQuery("select NAMARUANG from " + TABLE_NAME3, null);
        return listKelas;
    }

    public Cursor getDataKelas(String ruang){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor dataKelas = db.rawQuery("select * from " + TABLE_NAME2 + " where NAMARUANG = ?", new String[] {ruang});
        return dataKelas;
    }

    public boolean updateDataKelas(String id, String ruang, String tglPinjam, String jamPinjam, String tglKembali, String jamKembali){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2_1, id);
        contentValues.put(COL2_2, ruang);
        contentValues.put(COL2_3, tglPinjam);
        contentValues.put(COL2_4, jamPinjam);
        contentValues.put(COL2_5, tglKembali);
        contentValues.put(COL2_6, jamKembali);
        db.update(TABLE_NAME2, contentValues, "IDPINJAM = ?", new String[] {id});
        return true;
    }

    public Integer deleteDataKelas(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME2, "IDPINJAM = ?", new String[] {id});
    }


}
