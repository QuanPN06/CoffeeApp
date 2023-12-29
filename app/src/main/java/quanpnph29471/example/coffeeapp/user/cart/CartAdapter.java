package quanpnph29471.example.coffeeapp.user.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import quanpnph29471.example.coffeeapp.R;
import quanpnph29471.example.coffeeapp.admin.food.Coffee;
import quanpnph29471.example.coffeeapp.admin.food.CoffeeDAO;
import quanpnph29471.example.coffeeapp.setting.User;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Cart> list;
    private ArrayList<User> users;
    private CartDAO cartDao;
    private CoffeeDAO foodDao;

    private OnQuantityUpClickListener quantityUpClickListener;
    private OnQuantityDownClickListener quantityDownClickListener;

    public CartAdapter(Context context, ArrayList<Cart> list, CartDAO cartDao) {
        this.context = context;
        this.list = list;
        this.cartDao = cartDao;
        this.foodDao = new CoffeeDAO(context);
    }

    public void setData(ArrayList<Cart> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setOnQuantityUpClickListener(OnQuantityUpClickListener listener) {
        this.quantityUpClickListener = listener;
    }

    public void setOnQuantityDownClickListener(OnQuantityDownClickListener listener) {
        this.quantityDownClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Cart cart = list.get(position);
        Coffee food = foodDao.getById(cart.getIdFood());

        if (food != null) {
            holder.tv_name.setText(food.getName());
            Picasso.get().load(food.getImg()).into(holder.iv_img);

            holder.tv_price.setText(String.valueOf(food.getPrice()));
        }

//        holder.tv_price.setText(String.valueOf(food.getPrice() * cart.getQuanti())+" VND");
        holder.tv_price.setText(String.valueOf(food.getPrice() * cart.getQuanti())+" VND");
        holder.tv_quanti.setText(String.valueOf(cart.getQuanti()));

        holder.btn_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quanti = cart.getQuanti() + 1;
                holder.tv_quanti.setText(String.valueOf(quanti));
                holder.tv_price.setText(String.valueOf(food.getPrice()) +" đ");
                cart.setQuanti(quanti);
                cart.setSum(food.getPrice() * quanti);
                cartDao.updateSum(cart);

                if (quantityUpClickListener != null) {
                    quantityUpClickListener.onQuantityUpClick(holder.getAdapterPosition());
                }
            }
        });

        holder.btn_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quanti = cart.getQuanti();

                if (quanti > 1) {
                    quanti -= 1;
                    holder.tv_quanti.setText(String.valueOf(quanti));
                    holder.tv_price.setText(String.valueOf(food.getPrice() )+" đ");
                    cart.setQuanti(quanti);
                    cart.setSum(food.getPrice() * quanti);
                    cartDao.updateSum(cart);

                    if (quantityDownClickListener != null) {
                        quantityDownClickListener.onQuantityDownClick(holder.getAdapterPosition());
                    }
                } else {
                    Toast.makeText(context, "Số lượng không thể nhỏ hơn 1", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_name, tv_des, tv_price, tv_quanti;
        ImageView btn_up, btn_down,btn_del;
        CardView layout_foreground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_img = itemView.findViewById(R.id.iv_item_cart_foodImg);
            tv_name = itemView.findViewById(R.id.tv_item_cart_foodName);
//            tv_des = itemView.findViewById(R.id.tv_item_cart_foodContent);
            tv_price = itemView.findViewById(R.id.tv_item_cart_foodPrice);
            tv_quanti = itemView.findViewById(R.id.tv_item_cart_quantity);
            btn_up = itemView.findViewById(R.id.btn_item_cart_quantity_up);
            btn_down = itemView.findViewById(R.id.btn_item_cart_quantity_down);
            btn_del=itemView.findViewById(R.id.btn_item_cart_delete);
            layout_foreground=itemView.findViewById(R.id.layout_foreground);

        }
    }

    public interface OnQuantityUpClickListener {
        void onQuantityUpClick(int position);
    }

    public interface OnQuantityDownClickListener {
        void onQuantityDownClick(int position);
    }


}
