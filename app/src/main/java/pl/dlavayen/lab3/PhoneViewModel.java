/**
 * ViewModel udostępniający dane telefonów do UI oraz metody do operacji na danych
 */
package pl.dlavayen.lab3;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PhoneViewModel extends AndroidViewModel {
    private final PhoneRepository mRepository;
    private final LiveData<List<Phone>> mAllPhones;

    /**
     * Inicjalizuje ViewModel i repozytorium.
     */
    public PhoneViewModel(@NonNull Application application) {
        super(application);
        mRepository = new PhoneRepository(application);
        mAllPhones = mRepository.getAllPhones();
    }

    /**
     * Zwraca LiveData z listą wszystkich telefonów.
     */
    public LiveData<List<Phone>> getAllPhones() {
        return mAllPhones;
    }

    /**
     * Dodaje nowy telefon przez repozytorium.
     */
    public void insert(Phone phone) {
        mRepository.insert(phone);
    }

    /**
     * Aktualizuje telefon przez repozytorium.
     */
    public void update(Phone phone) {
        mRepository.update(phone);
    }

    /**
     * Usuwa telefon przez repozytorium.
     */
    public void delete(Phone phone) {
        mRepository.delete(phone);
    }

    /**
     * Usuwa wszystkie telefony przez repozytorium.
     */
    public void deleteAll() {
        mRepository.deleteAll();
    }
}
