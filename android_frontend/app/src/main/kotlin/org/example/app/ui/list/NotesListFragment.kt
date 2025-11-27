package org.example.app.ui.list

import android.os.Bundle
import android.view.*
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import org.example.app.R
import org.example.app.data.Note
import org.example.app.databinding.FragmentNotesListBinding
import org.example.app.ui.NotesViewModel
import org.example.app.ui.ViewModelFactory

/**
 * PUBLIC_INTERFACE
 * NotesListFragment displays all notes with search, empty state, and a FAB to add a new note.
 */
class NotesListFragment : Fragment() {

    private var _binding: FragmentNotesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NotesViewModel by viewModels {
        ViewModelFactory(requireActivity().application)
    }

    private val adapter = NotesListAdapter(
        onClick = { note ->
            val action = R.id.action_notesList_to_editor
            findNavController().navigate(action, Bundle().apply { putLong("noteId", note.id) })
        },
        onDelete = { note ->
            viewModel.deleteNote(note)
            Snackbar.make(binding.root, getString(R.string.snack_deleted), Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.undo)) {
                    // Reinsert quickly
                    viewModel.addNote(note.title, note.content)
                }.show()
        }
    )

    private var allNotes: List<Note> = emptyList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNotesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recycler.adapter = adapter
        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.action_notesList_to_editor)
        }

        binding.inputSearch.addTextChangedListener { editable ->
            val query = (editable?.toString() ?: "").trim().lowercase()
            val filtered = if (query.isEmpty()) allNotes else allNotes.filter {
                it.title.lowercase().contains(query) || it.content.lowercase().contains(query)
            }
            adapter.submitList(filtered)
            binding.emptyState.visibility = if (filtered.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.notes.observe(viewLifecycleOwner) {
            allNotes = it
            adapter.submitList(it)
            binding.emptyState.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.snackbar.observe(viewLifecycleOwner) { msg ->
            msg?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_SHORT).show()
                viewModel.consumeSnackbar()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
