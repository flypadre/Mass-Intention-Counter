package me.oblate.massintentioncounter.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MassEntity.class}, version = 1, exportSchema = false)
public abstract class MassDatabase extends RoomDatabase {
    public abstract MassDao massDao();

    private static volatile MassDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor  = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MassDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MassDatabase.class){
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MassDatabase.class, "mass_database").addCallback(sMassDatabaseCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sMassDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            databaseWriteExecutor.execute(() -> {
                MassDao dao = INSTANCE.massDao();
            });
        }
    };
}
