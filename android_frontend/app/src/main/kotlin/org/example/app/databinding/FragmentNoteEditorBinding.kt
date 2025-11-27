package org.example.app.databinding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.example.app.R

/**
 * PUBLIC_INTERFACE
 * Minimal ViewBinding surrogate for fragment_note_editor.xml
 */
class FragmentNoteEditorBinding private constructor(
    val root: View,
    val inputTitleLayout: TextInputLayout,
    val inputTitle: TextInputEditText,
    val inputContent: TextInputEditText,
    val btnSave: MaterialButton
) {
    companion object {
        // PUBLIC_INTERFACE
        fun inflate(inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean = false): FragmentNoteEditorBinding {
            val view = inflater.inflate(R.layout.fragment_note_editor, parent, attachToParent)
            val titleLayout = view.findViewById<TextInputLayout>(R.id.inputTitleLayout)
            val title = view.findViewById<TextInputEditText>(R.id.inputTitle)
            val content = view.findViewById<TextInputEditText>(R.id.inputContent)
            val btnSave = view.findViewById<MaterialButton>(R.id.btnSave)
            return FragmentNoteEditorBinding(view, titleLayout, title, content, btnSave)
        }
    }
}
