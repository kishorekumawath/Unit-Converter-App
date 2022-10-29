package com.AKapps.unitconvertor.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.AKapps.unitconvertor.R;
import com.AKapps.unitconvertor.interfaces.onclickConverterType;
import com.AKapps.unitconvertor.model.converter_type;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class converter_type_adapter extends RecyclerView.Adapter<converter_type_adapter.ViewHolder> {
    ArrayList<converter_type> arrayList = new ArrayList<>();
    Context context;
    onclickConverterType onclickConverterType;

    public converter_type_adapter(ArrayList<converter_type> arrayList, Context context, com.AKapps.unitconvertor.interfaces.onclickConverterType onclickConverterType) {
        this.arrayList = arrayList;
        this.context = context;
        this.onclickConverterType = onclickConverterType;
    }

    @NonNull
    @Override
    public converter_type_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_view,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull converter_type_adapter.ViewHolder viewHolder, int i) {
        // here just we set the data
        converter_type model = arrayList.get(i);
        viewHolder.text.setText(model.getName());
        viewHolder.img.setImageResource(model.getImgView());
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            // the onclick onclickConverterType interface is called,this interface is created in interface folder
            @Override
            public void onClick(View v) {
                onclickConverterType.onclick(model);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView text;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //here we just getting id or linking the id
            img = itemView.findViewById(R.id.ImageConverterType);
            text = itemView.findViewById(R.id.TextConverterType);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
