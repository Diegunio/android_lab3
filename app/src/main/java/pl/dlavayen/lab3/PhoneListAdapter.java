package pl.dlavayen.lab3;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import pl.dlavayen.lab3.databinding.ItemPhoneBinding;

public class PhoneListAdapter
        extends ListAdapter<Phone, PhoneListAdapter.PhoneViewHolder> {

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
        ItemPhoneBinding binding = ItemPhoneBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new PhoneViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class PhoneViewHolder extends RecyclerView.ViewHolder {
        private final ItemPhoneBinding binding;

        PhoneViewHolder(ItemPhoneBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Phone phone) {
            binding.textManufacturer.setText(phone.manufacturer);
            binding.textModel.setText(phone.model);
        }
    }
}
