package com.example.shoppinglist.fragments

import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglist.R

object FragmentManager {
    var currentFragment:BaseFragment? = null

    fun setFragment(baseFragment: BaseFragment, activity:AppCompatActivity){
        var transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.place_holder, baseFragment)
        transaction.commit()
        currentFragment = baseFragment
    }
}