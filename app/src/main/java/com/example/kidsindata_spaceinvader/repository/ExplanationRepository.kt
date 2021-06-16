package com.example.kidsindata_spaceinvader.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout

class ExplanationRepository {

//  Init vars to work with Firestore
    private val collectionName: String = "strings_explanation"
    private val documentName: String = "1"
    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var explanationDocument = firestore.collection(collectionName).document(documentName)

    private val _text: MutableLiveData<ArrayList<String>> = MutableLiveData()

    val text: LiveData<ArrayList<String>>
        get() = _text

    suspend fun getText() {
        try {   //Timeout triggers after 5 seconds
            withTimeout(5000) {
                val data = explanationDocument
                    .get()
                    .await()

//                Set the returned values in the array of text
//                The viewmodels observer receives the data
                _text.value = arrayListOf(
                    data.getString("explanation_start").toString(),
                    data.getString("explanation_first").toString(),
                    data.getString("explanation_second").toString(),
                    data.getString("explanation_third").toString()
                )

            }
        } catch (e: Exception) {
            throw FirestoreFetchingError(e.message.toString())
        }
    }

    class FirestoreFetchingError(message: String) : Exception(message)
}