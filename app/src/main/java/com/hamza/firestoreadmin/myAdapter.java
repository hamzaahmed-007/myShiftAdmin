package com.hamza.firestoreadmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class myAdapter extends FirestoreRecyclerAdapter<pendingShifts, myAdapter.myViewHolder> {

    Context mContext;
    List<pendingShifts> mList;

    FirebaseUser mUser;
    FirebaseAuth mAuth;




    public myAdapter(@NonNull FirestoreRecyclerOptions<pendingShifts> options) {

        super(options);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.home_items,viewGroup,false);

        return new myViewHolder(layout);
    }


    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull pendingShifts model) {

        holder.card_title.setText(model.getShiftName());

    }

    @Override
    public int getItemCount()
    {
        return mList.size();

    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {

        TextView card_title, card_content;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            card_title = itemView.findViewById(R.id.homeText1);
            card_content = itemView.findViewById(R.id.homeText2);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, shiftStatusAcitvity.class);
                    int position = getAdapterPosition();

                    intent.putExtra("title", mList.get(position).getShiftName());
                    intent.putExtra("content" ,mList.get(position).getDate());
                    intent.putExtra("ShiftID", mList.get(position).getUserID());
                    mContext.startActivity(intent);
                }
            });


        }

    }
    private void showMessage(String message)
    {

        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }


}
