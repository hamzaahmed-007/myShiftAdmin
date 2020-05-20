package com.hamza.firestoreadmin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class PendingShiftsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    shiftAdapter shiftAdapter;
    private CollectionReference myCol = FirebaseFirestore.getInstance().collection("pendingShifts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_shifts);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pending Shitfs");

        Query mQuery = myCol.whereEqualTo("status", "Pending");

        FirestoreRecyclerOptions<pendingShifts> options = new FirestoreRecyclerOptions.Builder<pendingShifts>()
                .setQuery(mQuery, pendingShifts.class)
                .build();

        shiftAdapter = new shiftAdapter(options,this);

        recyclerView = findViewById(R.id.pendingRV);
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
