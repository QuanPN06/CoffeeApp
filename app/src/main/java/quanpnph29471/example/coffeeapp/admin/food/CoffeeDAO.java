package quanpnph29471.example.coffeeapp.admin.food;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import quanpnph29471.example.coffeeapp.data.DbHelper;

public class CoffeeDAO {
    DbHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    public CoffeeDAO(Context contex){
        dbHelper = new DbHelper(contex);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    @SuppressLint("Range")
    public ArrayList<Coffee> getData(String sql, String... SelectAvg){
        ArrayList<Coffee> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_coffee", SelectAvg);
        while (cursor.moveToNext()){
            Coffee food = new Coffee();
            food.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("coffee_id"))));
            food.setImg(cursor.getString(cursor.getColumnIndex("coffee_img")));
            food.setName(cursor.getString(cursor.getColumnIndex("coffee_name")));
            food.setDes(cursor.getString(cursor.getColumnIndex("coffee_description")));
            food.setPrice(cursor.getInt(cursor.getColumnIndex("coffee_price")));
            list.add(food);
        }
        return list;
    }
    public long insert(Coffee coffee){
        ContentValues values = new ContentValues();
        values.put("coffee_img", coffee.getImg());
        values.put("coffee_name", coffee.getName());
        values.put("coffee_description", coffee.getDes());
        values.put("coffee_price", coffee.getPrice());
        return sqLiteDatabase.insert("tbl_coffee", null, values);
    }
    public ArrayList<Coffee> getAllData(){
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
    public ArrayList<Coffee> Search(String ten) {
        SQLiteDatabase sqLite = dbHelper.getWritableDatabase();
        ArrayList<Coffee> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_coffee WHERE coffee_name LIKE '%"+ ten +"%' ", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                Coffee coffee = new Coffee();
                coffee.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("coffee_id"))));
                coffee.setImg(cursor.getString(cursor.getColumnIndex("coffee_img")));
                coffee.setName(cursor.getString(cursor.getColumnIndex("coffee_name")));
                coffee.setDes(cursor.getString(cursor.getColumnIndex("coffee_description")));
                coffee.setPrice(Integer.parseInt(cursor.getString(cursor.getColumnIndex("coffee_price"))));
                list.add(coffee);

            }
            while (cursor.moveToNext());
        }
        return list;
    }
    public long update(Coffee coffee){
        ContentValues values = new ContentValues();

        values.put("coffee_img", coffee.getImg());
        values.put("coffee_name", coffee.getName());
        values.put("coffee_description", coffee.getDes());
        values.put("coffee_price", coffee.getPrice());
        return sqLiteDatabase.update("tbl_coffee", values, "coffee_id = ?", new String[]{String.valueOf(coffee.getId())});
    }
    public int delete(int ID) {
        return sqLiteDatabase.delete("tbl_coffee", "coffee_id = ?", new String[]{String.valueOf(ID)});
    }
    public Coffee getById(int id) {
        Cursor cursor = sqLiteDatabase.query("tbl_coffee", null,"coffee_id = ?",
        new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.moveToNext()){
            return new Coffee(cursor.getInt(0), cursor.getString(1),cursor.getString(2), cursor.getString(3), cursor.getInt(4));
        }else {
            return null;
        }
    }

}
