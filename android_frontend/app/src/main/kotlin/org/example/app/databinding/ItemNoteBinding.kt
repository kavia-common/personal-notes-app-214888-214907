package org.example.app.databinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.material.card.MaterialCardView
import org.example.app.R

/**
 * PUBLIC_INTERFACE
 * Minimal ViewBinding surrogate for item_note.xml to keep build passing in environments
 * where ViewBinding generation may be disabled by the experimental DSL.
 */
class ItemNoteBinding private constructor(
    val root: View,
    val card: MaterialCardView,
    val title: TextView,
    val content: TextView,
    val btnDelete: ImageButton
) {
    companion object {
        // PUBLIC_INTERFACE
        fun inflate(inflater: LayoutInflater, parent: ViewGroup, attachToParent: Boolean = false): ItemNoteBinding {
            val view = inflater.inflate(R.layout.item_note, parent, attachToParent)
            val card = view.findViewById<MaterialCardView>(R.id.card)
            val title = view.findViewById<TextView>(R.id.title)
            val content = view.findViewById<TextView>(R.id.content)
            val btnDelete = view.findViewById<ImageButton>(R.id.btnDelete)
            return ItemNoteBinding(view, card, title, content, btnDelete)
        }
    }
}
