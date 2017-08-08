package com.example.android.habittracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.habittracker.Data.HabitContract;
import com.example.android.habittracker.Data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button add = (Button) findViewById(R.id.button_intent);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Detail.class);
                startActivity(intent);
            }
        });

        mDbHelper = new HabitDbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        Cursor cursor = readAllHabits();

        TextView displayView = (TextView) findViewById(R.id.text_view_habit);

        try {
            displayView.setText("The habits table contains " + cursor.getCount() + " habit.\n\n");
            displayView.append(HabitContract.HabitEntry._ID + " - " +
                    HabitContract.HabitEntry.COLUMN_PERSON_NAME + " - " +
                    HabitContract.HabitEntry.COLUMN_PERSON_DISEASE + " - " +
                    HabitContract.HabitEntry.COLUMN_PERSON_AGE + "\n");

            int idColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_PERSON_NAME);
            int diseaseColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_PERSON_DISEASE);
            int ageColumnIndex = cursor.getColumnIndex(HabitContract.HabitEntry.COLUMN_PERSON_AGE);

            while (cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                String currentDisease = cursor.getString(diseaseColumnIndex);
                int currentAge = cursor.getInt(ageColumnIndex);
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentDisease + " - " +
                        currentAge));
            }
        } finally {
            cursor.close();
        }
    }

    public Cursor readAllHabits() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                HabitContract.HabitEntry._ID,
                HabitContract.HabitEntry.COLUMN_PERSON_NAME,
                HabitContract.HabitEntry.COLUMN_PERSON_DISEASE,
                HabitContract.HabitEntry.COLUMN_PERSON_AGE,
        };
        return db.query(
                HabitContract.HabitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);
    }
}
