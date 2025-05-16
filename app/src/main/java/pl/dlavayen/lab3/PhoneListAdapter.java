package pl.dlavayen.lab3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class PhoneListAdapter extends ListAdapter<Phone, PhoneListAdapter.PhoneViewHolder> {

    protected PhoneListAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Phone> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Phone>() {
                @Override
                public boolean areItemsTheSame(@NonNull Phone oldItem, @NonNull Phone newItem) {
                    return oldItem.id == newItem.id;
                }
                @Override
                public boolean areContentsTheSame(@NonNull Phone oldItem, @NonNull Phone newItem) {
                    return oldItem.manufacturer.equals(newItem.manufacturer)
                            && oldItem.model.equals(newItem.model);
                }
            };

    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_phone, parent, false);
        return new PhoneViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class PhoneViewHolder extends RecyclerView.ViewHolder {
        private final TextView textManufacturer;
        private final TextView textModel;

        PhoneViewHolder(View itemView) {
            super(itemView);
            textManufacturer = itemView.findViewById(R.id.text_manufacturer);
            textModel = itemView.findViewById(R.id.text_model);
        }

        void bind(Phone phone) {
            textManufacturer.setText(phone.manufacturer);
            textModel.setText(phone.model);
        }
    }
}
