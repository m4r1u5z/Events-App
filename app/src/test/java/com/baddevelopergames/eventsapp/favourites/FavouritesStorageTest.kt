package com.baddevelopergames.eventsapp.favourites

import android.content.Context
import android.content.SharedPreferences
import com.baddevelopergames.eventsapp.data.model.base.EventsAppItem
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class FavouritesStorageTest {
    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var sp: SharedPreferences

    private lateinit var favouritesStorage: FavouritesStorage

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        favouritesStorage = FavouritesStorage(context, sp)
    }

    @Test
    fun `test get key method`() {
        val item = mock<EventsAppItem>().apply {
            whenever(this.id).thenReturn("123")
        }
        val key = favouritesStorage.getKey(item)
        assertEquals("favourite_123", key)
    }

    @Test
    fun `value is added to shared preferences`() {
        val item = mock<EventsAppItem>().apply {
            whenever(this.id).thenReturn("123")
        }
        val editor = mock<SharedPreferences.Editor>()
        whenever(sp.edit()).thenReturn(editor)
        favouritesStorage.setFavourite(item, true)
        verify(sp).edit()
        verify(editor).putBoolean(any(), any())
        verify(editor).apply()
    }

    @Test
    fun `check if item is favourite`() {
        val item = mock<EventsAppItem>().apply {
            whenever(this.id).thenReturn("123")
        }
        whenever(sp.getBoolean("favourite_123", false)).thenReturn(false)
        assertFalse(favouritesStorage.isFavourite(item))
        whenever(sp.getBoolean("favourite_123", false)).thenReturn(true)
        assertTrue(favouritesStorage.isFavourite(item))
    }
}