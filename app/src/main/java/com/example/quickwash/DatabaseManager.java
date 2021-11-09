package com.example.quickwash;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * Database manage for user info used in login and register procces
 */

public class DatabaseManager extends SQLiteOpenHelper {
    //users table
    private static final String DATABASE_NAME = "users";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_LOGIN= "login";
    private static final String ID = "id";
    private static final String EMAIL = "email";
    private static final String fNAME = "fname";
    private static final String lNAME = "lname";
    private static final String PASSWORD = "password";
    private static final String USER_TYPE = "user_type";
     //payement table
    private static final String TABLE_payement = "payement";
    private static final String KEY = "id";
    private static final String account_holder = "name";
    private static final String account_number = "ac";
    private static final String CVV = "cvv";
    private static final String MMYY = "mmyy";
    private static final String user_email = "email";



    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void updateByIdAdmin(String f, String l, String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlUpdate = "update " + TABLE_LOGIN + " set " + fNAME + " = '" + f + "', "
                + lNAME + " = '" + l + "', "+PASSWORD+ " = '" + pass + "' where " + EMAIL + " = '" + email+"'";
        db.execSQL(sqlUpdate);
        db.close();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create  a table for users
        String sqlCreate = "create table "+ TABLE_LOGIN+" ("+ID;
        sqlCreate +=  " integer primary key autoincrement, " +EMAIL+" text, " +fNAME;
        sqlCreate += " text, " + lNAME + " text, "+PASSWORD+ " text, "+USER_TYPE +" text ) ";

        //create a table for payement
        String sqlCreatePayement = "create table "+ TABLE_payement+" ("+ID;
        sqlCreatePayement +=  " integer primary key autoincrement, " +account_holder+" text, " +account_number;
        sqlCreatePayement += " long, " + MMYY +" integer, "+CVV +" integer, "+user_email+" text" +") "; //0 = false, 1 = true

        db.execSQL(sqlCreate);
        db.execSQL(sqlCreatePayement);
    }


    /**
     * users
     * @param email
     * @param pass
     * @param user
     * @return
     */
    public String checkingUser(String email, String pass, String user){

        SQLiteDatabase db = this.getWritableDatabase();
        String sqlCheck = "select * FROM "+TABLE_LOGIN+" WHERE "+EMAIL+" = '"+email +"'"+" AND "+PASSWORD+" = '"+pass+"'"+" and "+USER_TYPE+" = '"+user+"'";
        Cursor myCursor = db.rawQuery(sqlCheck, null);
        if(myCursor.moveToNext()){
            User currentUser = new User(Integer.parseInt(myCursor.getString(0)),
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

    /**
     * users
     * @return
     */
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
    //return first name
    public String fName(String email){
        String sqlQuery = "select "+fNAME+" "+" from "+TABLE_LOGIN +" WHERE "+EMAIL +" = '"+email+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor myCursor = db.rawQuery(sqlQuery, null);
        String fnamee = null;
       // db.execSQL(sqlQuery);
        while(myCursor.moveToNext()){
           fnamee =  myCursor.getString(0);
        }
        Log.w("DatabaseManager", "***** First Name: "+fnamee);
        return fnamee;
    }
    //return first name
    public String password(String email){
        String sqlQuery = "select "+PASSWORD+" "+" from "+TABLE_LOGIN +" WHERE "+EMAIL +" = '"+email+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor myCursor = db.rawQuery(sqlQuery, null);
        String fnamee = null;
        // db.execSQL(sqlQuery);
        while(myCursor.moveToNext()){
            fnamee =  myCursor.getString(0);
        }
        Log.w("DatabaseManager", "***** First Name: "+fnamee);
        return fnamee;
    }
    //return first name

    /**
     * users
     * @param email
     * @return
     */
    public String lName(String email){
        String sqlQuery = "select "+lNAME+" "+" from "+TABLE_LOGIN +" WHERE "+EMAIL +" = '"+email+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor myCursor = db.rawQuery(sqlQuery, null);
        String lnamee = null;
        // db.execSQL(sqlQuery);
        while(myCursor.moveToNext()){
            lnamee =  myCursor.getString(0);
        }
        Log.w("DatabaseManager", "***** Last Name: "+lnamee);
        return lnamee; }
    /**
     * for payement
     * @param name
     * @param num
     * @param mmyy
     * @param cvv
     * @return
     */
    public String check_if_exist(String name, long num, int mmyy, int cvv, String email){
        SQLiteDatabase db = this.getWritableDatabase();
       // String check = "select "+account_holder+" from "+TABLE_payement+" WHERE "+account_holder+" = '"+name+"' AND "+account_number+" ='"+num+"' AND "+MMYY+" ='"+mmyy+"' AND "+CVV+" = '"+cvv+"'";
        String check = "select "+user_email+" from "+TABLE_payement+" WHERE "+user_email+" = '"+email+"'";
        Cursor myCursor = db.rawQuery(check, null);
        String temp = null;
        while(myCursor.moveToNext()){
            temp = myCursor.getString(0);
        }
        Log.w("DatabaseManage","*********: temp "+temp+" email "+email);
           if(email.equals(temp)){
               db.close();
               return "true";
           }
           else{
               db.close();
               return "false";
           }
    }

    /**
     * for Payment
     * @param email
     * @return
     */
    public String is_user_exist(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String check = "select "+user_email+" from "+TABLE_payement+" WHERE "+user_email+" = '"+email+"'";
        Cursor myCursor = db.rawQuery(check, null);
        String temp = null;
        while(myCursor.moveToNext()){
            temp = myCursor.getString(0);
        }
        Log.w("DatabaseManage","*********: temp "+temp+" email "+email);
        if(email.equals(temp)){
            db.close();
            return "true";
        }
        else{
            db.close();
            return "false";
        }
    }

    /**
     * for payement
     * @param email
     * @return
     */
    public String sendName(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String check = "select "+account_holder+" from "+TABLE_payement+" WHERE "+user_email+" = '"+email+"'";
        Cursor myCursor = db.rawQuery(check, null);
        String temp = null;
        while(myCursor.moveToNext()){
            temp = myCursor.getString(0);
        }
        Log.w("DatabaseMange","*******:name ac set text"+temp);
        return temp;
    }

    /**
     * for payement
     * @param email
     * @return
     */
    public String sendacc(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String check = "select "+account_number+" from "+TABLE_payement+" WHERE "+user_email+" = '"+email+"'";
        Cursor myCursor = db.rawQuery(check, null);
        String temp = null;
        long temp1= 0;
        while(myCursor.moveToNext()){
            temp = myCursor.getString(0);
        }
        Log.w("DatabaseMange","*******:num ac set text"+temp);
        return temp;
    }

    /**
     * for payement
     * @param email
     * @return
     */
    public String sendMMYY(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String check = "select "+MMYY+" from "+TABLE_payement+" WHERE "+user_email+" = '"+email+"'";
        Cursor myCursor = db.rawQuery(check, null);
        String temp = null;
        int temp1 = 0;
        while(myCursor.moveToNext()){
            temp = myCursor.getString(0);
        }
        Log.w("DatabaseMange","*******:MMYY set text"+temp);
        return temp;
    }
    /**
     * for payement
     * @param email
     * @return
     */
    public String sendCVV(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String check = "select "+CVV+" from "+TABLE_payement+" WHERE "+user_email+" = '"+email+"'";
        Cursor myCursor = db.rawQuery(check, null);
        String temp = null;
        int temp1 =0;
        while(myCursor.moveToNext()){
            temp = myCursor.getString(0);
        }
        Log.w("DatabaseMange","*******:CVV ac set text"+temp);
        return temp;
    }


    /**
     * for payement
     * @param email
     */
    public void DeletePayment(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_payement+" where "+user_email+" = '"+email+"'";
        db.execSQL(sqlDelete);
        db.close();
    }

    public void insertPayement(String name, long acc, int mmdd, int cvv, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_payement + " values (null, '" +
                name + "' , '" + acc + "' , '" +mmdd + "' , '" + cvv+ "' , '" +email + "'  )";
        db.execSQL(sqlInsert);
        db.close();
    }
    //admin section (delete user)

    /**
     * users
     * @param id
     */
    public void deleteById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_LOGIN+" where "+ID +" = "+id;
        db.execSQL(sqlDelete);
        db.close();
    }

    /**
     * users
     * @param myUserRegisteration
     * @param email
     * @param user
     * @return
     */
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

    /**
     * users
     * @param myUserRegisteration
     */
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
        db.execSQL( "drop table if exists " + TABLE_payement );
        // Re-create tables
        onCreate( db );
    }
}
