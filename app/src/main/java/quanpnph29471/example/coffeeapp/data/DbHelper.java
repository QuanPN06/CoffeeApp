package quanpnph29471.example.coffeeapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "data";
    private static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static final String TABLE_USER_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_user (" +
            "user_name TEXT PRIMARY KEY," +
            "user_pass TEXT NOT NULL," +
            "user_role TEXT," +
            "user_img TEXT" +
            ")";
    public static final String insert_admin = "Insert into tbl_user(user_name,user_pass,user_role) values" +
                "('admin','123','admin'), ('quan','123','quan')";
    public static final String TABLE_REQUEST_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_request (" +
            "request_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "request_name TEXT ," +
            "request_email TEXT ," +
            "request_phone TEXT ," +
            "request_content TEXT " +
            ")";

    public static final String TABLE_FOOD_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_coffee (" +
            "coffee_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "coffee_img TEXT NOT NULL, " +
            "coffee_name TEXT NOT NULL, " +
            "coffee_description TEXT NOT NULL, " +
            "coffee_price DOUBLE NOT NULL" +
            ")";

    public static final String TABLE_CART_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_cart (" +
            "cart_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "coffee_id INTEGER REFERENCES tbl_coffee(coffee_id), " +
            "user_name TEXT REFERENCES tbl_user(user_name)," +
            "cart_quantity INTEGER NOT NULL, " +
            "cart_sum DOUBLE NOT NULL" +
            ")";

    public static final String TABLE_INVOICE_CREATE = "CREATE TABLE IF NOT EXISTS " +
            "tbl_invoice (" +
            "invoice_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "user_name TEXT REFERENCES tbl_user(user_name)," +
            "cart_id INTEGER REFERENCES tbl_cart(cart_id), " +
            "cart_phone TEXT NOT NULL, " +
            "cart_address TEXT NOT NULL, " +
            "invoice_content TEXT NOT NULL, " +
            "invoice_sum DOUBLE NOT NULL, " +
            "invoice_status TEXT ," +
            "invoice_time TEXT NOT NULL" +
            ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_CREATE);

        db.execSQL(TABLE_FOOD_CREATE);

        db.execSQL(TABLE_CART_CREATE);

        db.execSQL(TABLE_REQUEST_CREATE);

        db.execSQL(TABLE_INVOICE_CREATE);




        db.execSQL("INSERT INTO tbl_coffee(coffee_img,coffee_name,coffee_description,coffee_price) VALUES (" +
                "'https://bonngamchan.vn/wp-content/uploads/2017/11/ca-phe-den-da.jpg'" +
                ", 'Coffee Đen Đá'," +
                " 'Cà phê Đen thường được phục vụ thêm đường bên cạnh, tuy nhiên theo cánh cà phê thủ phải uống Đen đá không đường mới chứng tỏ được bản lĩnh đàn ông.'" +
                ",80000), " +


                "('https://img.thuthuatphanmem.vn/uploads/2018/10/04/hinh-anh-ly-cafe-sua-dep_110731689.jpg'" +
                ", 'Coffee Đen Đá'," +
                " 'Cà phê Đen thường được phục vụ thêm đường bên cạnh, tuy nhiên theo cánh cà phê thủ phải uống Đen đá không đường mới chứng tỏ được bản lĩnh đàn ông.'" +
                ",80000), " +

                "('https://i1.wp.com/etramping.com/wp-content/uploads/2013/12/unnamed-45.jpg?resize=640%2C479&ssl=1'" +
                ", 'Coffee Đen Đá'," +
                " 'Cà phê Đen thường được phục vụ thêm đường bên cạnh, tuy nhiên theo cánh cà phê thủ phải uống Đen đá không đường mới chứng tỏ được bản lĩnh đàn ông.'" +
                ",50000), " +

                "('https://vhealthcoffee.com.vn/uploads/5.jpeg', " +
                "'Bạc Xỉu', " +
                "' Bạc Xỉu đơn giản mà Bonjour Coffee gợi ý chắc chắn bạn sẽ không quá khó để có thể tự thưởng cho mình một ly thức uống thơm ngon tại nhà hay bổ sung vào thực đơn quán cafe của mình. .'," +
                " 30000), " +

                "('https://massageishealthy.com/wp-content/uploads/2017/09/cach-lam-cafe-trung-caphe-giang-ha-noi-ngon-1.jpg', " +
                "'Coffee trứng', " +
                "' Bạc Xỉu đơn giản mà Bonjour Coffee gợi ý chắc chắn bạn sẽ không quá khó để có thể tự thưởng cho mình một ly thức uống thơm ngon tại nhà hay bổ sung vào thực đơn quán cafe của mình. .'," +
                " 60000), " +

                "('https://th.bing.com/th/id/OIP.unfBxgHLvH-U7GnPWVODVgHaE6?pid=ImgDet&rs=1', " +
                "'Cà Phê Phin', " +
                "' Đậm đà nguyên chất. .'," +
                " 65000), " +

                "('https://th.bing.com/th/id/OIP.kLuGtC6wef1lqozocKOIsQHaE7?pid=ImgDet&rs=1', " +
                "'Coffee Espresso', " +
                "'Coffee Espresso Đặc biệt. .'," +
                " 40000), " +

                "('https://th.bing.com/th/id/OIP.yAIG08E-XBa5CDGZG5M_dwHaEK?pid=ImgDet&rs=1'," +
                " 'Coffee Trung nguyên', " +
                "'100% nguyên chất không đường không thêm phụ gia khác đảm bảo mang đến cho quý khách hàng cảm giác tuyệt vời.', " +
                "50000),"+

                "('https://media.cnn.com/api/v1/images/stellar/prod/150929101049-black-coffee-stock.jpg?q=x_3,y_1231,h_1684,w_2993,c_crop/h_720,w_1280'," +
                " 'Coffee ', " +
                "'100% nguyên chất không đường không thêm phụ gia khác đảm bảo mang đến cho quý khách hàng cảm giác tuyệt vời.', " +
                "50000)");

        db.execSQL(insert_admin);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS tbl_usser");
            db.execSQL("DROP TABLE IF EXISTS tbl_request");
            db.execSQL("DROP TABLE IF EXISTS tbl_coffee");
            db.execSQL("DROP TABLE IF EXISTS tbl_cart");
            db.execSQL("DROP TABLE IF EXISTS tbl_invoice");
            onCreate(db);
        }
    }
}
