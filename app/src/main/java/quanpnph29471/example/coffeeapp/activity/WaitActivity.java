package quanpnph29471.example.coffeeapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import quanpnph29471.example.coffeeapp.R;

public class WaitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        ImageView imgWait = findViewById(R.id.imgWait);
        TextView tvWait = findViewById(R.id.tv_wait);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.hienthi);
        imgWait.startAnimation(animation);
        tvWait.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }
}