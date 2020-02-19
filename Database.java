package com.example.a2019rmcbride.collegeapphelper;

import android.provider.BaseColumns;

public final class Database {
    private Database(){}

    public static class College implements BaseColumns {
        public static final String TABLE_NAME = "colleges";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DECISION = "decisionPlan";
        public static final String COLUMN_DEADLINE = "applicationDeadline";
        public static final String COLUMN_PLATFORM = "applicationPlatform";
        public static final String COLUMN_INTERVIEW = "interview";
        public static final String COLUMN_MAJOR = "intendedMajor";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DECISION + " TEXT, " +
                COLUMN_DEADLINE + " TEXT, " +
                COLUMN_PLATFORM + " TEXT, " +
                COLUMN_INTERVIEW + " TEXT, " +
                COLUMN_MAJOR + " TEXT" + ")";
    }

}
