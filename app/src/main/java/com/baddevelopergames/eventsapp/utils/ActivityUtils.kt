package com.baddevelopergames.eventsapp.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun AppCompatActivity.showFragment(container: Int, fragment: Fragment, tag: String? = null) {
    supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    supportFragmentManager.beginTransaction()
        .replace(container, fragment, tag)
        .commit()
}

fun AppCompatActivity.addFragment(container: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction()
        .add(container, fragment, tag)
        .addToBackStack(tag)
        .commit()
}