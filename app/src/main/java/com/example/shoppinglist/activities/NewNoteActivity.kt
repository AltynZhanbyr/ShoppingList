package com.example.shoppinglist.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.shoppinglist.R
import com.example.shoppinglist.adapters.NoteAdapter
import com.example.shoppinglist.databinding.ActivityNewNoteBinding
import com.example.shoppinglist.entities.NoteItem
import com.example.shoppinglist.fragments.NoteFragment
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
import java.util.logging.SimpleFormatter

class NewNoteActivity : AppCompatActivity() {
    private var binding:ActivityNewNoteBinding?=null
    private var note:NoteItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewNoteBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        actionBarSettings()
        val item  = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(NoteFragment.UPDATE_NOTE_KEY ,NoteItem::class.java)
        } else {
            intent.getSerializableExtra(NoteFragment.UPDATE_NOTE_KEY)
        }

        if(item!=null) {
            note = item as NoteItem
        }

        if(note!=null){
            binding?.apply {
                this.edTitle.setText(note?.title)
                this.edDescription.setText(note?.content)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nw_note_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.save){
            setResult()
        } else if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setResult(){
        var newNote:NoteItem? = null
        newNote = if(note!=null) {
            intent.putExtra(NoteFragment.RETURN_NOTE, "note")
            updateNote()
        } else
            createNewNote()

        intent.putExtra(NoteFragment.NEW_NOTE_KEY, newNote)
        setResult(RESULT_OK ,intent)
        finish()
    }

    private fun updateNote():NoteItem?{
        return note?.copy(
            title = binding?.edTitle?.text.toString(),
            content = binding?.edDescription?.text.toString()
        )
    }

    private fun createNewNote():NoteItem{
        val title = binding?.edTitle?.text.toString()
        val description = binding?.edDescription?.text.toString()

        return NoteItem(
            null,
            title,
            description,
            getCurrentTime(),
            ""
        )
    }

    private fun getCurrentTime():String{
        val formatter  = SimpleDateFormat("hh:mm:ss - yyyy/MM/dd", Locale.getDefault())

        return formatter.format(Calendar.getInstance().time)
    }

    private fun actionBarSettings(){
        var ab = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}