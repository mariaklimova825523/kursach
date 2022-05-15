package com.example.submon;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.Flow;


public class fragment_all extends Fragment {


    DatabaseHelper mDatabaseHelper;

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_all, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDatabaseHelper = new DatabaseHelper(this.getContext());
        listView = view.findViewById(R.id.listView);
//        mDatabaseHelper.onUpgrade(mDatabaseHelper.getWritableDatabase(),0,0);
        populateListView();
        mDatabaseHelper.printColums();
    }

    public void populateListView() {
        Log.d("ListDataActivity", "populateListView: dispalying data in listView");

        Cursor data = mDatabaseHelper.getData();
        ArrayList<Subscription> list = new ArrayList<>();
        while (data.moveToNext()) {
            Subscription sub = new Subscription(data.getInt(0), data.getString(1), data.getString(2), data.getFloat(3), data.getInt(4));
            list.add(sub);
        }
        //TODO: Сделать нормальный вывод списка
        SubListAdapter adapter = new SubListAdapter(getContext(), R.layout.list_item, list, listView);
        listView.setAdapter(adapter);
    }


}