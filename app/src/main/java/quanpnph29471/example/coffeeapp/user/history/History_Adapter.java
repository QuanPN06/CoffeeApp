package quanpnph29471.example.coffeeapp.user.history;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import quanpnph29471.example.coffeeapp.R;

public class History_Adapter extends RecyclerView.Adapter<History_Adapter.ViewHolder> {
    private ArrayList<History_model> list;
    private Context context;

    private History_DAO history_dao;

    public History_Adapter(ArrayList<History_model> list, Context context, History_DAO history_dao) {
        this.list = list;
        this.context = context;
        this.history_dao = history_dao;
    }

    public void setData(ArrayList<History_model> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_history_user, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.id_cart.setText(String.valueOf(list.get(position).getId_history()));
        holder.phone.setText(String.valueOf(list.get(position).getPhone()));
        holder.name.setText(list.get(position).getName());
        holder.address.setText(list.get(position).getAddress());
        holder.time.setText(list.get(position).getTime());
        holder.sum.setText(String.valueOf(list.get(position).getSum()));
        holder.conten.setText(list.get(position).getContten());
        holder.status.setText(list.get(position).getStatus());
        history_dao = new History_DAO(context);
        History_model inv = list.get(position);
        if (inv.getStatus().equals("Chờ Xác Nhận")) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        showDele(list.get(position).getId_history());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView id_cart, phone, name,address,sum,time,conten,status;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id_cart =itemView.findViewById(R.id.id_cart);
            phone =itemView.findViewById(R.id.id_phone);
            name =itemView.findViewById(R.id.id_hoten);
            address =itemView.findViewById(R.id.id_address);
            sum =itemView.findViewById(R.id.id_sum);
            time =itemView.findViewById(R.id.id_time);
            conten=itemView.findViewById(R.id.id_noidung);
            status=itemView.findViewById(R.id.history_status);
        }
    }
    public void showDele(int id){
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_item_delete_invoice);

        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        AppCompatButton btnSubmit, btnCancel;
        btnSubmit = dialog.findViewById(R.id.btn_dialog_item_delete_submit);
        btnCancel = dialog.findViewById(R.id.btn_dialog_item_delete_cancel);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                History_DAO dao = new History_DAO(context);
                if (dao.delete(id) > 0) {
                    Toast.makeText(context, "Xóa Thành Công", Toast.LENGTH_SHORT).show();
                    list = dao.getAllData();
                    setData(list);
                } else {
                    Toast.makeText(context, "Xóa Thất Bại", Toast.LENGTH_SHORT).show();

                }
                dialog.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
