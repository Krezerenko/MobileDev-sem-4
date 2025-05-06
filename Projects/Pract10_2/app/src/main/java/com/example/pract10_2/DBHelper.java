package com.example.pract10_2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "products";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_AMOUNT = "amount";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_WEIGHT + " TEXT, " +
                    COLUMN_PRICE + " TEXT, " +
                    COLUMN_AMOUNT + " INTEGER);";
    public DBHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addEntry(DBEntry entry)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, entry.getName());
        values.put(COLUMN_WEIGHT, entry.getWeight());
        values.put(COLUMN_PRICE, entry.getPrice());
        values.put(COLUMN_AMOUNT, entry.getAmount());
        long result = db.insert(TABLE_NAME, null, values);
        db.close();
        return result != -1;
    }

    public DBEntry getEntry(String id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_WEIGHT, COLUMN_PRICE, COLUMN_AMOUNT},
                COLUMN_ID + " = " + id,
                null, null, null, null);
        DBEntry entry = null;
        if (cursor.moveToFirst())
        {
            entry = new DBEntry(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4));
        }
        cursor.close();
        db.close();
        return entry;
    }

    public boolean deleteEntry(String id)
    {
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(TABLE_NAME,
                COLUMN_ID + " = " + id, null);
        db.close();
        return result > 0;
    }

    public boolean setEntry(DBEntry newEntry)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, newEntry.getId());
        values.put(COLUMN_NAME, newEntry.getName());
        values.put(COLUMN_WEIGHT, newEntry.getWeight());
        values.put(COLUMN_PRICE, newEntry.getPrice());
        values.put(COLUMN_AMOUNT, newEntry.getAmount());
        long result = db.update(TABLE_NAME, values,
                COLUMN_ID + " = " + newEntry.getId(), null);
        db.close();
        return result > 0;
    }
}
