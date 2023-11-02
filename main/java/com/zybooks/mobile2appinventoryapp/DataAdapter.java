package com.zybooks.mobile2appinventoryapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter {


    private List<DataItem> dataItemList;
    private Context context;
    private int columnCount;

    private static final int TYPE_HEADER = 0;

    private static final int TYPE_ITEM = 1;


    private boolean isDeleteMode = false;

    private OnItemClickListener onItemClickListener;


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setDeleteMode(boolean deleteMode) {
        this.isDeleteMode = deleteMode;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public DataAdapter(Context context, List<DataItem> dataItemList, int columnCount) {
        this.context = context;
        this.dataItemList = dataItemList;
        this.columnCount = columnCount;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View headerView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.header_layout, parent, false); // This is the header's layout, you need to create it.
            return new HeaderViewHolder(headerView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_data, parent, false);
            return new DataViewHolder(itemView, onItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof HeaderViewHolder) {
            // You can set header data here if needed.
        } else {
            int actualPos = position - 1;  // Adjust for the header
            DataItem dataItem = dataItemList.get(actualPos);
            DataViewHolder dataViewHolder = (DataViewHolder) holder;
            dataViewHolder.dataItemTextView.setText(dataItem.getName());
            dataViewHolder.itemQtyTextView.setText(String.valueOf(dataItem.getQuantity()));


            if (isDeleteMode) {
                dataViewHolder.checkBox.setVisibility(View.VISIBLE);
            } else {
                dataViewHolder.checkBox.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return dataItemList.size() + 1; // Add 1 for the header
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        // Header views (you might need TextViews for "Inventory Item Name" and "Quantity")

        public HeaderViewHolder(View view) {
            super(view);
            // Initialize your header views, e.g., view.findViewById(...)
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(context, columnCount);
        recyclerView.setLayoutManager(layoutManager);
    }
    private void showEditDialog(DataItem currentItem, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_inventory_management, null);

        EditText editName = view.findViewById(R.id.addItemEditText);
        EditText editQuantity = view.findViewById(R.id.addItemQty);


        editName.setText(currentItem.getName());
        editQuantity.setText(String.valueOf(currentItem.getQuantity()));

        builder.setView(view)
                .setTitle("Edit Item")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String updatedName = editName.getText().toString();
                        int updatedQuantity = Integer.parseInt(editQuantity.getText().toString());

                        DataItem updatedItem = new DataItem(updatedName, updatedQuantity);
                        updatedItem.setId(currentItem.getId());
                        DatabaseHelper dbHelper = new DatabaseHelper(context);
                        // Update SQLite database
                        dbHelper.updateItem(updatedItem);

                        // Update dataItemList and RecyclerView
                        dataItemList.set(position, updatedItem);
                        notifyItemChanged(position + 1); // +1 to account for header
                    }
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    public class DataViewHolder extends RecyclerView.ViewHolder {  // Removed static modifier to access outer class members
        TextView dataItemTextView;
        CheckBox checkBox;

        TextView itemNameTextView;
        TextView itemQtyTextView;

        Button editItemButton;

        public DataViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            dataItemTextView = itemView.findViewById(R.id.dataItemTextView);
            checkBox = itemView.findViewById(R.id.itemCheckBox);

            itemQtyTextView = itemView.findViewById(R.id.itemQuantityTextView);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int adjustedPosition = getAdapterPosition() - 1;
                    if (adjustedPosition >= 0 && adjustedPosition < dataItemList.size()) {
                        dataItemList.get(adjustedPosition).setSelected(isChecked);

                        // Triggering the listener if required
                        if (listener != null) {
                            listener.onItemClick(adjustedPosition);
                        }
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adjustedPosition = getAdapterPosition() - 1; // Remember you have a header
                    if(adjustedPosition >= 0 && adjustedPosition < dataItemList.size()) {
                        DataItem currentItem = dataItemList.get(adjustedPosition);
                        showEditDialog(currentItem, adjustedPosition);
                    }
                }
            });

        }

        }
    }

