 package com.example.lab6carrillo;

 import android.os.Bundle;
 import android.view.View;
 import android.widget.Button;
 import android.widget.EditText;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.room.Room;

 public class MainActivity extends AppCompatActivity {

     EditText nameText;
     Button submitBtn;
     Button nameList;
     public LabDatabase labDB;


     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         nameText = findViewById(R.id.nameInputId);
         submitBtn = findViewById(R.id.submitBtn);
         nameList = findViewById(R.id.nameListBtn);
         labDB  = Room.databaseBuilder(this, LabDatabase.class, "LabDatabase").build();

         final InsertNameTask insertNameTask = new InsertNameTask();

         submitBtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Person person = new Person();
                 person.setName(nameText.getText().toString());
                 if (labDB.personDao() != null) {
                     Object[] objects = new Object[2];
                     objects[0] = labDB;
                     objects[1] = person;
                     insertNameTask.doInBackground(objects);
                     Toast toast = Toast.makeText(MainActivity.this, nameText.getText().toString() + " was successfully added! ", Toast.LENGTH_SHORT);
                     toast.show();
                 }
             }
         });

         final PersonNameListTask personNameListTask = new PersonNameListTask();

         nameList.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Object[] objects = new Object[2];
                 objects[0] = labDB;
                 objects[1] = getApplicationContext();
                 personNameListTask.doInBackground(objects);
             }
         });


     }
 }


