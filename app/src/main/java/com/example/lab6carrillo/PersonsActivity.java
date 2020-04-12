package com.example.lab6carrillo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;

public class PersonsActivity extends AppCompatActivity {
    ListView listView;
    Button backBtn, deleteAllBtn;
    public LabDatabase labDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons);

        listView = findViewById(R.id.personNameListId);
        ArrayList<String> i = (ArrayList) this.getIntent().getExtras().get("Persons");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, i);
        listView.setAdapter(adapter);
        labDB  = Room.databaseBuilder(this, LabDatabase.class, "LabDatabase").build();

        backBtn = findViewById(R.id.backBtn);
        deleteAllBtn = findViewById(R.id.deleteAllBtnId);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);
            }
        });

        final DeleteAllItemsTask deleteAllItemsTask = new DeleteAllItemsTask();

        deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Object[] objects = new Object[1];
                objects[0] = labDB;

                deleteAllItemsTask.doInBackground(objects);
                Toast toast = Toast.makeText(PersonsActivity.this, "All items have been deleted successfully!", Toast.LENGTH_SHORT);
                toast.show();

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(i);
            }
        });


    }
}