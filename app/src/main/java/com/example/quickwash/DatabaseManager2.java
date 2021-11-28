package com.example.quickwash;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.quickwash.Garment.Garment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Database manage for user info used in login and register procces
 */

public class DatabaseManager2 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Orders";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_ORDER = "orders";

    private static final String ID = "id";
    private static final String GARMENT_TYPE = "garment";
    private static final String CLEANING_METHOD = "cleaningMethod";
    private static final String QUANTITY = "quantity";
    private static final String PRICE = "price";
    private static final String RECEIVED = "received";
    private static final String STATUS = " order_status"; // recieved, processing , ready for pick up, delivered
    private static final String RECIEPTNUMBER = "OrderNumber";
    private static final String CUSTOMER_EMAIL = "Cus_Email";
    private static final String DELIVERED = "delivered";
    private static final String SUBTOTAL = "price";
    private static final String TOTAL = "price";
    private static final String TAX = "price";
    private static final String OWNER = "owner";

    private static final String TABLE_CART = "Cart";

    private static final String CART_ID = "id";
    private static final String CART_GARMENT_TYPE = "garment";
    private static final String CART_CLEANING_METHOD = "cleaningMethod";
    private static final String CART_QUANTITY = "quantity";
    private static final String CART_PRICE = "price";
    private static final String CART_RECEIVED = "received";
    private static final String CART_DELIVERED = "delivered";
    private static final String CART_STATUS = " order_status"; // recieved, processing , ready for pick up, delivered
    private static final String CART_RECIEPTNUMBER = "OrderNumber";
    private static final String CART_CUSTOMER_EMAIL = "Cus_Email";
    private  static int CART_REC_NUM = 1;
    private  static  int ORDER_REC_NUM = 1;



    public DatabaseManager2(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "create table " + TABLE_ORDER + " ( " + ID;
        sqlCreate += " integer primary key autoincrement, " + GARMENT_TYPE + " text, ";
        sqlCreate += CLEANING_METHOD + " text, " + QUANTITY + " integer, " + PRICE + " real, ";
        sqlCreate += RECEIVED + " datetime, "+DELIVERED+" datetime, "+STATUS+" text, "+RECIEPTNUMBER+" text, "+CUSTOMER_EMAIL+" text )";

        String sqlCart = "create table " + TABLE_CART + " ( " + CART_ID;
        sqlCart += " integer primary key autoincrement, " + CART_GARMENT_TYPE + " text, ";
        sqlCart += CART_CLEANING_METHOD + " text, " + CART_QUANTITY + " integer, " + CART_PRICE + " real, ";
        sqlCart += CART_RECEIVED + " datetime, "+CART_DELIVERED+" datetime, "+CART_STATUS+" text, "+CART_RECIEPTNUMBER+" text, "+CART_CUSTOMER_EMAIL+" text )";

        db.execSQL(sqlCreate);
        db.execSQL(sqlCart);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertGarmentInOrder(String garmentName, String cleaningMethod, double price, double quantity, String status, int recieptNum, String email) {
        Log.w("DB insert garment","*****"+garmentName+" "+cleaningMethod+" "+price+" "+quantity+" "+status+" "+recieptNum+" "+email);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd   HH:mm");
        LocalDateTime now = LocalDateTime.now();
        Log.w("DB","*****"+now);
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_ORDER + " values ( null, '" + garmentName
                + "', '" + cleaningMethod + "', " + quantity + ", " + price*quantity
                + ", '" + now +"', null, '"+status+"', '"+ORDER_REC_NUM+"', '"+email+"' )";
        db.execSQL(sqlInsert);
        db.close();
        ORDER_REC_NUM = ORDER_REC_NUM+1;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void insertGarment(Garment garment, int quantity, String status, int recieptNum, String email) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd   HH:mm");
        LocalDateTime now = LocalDateTime.now();
        Log.w("DB","*****"+now);
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_CART + " values ( null, '" + garment.getGarmentName()
                + "', '" + garment.getCleaningMethod() + "', " + quantity + ", " + garment.getPrice()*quantity
                + ", '" + now +"', null,'"+status+"', '"+CART_REC_NUM+"', '"+email+"' )";

        db.execSQL(sqlInsert);
        db.close();
        CART_REC_NUM = CART_REC_NUM+1;
        Log.w("rec num","******"+CART_REC_NUM );
    }
    //admin
    public ArrayList<Order> selectAllNewOrders(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "select * from "+TABLE_ORDER +" WHERE "+STATUS+" = 'recieved'";

        Cursor myCursor = db.rawQuery(sqlQuery, null);
        ArrayList<Order> currentArray = new ArrayList<>();
        while(myCursor.moveToNext()){
            Order currentOrderInstane = new Order(Integer.parseInt(myCursor.getString(0)),
                    myCursor.getString(1), myCursor.getString(2),
                    myCursor.getString(3),myCursor.getString(4),myCursor.getString(5),
                    myCursor.getString(6),myCursor.getString(7),myCursor.getString(8),
                    myCursor.getString(9));
            currentArray.add(currentOrderInstane);
        }
        db.close();
        return currentArray;
    }

    public ArrayList<Order> selectUserOrderHistory(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "select * from " + TABLE_ORDER + " where " + CUSTOMER_EMAIL + "= '"
                + email + "' ";
        Cursor myCursor = db.rawQuery(sqlQuery, null);
        ArrayList<Order> currentArray = new ArrayList<>();
        while(myCursor.moveToNext()){
            Order currentOrderInstant = new Order(Integer.parseInt(myCursor.getString(0)),
                    myCursor.getString(1), myCursor.getString(2),
                    myCursor.getString(3), myCursor.getString(4), myCursor.getString(5),
                    myCursor.getString(6),myCursor.getString(7),myCursor.getString(8),myCursor.getString(9));
            currentArray.add(currentOrderInstant);
        }
        db.close();
        return currentArray;

    }


    public ArrayList<Order> checkStatus(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlQuery = "select * from " + TABLE_ORDER + " where " + CUSTOMER_EMAIL + "= '"
                + email + "' ";
        Cursor myCursor = db.rawQuery(sqlQuery, null);
        ArrayList<Order> currentArray = new ArrayList<>();
        while(myCursor.moveToNext()){
            Order currentOrderInstant = new Order(Integer.parseInt(myCursor.getString(0)),
                    myCursor.getString(1), myCursor.getString(2),
                    myCursor.getString(3), myCursor.getString(4), myCursor.getString(5),
                    myCursor.getString(6),myCursor.getString(7),myCursor.getString(8),myCursor.getString(9));
            currentArray.add(currentOrderInstant);
        }
        db.close();
        return currentArray;

    }

    public ArrayList<Order> selectAllPendingOrders(String email){
        String sqlQuery = "select * from "+TABLE_CART +" WHERE "+STATUS+" = 'in cart' and "+CART_CUSTOMER_EMAIL+" = '"+email+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor myCursor = db.rawQuery(sqlQuery, null);
        ArrayList<Order> currentArray = new ArrayList<>();
        while(myCursor.moveToNext()){
            Order currentOrderInstane = new Order(Integer.parseInt(myCursor.getString(0)),
                    myCursor.getString(1), myCursor.getString(2),
                    myCursor.getString(3), myCursor.getString(4), myCursor.getString(5),
                    myCursor.getString(6),myCursor.getString(7),myCursor.getString(8),myCursor.getString(9));
            currentArray.add(currentOrderInstane);
        }
        db.close();
        return currentArray;
    }

//    public ArrayList<Order> selectCurrentOrders(String email){
//        String sqlQuery = "select * from "+TABLE_CART +" WHERE "+STATUS+" = 'in cart' and "+CART_CUSTOMER_EMAIL+" = '"+email+"'";
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor myCursor = db.rawQuery(sqlQuery, null);
//        ArrayList<Order> currentArray = new ArrayList<>();
//        while(myCursor.moveToNext()){
//            Order currentOrderInstane = new Order(myCursor.getString(1), myCursor.getString(2),myCursor.getString(3),myCursor.getString(4),myCursor.getString(5),myCursor.getString(6),myCursor.getString(7),myCursor.getString(8),myCursor.getString(9));
//            currentArray.add(currentOrderInstane);
//        }
//        db.close();
//        return currentArray;
//    }

    public void deleteItem(String cleaningMethod, String garmentType, String quantity, String time, String number){
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from "+TABLE_CART+" where "+CART_CLEANING_METHOD +" = '"+cleaningMethod+"' and "+CART_GARMENT_TYPE +" = '"+garmentType+"' and "+CART_QUANTITY+" = '"+quantity+"' and "+CART_RECEIVED+" = '"+time+"' and "+CART_RECIEPTNUMBER+" = '"+number+"'";
        db.execSQL(sqlDelete);
        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop old table if it exists
        db.execSQL( "drop table if exists " + TABLE_ORDER );
        db.execSQL( "drop table if exists " + TABLE_CART );
        // Re-create tables
        onCreate( db );
    }
}
