package com.pratikcodes.asvaidik.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pratikcodes.asvaidik.R;
import com.pratikcodes.asvaidik.ReqActivity;
import com.pratikcodes.asvaidik.TeamActivity;
import com.pratikcodes.asvaidik.models.HomeModel;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewHolder> {
    ArrayList<HomeModel> list;
    Context context;

    public HomeAdapter(ArrayList<HomeModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(
                R.layout.design_card,
                parent,
                false
        );
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        HomeModel model = list.get(position);

        holder.image.setImageResource(model.getImage());
        holder.title.setText(model.getTitle());

        holder.image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent(context, ReqActivity.class);
                        break;
                    case 1:
                        intent = new Intent(context, TeamActivity.class);
                        break;
                    case 2:
                        Toast.makeText(context, "Contact", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(context, "Testimonials", Toast.LENGTH_SHORT).show();
                        break;

                }
                context.startActivity(intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.cardimage);
            title = itemView.findViewById(R.id.cardname);
        }
    }
}
