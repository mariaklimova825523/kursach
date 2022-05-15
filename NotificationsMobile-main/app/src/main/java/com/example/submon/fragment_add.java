package com.example.submon;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link fragment_add#newInstance} factory method to
// * create an instance of this fragment.
// */
public class fragment_add extends Fragment {


    DatabaseHelper mDatabaseHelper;

    EditText nameTextEdit;
    EditText infoTextEdit;
    EditText priceTextEdit;
    EditText dateTextEdit;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("fragment_add","created");
        mDatabaseHelper = new DatabaseHelper(this.getContext());
        nameTextEdit = getView().findViewById(R.id.nameTe);
        infoTextEdit = getView().findViewById(R.id.infoTe);
        priceTextEdit = getView().findViewById(R.id.priceTe);
        dateTextEdit = getView().findViewById(R.id.dateTe);
        button = getView().findViewById(R.id.addToDbButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newEntryName = nameTextEdit.getText().toString();
                String newEntryInfo = infoTextEdit.getText().toString();
                String newEntryPrice = priceTextEdit.getText().toString();
                String newEntryDate = dateTextEdit.getText().toString();
                if (newEntryInfo.length() > 0 && newEntryPrice.length() > 0 && newEntryName.length() > 0 && newEntryDate.length() > 0) {
                    AddData(newEntryName, newEntryInfo, Float.valueOf(newEntryPrice), Integer.parseInt(newEntryDate));
                    nameTextEdit.setText("");
                    infoTextEdit.setText("");
                    priceTextEdit.setText("");
                    dateTextEdit.setText("");
                } else {
                    toastMessage("Missing input");
                }
            }
        });
    }


    public void AddData(String name, String description, Float price, int day) {
        boolean insertData = mDatabaseHelper.addData(name, description, price, day);

        if (insertData) {
            toastMessage("Data inserted");
        } else {
            toastMessage("Data insert ERR");
        }
    }

    private void toastMessage(String msg) {
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}