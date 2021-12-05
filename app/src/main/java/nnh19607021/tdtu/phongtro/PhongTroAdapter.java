package nnh19607021.tdtu.phongtro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PhongTroAdapter extends RecyclerView.Adapter<PhongTroAdapter.ViewHolder> {

    private ArrayList<class_PhongTro>lstPhong;
    private Context mContext;

    public PhongTroAdapter(ArrayList<class_PhongTro> lstPhong, Context mContext) {
        this.lstPhong = lstPhong;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        class_PhongTro pt = lstPhong.get(position);
        if (pt==null)
            return;
        holder.twSoPhong.setText(pt.getSoPhong());
        holder.twThongTin.setText(pt.getNguoitro().getHoTen());
        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i = new Intent(mContext, DetailPhongActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("object_person", pt);
//                i.putExtras(bundle);
//                mContext.startActivity(i);
                Toast.makeText(mContext, "Click" + pt.getSoPhong(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void release(){
        mContext=null;
    }
    @Override
    public int getItemCount() {
        if (lstPhong!=null)
            return lstPhong.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView twSoPhong,twThongTin;
        CardView layout_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            twSoPhong = itemView.findViewById(R.id.twSoPhong);
            twThongTin = itemView.findViewById(R.id.twThongTin);
            layout_item = itemView.findViewById(R.id.layout_item);
        }
    }
}
