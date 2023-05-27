package com.fue.mynotes;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<String> ToDoList;
    android.widget.ArrayAdapter<String> ArrayAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToDoList=new ArrayList<>();
        listView=findViewById(R.id.ListView);
        ArrayAdapter=new ArrayAdapter<String>(this,R.layout.layout_note,R.id.note_layout,ToDoList);
        listView.setAdapter(ArrayAdapter);
        //ToDoList.add("zadv");
        ArrayAdapter.notifyDataSetChanged();
    }
}