package com.example.kidsindata_spaceinvader.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout

class ExplanationRepository {

    private val collectionName: String = "strings_explanation"

    private var firestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    private var explanationDocument = firestore.collection(collectionName).document("1")

    private val _text: MutableLiveData<ArrayList<String>> = MutableLiveData()

    val text: LiveData<ArrayList<String>>
        get() = _text

    suspend fun getText() {
        try {
            withTimeout(5_000) {
                val data = explanationDocument
                    .get()
                    .await()

                val textStart = data.getString("explanation_start").toString()
                val text1 = data.getString("explanation_first").toString()
                val text2 = data.getString("explanation_second").toString()
                val text3 = data.getString("explanation_third").toString()

                val tempArray: ArrayList<String> = ArrayList()
                tempArray.add(textStart)
                tempArray.add(text1)
                tempArray.add(text2)
                tempArray.add(text3)
                _text.value = tempArray

            }
        } catch (e: Exception) {
            throw QuizRetrievalError("$e Gaat fout broer")
        }
    }

    class QuizRetrievalError(message: String) : Exception(message)
}