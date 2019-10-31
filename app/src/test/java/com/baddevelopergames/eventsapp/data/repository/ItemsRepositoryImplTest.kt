package com.baddevelopergames.eventsapp.data.repository

import android.content.Context
import com.baddevelopergames.eventsapp.data.Items
import com.baddevelopergames.eventsapp.data.model.*
import com.baddevelopergames.eventsapp.favourites.FavouritesStorage
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ItemsRepositoryImplTest {
    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var favouritesStorage: FavouritesStorage

    private lateinit var itemsRepository: ItemsRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        itemsRepository = ItemsRepositoryImpl(context, favouritesStorage)
        createMockItems()
    }

    private fun createMockItems() {
        Items.items = listOf(
            LectureItem("1", "", "", 1548975600001L, "", "", false),
            LectureItem("2", "", "", 1548975600001L, "", "", false),
            LectureItem("3", "", "", 1549062000001L, "", "", false),
            WorkshopItem("4", "", "", 1549062000001L, "", ""),
            WorkshopItem("5", "", "", 1549148400001L, "", ""),
            ActivityItem("6", "", "", 1549148400001L, "", "", "")
        )
    }

    @Test
    fun `fetch items test`() {
        val allItems = itemsRepository()
        val allItemsSize = allItems.size
        assertEquals(6, allItemsSize)

        val eventItems = itemsRepository(type = ItemType.ACTIVITY)
        val eventItemsSize = eventItems.size
        assertEquals(1, eventItemsSize)

        val lectureItems = itemsRepository(type = ItemType.LECTURE)
        val lectureItemsSize = lectureItems.size
        assertEquals(3, lectureItemsSize)

        val workshopItems = itemsRepository(type = ItemType.WORKSHOP)
        val workshopItemsSize = workshopItems.size
        assertEquals(2, workshopItemsSize)

        assertEquals(allItemsSize, eventItemsSize + lectureItemsSize + workshopItemsSize)
    }

    @Test
    fun `fetch items by day`() {
        val all = itemsRepository()
        val allSize = all.size
        assertEquals(6, allSize)

        val day1 = itemsRepository(day = Day.Friday)
        val day1Size = day1.size
        assertEquals(2, day1Size)

        val day2 = itemsRepository(day = Day.Saturday)
        val day2Size = day2.size
        assertEquals(2, day2Size)

        val day3 = itemsRepository(day = Day.Sunday)
        val day3Size = day3.size
        assertEquals(2, day3Size)

        assertEquals(allSize, day1Size + day2Size + day3Size)
    }

    @Test
    fun `favourites fetching test`() {
        whenever(favouritesStorage.getAll()).thenReturn(listOf("1", "2"))

        val all = itemsRepository(onlyFavourites = false)
        val allSize = all.size
        assertEquals(6, allSize)

        val favourites = itemsRepository(onlyFavourites = true)
        val favouritesSize = favourites.size
        assertEquals(2, favouritesSize)
    }
}