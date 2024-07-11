package com.example.sqlitewithandroidtutorials;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "booklibrary.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "book_title";
    private static final String COLUMN_AUTHOR = "book_author";
    private static final String COLUMN_PAGES = "book_pages";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AUTHOR + " TEXT, " +
                COLUMN_PAGES + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook(String title, String author, int pages) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);
        long res = db.insert(TABLE_NAME, null, cv);
        if (res == -1) {
            Toast.makeText(context, "Failed to insert data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully inserted data", Toast.LENGTH_SHORT).show();
        }
//        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COLUMN_TITLE + ", " + COLUMN_AUTHOR + ", " + COLUMN_PAGES + ") VALUES ('" + title + "', '" + author + "', '" + pages + "')");
    }

    void updateBook(int rowId, String title, String author, int pages) {
        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("UPDATE " + TABLE_NAME + " SET " + COLUMN_TITLE + " = '" + title + "', " + COLUMN_AUTHOR + " = '" + author + "', " + COLUMN_PAGES + " = '" + pages + "' WHERE " + COLUMN_ID + " = '" + id + "'");
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_AUTHOR, author);
        cv.put(COLUMN_PAGES, pages);

        long res = db.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(rowId)});
        if (res == -1) {
            Toast.makeText(context, "Failed to update data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully updated data", Toast.LENGTH_SHORT).show();
        }
    }


    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        } else {
            Toast.makeText(context, "Database is null", Toast.LENGTH_SHORT).show();
        }
        return cursor;
    }
}
