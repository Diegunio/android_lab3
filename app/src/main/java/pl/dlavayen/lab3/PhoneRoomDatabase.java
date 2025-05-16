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
    public abstract PhoneDao phoneDao();

    private static volatile PhoneRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

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
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    databaseWriteExecutor.execute(() -> {
                        PhoneDao dao = INSTANCE.phoneDao();
                        // przykładowe rekordy przy tworzeniu bazy :contentReference[oaicite:7]{index=7}
                        dao.insert(new Phone("Samsung", "Galaxy S21", "11", "https://www.samsung.com"));
                        dao.insert(new Phone("Google", "Pixel 6", "12", "https://store.google.com"));
                        dao.insert(new Phone("OnePlus", "9 Pro", "11", "https://www.oneplus.com"));
                    });
                }
            };
}
