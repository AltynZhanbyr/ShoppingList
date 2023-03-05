package com.example.shoppinglist.fragments

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.R
import com.example.shoppinglist.activities.MainApp
import com.example.shoppinglist.activities.NewNoteActivity
import com.example.shoppinglist.adapters.NoteAdapter
import com.example.shoppinglist.databinding.FragmentNoteBinding
import com.example.shoppinglist.entities.NoteItem
import com.example.shoppinglist.viewmodels.MainViewModel

class NoteFragment : BaseFragment(), NoteAdapter.OnItemClickListener {

    private var binding:FragmentNoteBinding? = null
    private var viewModel:MainViewModel?= null
    private var adapter: NoteAdapter? = null
    private var editLauncher:ActivityResultLauncher<Intent>? = null
    override fun onClickNew() {
           editLauncher?.launch(Intent(activity, NewNoteActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setEditLauncher()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNoteBinding.inflate(inflater)
        return binding?.root
    }

    private fun setEditLauncher(){
        this.editLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Activity.RESULT_OK){
                val note = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    it.data?.getSerializableExtra(NEW_NOTE_KEY, NoteItem::class.java)
                } else {
                    it.data?.getSerializableExtra(NEW_NOTE_KEY) as NoteItem
                }
                val res = it.data?.getStringExtra(RETURN_NOTE)

                if(res!=null)
                    viewModel?.updateNote(note!!)
                else
                    viewModel?.insertNote(note!!)
            }
        }
    }

    override fun onStart() {
        super.onStart()

        val viewModelFactory = MainViewModel.MainViewModelFactory((context?.applicationContext as MainApp).database)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]


        viewModel?.allNotes?.observe(viewLifecycleOwner){
            adapter?.submitList(it)
            adapter?.notifyDataSetChanged()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.adapter = NoteAdapter(this)
        binding?.noteList?.adapter = adapter
    }

    companion object {
        final const val NEW_NOTE_KEY = "newNoteKey"
        final const val UPDATE_NOTE_KEY = "updateNote"
        final const val RETURN_NOTE = "returnNote"
        @JvmStatic
        fun newInstance() =
            NoteFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onDelete(noteItem: NoteItem) {
        viewModel?.deleteNote(noteItem)
    }

    override fun onClick(noteItem: NoteItem) {
        val intent = Intent(activity, NewNoteActivity::class.java)
        intent.putExtra(UPDATE_NOTE_KEY, noteItem)
        editLauncher?.launch(intent)
    }
}