package kr.nayeon.feastofjoy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Student on 2015-11-13.
 */
public class DBManager extends SQLiteOpenHelper {
    int number=1;
    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        //Create new table
        db.execSQL("CREATE TABLE ROTATERECORD(score integer primary key, level integer);");
        db.execSQL("CREATE TABLE DROTATERECORD(score integer primary key, level integer);");
        db.execSQL("CREATE TABLE TRANSLATERECORD(score integer primary key, level integer);");
        db.execSQL("CREATE TABLE OPACITYRECORD(score integer primary key, level integer);");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insert(String query) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public String PrintRotateData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from ROTATERECORD order by score desc", null);
        while (cursor.moveToNext()) {
            str += cursor.getCount()+number+" "+cursor.getInt(0) + "\t" + cursor.getInt(1) + "\n";
            number=number+1;

        }
        return str;
    }
    public String PrintDRotateData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from DROTATERECORD order by score desc", null);
        while (cursor.moveToNext()) {
            str += number+" "+cursor.getInt(0) + "\t" + cursor.getInt(1) + "\n";
            number=number+1;
            if(number>10)
                break;
        }
        return str;
    }
    public String PrintTranslateData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from TRANSLATERECORD order by score desc", null);
        while (cursor.moveToNext()) {
            str += number+" "+cursor.getInt(0) + "\t" + cursor.getInt(1) + "\n";
            number=number+1;
            if(number>10)
                break;
        }
        return str;
    }
    public String PrintOpacityData() {
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from OPACITYRECORD order by score desc", null);
        while (cursor.moveToNext()) {
            str += number+" "+cursor.getInt(0) + "\t" + cursor.getInt(1) + "\n";
            number=number+1;
            if(number>10)
                break;
        }
        return str;
    }
}