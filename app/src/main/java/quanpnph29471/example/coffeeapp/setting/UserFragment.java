package quanpnph29471.example.coffeeapp.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import quanpnph29471.example.coffeeapp.R;
import quanpnph29471.example.coffeeapp.activity.ChangePassActivity;
import quanpnph29471.example.coffeeapp.activity.LoginActivity;


public class UserFragment extends Fragment {
//    private static final int REQUEST_IMAGE_PICK = 1;

    TextView tv_userName, tv_changePass, tv_exit, tv_logout, tvUserName;
    ImageView imgcout;

    User user;

    public UserFragment() {
        // Required empty public constructor
    }


    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user = new User();
        tv_changePass = view.findViewById(R.id.tv_changePass);
        tv_exit = view.findViewById(R.id.tv_exit);
        tv_logout = view.findViewById(R.id.tv_logout);
        tvUserName = view.findViewById(R.id.tv_user_userName);
//        tvthemanh = view.findViewById(R.id.tvthemanh);
        imgcout = view.findViewById(R.id.imgcout);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("USERNAME", "");
        tvUserName.setText(loggedInUserName);

        tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        tv_changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ChangePassActivity.class);
                startActivity(i);


            }
        });
//        tvthemanh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dialog dialog = new Dialog(getContext());
//                dialog.setContentView(R.layout.dialog_themanh_user);
//
//                EditText link = dialog.findViewById(R.id.link_anh_user);
//                Button thoat = dialog.findViewById(R.id.btn_dialog_user_thoat);
//
//                thoat.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
//
//                Button themAnh = dialog.findViewById(R.id.btn_dialog_user_themAnh);
//                themAnh.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String linkAnh = link.getText().toString();
//                        Picasso.get().load(linkAnh).into((Target) link);
//                        User user1 = new User();
//                        UserDAO dao = new UserDAO(getContext());
//                        user1.setUser_img(linkAnh);
//                        if (dao.insert(user1) >= 0) {
//                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_LONG).show();
//
//                            dialog.dismiss();
//                        } else {
//                            Toast.makeText(getContext(), "Thêm thất bại!", Toast.LENGTH_LONG).show();
//                        }
//                        dialog.dismiss();
//                    }
//                });
//
//                dialog.show();
//                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.getWindow().getAttributes().windowAnimations = R.style.dialogAnimation;
//                dialog.getWindow().setGravity(Gravity.BOTTOM);
//
//            }
//        });

    }


}