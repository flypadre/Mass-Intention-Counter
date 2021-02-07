package me.oblate.massintentioncounter.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MassDao {
    @Query("SELECT * FROM mass_intentions")
    LiveData<List<MassEntity>> getAll();

    @Delete
    void delete(MassEntity massEntity);
}
