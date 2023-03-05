package com.example.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityMainBinding
import com.example.shoppinglist.fragments.FragmentManager
import com.example.shoppinglist.fragments.NoteFragment
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setBottomNavigationView()
    }

    private fun setBottomNavigationView(){
        binding?.bottomNavigationMenu?.setOnItemSelectedListener {
            var mes = ""
            when(it.itemId){
                R.id.settings -> {
                    mes = "Settings"
                }
                R.id.notes-> {
                    FragmentManager.setFragment(NoteFragment.newInstance(), this)
                }
                R.id.shop_list->{
                    mes = "Shop List"
                }
                R.id.new_item->{
                    FragmentManager.currentFragment?.onClickNew()
                }
            }
            true
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}