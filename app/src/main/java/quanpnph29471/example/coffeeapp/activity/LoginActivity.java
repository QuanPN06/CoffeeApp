package quanpnph29471.example.coffeeapp.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import quanpnph29471.example.coffeeapp.MainActivity;
import quanpnph29471.example.coffeeapp.R;
import quanpnph29471.example.coffeeapp.setting.UserDAO;

public class LoginActivity extends AppCompatActivity {
    TextView tv_Register;
    EditText edUsername, edPassword;
    Button btnLogin;
    UserDAO dao;
    String strUser, strPass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUsername=findViewById(R.id.edUsername);
        edPassword=findViewById(R.id.edPassword);
        btnLogin=findViewById(R.id.btn_login);
        checkAutoLogin();
        dao=new UserDAO(this);
        tv_Register=findViewById(R.id.tv_Register);

        tv_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

    }
    public void checkLogin() {
        strUser = edUsername.getText().toString();
        strPass = edPassword.getText().toString();
        if (strUser.length() == 0) {
            edUsername.requestFocus();
            edUsername.setError("Vui lòng nhập tên đăng nhập");
        } else if (strPass.length() == 0) {
            edPassword.requestFocus();
            edPassword.setError("Vui lòng nhập mật khẩu");
        }else {
            if (dao.checkLogin(strUser, strPass) > 0) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                rememberUser(strUser, strPass,dao.getRole(strUser));
                finish();
            } else {
                showLoginFailedDialog();
            }

        }
    }
    private void checkAutoLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "");
        String password = sharedPreferences.getString("PASSWORD", "");

        if (!username.isEmpty() && !password.isEmpty()) {
            edUsername.setText(username);
            edPassword.setText(password);
        }
    }

    private void showLoginFailedDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Đăng nhập không thành công bạn cần đăng ký sau đó đăng nhập lại");
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void rememberUser(String u, String p,String r) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("USERNAME", u);
        edit.putString("PASSWORD", p);
        edit.putString("ROLE",r);
        edit.commit();
    }


}