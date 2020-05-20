package com.hamza.firestoreadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    shiftAdapter shiftAdapter;
    private CollectionReference myCol = FirebaseFirestore.getInstance().collection("pendingShifts");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Applications");

        Query mQuery = myCol.orderBy("username", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<pendingShifts> options = new FirestoreRecyclerOptions.Builder<pendingShifts>()
                .setQuery(mQuery, pendingShifts.class)
                .build();

        shiftAdapter = new shiftAdapter(options,this);

        recyclerView = findViewById(R.id.postRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(shiftAdapter);


    }


    @Override
    protected void onStart() {
        super.onStart();
        shiftAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();

        if (shiftAdapter != null) {
            shiftAdapter.stopListening();
        }
    }
    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }


    private class myViewHolder extends RecyclerView.ViewHolder {
        private View view;



        myViewHolder(@NonNull View itemView) {

            super(itemView);
            view = itemView;


        }

        void setShiftName(String shiftName) {

            TextView card_title = view.findViewById(R.id.homeText1);

            card_title.setText(shiftName);

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent backIntent = new Intent(getApplicationContext(), homeActivity.class);
        startActivity(backIntent);
        finish();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}