package quanpnph29471.example.coffeeapp.admin.list_history;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import quanpnph29471.example.coffeeapp.data.DbHelper;

public class invoce_DAO {
    DbHelper dbHelper;
    private SQLiteDatabase sqLite;

    public invoce_DAO(Context context) {
        dbHelper = new DbHelper(context);
        sqLite = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public ArrayList<invoice> getDaTaInvoice(String sql, String... SelectAvg) {
        ArrayList<invoice> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice", SelectAvg);
        while (cursor.moveToNext()) {
            invoice i = new invoice();
            i.setId_history(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
            i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
            i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
            i.setName(cursor.getString(cursor.getColumnIndex("user_name")));
            i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
            i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
            i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
            i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
            i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
            list.add(i);
        }
        return list;
    }

    public ArrayList<invoice> getAllData() {
        String sql = "SELECT * FROM tbl_invoice";
        return getDaTaInvoice(sql);
    }

    public long update(invoice i) {
        ContentValues values = new ContentValues();
        values.put("invoice_status", i.getStatus());
        return sqLite.update("tbl_invoice", values, "invoice_id = ?", new String[]{String.valueOf(i.getId_history())});
    }

    public int delete(int ID) {
        return sqLite.delete("tbl_invoice", "invoice_id = ?", new String[]{String.valueOf(ID)});
    }
    @SuppressLint("Range")
    public ArrayList<invoice> SeLectDaDatHang() {
        ArrayList<invoice> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice WHERE invoice_status LIKE '%Đã Đặt Hàng%'  ", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                invoice i = new invoice();
                i.setId_history(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
                i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
                i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
                i.setName(cursor.getString(cursor.getColumnIndex("user_name")));
                i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
                i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
                i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
                i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
                i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
                list.add(i);

            }
            while (cursor.moveToNext());
        }
        return list;
    }
    @SuppressLint("Range")
    public ArrayList<invoice> SeLectDangGiao() {
        ArrayList<invoice> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice WHERE invoice_status LIKE '%Đang Giao%'  ", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                invoice i = new invoice();
                i.setId_history(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
                i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
                i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
                i.setName(cursor.getString(cursor.getColumnIndex("user_name")));
                i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
                i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
                i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
                i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
                i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
                list.add(i);

            }
            while (cursor.moveToNext());
        }
        return list;
    }
    @SuppressLint("Range")
    public ArrayList<invoice> SeLectDaThanhToan() {
        ArrayList<invoice> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice WHERE invoice_status LIKE '%Đã Thanh Toán%'  ", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                invoice i = new invoice();
                i.setId_history(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
                i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
                i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
                i.setName(cursor.getString(cursor.getColumnIndex("user_name")));
                i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
                i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
                i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
                i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
                i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
                list.add(i);

            }
            while (cursor.moveToNext());
        }
        return list;
    }

    @SuppressLint("Range")
    public ArrayList<invoice> SeLecthuyDon() {
        ArrayList<invoice> list = new ArrayList<>();
        Cursor cursor = sqLite.rawQuery("SELECT * FROM tbl_invoice WHERE invoice_status LIKE '%Hủy Đơn%'  ", null);
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            do {
                invoice i = new invoice();
                i.setId_history(Integer.parseInt(cursor.getString(cursor.getColumnIndex("invoice_id"))));
                i.setId_cart(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_id"))));
                i.setPhone(Integer.parseInt(cursor.getString(cursor.getColumnIndex("cart_phone"))));
                i.setName(cursor.getString(cursor.getColumnIndex("user_name")));
                i.setAddress(cursor.getString(cursor.getColumnIndex("cart_address")));
                i.setTime(cursor.getString(cursor.getColumnIndex("invoice_time")));
                i.setContten(cursor.getString(cursor.getColumnIndex("invoice_content")));
                i.setSum(Double.parseDouble(cursor.getString(cursor.getColumnIndex("invoice_sum"))));
                i.setStatus(cursor.getString(cursor.getColumnIndex("invoice_status")));
                list.add(i);

            }
            while (cursor.moveToNext());
        }
        return list;
    }
}
