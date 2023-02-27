package com.example.shoppinglist.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shoppinglist.R
import com.example.shoppinglist.databinding.ActivityMainBinding
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
                    mes = "Notes"
                }
                R.id.shop_list->{
                    mes = "Shop List"
                }
                R.id.new_item->{
                    mes = "New"
                }
            }
            Snackbar.make(binding?.root?.rootView!!, "You clicked on $mes button",Snackbar.LENGTH_SHORT).show()
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        binding = null
    }
}