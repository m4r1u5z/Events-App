package com.baddevelopergames.eventsapp.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.baddevelopergames.eventsapp.R
import com.baddevelopergames.eventsapp.favourites.FavouritesFragment
import com.baddevelopergames.eventsapp.eventslist.EventsFragment
import com.baddevelopergames.eventsapp.eventslist.preview.PreviewFragment
import com.baddevelopergames.eventsapp.utils.addFragment
import com.baddevelopergames.eventsapp.utils.showFragment
import com.baddevelopergames.eventsapp.workshops.WorkshopsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    FavouritesFragment.FavouritesFragmentInteractions, MainActivityInteractions {

    companion object {
        fun navigateTo(context: Context) {
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        bottom_navigation.setOnNavigationItemSelectedListener(this)
        savedInstanceState ?: showFragment(EventsFragment.newInstance())
        toolbar.title = getString(R.string.app_name)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bottom_bar_calendar -> {
                showCalendar()
                true
            }
            R.id.bottom_bar_favourites -> {
                showFavourites()
                true
            }
            R.id.bottom_bar_workshops -> {
                showWorkshops()
                true
            }
            else -> false
        }
    }

    private fun showWorkshops() {
        toolbar.title = getString(R.string.workshops)
        showFragment(WorkshopsFragment.newInstance())
    }

    private fun showFavourites() {
        toolbar.title = getString(R.string.favourites)
        showFragment(FavouritesFragment.newInstance())
    }

    private fun showCalendar() {
        toolbar.title = getString(R.string.app_name)
        showFragment(EventsFragment.newInstance())
    }

    private fun showFragment(fragment: Fragment) {
        showFragment(R.id.fragment_container, fragment)
    }

    override fun onEmptyViewClicked() {
        showCalendar()
        bottom_navigation.selectedItemId = R.id.bottom_bar_calendar
    }

    override fun onImageClicked(imageUrl: String) {
        addFragment(R.id.fragment_container, PreviewFragment.newInstance(imageUrl), PreviewFragment.TAG)
    }
}
