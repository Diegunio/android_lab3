package pl.dlavayen.lab3;

import android.app.Application;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneRepository {
    private final PhoneDao mPhoneDao;
    private final LiveData<List<Phone>> mAllPhones;

    public PhoneRepository(Application application) {
        PhoneRoomDatabase db = PhoneRoomDatabase.getDatabase(application);
        mPhoneDao = db.phoneDao();
        mAllPhones = mPhoneDao.getAllPhones();
    }

    public LiveData<List<Phone>> getAllPhones() {
        return mAllPhones;
    }

    public void insert(Phone phone) {
        PhoneRoomDatabase.databaseWriteExecutor.execute(() -> mPhoneDao.insert(phone));
    }

    public void deleteAll() {
        PhoneRoomDatabase.databaseWriteExecutor.execute(mPhoneDao::deleteAll);
    }
}
