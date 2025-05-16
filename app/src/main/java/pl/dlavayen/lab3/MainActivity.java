package pl.dlavayen.lab3;

import android.os.Bundle;
import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.ItemTouchHelper;

public class MainActivity extends AppCompatActivity {
    private PhoneViewModel mPhoneViewModel;
    private Phone editingPhone = null;

    private final ActivityResultLauncher<Intent> addPhoneLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Intent data = result.getData();
                    long id = data.getLongExtra("id", -1);
                    String manufacturer = data.getStringExtra("manufacturer");
                    String model = data.getStringExtra("model");
                    String androidVersion = data.getStringExtra("androidVersion");
                    String website = data.getStringExtra("website");
                    if (id == -1) {
                        Phone phone = new Phone(manufacturer, model, androidVersion, website);
                        mPhoneViewModel.insert(phone);
                    } else {
                        Phone phone = new Phone(id, manufacturer, model, androidVersion, website);
                        mPhoneViewModel.update(phone);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ustaw kolor status bar na colorPrimaryDark
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        PhoneListAdapter adapter = new PhoneListAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        mPhoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);
        mPhoneViewModel.getAllPhones().observe(this, adapter::submitList);

        findViewById(R.id.fab_add).setOnClickListener(v -> {
            editingPhone = null;
            Intent intent = new Intent(this, NewPhoneActivity.class);
            addPhoneLauncher.launch(intent);
        });

        adapter.setOnItemClickListener(phone -> {
            editingPhone = phone;
            Intent intent = new Intent(this, NewPhoneActivity.class);
            intent.putExtra("id", phone.id);
            intent.putExtra("manufacturer", phone.manufacturer);
            intent.putExtra("model", phone.model);
            intent.putExtra("androidVersion", phone.androidVersion);
            intent.putExtra("website", phone.website);
            addPhoneLauncher.launch(intent);
        });

        // Swipe to delete
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                Phone phone = adapter.getCurrentList().get(pos);
                mPhoneViewModel.delete(phone);
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_delete_all) {
            mPhoneViewModel.deleteAll();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
