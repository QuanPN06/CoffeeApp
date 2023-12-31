package quanpnph29471.example.coffeeapp.admin.list_history;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import quanpnph29471.example.coffeeapp.R;


public class Invoice_Adapter extends RecyclerView.Adapter<Invoice_Adapter.ViewHolder> {
    private ArrayList<invoice> list;
    private Context context;


    private invoce_DAO invoce_dao;

    public Invoice_Adapter(ArrayList<invoice> list, Context context) {
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
        View view = inflater.inflate(R.layout.item_invoice, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        invoice inv;
        invoce_dao = new invoce_DAO(context);
        inv = list.get(position);
        holder.id_cart.setText(String.valueOf(list.get(position).getId_history()));
        holder.phone.setText(String.valueOf(list.get(position).getPhone()));
        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getAddress());
        holder.time.setText(list.get(position).getTime());
        holder.sum.setText(String.valueOf(list.get(position).getSum()));
        holder.content.setText(list.get(position).getContten());
        holder.status.setText(list.get(position).getStatus());
        holder.status.setText("Xác Nhận Đơn");
        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.status.setText("Đang giao");
                inv.setStatus("Đang giao");
                invoce_dao.update(inv);
                list = invoce_dao.SeLectDaDatHang();
                setData(list);
                Toast.makeText(context, "Đơn Hàng giao thành công", Toast.LENGTH_SHORT).show();
            }
        });
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
