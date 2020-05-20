package com.hamza.firestoreadmin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class shiftStatusAcitvity extends AppCompatActivity {


    TextView name, location, date, posted, applied;
    Button accept, reject, later;

    String id;

    Intent homeIntent;

    private CollectionReference myCol = FirebaseFirestore.getInstance().collection("pendingShifts");
    private CollectionReference myColNew = FirebaseFirestore.getInstance().collection("newShifts");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_status_acitvity);

        Intent intent = getIntent();
        homeIntent = new Intent(getApplicationContext(), MainActivity.class);

        id = intent.getExtras().getString("id");

        name = findViewById(R.id.statusName);
        location = findViewById(R.id.statusLocation);
        date = findViewById(R.id.statusDate);
        posted = findViewById(R.id.statusPosted);
        applied = findViewById(R.id.statusApplied);

        accept = findViewById(R.id.statusAccept);
        reject = findViewById(R.id.statusReject);
        later = findViewById(R.id.laterButton);

        getDate();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCol.document(id).update("status", "Accepted");
                myColNew.document(id).delete();
                startActivity(homeIntent);

            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCol.document(id).update("status", "Rejected");
                startActivity(homeIntent);
            }
        });

        later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCol.document(id).update("status", "Pending");
                startActivity(homeIntent);
            }
        });


    }

    private void getDate()
    {



        myCol.document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {



                    name.setText(documentSnapshot.getString("shiftName"));
                    location.setText(documentSnapshot.getString("location"));
                    posted.setText(documentSnapshot.getString("userID"));
                    applied.setText(documentSnapshot.getString("username"));
                    date.setText(documentSnapshot.getString("date"));

                }
            }
        });
    }
}
