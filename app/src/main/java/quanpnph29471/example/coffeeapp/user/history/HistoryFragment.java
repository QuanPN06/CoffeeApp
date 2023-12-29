package quanpnph29471.example.coffeeapp.user.history;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

import quanpnph29471.example.coffeeapp.R;


public class HistoryFragment extends Fragment {
    private History_DAO dao;
    private ArrayList<History_model> list;

    private History_Adapter adapter;
    RecyclerView recyclerView;

    TabLayout tab_layout_history;
    ViewPager2 vp_history;
    private Adaprte_History adaprteHistory;

    public HistoryFragment() {
        // Required empty public constructor
    }


    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tab_layout_history = view.findViewById(R.id.tab_layout_history);
        vp_history = view.findViewById(R.id.vp_history);
        adaprteHistory = new Adaprte_History(getActivity());
        vp_history.setAdapter(adaprteHistory);
        new TabLayoutMediator(tab_layout_history, vp_history, ((tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Chờ Xác Nhận");
                    break;
                case 1:
                    tab.setText("Trạng Thái");
                    break;
                case 2:
                    tab.setText("Các Đơn Hàng Đã Nhận");

                    break;
            }
        })).attach();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//
        return inflater.inflate(R.layout.fragment_history, container, false);
//        View view = inflater.inflate(R.layout.fragment_don_hang_, container, false);
//        recyclerView =view.findViewById(R.id.choXN_ry);
//        reloadData();
//        return ;
    }

    public void reloadData(){
        dao = new History_DAO(getActivity());
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String loggedInUserName = sharedPreferences.getString("USERNAME", "");
        list = dao.getByUser(loggedInUserName);
        list = dao.getAllData();
        adapter = new History_Adapter(list, getContext(), dao);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
    }
}