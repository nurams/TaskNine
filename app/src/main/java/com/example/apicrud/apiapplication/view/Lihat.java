package com.example.apicrud.apiapplication.view;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apicrud.apiapplication.Adapter.Adapter;
import com.example.apicrud.apiapplication.R;
import com.example.apicrud.apiapplication.model.getall.DataItem;
import com.example.apicrud.apiapplication.model.getall.GetResponse;
import com.example.apicrud.apiapplication.presenter.Presenter;
import com.example.apicrud.apiapplication.presenter.View;

import java.util.ArrayList;
import java.util.List;

public class Lihat extends AppCompatActivity implements View, Adapter.OnAdapterClickListener {
    private RecyclerView recyclerView;
    private Adapter itemsAdapter;
    private Presenter presenter;
    private List<DataItem> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat);
        list = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_items);
        itemsAdapter = new Adapter(this, list, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(itemsAdapter);
        presenter = new Presenter(this);
        presenter.getAllItems();
    }
        private void deleteDialog(final String id) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Apakah kita Benar Akan Menghapus Item ini?");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.deleteItems(id);
                }
            });
            builder.setNegativeButton("Tidak", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.show();
        }
        private void editDialog(final String id, final String name, final String
                description) {
            LayoutInflater factory = LayoutInflater.from(this);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Tambah Barang");
            final android.view.View textEntryView = factory.inflate(R.layout.text_entry, null);
            final EditText edtName = (EditText)
                    textEntryView.findViewById(R.id.edt_name);
            final EditText edtDescription = (EditText)
                    textEntryView.findViewById(R.id.edt_description);
            edtName.setText(name, TextView.BufferType.EDITABLE);
            edtDescription.setText(description, TextView.BufferType.EDITABLE);
            builder.setView(textEntryView);
            builder.setTitle("Update Barang");
            builder.setPositiveButton("Ya", new DialogInterface.OnClickListener()
            {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    presenter.updateItems(id, edtName.getText().toString(),
                            edtDescription.getText().toString());
                }
            });
            builder.setNegativeButton("Tidak", new
                    DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            builder.show();
        }

        @Override
        protected void onResume() {
            super.onResume();
            presenter.getAllItems();
        }
        @Override
        public void onClicked(String id, String name, String description, String key) {
            if (key.equalsIgnoreCase("edit")) {
                editDialog(id, name, description);
            } else {
                deleteDialog(id);
            }
        }

        @Override
        public void getSuccess(GetResponse list) {
            this.list.clear();
            this.list.addAll(list.getData());
            itemsAdapter.notifyDataSetChanged();
        }

        @Override
        public void setToast(String message) {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            presenter.getAllItems();
        }

        @Override
        public void onError(String errorMessage) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }

        @Override
        public void onFailure(String failureMessage) {
            Toast.makeText(this, failureMessage, Toast.LENGTH_LONG).show();
        }


}
