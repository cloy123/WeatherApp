package com.monsieur.cloy.weatherapp.utilits

import android.widget.Toast
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.monsieur.cloy.weatherapp.R
import java.time.DayOfWeek

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

fun changeToolBar(menu: Boolean, homeButton: Boolean, title: String) {
    if (toolbarMenu != null && toolbarMenu!!.children.count() > 0) {
        for (menuItem in toolbarMenu?.children!!) {
            menuItem.isVisible = menu
        }
    }
    if (homeButton) {
        addHomeButton()
    } else {
        deleteHomeButton()
    }
    APP_ACTIVITY.supportActionBar?.title = title
}

private fun addHomeButton() {
    val actionBar = APP_ACTIVITY.supportActionBar
    actionBar?.setHomeButtonEnabled(true)
    actionBar?.setDisplayHomeAsUpEnabled(true)
}

private fun deleteHomeButton() {
    val actionBar = APP_ACTIVITY.supportActionBar
    actionBar?.setHomeButtonEnabled(false)
    actionBar?.setDisplayHomeAsUpEnabled(false)
}

fun backButton() {
    APP_ACTIVITY.onBackPressed()
}

fun showToast(message: String) {
    Toast.makeText(APP_ACTIVITY, message, Toast.LENGTH_LONG).show()
}

fun getPopIconId(pop: Int): Int {
    return if (pop >= 60) {
        R.drawable.blob_1
    } else if (pop >= 20 && pop < 60) {
        R.drawable.blob_2
    } else {
        R.drawable.blob_3
    }
}

fun getWeatherIconId(iconName: String):Int{
    return when(iconName){
        "01d"-> R.drawable.icon01d
        "02d"-> R.drawable.icon02d
        "03d"-> R.drawable.icon03d
        "04d"-> R.drawable.icon04d
        "09d"-> R.drawable.icon09d
        "10d"-> R.drawable.icon10d
        "11d"-> R.drawable.icon11d
        "13d"-> R.drawable.icon13d
        "50d"-> R.drawable.icon50d
        else -> R.drawable.icon01d
    }
}

fun getDayOfWeek(dayOfWeek: DayOfWeek): String{
    return when(dayOfWeek){
        DayOfWeek.MONDAY -> APP_ACTIVITY.getString(R.string.monday)
        DayOfWeek.TUESDAY -> APP_ACTIVITY.getString(R.string.tuesday)
        DayOfWeek.WEDNESDAY -> APP_ACTIVITY.getString(R.string.wednesday)
        DayOfWeek.THURSDAY -> APP_ACTIVITY.getString(R.string.thursday)
        DayOfWeek.FRIDAY -> APP_ACTIVITY.getString(R.string.friday)
        DayOfWeek.SATURDAY -> APP_ACTIVITY.getString(R.string.saturday)
        DayOfWeek.SUNDAY -> APP_ACTIVITY.getString(R.string.sunday)
    }
}