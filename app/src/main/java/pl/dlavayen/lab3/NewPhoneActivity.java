package pl.dlavayen.lab3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class NewPhoneActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_phone);

        EditText editManufacturer = findViewById(R.id.edit_manufacturer);
        EditText editModel = findViewById(R.id.edit_model);
        EditText editAndroidVersion = findViewById(R.id.edit_android_version);
        EditText editWebsite = findViewById(R.id.edit_website);

        Button buttonSave = findViewById(R.id.button_save);
        Button buttonCancel = findViewById(R.id.button_cancel);
        Button buttonWebsite = findViewById(R.id.button_website);

        buttonSave.setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra("manufacturer", editManufacturer.getText().toString());
            result.putExtra("model", editModel.getText().toString());
            result.putExtra("androidVersion", editAndroidVersion.getText().toString());
            result.putExtra("website", editWebsite.getText().toString());
            setResult(Activity.RESULT_OK, result);
            finish();
        });

        buttonCancel.setOnClickListener(v -> {
            setResult(Activity.RESULT_CANCELED);
            finish();
        });

        buttonWebsite.setOnClickListener(v -> {
            // Funkcjonalność do wykorzystania w kolejnych zadaniach
        });
    }
}
