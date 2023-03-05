package com.example.shoppinglist.viewmodels

import androidx.lifecycle.*
import com.example.shoppinglist.db.MainDataBase
import com.example.shoppinglist.entities.NoteItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(database:MainDataBase): ViewModel() {
    private val dao = database.getDao()

    val allNotes: LiveData<List<NoteItem>> = dao.getAllNotes().asLiveData()

    fun insertNote(noteItem: NoteItem){
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertNote(noteItem)
        }
    }

    fun deleteNote(noteItem: NoteItem){
        viewModelScope.launch(Dispatchers.IO) {
            dao.deleteNote(noteItem)
        }
    }

    fun updateNote(noteItem: NoteItem){
        viewModelScope.launch(Dispatchers.IO) {
            dao.updateNote(noteItem)
        }
    }

    class MainViewModelFactory(private val database: MainDataBase):ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(MainViewModel::class.java)){
                return MainViewModel(database) as T
            }
            throw IllegalAccessException("Unknown ViewModel class")
        }
    }
}