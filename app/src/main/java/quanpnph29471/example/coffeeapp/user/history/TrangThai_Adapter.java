package quanpnph29471.example.coffeeapp.user.history;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import quanpnph29471.example.coffeeapp.R;
import quanpnph29471.example.coffeeapp.admin.list_history.invoice;


public class TrangThai_Adapter extends RecyclerView.Adapter<TrangThai_Adapter.ViewHolder>{

    private ArrayList<invoice> list;

    private Context context;


    private History_DAO history_dao;

    public TrangThai_Adapter(ArrayList<invoice> list, Context context) {
        this.list = list;
        this.context = context;

    }

    public void setData(ArrayList<invoice> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_userchoxn, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        invoice historyModel;
        history_dao = new History_DAO(context);
        historyModel = list.get(position);
        holder.id_cart.setText(String.valueOf(historyModel.getId_history()));
        holder.phone.setText(String.valueOf(historyModel.getPhone()));
        holder.name.setText(historyModel.getName());
        holder.address.setText(historyModel.getAddress());
        holder.time.setText(historyModel.getTime());
        holder.sum.setText(String.valueOf(historyModel.getSum()));
        holder.content.setText(historyModel.getContten());
        holder.status.setText(historyModel.getStatus());
        holder.status.setText("Đơn Hàng Đang Giao");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_cart, phone, name, address, sum, time, status, content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_cart = itemView.findViewById(R.id.id_cart);
            phone = itemView.findViewById(R.id.id_phone);
            name = itemView.findViewById(R.id.id_hoten);
            address = itemView.findViewById(R.id.id_address);
            sum = itemView.findViewById(R.id.id_sum);
            time = itemView.findViewById(R.id.id_time);
            status = itemView.findViewById(R.id.status);
            content = itemView.findViewById(R.id.id_noidung);

        }
    }
}
