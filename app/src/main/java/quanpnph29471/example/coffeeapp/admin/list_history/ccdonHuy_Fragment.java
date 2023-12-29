package quanpnph29471.example.coffeeapp.admin.list_history;

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


public class ccdonHuy_Fragment extends Fragment {
    private invoce_DAO dao;
    private ArrayList<invoice> list;

    private HuyDon_Adapter adapter;
    RecyclerView recyclerView;

    public ccdonHuy_Fragment() {
        // Required empty public constructor
    }


    public static ccdonHuy_Fragment newInstance(String param1, String param2) {
        ccdonHuy_Fragment fragment = new ccdonHuy_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.invoice_ry_da_huy_don);
        dao = new invoce_DAO(getContext());
        list = dao.SeLecthuyDon();
        adapter = new HuyDon_Adapter(list, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ccdon_huy_, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();
        list=dao.SeLecthuyDon();
        adapter.setData(list);
    }
}