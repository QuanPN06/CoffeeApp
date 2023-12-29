package quanpnph29471.example.coffeeapp.user.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import quanpnph29471.example.coffeeapp.R;
import quanpnph29471.example.coffeeapp.admin.list_history.invoce_DAO;
import quanpnph29471.example.coffeeapp.admin.list_history.invoice;


public class ChoXN_Fragment extends Fragment {
    private ArrayList<invoice> list;

    choXN_Adapter adapter;
    RecyclerView recyclerView;
    invoce_DAO dao;


    public ChoXN_Fragment() {
        // Required empty public constructor
    }


    public static ChoXN_Fragment newInstance(String param1, String param2) {
        ChoXN_Fragment fragment = new ChoXN_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_don_hang_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.choXN_ry);
        invoce_DAO dao = new invoce_DAO(getContext());
        list = dao.SeLectDaDatHang();
        adapter = new choXN_Adapter(list, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        dao = new invoce_DAO(getContext());
//        list = dao.SeLectDaDatHang();
//        adapter = new choXN_Adapter(list, getContext());
//        adapter.setData(list);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//    }

}