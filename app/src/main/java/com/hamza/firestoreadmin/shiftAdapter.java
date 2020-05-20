package com.hamza.firestoreadmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


public class shiftAdapter extends FirestoreRecyclerAdapter<pendingShifts, shiftAdapter.shiftHolder> {
 Context mContext;

    public shiftAdapter(@NonNull FirestoreRecyclerOptions<pendingShifts> options, Context mContext) {
        super(options);
        this.mContext = mContext;

    }

    @Override
    protected void onBindViewHolder(@NonNull shiftHolder holder, int position, @NonNull pendingShifts model) {
    holder.title.setText(model.getShiftName());
    holder.date.setText(model.getDate());
    holder.status.setText(model.getStatus());
    if(model.getStatus().equals("Accepted"))
    {
        holder.status.setTextColor(Color.GREEN);
        }else if(model.getStatus().equals("Rejected")) {
            holder.status.setTextColor(Color.RED);

        }
    holder.id = model.getShiftID();
    }

    @NonNull
    @Override
    public shiftHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_items,viewGroup,false);

        return new shiftHolder(v);
    }

    class shiftHolder extends RecyclerView.ViewHolder
    {
        TextView title,date, status;
        String id;

        public shiftHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.homeText1);
            date = itemView.findViewById(R.id.homeText2);
            status = itemView.findViewById(R.id.homeText3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(mContext, shiftStatusAcitvity.class);
                   intent.putExtra("id", id);
                    mContext.startActivity(intent);

                }
            });

        }
    }
}
