package quanpnph29471.example.coffeeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ConfirmCart extends AppCompatActivity {
    TextView tvAddrs;
    ImageView ivBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_cart);
        tvAddrs = findViewById(R.id.tv_confirm_address);
        ivBack = findViewById(R.id.iv_confirm_back);


        String dataAddress = getIntent().getStringExtra("address");

        tvAddrs.setText(dataAddress);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}