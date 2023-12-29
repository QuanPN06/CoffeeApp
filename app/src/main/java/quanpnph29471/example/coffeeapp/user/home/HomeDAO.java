package quanpnph29471.example.coffeeapp.user.home;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Collections;

import quanpnph29471.example.coffeeapp.data.DbHelper;

public class HomeDAO {
    DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    public HomeDAO(Context contex){
        dbHelper = new DbHelper(contex);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<Home> getData(String sql, String... SelectAvg){
        ArrayList<Home> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_coffee", SelectAvg);
        while (cursor.moveToNext()){
            Home home = new Home();
            home.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("coffee_id"))));
            home.setImg(cursor.getString(cursor.getColumnIndex("coffee_img")));
            home.setName(cursor.getString(cursor.getColumnIndex("coffee_name")));
            home.setDes(cursor.getString(cursor.getColumnIndex("coffee_description")));
            home.setPrice(cursor.getInt(cursor.getColumnIndex("coffee_price")));
            list.add(home);
        }
        return list;
    }
    public long insert(Home home){
        ContentValues values = new ContentValues();
        values.put("coffee_img", home.getImg());
        values.put("coffee_name", home.getName());
        values.put("coffee_description", home.getDes());
        values.put("coffee_price", home.getPrice());
        return sqLiteDatabase.insert("tbl_coffee", null, values);
    }
    public ArrayList<Home> getAllData(){
        String sql = "SELECT * FROM tbl_coffee";
        return getData(sql);
    }
    public ArrayList<String> name() {
        String name = "SELECT coffee_name FROM tbl_coffee";
        return getName(name);
    }
    public ArrayList<String> getName(String sql, String... SelectAvgs) {
        ArrayList<String> lst = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, SelectAvgs);
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("coffee_name"));
            lst.add(name);
        }
        return lst;

    }
    @SuppressLint("Range")
    public ArrayList<Home> Search(String ten) {
        SQLiteDatabase sqLite = dbHelper.getWritableDatabase();
        ArrayList<Home> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_coffee WHERE coffee_name LIKE '%"+ ten +"%' ", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                Home home = new Home();
                home.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("coffee_id"))));
                home.setImg(cursor.getString(cursor.getColumnIndex("coffee_img")));
                home.setName(cursor.getString(cursor.getColumnIndex("coffee_name")));
                home.setDes(cursor.getString(cursor.getColumnIndex("coffee_description")));
                home.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex("coffee_price"))));
                list.add(home);
            }
            while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<Home> gettop5(){
        String sql = "SELECT * FROM tbl_coffee ORDER BY coffee_id DESC LIMIT 5";
        ArrayList<Home> resultList = getData(sql);

        Collections.reverse(resultList);

        return resultList;
    }


}
