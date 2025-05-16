package pl.dlavayen.lab3;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PhoneDao {
    @Insert
    void insert(Phone phone);

    @Query("SELECT * FROM phones ORDER BY manufacturer ASC")
    LiveData<List<Phone>> getAllPhones();

    @Query("DELETE FROM phones")
    void deleteAll();
}
