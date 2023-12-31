package quanpnph29471.example.coffeeapp.user.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;
import quanpnph29471.example.coffeeapp.R;


public class HomeFragment extends Fragment {
    private  RecyclerView recyclerView;
    private RecyclerView recyclerViewchaynhat;
    HomeDAO homeDAO;
    ArrayList<Home> listHome;
    ArrayList<Home> listHome2;
    HomeAdapter adapter;
    HomeAdapterList homeAdapterList;
    Context context;


    ViewPager viewPager;
    CircleIndicator circleIndicator;
    SlideAdapter slideAdapter;
    ArrayList<Slide> listPhoto;
    Timer timer;

    public HomeFragment() {
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recy_fragment_home_listcoffee);

        recyclerViewchaynhat = view.findViewById(R.id.ry_home_chaynhat);
        ImageButton img_tapsearch = view.findViewById(R.id.btn_fragment_home_tapSearch);
        EditText edSearch = view.findViewById(R.id.ed_fragment_home_search);

        viewPager = view.findViewById(R.id.viewPager);
        circleIndicator = view.findViewById(R.id.circle_indicator);

        listPhoto = getListPhoto();
        slideAdapter = new SlideAdapter(getActivity(), listPhoto);
        viewPager.setAdapter(slideAdapter);
        circleIndicator.setViewPager(viewPager);
        slideAdapter.registerDataSetObserver(circleIndicator.getDataSetObserver());

        img_tapsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edSearch.length()>0){
                    String searchName = edSearch.getText().toString();
                    LinearLayoutManager linearLayoutManager = new GridLayoutManager(getContext(), 1);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    HomeDAO homeDAO1 = new HomeDAO(getContext());
                    listHome = new ArrayList<>();
                    listHome = homeDAO1.Search(searchName);
                    adapter.setData(listHome);
                    recyclerView.setAdapter(adapter);
                }else {
                    reloadData();
                }
            }
        });

        autoSlideShow();
        reloadDatalist();
        reloadData();
    }
    private ArrayList<Slide> getListPhoto(){
        ArrayList<Slide> list = new ArrayList<>();
        list.add(new Slide(R.drawable.banner));
        list.add(new Slide(R.drawable.banner1));
        list.add(new Slide(R.drawable.banner2));
        list.add(new Slide(R.drawable.banner4));
        return list;
    }
    private void autoSlideShow(){
        if (listPhoto == null || listPhoto.isEmpty() || viewPager == null){
            return;
        }
        if (timer == null){
            timer = new Timer();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = viewPager.getCurrentItem();
                        int totalItem = listPhoto.size()-1;
                        if (currentItem < totalItem){
                            currentItem ++;
                            viewPager.setCurrentItem(currentItem);
                        }else {
                            viewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 3000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (timer != null){
            timer.cancel();
            timer = null;
        }
    }

    private void reloadData(){
        homeDAO = new HomeDAO(getContext());
        listHome = homeDAO.getAllData();
        adapter = new HomeAdapter(getContext(),listHome,homeDAO);
        adapter.setData(listHome);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }
    private void reloadDatalist(){
        homeDAO = new HomeDAO(getContext());
        listHome2 = homeDAO.gettop5();

        homeAdapterList = new HomeAdapterList(getContext(), listHome2, homeDAO);
        homeAdapterList.setData(listHome2);

        recyclerViewchaynhat.setAdapter(homeAdapterList);
        recyclerViewchaynhat.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
    }

}