/**
 * Klasa RoomDatabase przechowująca bazę danych telefonów
 * Singleton, inicjalizuje bazę i domyślne rekordy
 */
package pl.dlavayen.lab3;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Phone.class}, version = 1, exportSchema = false)
public abstract class PhoneRoomDatabase extends RoomDatabase {
    /**
     * Zwraca DAO do operacji na tabeli phones.
     */
    public abstract PhoneDao phoneDao();

    private static volatile PhoneRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Zwraca singleton bazy danych, inicjalizuje ją jeśli nie istnieje.
     * Dodaje domyślne rekordy jeśli baza jest pusta.
     */
    static PhoneRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PhoneRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    PhoneRoomDatabase.class, "phone_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        databaseWriteExecutor.execute(() -> {
            PhoneDao dao = INSTANCE.phoneDao();
            if (dao.getCount() == 0) {
                dao.insert(new Phone("Samsung", "Galaxy S21", "11", "https://www.samsung.com"));
                dao.insert(new Phone("Google", "Pixel 6", "12", "https://store.google.com"));
                dao.insert(new Phone("OnePlus", "9 Pro", "11", "https://www.oneplus.com"));
            }
        });
        return INSTANCE;
    }

    /**
     * Callback wywoływany przy tworzeniu bazy, wstawia domyślne rekordy.
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    databaseWriteExecutor.execute(() -> {
                        PhoneDao dao = INSTANCE.phoneDao();
                        if (dao.getCount() == 0) {
                            dao.insert(new Phone("Samsung", "Galaxy S21", "11", "https://www.samsung.com"));
                            dao.insert(new Phone("Google", "Pixel 6", "12", "https://store.google.com"));
                            dao.insert(new Phone("OnePlus", "9 Pro", "11", "https://www.oneplus.com"));
                        }
                    });
                }
            };
}
