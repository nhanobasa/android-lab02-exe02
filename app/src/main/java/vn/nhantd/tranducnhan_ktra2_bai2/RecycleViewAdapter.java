package vn.nhantd.tranducnhan_ktra2_bai2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.nhantd.tranducnhan_ktra2_bai2.model.QuyenGop;

/**
 * Created by Tran Duc Nhan on 2021-05-14
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.quyenGoptViewHolder> { // for card_view.xml

    private Context context;
    List<QuyenGop> list;

    public RecycleViewAdapter(Context context) {
        this.context = context;
        this.list = list;
    }

    public void setData(List<QuyenGop> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public quyenGoptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.cart_item, parent, false);
        return new quyenGoptViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull quyenGoptViewHolder holder, int position) {
        QuyenGop s = list.get(position);
        holder.txtName.setText(s.getName() + " - " + s.getId());
        holder.txtCity.setText(" City:" + s.getCity());
        holder.txtDate.setText(" Date:" + s.getDate());
        holder.txtMoney.setText(" Money:" + s.getMoney());
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public Double totalMoney() {
        Double totalMoney = 0.0;
        for (QuyenGop quyenGop : list) {
            totalMoney += quyenGop.getMoney();
        }
        return totalMoney;
    }

    public class quyenGoptViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView txtName;
        private TextView txtCity;
        private TextView txtDate;
        private TextView txtMoney;

        public quyenGoptViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener((View.OnClickListener) this);
            txtName = itemView.findViewById(R.id.txtName);
            txtCity = itemView.findViewById(R.id.txtCity);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtMoney = itemView.findViewById(R.id.txtMoney);

        }

        @Override
        public void onClick(View v) {
            int position = getLayoutPosition();
            Intent intent = new Intent(context, UpdateAndDeleteActivity.class);
            QuyenGop quyenGop = list.get(position);

            intent.putExtra("quyengop", quyenGop);
            context.startActivity(intent);
        }
    }
}
