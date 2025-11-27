package org.example.app.databinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import org.example.app.R

/**
 * PUBLIC_INTERFACE
 * Minimal ViewBinding surrogate for fragment_notes_list.xml
 */
class FragmentNotesListBinding private constructor(
    val root: View,
    val inputSearch: TextInputEditText,
    val recycler: RecyclerView,
    val emptyState: LinearLayout,
    val fabAdd: FloatingActionButton
) {
    companion object {
        // PUBLIC_INTERFACE
        fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean = false): FragmentNotesListBinding {
            val view = inflater.inflate(R.layout.fragment_notes_list, parent, attachToParent)
            val inputSearch = view.findViewById<TextInputEditText>(R.id.inputSearch)
            val recycler = view.findViewById<RecyclerView>(R.id.recycler)
            val emptyRoot = view.findViewById<LinearLayout>(R.id.emptyState)
            val fab = view.findViewById<FloatingActionButton>(R.id.fabAdd)
            return FragmentNotesListBinding(view, inputSearch, recycler, emptyRoot, fab)
        }
    }
}
