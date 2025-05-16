/**
 * Repozytorium pośredniczące między DAO a ViewModel
 * Zapewnia dostęp do danych telefonów
 */
package pl.dlavayen.lab3;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneRepository {
    private final PhoneDao mPhoneDao;
    private final LiveData<List<Phone>> mAllPhones;

    /**
     * Inicjalizuje repozytorium i uzyskuje dostęp do DAO oraz listy telefonów.
     */
    public PhoneRepository(Application application) {
        PhoneRoomDatabase db = PhoneRoomDatabase.getDatabase(application);
        mPhoneDao = db.phoneDao();
        mAllPhones = mPhoneDao.getAllPhones();
    }

    /**
     * Zwraca LiveData z listą wszystkich telefonów.
     */
    public LiveData<List<Phone>> getAllPhones() {
        return mAllPhones;
    }

    /**
     * Wstawia nowy rekord telefonu asynchronicznie.
     */
    public void insert(Phone phone) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> mPhoneDao.insert(phone));
    }

    /**
     * Aktualizuje rekord telefonu asynchronicznie.
     */
    public void update(Phone phone) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> mPhoneDao.update(phone));
    }

    /**
     * Usuwa rekord telefonu asynchronicznie.
     */
    public void delete(Phone phone) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> mPhoneDao.delete(phone));
    }

    /**
     * Usuwa wszystkie rekordy telefonów asynchronicznie.
     */
    public void deleteAll() {
        PhoneRoomDatabase.databaseWriteExecutor.execute(mPhoneDao::deleteAll);
    }
}
