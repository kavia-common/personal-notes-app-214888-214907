package org.example.app.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * PUBLIC_INTERFACE
 * Note entity represents a single note with title, content, and timestamp fields.
 */
@Entity(tableName = "notes")
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val title: String,
    val content: String,
    val updatedAt: Long = System.currentTimeMillis()
)
