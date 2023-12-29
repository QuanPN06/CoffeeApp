package quanpnph29471.example.coffeeapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import quanpnph29471.example.coffeeapp.R;
import quanpnph29471.example.coffeeapp.setting.User;
import quanpnph29471.example.coffeeapp.setting.UserDAO;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername_regis,edUserpass_regis,edUserRePass;
    Button btn_register,cancle;
    UserDAO dao;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername_regis=findViewById(R.id.edUsername_regis);
        edUserpass_regis=findViewById(R.id.edUserpass_regis);
        edUserRePass=findViewById(R.id.edUserRePass);
        btn_register=findViewById(R.id.btn_Register);
        cancle = findViewById(R.id.btn_dangky_canle);
        dao=new UserDAO(this);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=new User();
                user.setUser_name(edUsername_regis.getText().toString());
                user.setUser_pass(edUserpass_regis.getText().toString());
                user.setUser_role("user");
                if(validate()>0){
                    if(dao.insert(user)>0){
                        Toast.makeText(getApplicationContext(), "thêm tài khoản thành công", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                        finish();
                        startActivity(i);
                    }else {
                        Toast.makeText(getApplicationContext(), "them that bai", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
    public int validate(){

        String tk = edUsername_regis.getText().toString();
        String mk = edUserpass_regis.getText().toString();
        String checkmk = edUserRePass.getText().toString();
        int check=1;
        if(tk.length()==0){
            edUsername_regis.requestFocus();
            edUsername_regis.setError("Vui lòng nhập tên dăng nhập");
            check=-1;
        } else if (mk.length()==0) {
            edUserpass_regis.requestFocus();
            edUserpass_regis.setError("Vui lòng nhập mật khẩu");
            check=-1;
        } else{
            String pass=edUserpass_regis.getText().toString();
            String repass=edUserRePass.getText().toString();
            if(!pass.equals(repass)){
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                edUserRePass.requestFocus();
                edUserRePass.setError("vui lòng nhập đầy đủ thông tin");
                check=-1;
            }else{
                check=1;
            }

        }
        return check;
    }
}