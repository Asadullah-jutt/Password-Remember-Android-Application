package com.example.passwordsaver;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DatabaseHelper{

//    static SQLiteDatabase db;
private static final int DATABASE_VERSION = 10;
    private static final String DATABASE_NAME = "userdata";
    private static final String TABLE_NAME = "userdetails";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    private final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_EMAIL + " TEXT NOT NULL UNIQUE, " +  // Add UNIQUE constraint
                    COLUMN_PASSWORD + " TEXT NOT NULL);";


    private final String TABLE_PASS = "userpasswords";
    private final String TABLE_DEL_PASS = "deleteduserpasswords";
    private final String KEY_ID = "_id";
    private final String KEY_URL = "_url";
    private final String KEY_OFUSER = "_ofuser";
    private final String KEY_USERNAME = "_username";
    private final String KEY_PASSWORD = "_password";


    private final String PASSWORD_MANAGER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_PASS + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY ," +
                    KEY_OFUSER + " TEXT NOT NULL , " +
                    KEY_URL + " TEXT NOT NULL, " +
                    KEY_USERNAME + " TEXT NOT NULL , " +  // Add UNIQUE constraint
                    KEY_PASSWORD + " TEXT NOT NULL);";

    private final String DEL_PASSWORD_MANAGER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_DEL_PASS + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY ," +
                    KEY_OFUSER + " TEXT NOT NULL , " +
                    KEY_URL + " TEXT NOT NULL, " +
                    KEY_USERNAME + " TEXT NOT NULL , " +  // Add UNIQUE constraint
                    KEY_PASSWORD + " TEXT NOT NULL);";


    Context context ;
    MyDatabaseHelper helper;
    SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context){

        this.context = context ;
        helper = new MyDatabaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
        sqLiteDatabase = helper.getWritableDatabase();
    }




    public void insertData(String a , String b , String c){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,a);
        values.put(COLUMN_EMAIL,b);
        values.put(COLUMN_PASSWORD,c);

        try{
            sqLiteDatabase.insert(TABLE_NAME, null ,values);
            Log.d("Insert SUCCESS", values.toString());
        } catch (Exception e){
            Log.d("Insert FAILURE", e.toString());
        }
        //db.close();
    }

    public void deletePassword(int id ,String ofUser ,String name, String text , String URL)
    {

        try {
            int rows = sqLiteDatabase.delete(TABLE_PASS, KEY_ID + "=?", new String[]{String.valueOf(id)});
            if (rows > 0) {
                Log.d("SQLite", "Row deleted successfully");
                insertDelPassword(ofUser,name,text,URL);
            } else {
                Log.d("SQLite", "No rows were deleted");
            }
        } catch (Exception e) {
            Log.e("SQLite Error", "Error occurred while deleting", e); // Log the error
        }

    }
    public void insertPassword(String ofUser ,String name, String text , String URL){
        ContentValues values = new ContentValues();

        values.put(KEY_OFUSER,ofUser);
        values.put(KEY_USERNAME,name);
        values.put(KEY_PASSWORD,text);
        values.put(KEY_URL,URL);

        try{
            sqLiteDatabase.insert(TABLE_PASS, null ,values);
//            Log.d("Insert SUCCESS", values.toString());
//            Toast.makeText(context, "Asa", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
//            Toast.makeText(context, "Asaddd", Toast.LENGTH_SHORT).show();
        }
        //db.close();
    }
    public void close()
    {
        sqLiteDatabase.close();
        helper.close();
    }

    public void insertDelPassword(String ofUser ,String name, String text , String URL){
        ContentValues values = new ContentValues();

        values.put(KEY_OFUSER,ofUser);
        values.put(KEY_USERNAME,name);
        values.put(KEY_PASSWORD,text);
        values.put(KEY_URL,URL);

        try{
            sqLiteDatabase.insert(TABLE_DEL_PASS, null ,values);
//            Log.d("Insert SUCCESS", values.toString());
//            Toast.makeText(context, "Asa", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
//            Toast.makeText(context, "Asaddd", Toast.LENGTH_SHORT).show();
        }
        //db.close();
    }


    public String userExists(String emailid,String password){

        Cursor c =  sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME, null);
//        ArrayList<Contact> contacts = new ArrayList<>();
        int id_index = c.getColumnIndex(COLUMN_ID);
        int id_n = c.getColumnIndex(COLUMN_NAME);
        int id_name = c.getColumnIndex(COLUMN_EMAIL);
        int id_phone = c.getColumnIndex(COLUMN_PASSWORD);
        Toast.makeText(context, ""+c.getCount(), Toast.LENGTH_SHORT).show();

        if(c.moveToFirst())
        {
            do{

                Toast.makeText(context, ""+c.getInt(id_index), Toast.LENGTH_SHORT).show();
                if(c.getString(id_name).equals(emailid))
                    if(c.getString(id_phone).equals(password))
                    {
                        String s = c.getString(id_name) ;
                        c.close();
                        return s;
                    }


            }while(c.moveToNext());
        }

        c.close();
        return "-1";
    }

    public ArrayList<PasswordData> getPasswords(String username)
    {
//        Toast.makeText(this.context, "1", Toast.LENGTH_SHORT).show();

        Cursor c =  sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_PASS , null);
        ArrayList<PasswordData> contacts = new ArrayList<>();
        int id_index = c.getColumnIndex(KEY_ID);
        int id_ofuser = c.getColumnIndex(KEY_OFUSER);
        int id_name = c.getColumnIndex(KEY_USERNAME);
        int id_pass = c.getColumnIndex(KEY_PASSWORD);
        int id_url = c.getColumnIndex(KEY_URL);

//        Toast.makeText(this.context,""+ c.getCount(), Toast.LENGTH_SHORT).show();

        if(c.moveToFirst())
        {
            do{
                if(c.getString(id_ofuser).equals(username)) {
                    PasswordData pass = new PasswordData(c.getInt(id_index),c.getString(id_ofuser), c.getString(id_name), c.getString(id_pass), c.getString(id_url));
                    contacts.add(pass);
                }
            }while(c.moveToNext());
        }

        c.close();
        return contacts;
    }

    public void updatepassword(int id, String ofUser ,String name, String text , String URL) {
        ContentValues values = new ContentValues();
        values.put(KEY_OFUSER,ofUser);
        values.put(KEY_USERNAME,name);
        values.put(KEY_PASSWORD,text);
        values.put(KEY_URL,URL);


        int rows = sqLiteDatabase.update(TABLE_PASS, values, KEY_ID+"=?", new String[]{id+""});
        if(rows>0)
        {
            Toast.makeText(context, "Contact updated successfully", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Failed to update contact", Toast.LENGTH_SHORT).show();
        }
    }


    //    getDeletedPasswords(MainActivity3.id)
    public ArrayList<PasswordData> getDeletedPasswords(String username)
    {
//        Toast.makeText(this.context, "1", Toast.LENGTH_SHORT).show();

        Cursor c =  sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_DEL_PASS , null);
        ArrayList<PasswordData> contacts = new ArrayList<>();
        int id_index = c.getColumnIndex(KEY_ID);
        int id_ofuser = c.getColumnIndex(KEY_OFUSER);
        int id_name = c.getColumnIndex(KEY_USERNAME);
        int id_pass = c.getColumnIndex(KEY_PASSWORD);
        int id_url = c.getColumnIndex(KEY_URL);

//        Toast.makeText(this.context,""+ c.getCount(), Toast.LENGTH_SHORT).show();

        if(c.moveToFirst())
        {
            do{
                if(c.getString(id_ofuser).equals(username)) {
                    PasswordData pass = new PasswordData(c.getInt(id_index),c.getString(id_ofuser), c.getString(id_name), c.getString(id_pass), c.getString(id_url));
                    contacts.add(pass);
                }
            }while(c.moveToNext());
        }

        c.close();
        return contacts;
    }



    private class MyDatabaseHelper extends SQLiteOpenHelper
    {
        public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
            db.execSQL(PASSWORD_MANAGER_TABLE);
            db.execSQL(DEL_PASSWORD_MANAGER_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PASS);
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_DEL_PASS);
            onCreate(db);
        }
    }
}


