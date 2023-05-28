package com.fue.mynotes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

public class note_Activity extends AppCompatActivity {
    EditText titleEditText, contentEditText;
    TextView date_time;
    ImageButton saveNoteBtn, deleteNoteTextViewBtn, backbtn;
    String title, content, date, docId;
    DocumentReference documentReference;
    boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_note);

        titleEditText = findViewById(R.id.note_title);
        contentEditText = findViewById(R.id.note_content);
        date_time = findViewById(R.id.time_and_date);
        saveNoteBtn = findViewById(R.id.done_button);
        deleteNoteTextViewBtn = findViewById(R.id.delete_note_text_view_btn);
        backbtn = findViewById(R.id.back_button);
        backbtn.setOnClickListener(v -> {startActivity(new Intent(note_Activity.this,MainActivity.class));finish();});

        //receive data
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        date = getIntent().getStringExtra("date");
        docId = getIntent().getStringExtra("docId");

        if (docId != null && !docId.isEmpty()) {
            isEditMode = true;
        }


        if (isEditMode) {
            titleEditText.setText(title);
            contentEditText.setText(content);
            date_time.setText(date);
            deleteNoteTextViewBtn.setVisibility(View.VISIBLE);
        }else{
            date_time.setText(FirebaseUserReference.timestampToString(Timestamp.now()));
        }

        saveNoteBtn.setOnClickListener((v) -> saveNote());

        deleteNoteTextViewBtn.setOnClickListener((v) -> deleteNoteFromFirebase());

    }

    void saveNote() {
        String noteTitle = titleEditText.getText().toString();
        String noteContent = contentEditText.getText().toString();
        if (noteTitle.isEmpty()) {
            titleEditText.setError("Title is required");
            return;
        }
        saveNoteToFirebase(new Note(noteTitle, noteContent,Timestamp.now()));

    }

    void saveNoteToFirebase(Note note) {
        if (isEditMode) {
            //update the note
            documentReference = FirebaseUserReference.getCollectionReferenceForNotes().document(docId);
        } else {
            //create new note
            documentReference = FirebaseUserReference.getCollectionReferenceForNotes().document();
        }
        documentReference.set(note).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //note is added
                Toast.makeText(note_Activity.this, "Note added successfully", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(note_Activity.this, "Failed while adding note", Toast.LENGTH_LONG).show();
            }
        });

    }

    void deleteNoteFromFirebase() {
        documentReference = FirebaseUserReference.getCollectionReferenceForNotes().document(docId);
        documentReference.delete().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //note is deleted
                Toast.makeText(note_Activity.this, "Note deleted successfully", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(note_Activity.this, "Failed while deleting note", Toast.LENGTH_LONG).show();
            }
        });
    }


}