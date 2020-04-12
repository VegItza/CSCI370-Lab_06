package com.example.lab6carrillo;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class PersonNameListTask extends AsyncTask {
    PersonNameListTask(){}
    @Override
    protected Boolean doInBackground(Object[] objects) {
        final LabDatabase labDatabase = (LabDatabase) objects[0];
        final Context context = (Context) objects[1];

        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> personNames = new ArrayList<>();
                List<Person> persons = labDatabase.personDao().getAllPersons();

                for(Person p: persons) {
                    personNames.add(p.getName());
                }

                Intent i = new Intent(context, PersonsActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("Persons", personNames);
                context.startActivity(i);
            }
        }) .start();

        return false;
    }
}
