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

public class TrangThai_Fragment extends Fragment {
    private invoce_DAO dao;
    private ArrayList<invoice> list;

    private TrangThai_Adapter adapter;
    RecyclerView recyclerView;


    public TrangThai_Fragment() {
        // Required empty public constructor
    }

    public static TrangThai_Fragment newInstance(String param1, String param2) {
        TrangThai_Fragment fragment = new TrangThai_Fragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView =view.findViewById(R.id.invoice_ry_trang_thai);
    }
    @Override
    public void onResume() {
        super.onResume();
        invoce_DAO dao = new invoce_DAO(getContext());
        list = dao.SeLectDangGiao();
        adapter = new TrangThai_Adapter(list, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_trang_thai_, container, false);
    }
}