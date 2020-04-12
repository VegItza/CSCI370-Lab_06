package com.example.lab6carrillo;

import android.os.AsyncTask;

public class DeleteAllItemsTask extends AsyncTask {

    DeleteAllItemsTask(){}

    @Override
    protected Boolean doInBackground(Object[] objects) {
        final LabDatabase labDatabase = (LabDatabase) objects[0];

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (labDatabase != null) {
                    labDatabase.personDao().deleteAll();
                }
            }
        }) .start();
        return false;
    }
}