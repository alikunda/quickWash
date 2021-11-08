package com.example.quickwash;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quickwash.Garment.Garment;

/**
 * Database manage for user info used in login and register procces
 */

public class DatabaseManager2 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Orders";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ORDER = "order";

    private static final String ID = "id";
    private static final String GARMENT_TYPE = "garment";
    private static final String CLEANING_METHOD = "cleaningMethod";
    private static final String QUANTITY = "quantity";
    private static final String PRICE = "price";
    private static final String SUBTOTAL = "price";
    private static final String TOTAL = "price";
    private static final String TAX = "price";
    private static final String OWNER = "owner";

    public DatabaseManager2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_ORDER + " ( " + ID;
        sqlCreate += " integer primary key autoincrement, " + GARMENT_TYPE + " text, ";
        sqlCreate += CLEANING_METHOD + " text, " + QUANTITY + " integer, " + PRICE + " real)";

        db.execSQL(sqlCreate);
    }

    public void insertGarment(Garment garment, int quantity) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_ORDER + " values ( null, '" + garment.getGarmentName()
                + "', '" + garment.getCleaningMethod() + "', " + quantity + ", " + garment.getPrice() + " )";
        db.execSQL(sqlInsert);
        db.close();

    }

    /*

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
        }*/


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if it exists
        db.execSQL(" drop table if exists " + TABLE_ORDER);
        // Re-create tables
        onCreate(db);
    }
}
