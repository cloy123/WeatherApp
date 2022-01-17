package com.monsieur.cloy.weatherapp.utilits

import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.monsieur.cloy.weatherapp.R

fun replaceFragment(fragment: Fragment, addStack: Boolean = true) {
    /* Функция расширения для AppCompatActivity, позволяет устанавливать фрагменты */
    if (addStack) {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(
                R.id.container,
                fragment
            ).commit()
    } else {
        APP_ACTIVITY.supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                fragment
            ).commit()
    }
    //APP_ACTIVITY.findViewById<ConstraintLayout>(R.id.container).visibility = View.VISIBLE
}

fun changeToolBar(menu: Boolean, homeButton: Boolean, title: String){
    if(toolbarMenu != null && toolbarMenu!!.children.count() > 0){
        for(menuItem in toolbarMenu?.children!!){
            menuItem.isVisible = menu
        }
    }
    if(homeButton){
        addHomeButton()
    }else{
        deleteHomeButton()
    }
    APP_ACTIVITY.supportActionBar?.title = title
}

private fun addHomeButton(){
    val actionBar = APP_ACTIVITY.supportActionBar
    actionBar?.setHomeButtonEnabled(true)
    actionBar?.setDisplayHomeAsUpEnabled(true)
}
private fun deleteHomeButton(){
    val actionBar = APP_ACTIVITY.supportActionBar
    actionBar?.setHomeButtonEnabled(false)
    actionBar?.setDisplayHomeAsUpEnabled(false)
}

fun backButton(){
    APP_ACTIVITY.onBackPressed()
}

fun showToast(message: String){
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_LONG).show()
}