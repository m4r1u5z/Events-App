package com.baddevelopergames.eventsapp.favourites

import android.content.Context
import android.content.res.Resources
import androidx.test.core.app.ApplicationProvider
import androidx.test.rule.ActivityTestRule
import com.baddevelopergames.eventsapp.BaseTestBehaviour
import com.baddevelopergames.eventsapp.R
import com.baddevelopergames.eventsapp.data.Items
import com.baddevelopergames.eventsapp.data.model.ActivityItem
import com.baddevelopergames.eventsapp.data.model.LectureItem
import com.baddevelopergames.eventsapp.data.model.WorkshopItem
import com.baddevelopergames.eventsapp.main.MainActivity
import com.baddevelopergames.eventsapp.main.MainScreen
import com.baddevelopergames.eventsapp.workshops.WorkshopsScreen
import com.baddevelopergames.eventsapp.workshops.WorkshopsScreenItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.BlockJUnit4ClassRunner

@RunWith(BlockJUnit4ClassRunner::class)
class FavouritesFragmentTest : BaseTestBehaviour() {
    @get:Rule
    val testRule = ActivityTestRule(MainActivity::class.java)

    private val mainScreen = MainScreen()
    private val favouritesScreen = FavouritesScreen()
    private val workshopsScreen = WorkshopsScreen()
    private lateinit var resources: Resources

    @Before
    override fun setUp() {
        super.setUp()
        resources = ApplicationProvider.getApplicationContext<Context>().resources
        Items.items = listOf(
            LectureItem("1", "www.google.com", "", 1548975600001L, "", "", false),
            LectureItem("2", "www.google.com", "", 1548975600001L, "", "", false),
            LectureItem("3", "www.google.com", "", 1549062000001L, "", "", false),
            WorkshopItem("4", "www.google.com", "", 1549062000001L, "", ""),
            WorkshopItem("5", "www.google.com", "", 1549148400001L, "", ""),
            ActivityItem("6", "www.google.com", "", 1549148400001L, "", "", "")
        )
    }

    @Test
    fun openEmptyFavouritesViewTest() {
        mainScreen {
            bottomNavigation {
                setSelectedItem(R.id.bottom_bar_favourites)
            }
        }
        favouritesScreen {
            placeholder {
                isDisplayed()
                hasText(resources.getString(R.string.no_favourites_text))
            }
        }
    }

    @Test
    fun addItemToFavouritesTest() {
        mainScreen {
            bottomNavigation {
                setSelectedItem(R.id.bottom_bar_favourites)
            }
        }
        favouritesScreen {
            placeholder {
                isDisplayed()
                hasText(resources.getString(R.string.no_favourites_text))
            }
        }
        mainScreen {
            bottomNavigation {
                setSelectedItem(R.id.bottom_bar_workshops)
            }
        }
        workshopsScreen {
            list {
                firstChild<WorkshopsScreenItem> {
                    title {
                        click()
                    }
                    favourites {
                        click()
                    }
                }
            }
        }
        mainScreen {
            bottomNavigation {
                setSelectedItem(R.id.bottom_bar_favourites)
            }
        }
        favouritesScreen {
            placeholder {
                isNotDisplayed()
            }
        }
    }
}