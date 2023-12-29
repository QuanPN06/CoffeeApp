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
import quanpnph29471.example.coffeeapp.admin.list_history.DaThanhToan_Adapter;
import quanpnph29471.example.coffeeapp.admin.list_history.invoce_DAO;
import quanpnph29471.example.coffeeapp.admin.list_history.invoice;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DaNhan_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DaNhan_Fragment extends Fragment {

    private invoce_DAO dao;
    private ArrayList<invoice> list;

    private DaThanhToan_Adapter adapter;
    RecyclerView recyclerView;
    public DaNhan_Fragment() {

    }

    public static DaNhan_Fragment newInstance(String param1, String param2) {
        DaNhan_Fragment fragment = new DaNhan_Fragment();

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
        return inflater.inflate(R.layout.fragment_da_nhan_, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.invoice_ry_da_nhan);
        dao = new invoce_DAO(getContext());
        list = dao.SeLectDaThanhToan();
        adapter = new DaThanhToan_Adapter(list, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}