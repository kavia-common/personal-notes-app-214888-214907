package org.example.app.data

import android.content.Context
import androidx.lifecycle.LiveData

/**
 * PUBLIC_INTERFACE
 * NotesRepository exposes CRUD methods and LiveData stream for notes list.
 */
class NotesRepository private constructor(context: Context) {
    private val db = NotesDatabase.getInstance(context)
    private val dao = db.noteDao()

    fun getNotes(): LiveData<List<Note>> = dao.getAll()

    suspend fun getNote(id: Long): Note? = dao.getById(id)

    suspend fun addNote(title: String, content: String): Long {
        return dao.insert(Note(title = title, content = content))
    }

    suspend fun updateNote(note: Note) = dao.update(note)

    suspend fun deleteNote(note: Note) = dao.delete(note)

    companion object {
        @Volatile private var INSTANCE: NotesRepository? = null

        // PUBLIC_INTERFACE
        fun getInstance(context: Context): NotesRepository =
            INSTANCE ?: synchronized(this) {
                NotesRepository(context.applicationContext).also { INSTANCE = it }
            }
    }
}
