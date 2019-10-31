package com.baddevelopergames.eventsapp.eventslist

import android.content.Context
import android.content.res.Resources
import com.baddevelopergames.eventsapp.data.model.ActivityItem
import com.baddevelopergames.eventsapp.data.model.LectureItem
import com.baddevelopergames.eventsapp.data.model.WorkshopItem
import com.baddevelopergames.eventsapp.favourites.FavouritesStorage
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class EventsAdapterTest {
    private lateinit var adapter: EventsAdapter
    private val lectureItem = LectureItem("1", "", "", 0L, "", "", false)
    private val workshopItem = WorkshopItem("2", "", "", 0L, "", "")
    private val activityItem = ActivityItem("3", "", "", 0L, "", "", "")
    private val itemsList = mutableListOf(lectureItem, workshopItem, activityItem)

    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var interactions: EventsAdapterInteractions
    @Mock
    private lateinit var favouritesStorage: FavouritesStorage
    @Mock
    private lateinit var resources: Resources
    @Mock
    private lateinit var dateParser: DateParser

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        adapter = EventsAdapter(context, itemsList, interactions, favouritesStorage, resources, dateParser)
    }

    @Test
    fun `check if bind views use proper itemType`() {
        val lectureViewHolder = mock<EventsAdapter.LectureViewHolder>()
        adapter.onBindViewHolder(lectureViewHolder, 0)
        verify(lectureViewHolder).bind(lectureItem)

        val workshopViewHolder = mock<EventsAdapter.WorkshopViewHolder>()
        adapter.onBindViewHolder(workshopViewHolder, 1)
        verify(workshopViewHolder).bind(workshopItem)

        val activityViewHolder = mock<EventsAdapter.ActivityViewHolder>()
        adapter.onBindViewHolder(activityViewHolder, 2)
        verify(activityViewHolder).bind(activityItem)
    }

    @Test
    fun `check if bind views user proper itemType with captor`() {
        val lectureViewHolder = mock<EventsAdapter.LectureViewHolder>()
        adapter.onBindViewHolder(lectureViewHolder, 0)
        val captor1 = argumentCaptor<LectureItem>()
        val value1 = verify(lectureViewHolder).bind(captor1.capture()).run { captor1.firstValue }
        assertEquals("1", value1.id)

        val workshopViewHolder = mock<EventsAdapter.WorkshopViewHolder>()
        adapter.onBindViewHolder(workshopViewHolder, 1)
        val captor2 = argumentCaptor<WorkshopItem>()
        val value2 = verify(workshopViewHolder).bind(captor2.capture()).run { captor2.firstValue }
        assertEquals("2", value2.id)

        val activityViewHolder = mock<EventsAdapter.ActivityViewHolder>()
        adapter.onBindViewHolder(activityViewHolder, 2)
        val captor3 = argumentCaptor<ActivityItem>()
        val value3 = verify(activityViewHolder).bind(captor3.capture()).run { captor3.firstValue }
        assertEquals("3", value3.id)
    }
}