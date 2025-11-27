package org.example.app.ui.editor

import android.os.Bundle
import android.view.*
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.example.app.R
import org.example.app.data.Note
import org.example.app.data.NotesRepository
import org.example.app.databinding.FragmentNoteEditorBinding
import org.example.app.ui.NotesViewModel
import org.example.app.ui.ViewModelFactory

/**
 * PUBLIC_INTERFACE
 * NoteEditorFragment allows creating and editing a note with validation.
 */
class NoteEditorFragment : Fragment() {

    private var _binding: FragmentNoteEditorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NotesViewModel by viewModels {
        ViewModelFactory(requireActivity().application)
    }

    private var editingId: Long = -1
    private var loadedNote: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editingId = arguments?.getLong("noteId", -1) ?: -1
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNoteEditorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.inputTitle.addTextChangedListener { validate() }
        binding.inputContent.addTextChangedListener { validate() }

        if (editingId > 0) {
            viewLifecycleOwner.lifecycleScope.launch {
                val repo = NotesRepository.getInstance(requireContext())
                loadedNote = withContext(Dispatchers.IO) { repo.getNote(editingId) }
                loadedNote?.let {
                    binding.inputTitle.setText(it.title)
                    binding.inputContent.setText(it.content)
                }
            }
        }
        binding.btnSave.setOnClickListener { saveNote() }
    }

    private fun validate() {
        val titleOk = binding.inputTitle.text?.isNotBlank() == true
        val contentOk = binding.inputContent.text?.isNotBlank() == true
        binding.btnSave.isEnabled = titleOk || contentOk
        binding.inputTitleLayout.error = if (!titleOk && !contentOk) getString(R.string.validation_need_title_or_content) else null
    }

    private fun saveNote() {
        val title = binding.inputTitle.text?.toString() ?: ""
        val content = binding.inputContent.text?.toString() ?: ""
        if (editingId > 0 && loadedNote != null) {
            viewModel.updateNote(loadedNote!!.copy(title = title, content = content))
        } else {
            if (title.isBlank() && content.isBlank()) {
                binding.inputTitleLayout.error = getString(R.string.validation_need_title_or_content)
                return
            }
            viewModel.addNote(title, content)
        }
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
