package com.example.submon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "Database";

    private static final String TABLE_NAME = "notification_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "Info";
    private static final String COL3 = "Description";
    private static final String COL4 = "Price";
    private static final String COL5 = "Date";


    public DatabaseHelper(@Nullable Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableCommand = "CREATE TABLE " + TABLE_NAME + " ( " +
                COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 + " TEXT, " +
                COL3 + "  TEXT, " +
                COL4 + " REAL, " +
                COL5 + " INT  )";
        sqLiteDatabase.execSQL(createTableCommand);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addData(String info, String description, Float price, int day ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, info);
        contentValues.put(COL3, description);
        contentValues.put(COL4, price);
        contentValues.put(COL5, day);

        Log.d(TAG, "addData: Adding " + info + "|" + price + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void printColums() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        String[] collumNames = cursor.getColumnNames();
        for (String i : collumNames) {
            Log.d(TAG, i);
        }
    }

    public Integer deleteFromDb(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {String.valueOf(id)});
    }
}
