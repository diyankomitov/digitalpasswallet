package com.diyankomitov.digitalpasswallet.models.room;

import android.app.Application;

import com.diyankomitov.digitalpasswallet.models.pass.PassModel;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

/**
 * This is the Database class for the Pass Room
 */
@Database(entities = {PassModel.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class PassDatabase extends RoomDatabase {
    
    private static PassDatabase instance;
    
    /**
     * Gets the Pass DAO
     *
     * @return the Pass DAO
     */
    public abstract PassDao passDao();
    
    /**
     * Gets the singleton instance of this Database.
     *
     * @param application the application which is needed to initially build the database.
     *
     * @return the singleton instance of this Database
     */
    public static synchronized PassDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(application.getApplicationContext(), PassDatabase.class, "pass_database")
            .fallbackToDestructiveMigration()
            .build();
        }
        
        return instance;
    }
}
