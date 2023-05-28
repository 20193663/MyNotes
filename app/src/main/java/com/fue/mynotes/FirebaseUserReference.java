package com.fue.mynotes;

import android.annotation.SuppressLint;

import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;

public class FirebaseUserReference {
    static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    static FirebaseFirestore FireStore =FirebaseFirestore.getInstance();
    static CollectionReference getCollectionReferenceForNotes(){
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        assert currentUser != null;
        return FireStore.collection("notes")
                .document(currentUser.getUid()).collection("my_notes");
    }

    @SuppressLint("SimpleDateFormat")
    static String timestampToString(Timestamp timestamp){
        return new SimpleDateFormat("MM/dd/yyyy HH:MM:SS aaa").format(timestamp.toDate());
    }
}
