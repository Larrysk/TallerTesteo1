package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Información de la base de datos
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyAppDatabase";

    // Tabla de usuarios
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    // Tabla de precios de artículos
    private static final String TABLE_ITEMS = "items";
    private static final String KEY_ITEM_ID = "item_id";
    private static final String KEY_ITEM_NAME = "item_name";
    private static final String KEY_ITEM_PRICE = "item_price";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";
        db.execSQL(createUsersTable);

        // Crear tabla de precios de artículos
        String createItemsTable = "CREATE TABLE " + TABLE_ITEMS + "("
                + KEY_ITEM_ID + " INTEGER PRIMARY KEY,"
                + KEY_ITEM_NAME + " TEXT,"
                + KEY_ITEM_PRICE + " REAL" + ")";
        db.execSQL(createItemsTable);

        // Agregar registros de ejemplo a la tabla de precios de artículos
        ContentValues values1 = new ContentValues();
        values1.put(KEY_ITEM_NAME, "Item 1");
        values1.put(KEY_ITEM_PRICE, 10.99);
        db.insert(TABLE_ITEMS, null, values1);

        ContentValues values2 = new ContentValues();
        values2.put(KEY_ITEM_NAME, "Item 2");
        values2.put(KEY_ITEM_PRICE, 22.99);
        db.insert(TABLE_ITEMS, null, values2);

        ContentValues values3 = new ContentValues();
        values3.put(KEY_ITEM_NAME, "Item 3");
        values3.put(KEY_ITEM_PRICE, 43.99);
        db.insert(TABLE_ITEMS, null, values3);

        // Agregar usuario de ejemplo
        ContentValues values4 = new ContentValues();
        values4.put(KEY_USERNAME, "user");
        values4.put(KEY_PASSWORD, "password");
        db.insert(TABLE_USERS, null, values4);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Borrar tablas si existen
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Crear tablas de nuevo
        onCreate(db);
    }

    // Registrar un nuevo usuario en la tabla de usuarios
    public long addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        long id = db.insert(TABLE_USERS, null, values);
        db.close();
        return id;
    }
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {KEY_ID};
        String selection = KEY_ID + " = ?";
        String[] selectionArgs = { username };
        Cursor cursor = db.query(TABLE_USERS, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }

}