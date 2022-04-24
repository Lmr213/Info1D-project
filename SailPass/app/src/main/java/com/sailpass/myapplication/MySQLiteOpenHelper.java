package com.sailpass.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.sailpass.myapplication.bean.Ticket;

import java.util.ArrayList;
import java.util.List;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME= "mySQLite.db";
    private static final String TABLE_NAME_TICKET = "ticket";

    private static final String CREATE_TABLE_SQL = "create table " + TABLE_NAME_TICKET + " (id integer primary key autoincrement, destination text, departureDate text, arrivalDate text, adultPrice text, childPrice text)";

    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // for designer
    public void insertData() {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values1 = new ContentValues();
        values1.put("destination","Batam, Indonesia");
        values1.put("departureDate","28-04-2022 23:30");
        values1.put("arrivalDate","29-04-2022 09:00");
        values1.put("adultPrice","32");
        values1.put("childPrice","28");

        ContentValues values2 = new ContentValues();
        values2.put("destination","Penang, Malaysia");
        values2.put("departureDate","25-04-2022 22:00");
        values2.put("arrivalDate","26-04-2022 04:00");
        values2.put("adultPrice","24");
        values2.put("childPrice","20");

        ContentValues values3 = new ContentValues();
        values3.put("destination","Moro, Indonesia");
        values3.put("departureDate","20-04-2022 14:00");
        values3.put("arrivalDate","20-04-2022 23:00");
        values3.put("adultPrice","40");
        values3.put("childPrice","36");

        ContentValues values4 = new ContentValues();
        values4.put("destination","Songkhla, Malaysia");
        values4.put("departureDate","23-06-2022 10:00");
        values4.put("arrivalDate","23-06-2022 17:30");
        values4.put("adultPrice","67");
        values4.put("childPrice","59");

        ContentValues values5 = new ContentValues();
        values5.put("destination","Manlia, Philippine");
        values5.put("departureDate","13-08-2022 02:00");
        values5.put("arrivalDate","14-08-2022 08:30");
        values5.put("adultPrice","82");
        values5.put("childPrice","75");

        ContentValues values6 = new ContentValues();
        values6.put("destination","Moro, Indonesia");
        values6.put("departureDate","17-05-2022 14:00");
        values6.put("arrivalDate","17-05-2022 23:00");
        values6.put("adultPrice","45");
        values6.put("childPrice","40");

        ContentValues values7 = new ContentValues();
        values7.put("destination","Batam, Indonesia");
        values7.put("departureDate","12-09-2022 23:30");
        values7.put("arrivalDate","13-09-2022 09:00");
        values7.put("adultPrice","38");
        values7.put("childPrice","32");

        db.insert(TABLE_NAME_TICKET, null, values1);
        db.insert(TABLE_NAME_TICKET, null, values2);
        db.insert(TABLE_NAME_TICKET, null, values3);
        db.insert(TABLE_NAME_TICKET, null, values4);
        db.insert(TABLE_NAME_TICKET, null, values5);
        db.insert(TABLE_NAME_TICKET, null, values6);
        db.insert(TABLE_NAME_TICKET, null, values7);

    }

    // for designer
    public void deleteByDes(String destination){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME_TICKET, "destination like ?", new String[]{destination});
    }

    public void deleteAllData(){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME_TICKET, null, null);

    }


    // for user
    public List<Ticket> queryFromDbByDes(String destination) {

        SQLiteDatabase db = getWritableDatabase();
        List<Ticket> ticketList = new ArrayList<>();


        Cursor cursor = db.query(TABLE_NAME_TICKET, null, "destination like ?", new String[]{destination}, null, null, null);


        if (cursor != null) {

            while (cursor.moveToNext()) {
                String destination1 = cursor.getString(cursor.getColumnIndexOrThrow("destination"));
                String departureDate = cursor.getString(cursor.getColumnIndexOrThrow("departureDate"));
                String arrivalDate = cursor.getString(cursor.getColumnIndexOrThrow("arrivalDate"));
                String adultPrice = cursor.getString(cursor.getColumnIndexOrThrow("adultPrice"));
                String childPrice = cursor.getString(cursor.getColumnIndexOrThrow("childPrice"));

                Ticket ticket = new Ticket();
                ticket.setDestination(destination1);
                ticket.setDepartureDate(departureDate);
                ticket.setArrivalDate(arrivalDate);
                ticket.setAdultPrice(adultPrice);
                ticket.setChildPrice(childPrice);

                ticketList.add(ticket);

            }

            cursor.close();

        }

        return ticketList;

    }


    public List<Ticket> queryFromDbByInfo(){
        SQLiteDatabase db = getWritableDatabase();
        List<Ticket> ticketList = new ArrayList<>();


        Cursor cursor = db.query(TABLE_NAME_TICKET, null, null, null, null, null, null);

        if (cursor != null) {

            while (cursor.moveToNext()) {
                String destination = cursor.getString(cursor.getColumnIndexOrThrow("destination"));
                String departureDate = cursor.getString(cursor.getColumnIndexOrThrow("departureDate"));
                String arrivalDate = cursor.getString(cursor.getColumnIndexOrThrow("arrivalDate"));
                String adultPrice = cursor.getString(cursor.getColumnIndexOrThrow("adultPrice"));
                String childPrice = cursor.getString(cursor.getColumnIndexOrThrow("childPrice"));

                Ticket ticket = new Ticket();
                ticket.setDestination(destination);
                ticket.setDepartureDate(departureDate);
                ticket.setArrivalDate(arrivalDate);
                ticket.setAdultPrice(adultPrice);
                ticket.setChildPrice(childPrice);

                ticketList.add(ticket);

            }
            cursor.close();

        }
        return ticketList;

    }
}
