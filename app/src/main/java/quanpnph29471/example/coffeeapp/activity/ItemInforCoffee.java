package quanpnph29471.example.coffeeapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import quanpnph29471.example.coffeeapp.MainActivity;
import quanpnph29471.example.coffeeapp.R;
import quanpnph29471.example.coffeeapp.user.cart.Cart;
import quanpnph29471.example.coffeeapp.user.cart.CartDAO;
import quanpnph29471.example.coffeeapp.user.home.Home;


public class ItemInforCoffee extends AppCompatActivity {
    ArrayList<Home> list;
    Context context = this;

    Button btn_add;
    ImageButton btn_back, btn_next;


    private Home home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_infor_food);

        btn_back = findViewById(R.id.btn_infor_food_back);
        btn_next = findViewById(R.id.btn_infor_food_next);
        btn_add = findViewById(R.id.btn_add_cart);

        ImageView iv_image = findViewById(R.id.iv_infor_food_img);
        TextView tv_name = findViewById(R.id.tv_infor_food_name);
        TextView tv_content = findViewById(R.id.tv_infor_food_content);
        TextView tv_price = findViewById(R.id.tv_infor_food_price);


        home = new Home();
        int id_coffee = getIntent().getIntExtra("coffeeId", 0);
        String dataImage = getIntent().getStringExtra("coffeeImg");
        String dataName = getIntent().getStringExtra("coffeeName");
        String dataContent = getIntent().getStringExtra("coffeeDes");
        int dataPrice = getIntent().getIntExtra("coffeePrice", 0);

        home.setId(id_coffee);
        home.setImg(dataImage);
        home.setName(dataName);
        home.setDes(dataContent);
        home.setPrice(dataPrice);


        Picasso.get().load(dataImage).into(iv_image);
        tv_name.setText(dataName);
        tv_content.setText(dataContent);
        tv_price.setText(String.valueOf(dataPrice) + " đ");


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);

                Bundle bundle = new Bundle();
                System.out.println(home);
                bundle.putSerializable("data", home);
                intent.putExtras(bundle);
                startActivity(intent);



            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CartDAO cartDAO = new CartDAO(getApplicationContext());
                Cart cart = new Cart();
                cart.setIdFood(id_coffee);
                cart.setQuanti(1);
                cart.setSum(dataPrice);
                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String loggedInUserName = sharedPreferences.getString("USERNAME", "");
                cart.setUsername(loggedInUserName);
                if (!cartDAO.isFoodExists(cart.getIdFood(), cart.getUsername())) {
                    if (cartDAO.insert(cart) > 0) {
                        Toast.makeText(ItemInforCoffee.this, "Đã Thêm Vào Giỏ Hàng", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(ItemInforCoffee.this, "Thêm Vào Giỏ Hàng bị lỗi", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ItemInforCoffee.this, "Sản phẩm đã được chọn", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }


}