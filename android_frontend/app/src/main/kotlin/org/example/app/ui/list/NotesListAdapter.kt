package org.example.app.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.example.app.data.Note
import org.example.app.databinding.ItemNoteBinding

/**
 * PUBLIC_INTERFACE
 * NotesListAdapter shows note cards with title and content preview.
 */
class NotesListAdapter(
    private val onClick: (Note) -> Unit,
    private val onDelete: (Note) -> Unit
) : ListAdapter<Note, NotesListAdapter.VH>(Diff) {

    object Diff : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
    }

    inner class VH(val binding: ItemNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Note) {
            binding.title.text = item.title.ifBlank { "(No title)" }
            binding.content.text = item.content
            binding.card.setOnClickListener { onClick(item) }
            binding.btnDelete.setOnClickListener { onDelete(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}
