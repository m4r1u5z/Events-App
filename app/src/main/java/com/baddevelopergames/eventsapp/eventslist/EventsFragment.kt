package com.baddevelopergames.eventsapp.eventslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baddevelopergames.eventsapp.R
import com.baddevelopergames.eventsapp.utils.activityNonNull
import kotlinx.android.synthetic.main.fragment_events.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class EventsFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = EventsFragment()
    }

    private val adapter: EventsPagerAdapter by inject { parametersOf(activityNonNull().supportFragmentManager) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_events, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        events_vp.adapter = adapter
        events_vp_tabs.setupWithViewPager(events_vp)
    }
}