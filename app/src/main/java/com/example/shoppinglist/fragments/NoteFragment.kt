package com.example.shoppinglist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.FragmentNoteBinding

class NoteFragment : BaseFragment() {

    private var binding:FragmentNoteBinding? = null
    override fun onClickNew() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentNoteBinding.inflate(inflater)
        return binding?.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NoteFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}