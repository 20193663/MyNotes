package com.fue.mynotes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class note extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Edit_Notes:
                System.out.println("edit_note");
                // Edit Note
                break;
            case R.id.Delete_Notes:
                // Delete Note
                System.out.println("delete_note");
                break;
            case R.id.Logout:
                // Logout
                System.out.println("Logout");
                break;
            default:
                System.err.println("Error in menu selection");
                break;
        }

        return true;
    }
}
