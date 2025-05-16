/**
 * DAO - interfejs do operacji CRUD na tabeli phones w Room.
 */
package pl.dlavayen.lab3;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PhoneDao {
    /**
     * Wstawia nowy rekord telefonu do bazy.
     */
    @Insert
    void insert(Phone phone);

    /**
     * Zwraca listę wszystkich telefonów jako LiveData.
     */
    @Query("SELECT * FROM phones ORDER BY manufacturer ASC")
    LiveData<List<Phone>> getAllPhones();

    /**
     * Usuwa wszystkie rekordy z tabeli phones.
     */
    @Query("DELETE FROM phones")
    void deleteAll();

    /**
     * Zwraca liczbę rekordów w tabeli phones.
     */
    @Query("SELECT COUNT(*) FROM phones")
    int getCount();

    /**
     * Aktualizuje istniejący rekord telefonu.
     */
    @Update
    void update(Phone phone);

    /**
     * Usuwa wskazany rekord telefonu.
     */
    @Delete
    void delete(Phone phone);
}
