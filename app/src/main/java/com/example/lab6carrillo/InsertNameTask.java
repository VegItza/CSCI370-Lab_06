package com.example.lab6carrillo;


import android.os.AsyncTask;

public  class  InsertNameTask extends AsyncTask {
    InsertNameTask( ){}

    @Override
    protected Boolean doInBackground(final Object[] objects) {
        final LabDatabase labDatabase = (LabDatabase) objects[0];
        final Person person = (Person) objects[1];

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (labDatabase != null) {
                    labDatabase.personDao().insertPerson(person);
                }
            }
        }) .start();

        return false;
    }
}