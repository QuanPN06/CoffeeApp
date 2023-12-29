package quanpnph29471.example.coffeeapp.user.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import quanpnph29471.example.coffeeapp.R;
import quanpnph29471.example.coffeeapp.activity.ItemInforCoffee;
import quanpnph29471.example.coffeeapp.user.cart.Cart;
import quanpnph29471.example.coffeeapp.user.cart.CartDAO;

public class HomeAdapterList extends RecyclerView.Adapter<HomeAdapterList.ViewHolder>  {

    Context context;

    private ArrayList<Home> list;
    private ArrayList<Cart> listCart;
    private HomeDAO homeDAO;
    private CartDAO cartDAO;

    public HomeAdapterList(Context context, ArrayList<Home> list, HomeDAO homeDAO) {
        this.context = context;
        this.list = list;
        this.homeDAO = homeDAO;
        cartDAO = new CartDAO(context);
    }

    public void setData(ArrayList<Home> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_home, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        listCart = cartDAO.getAllData();
        Home home = list.get(position);
        holder.tv_name.setText(list.get(position).getName());
        String img = list.get(position).getImg();
        Picasso.get().load(img).into(holder.iv_img);
        holder.tv_des.setText(home.getDes()+"....");
        holder.tv_price.setText(String.valueOf(list.get(position).getPrice()) );
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ItemInforCoffee.class);
                i.putExtra("coffeeImg", list.get(position).getImg());
                i.putExtra("coffeeName", list.get(position).getName());
                i.putExtra("coffeeDes", list.get(position).getDes());
                i.putExtra("coffeePrice", list.get(position).getPrice());
                i.putExtra("coffeeId",list.get(position).getId());
                context.startActivity(i);
            }
        });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_name, tv_price, tv_des;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_item_food_foodImg);
            tv_name = itemView.findViewById(R.id.tv_item_food_foodName);
            tv_price = itemView.findViewById(R.id.tv_item_food_foodPrice);
            tv_des = itemView.findViewById(R.id.tv_item_food_des);
        }
    }
}
