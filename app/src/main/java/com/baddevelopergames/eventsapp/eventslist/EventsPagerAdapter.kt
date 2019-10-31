package com.baddevelopergames.eventsapp.eventslist

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.baddevelopergames.eventsapp.data.model.Day
import com.baddevelopergames.eventsapp.data.usecase.UseCaseFactory

class EventsPagerAdapter(
    private val tabs: Array<String>,
    fragmentManager: FragmentManager,
    private val useCaseFactory: UseCaseFactory
) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int) = EventsPageFragment.newInstance(
        when (position) {
            0 -> useCaseFactory.getItemsForDayUseCase(day = Day.Friday)
            1 -> useCaseFactory.getItemsForDayUseCase(day = Day.Saturday)
            2 -> useCaseFactory.getItemsForDayUseCase(day = Day.Sunday)
            else -> throw RuntimeException("Passed value is not supported")
        }
    )

    override fun getCount() = useCaseFactory.getDaysCountUseCase()

    override fun getPageTitle(position: Int) = tabs[position]
}