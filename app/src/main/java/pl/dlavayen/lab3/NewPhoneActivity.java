package pl.dlavayen.lab3;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
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

        // Obsługa edycji
        Intent intent = getIntent();
        long id = intent.getLongExtra("id", -1);
        if (id != -1) {
            editManufacturer.setText(intent.getStringExtra("manufacturer"));
            editModel.setText(intent.getStringExtra("model"));
            editAndroidVersion.setText(intent.getStringExtra("androidVersion"));
            editWebsite.setText(intent.getStringExtra("website"));
        }

        // Disable save button initially
        buttonSave.setEnabled(false);

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean allFilled = !editManufacturer.getText().toString().trim().isEmpty()
                        && !editModel.getText().toString().trim().isEmpty()
                        && !editAndroidVersion.getText().toString().trim().isEmpty()
                        && !editWebsite.getText().toString().trim().isEmpty();
                buttonSave.setEnabled(allFilled);
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };

        editManufacturer.addTextChangedListener(watcher);
        editModel.addTextChangedListener(watcher);
        editAndroidVersion.addTextChangedListener(watcher);
        editWebsite.addTextChangedListener(watcher);

        buttonSave.setOnClickListener(v -> {
            boolean valid = true;
            if (TextUtils.isEmpty(editManufacturer.getText())) {
                editManufacturer.setError("Pole wymagane");
                valid = false;
            }
            if (TextUtils.isEmpty(editModel.getText())) {
                editModel.setError("Pole wymagane");
                valid = false;
            }
            if (TextUtils.isEmpty(editAndroidVersion.getText())) {
                editAndroidVersion.setError("Pole wymagane");
                valid = false;
            }
            if (TextUtils.isEmpty(editWebsite.getText())) {
                editWebsite.setError("Pole wymagane");
                valid = false;
            }
            if (!valid) return;

            Intent result = new Intent();
            if (id != -1) result.putExtra("id", id);
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
            String url = editWebsite.getText().toString().trim();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            try {
                startActivity(browserIntent);
            } catch (Exception e) {
                Toast.makeText(this, "Nie można otworzyć strony", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
