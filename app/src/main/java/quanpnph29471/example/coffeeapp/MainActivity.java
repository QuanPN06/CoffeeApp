package quanpnph29471.example.coffeeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationBarView;

import quanpnph29471.example.coffeeapp.admin.food.CoffeeFragment;
import quanpnph29471.example.coffeeapp.admin.list_history.Quanly_StatusFragment;
import quanpnph29471.example.coffeeapp.admin.list_request.ListRequestFragment;
import quanpnph29471.example.coffeeapp.admin.statis.StatisFragment;
import quanpnph29471.example.coffeeapp.setting.UserFragment;
import quanpnph29471.example.coffeeapp.user.cart.Cart_Fragment;
import quanpnph29471.example.coffeeapp.user.history.HistoryFragment;
import quanpnph29471.example.coffeeapp.user.home.Home;
import quanpnph29471.example.coffeeapp.user.home.HomeFragment;
import quanpnph29471.example.coffeeapp.user.request.RequestFragment;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private Home home;
    private Cart_Fragment cart_fragment;
    NavigationBarView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cart_fragment = new Cart_Fragment();
        // hàm lấy dữ liều từ activity thongtinsp;

        view = findViewById(R.id.bottom_navi);
        sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String role = sharedPreferences.getString("ROLE", "");
        if (role.equalsIgnoreCase("admin")) {
            view.getMenu().clear();
            view.inflateMenu(R.menu.bottom_navigation_menu_admin);
            replaceFragment(new CoffeeFragment());
        } else {
            view.getMenu().clear();
            view.inflateMenu(R.menu.bottom_navigation_menu_user);
            replaceFragment(new HomeFragment());
        }
        view.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_Home) {
                    replaceFragment(new HomeFragment());
                    return true;

                } else if (item.getItemId() == R.id.action_Cart) {
                    replaceFragment(cart_fragment);
                    return true;

                } else if (item.getItemId() == R.id.action_Request) {
                    replaceFragment(new RequestFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_History) {
                    replaceFragment(new HistoryFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_User) {
                    replaceFragment(new UserFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_ListFood) {
                    replaceFragment(new CoffeeFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_ListRequestt) {
                    replaceFragment(new ListRequestFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_List_Invo) {
                    replaceFragment(new Quanly_StatusFragment());
                    return true;
                } else if (item.getItemId() == R.id.action_Statis) {
                    replaceFragment(new StatisFragment());
                    return true;
                } else {
                    return false;
                }
            }
        });
        laydulieu();


    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.commit();
    }

    public void laydulieu() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            home = (Home) bundle.getSerializable("data");

            cart_fragment.nhanData(home);

            replaceFragment(cart_fragment);
            view.setSelectedItemId(R.id.action_Cart);
        }

    }


}