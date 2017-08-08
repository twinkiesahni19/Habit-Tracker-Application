package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.habittracker.Data.HabitContract;
import com.example.android.habittracker.Data.HabitDbHelper;

public class Detail extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mDiseaseEditText;
    private EditText mAgeEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mNameEditText = (EditText) findViewById(R.id.edit_name);
        mDiseaseEditText = (EditText) findViewById(R.id.edit_disease);
        mAgeEditText = (EditText) findViewById(R.id.edit_age);
    }

    private void insertHabit() {
        String nameString = mNameEditText.getText().toString().trim();
        String diseaseString = mDiseaseEditText.getText().toString().trim();
        String ageString = mAgeEditText.getText().toString().trim();
        int age = Integer.parseInt(ageString);

        HabitDbHelper mDbHelper = new HabitDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabitEntry.COLUMN_PERSON_NAME, nameString);
        values.put(HabitContract.HabitEntry.COLUMN_PERSON_DISEASE, diseaseString);
        values.put(HabitContract.HabitEntry.COLUMN_PERSON_AGE, age);


        long Id = db.insert(HabitContract.HabitEntry.TABLE_NAME, null, values);
        if (Id == -1) {
            Toast.makeText(this, "ERROR SAVIND DATA", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "DATA SAVED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                insertHabit();
                finish();
                return true;
            case android.R.id.home:
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
