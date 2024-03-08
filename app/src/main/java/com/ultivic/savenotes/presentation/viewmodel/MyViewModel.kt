package com.ultivic.savenotes.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ultivic.savenotes.domain.model.NoteDetailsModel
import com.ultivic.savenotes.domain.repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(private val notesRepository: NotesRepository) : ViewModel() {

    var a: NoteDetailsModel? = null

    private val _noteList = MutableLiveData<ArrayList<NoteDetailsModel>>()
    val noteListObservee: LiveData<ArrayList<NoteDetailsModel>>
        get() = _noteList




    init {
        // Initialize the note list with an empty ArrayList
        _noteList.value = ArrayList()
    }

    fun addNotee(noteModel: NoteDetailsModel) = viewModelScope.launch(Dispatchers.IO) {
        notesRepository.insert(noteModel)
        Log.e("noteModel", noteModel.toString())
    }

    fun getAllNotes() = viewModelScope.launch(Dispatchers.IO) {
        val notes = notesRepository.getAllNotes()
        _noteList.postValue(notes as ArrayList<NoteDetailsModel>?)
        Log.e("fruitData", notes.toString())
    }

    fun update(fruit: NoteDetailsModel) = viewModelScope.launch(Dispatchers.IO) {
        Log.e("fruitUpdate", fruit.toString())
        notesRepository.updateNote(fruit)
    }

    fun delete(fruitModel: NoteDetailsModel) = viewModelScope.launch(Dispatchers.IO) {
        notesRepository.deleteNote(fruitModel)
    }

    fun handleSelectedItem(item: NoteDetailsModel) {
        // Process the selected item here
        // You can perform any operations related to the selected item
        // For example, you can pass it to a function, update a LiveData, etc.
        a = item
        Log.e("fff", a.toString() + item)
    }


}