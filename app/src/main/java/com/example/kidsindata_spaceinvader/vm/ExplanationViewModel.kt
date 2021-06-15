package com.example.kidsindata_spaceinvader.vm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kidsindata_spaceinvader.repository.ExplanationRepository
import kotlinx.coroutines.launch

class ExplanationViewModel(application: Application) : AndroidViewModel(application) {

    private val explanationRepository: ExplanationRepository = ExplanationRepository()

    val text: LiveData<ArrayList<String>> = explanationRepository.text

    fun getText() {
        viewModelScope.launch {
            try {
                explanationRepository.getText() //Execute the getText function, which gets the text from Firestore
            } catch (ex: ExplanationRepository.FirestoreFetchingError) {
                val errorMsg = "Something went wrong while fetching the text"
                Log.e("FIREBASE EXPLANATION", ex.message ?: errorMsg)
            }
        }
    }
}