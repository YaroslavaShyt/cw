package com.example.cw

import androidx.lifecycle.ViewModel
import com.example.cw.data.plants.Plant
import com.google.firebase.firestore.FirebaseFirestore

class MainViewModel : ViewModel(){
    val firestore = FirebaseFirestore.getInstance()

    fun addNoteToFirestore(note: Plant) {
        val notesCollection = firestore.collection("notes")
        notesCollection.add(note)
            .addOnSuccessListener {
                // Successfully added
            }
            .addOnFailureListener { exception ->
                // Handle failure
            }
    }



}