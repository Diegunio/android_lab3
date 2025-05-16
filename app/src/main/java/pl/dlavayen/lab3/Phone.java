package pl.dlavayen.lab3;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

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

    public Phone(@NonNull String manufacturer, @NonNull String model,
                 String androidVersion, String website) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.androidVersion = androidVersion;
        this.website = website;
    }
}
