package quanpnph29471.example.coffeeapp.user.history;

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
import quanpnph29471.example.coffeeapp.admin.list_history.invoce_DAO;
import quanpnph29471.example.coffeeapp.admin.list_history.invoice;


public class choXN_Adapter extends RecyclerView.Adapter<choXN_Adapter.ViewHolder> {

    private ArrayList<invoice> list;

    private Context context;
  private invoce_DAO dao;

    public choXN_Adapter(ArrayList<invoice> list, Context context) {
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
        View view = inflater.inflate(R.layout.item_choxn, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        invoice historyModel;
       dao = new invoce_DAO(context);
        historyModel = list.get(position);
        holder.id_cart.setText(String.valueOf(historyModel.getId_history()));
        holder.phone.setText(String.valueOf(historyModel.getPhone()));
        holder.name.setText(historyModel.getName());
        holder.address.setText(historyModel.getAddress());
        holder.time.setText(historyModel.getTime());
        holder.sum.setText(String.valueOf(historyModel.getSum()));
        holder.content.setText(historyModel.getContten());
        holder.status.setText(historyModel.getStatus());
        holder.status.setText("Hủy Đơn");
        holder.status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historyModel.setStatus("Hủy Đơn");
                dao.update(historyModel);
                list = dao.SeLectDaDatHang();
                setData(list);
                Toast.makeText(context, "Đơn Hàng đã hủy thành công", Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public int getItemCount() {
            return list.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView id_cart, phone, name, address, sum, time, status, content;
//        Button huydon;

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
//            huydon = itemView.findViewById(R.id.btn_huydon);

        }
    }


}
