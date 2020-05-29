package com.wds.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.wds.geeknews.R;
import com.wds.util.ConstantsUtil;


public class GoldManager extends RecyclerView.Adapter<GoldManager.ViewHolder> {
    private Context context;

    public GoldManager(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.gold_item, viewGroup, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        String string = ConstantsUtil.titleList.get(i);
        viewHolder.title.setText(string);
        viewHolder.switchCompat.setChecked(ConstantsUtil.isSelected.get(i));
        viewHolder.switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ConstantsUtil.isSelected.set(i,b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ConstantsUtil.titleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        SwitchCompat switchCompat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            switchCompat=itemView.findViewById(R.id.switchCompat);
        }
    }
}
