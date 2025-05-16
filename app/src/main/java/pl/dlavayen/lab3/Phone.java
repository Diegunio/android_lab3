/**
 * Model danych reprezentujący telefon w bazie danych Room.
 * Zawiera producenta, model, wersję Androida oraz stronę WWW.
 */
package pl.dlavayen.lab3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;
import androidx.room.Ignore;

@Entity(tableName = "phones")
public class Phone {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @NonNull
    @ColumnInfo(name = "manufacturer")
    public String manufacturer;

    @NonNull
    @ColumnInfo(name = "model")
    public String model;

    @ColumnInfo(name = "android_version")
    public String androidVersion;

    @ColumnInfo(name = "website")
    public String website;

    /**
     * Konstruktor do tworzenia nowego obiektu Phone bez ID (do wstawiania nowych rekordów).
     */
    @Ignore
    public Phone(@NonNull String manufacturer, @NonNull String model,
                 String androidVersion, String website) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.website = website;
    }

    /**
     * Konstruktor do tworzenia obiektu Phone z określonym ID (do aktualizacji istniejących rekordów).
     */
    public Phone(long id, @NonNull String manufacturer, @NonNull String model,
                 String androidVersion, String website) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.website = website;
    }
}
