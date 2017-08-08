package com.example.android.habittracker.Data;

import android.provider.BaseColumns;

/**
 * Created by Twinkle Sahni on 11-May-17.
 */

public final class HabitContract {

    private HabitContract() {
    }


    public static final class HabitEntry implements BaseColumns {
        public final static String TABLE_NAME = "habit";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PERSON_NAME = "name";
        public final static String COLUMN_PERSON_DISEASE = "disease";
        public final static String COLUMN_PERSON_AGE = "age";

    }
}

