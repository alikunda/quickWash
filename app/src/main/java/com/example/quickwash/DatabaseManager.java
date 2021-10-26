package com.example.quickwash;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Database manage for user info used in login and register procces
 */

public class DatabaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "users";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LOGIN= "login";
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String fNAME = "fname";
    private static final String lNAME = "lname";
    private static final String PASSWORD = "password";
    private static final String USER_TYPE = "user_type";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table "+ TABLE_LOGIN+" ("+ID;
        sqlCreate +=  " integer primary key autoincrement, " +EMAIL+" text, " +fNAME;
        sqlCreate += " text, " + lNAME + " text, "+PASSWORD+ " text, "+USER_TYPE +" text ) ";

        db.execSQL(sqlCreate);
    }

    public String checkingUser(String email, String pass, String user){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlCheck = "select * FROM "+TABLE_LOGIN+" WHERE "+EMAIL+" = '"+email +"'"+" AND "+PASSWORD+" = '"+pass+"'"+" and "+USER_TYPE+" = '"+user+"'";
       // String userName = "select "+fNAME+" FROM "+TABLE_LOGIN+" WHERE "+EMAIL+" = '"+email +"'";
        Cursor myCursor = db.rawQuery(sqlCheck, null);
        if(myCursor.moveToNext()){
            user currentUser = new user(Integer.parseInt(myCursor.getString(0)),
                    myCursor.getString(1),myCursor.getString(2),myCursor.getString(3));
            db.close();
           // nav_header myNav_header = new nav_header(userName,email);
        return "success";
        }
        else{
            db.close();
            return "fail";
        }
    }
    public ArrayList<userRegisteration> selectAll(){
        String sqlQuery = "select * from "+TABLE_LOGIN +" WHERE "+USER_TYPE +" <> 'admin'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor myCursor = db.rawQuery(sqlQuery, null);

        ArrayList<userRegisteration> myUsers = new ArrayList<>();
        while(myCursor.moveToNext()){
            userRegisteration currentuser = new userRegisteration(Integer.parseInt(myCursor.getString(0)), myCursor.getString(1),myCursor.getString(2),myCursor.getString(3),myCursor.getString(4),myCursor.getString(5));
            myUsers.add(currentuser);
        }
        db.close();
        return myUsers;
    }
    //admin section (delete user)
    public void deleteById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_LOGIN+" where "+ID +" = "+id;
        db.execSQL(sqlDelete);
        db.close();
    }
    public boolean userCheck(userRegisteration myUserRegisteration,String email,  String user){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlCheck = "select * FROM "+TABLE_LOGIN+" WHERE "+EMAIL+" = '"+email +"'"+" AND " +USER_TYPE+" = '"+user+"'";
        Cursor myCursor = db.rawQuery(sqlCheck, null);
        if(myCursor.moveToNext()){
            userRegisteration currentUserRegisteration= new userRegisteration(Integer.parseInt(myCursor.getString(0)),
                    myCursor.getString(1),myCursor.getString(2),myCursor.getString(3),myCursor.getString(4),myCursor.getString(5));
            db.close();
            return true;
        }
        else{
            db.close();
            return false;
        }
    }
    public void insertUser(userRegisteration myUserRegisteration){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_LOGIN + " values (null, '" +
                myUserRegisteration.getEmail() + "' , '" + myUserRegisteration.getfName() + "' , '" +
                myUserRegisteration.getlName() + "' , '" + myUserRegisteration.getPassword() + "' , '"
                + myUserRegisteration.getUserType()   + "' )";
        db.execSQL(sqlInsert);
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_LOGIN );
        // Re-create tables
        onCreate( db );
    }
}
