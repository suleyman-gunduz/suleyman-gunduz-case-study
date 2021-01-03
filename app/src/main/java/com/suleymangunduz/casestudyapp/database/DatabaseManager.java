package com.suleymangunduz.casestudyapp.database;

import android.content.Context;

import com.suleymangunduz.casestudyapp.database.dao.FeedbackDao;
import com.suleymangunduz.casestudyapp.database.entity.FeedBack;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {FeedBack.class}, version = 1)
public abstract class DatabaseManager extends RoomDatabase {

    private static DatabaseManager INSTANCE = null;

    public abstract FeedbackDao feedbackDao();

    public static DatabaseManager getDatabaseManager(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    DatabaseManager.class,
                    "case-study-database"
            ).allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
