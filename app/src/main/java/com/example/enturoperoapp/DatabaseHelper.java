package com.example.enturoperoapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "articulos.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_ARTICULOS = "articulos";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRECIO = "precio";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_ARTICULOS +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PRECIO + " INTEGER" +
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICULOS);
        onCreate(db);
    }

    public void addArticulo(Articulo articulo) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, articulo.getId());
        values.put(COLUMN_PRECIO, articulo.getPrecio());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_ARTICULOS, null, values);
        db.close();
    }

    public List<Articulo> getAllArticulos() {
        List<Articulo> articulos = new ArrayList<>();

        String query = "SELECT * FROM articulos";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") int precio = cursor.getInt(cursor.getColumnIndex(COLUMN_PRECIO));
                articulos.add(new Articulo(id, precio));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return articulos;
    }

    public void agregarArticulos(List<Articulo> articulos) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (Articulo articulo : articulos) {
                values.put(COLUMN_PRECIO, articulo.getPrecio());
                db.insert(TABLE_ARTICULOS, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    public void vaciarTablaArticulos() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("articulos", null, null);
        db.close();
    }

}
