package com.andorid.mydoctorapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

//    public Database(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
//        super(context, name, version, openParams);
//    }

    private static final String TABLE_NAME = "user";
    private static final String ID_COL = "id";
    private static final String FULL_NAME_COL = "fullName";
    private static final String USER_NAME_COL = "userName";
    private static final String EMAIL_COL = "email";
    private static final String MOBILE_COL = "email";
    private static final String PASSWORD_COL = "password";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String query = "CREATE TABLE user (id INTEGER PRIMARY KEY AUTOINCREMENT," +
//        "user_name TEXT," +
//        "email TEXT," +
//        "password TEXT)";

        String query2 = "CREATE TABLE " + TABLE_NAME + "("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FULL_NAME_COL + " TEXT,"
                + USER_NAME_COL + " TEXT,"
                + EMAIL_COL + " TEXT,"
                + MOBILE_COL + " TEXT,"
                + PASSWORD_COL + " TEXT)";

        String query3 = "CREATE TABLE " + "doctors" + "("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "doctorName" + " TEXT,"
                + "email" + " TEXT,"
                + "address" + " TEXT)";

        sqLiteDatabase.execSQL(query2);
        sqLiteDatabase.execSQL(query3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addNewUser(String fullName, String userName, String email, String mobile, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values =  new ContentValues();
        values.put(FULL_NAME_COL, fullName);
        values.put(USER_NAME_COL, userName);
        values.put(EMAIL_COL, email);
        values.put(MOBILE_COL, mobile);
        values.put(PASSWORD_COL, password);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

//    public void addDoctor(Doctor doctor){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values =  new ContentValues();
//        values.put("doctorName", doctor.getDoctorName());
//        values.put("email", doctor.getEmail());
//        values.put("address", doctor.getAddress());
//        db.insert("doctors", null, values);
//        db.close();
//    }

    public int login(String userName, String password){

        String[] arr = new String[2];
        arr[0] = userName;
        arr[1] = password;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from user where userName=? and password=? ", arr);
        if (c.moveToFirst()){
            return 1;
        }
        return 0;
    }
}
