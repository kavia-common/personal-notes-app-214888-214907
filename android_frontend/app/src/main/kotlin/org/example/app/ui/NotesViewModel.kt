package org.example.app.ui

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.example.app.data.Note
import org.example.app.data.NotesRepository

/**
 * PUBLIC_INTERFACE
 * NotesViewModel holds UI state for notes list and exposes CRUD operations.
 */
class NotesViewModel(app: Application) : AndroidViewModel(app) {
    private val repo = NotesRepository.getInstance(app)

    val notes: LiveData<List<Note>> = repo.getNotes()

    private val _snackbar = MutableLiveData<String?>()
    val snackbar: LiveData<String?> = _snackbar

    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            repo.addNote(title.trim(), content.trim())
            _snackbar.value = "Note saved"
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            repo.updateNote(note.copy(
                title = note.title.trim(),
                content = note.content.trim(),
                updatedAt = System.currentTimeMillis()
            ))
            _snackbar.value = "Note updated"
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repo.deleteNote(note)
            _snackbar.value = "Note deleted"
        }
    }

    fun consumeSnackbar() {
        _snackbar.value = null
    }
}
