package com.baddevelopergames.eventsapp.main

import com.agoda.kakao.bottomnav.KBottomNavigationView
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.screen.Screen
import com.baddevelopergames.eventsapp.R

class MainScreen : Screen<MainScreen>() {
    val toolbar = KView { withId(R.id.toolbar) }
    val bottomNavigation = KBottomNavigationView { withId(R.id.bottom_navigation) }
}