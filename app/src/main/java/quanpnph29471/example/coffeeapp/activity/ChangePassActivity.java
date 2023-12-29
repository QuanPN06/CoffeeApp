package quanpnph29471.example.coffeeapp.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import quanpnph29471.example.coffeeapp.R;
import quanpnph29471.example.coffeeapp.setting.User;
import quanpnph29471.example.coffeeapp.setting.UserDAO;

public class ChangePassActivity extends AppCompatActivity {
    EditText edPassOld, edPass, edRePass;
    Button btn_update, btn_back;
    UserDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        edPassOld=findViewById(R.id.edOldPass);
        edPass=findViewById(R.id.edNewPass);
        edRePass=findViewById(R.id.edReNewPass);
        btn_update=findViewById(R.id.btn_changePass);
        btn_back =findViewById(R.id.btn_changePass_cancel);
        dao=new UserDAO(this);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref=getApplication().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user=pref.getString("USERNAME","");
                if(validate()>0){
                    User user1=dao.getByID(user);
                    user1.setUser_pass(edPass.getText().toString());
                    if(dao.update(user1)>0){
                        Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPass.setText("");
                        edPassOld.setText("");
                        edRePass.setText("");
//
                    }else{
                        edRePass.requestFocus();
                        edRePass.setError("Mật khẩu không khớp");

                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Thay doi mat khau that bai", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    public int validate(){

        String passcu = edPassOld.getText().toString();
        String passmoi = edPass.getText().toString();
        String passmoimoi = edRePass.getText().toString();

        int check=1;
        if(passcu.length()==0){
            edPassOld.requestFocus();
            edPassOld.setError("Vui lòng nhập mật khẩu cũ");
            check=-1;
        } else if (passmoi.length()==0){
            edPass.requestFocus();
            edPass.setError("Vui lòng nhập mật khẩu mới");
            check=-1;
        }else if (passmoimoi.length()==0){
            edRePass.requestFocus();
            edRePass.setError("Vui lòng nhập lại mật khẩu mới");
            check=-1;
        }
        else {
            SharedPreferences pref=getApplication().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld=pref.getString("PASSWORD","");
            String pass=edPassOld.getText().toString();
            String passN=edPass.getText().toString();
            String repass=edRePass.getText().toString();
            if (!passOld.equals(pass)){
                edPass.requestFocus();
                edPass.setError("Sai mật  khẩu");
                check=-1;
            }
            if(!passN.equals(repass)){

                edRePass.setError("Mật Khẩu không khớp");
                check=-1;}
        }
        return check;
    }
}