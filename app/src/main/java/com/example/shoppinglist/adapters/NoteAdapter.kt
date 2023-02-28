package com.example.shoppinglist.adapters

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.NoteListItemBinding
import com.example.shoppinglist.entities.NoteItem

class NoteAdapter:ListAdapter<NoteItem, NoteAdapter.ItemHolder>(ItemComparator()) {

    class ItemHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding = NoteListItemBinding.bind(view)

        fun bind(noteItem: NoteItem){
            with(binding) {
                this.noteDescription.text = noteItem.content
                this.noteTime.text = noteItem.time
                this.noteTitle.text = noteItem.title
            }
        }
        companion object{
            fun createItemViewHolder(parent: ViewGroup):ItemHolder{
                return ItemHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.note_list_item, parent,false)
                )
            }
        }
    }

    class ItemComparator:DiffUtil.ItemCallback<NoteItem>(){
        override fun areItemsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem.id==newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteItem, newItem: NoteItem): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder.createItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(getItem(position))
    }
}