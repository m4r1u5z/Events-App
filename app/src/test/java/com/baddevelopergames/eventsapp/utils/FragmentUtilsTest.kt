package com.baddevelopergames.eventsapp.utils

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.mockito.MockitoAnnotations

internal class FragmentUtilsTest {
    @get:Rule
    val exception: ExpectedException = ExpectedException.none()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `return non null context`() {
        val fragment = mock<Fragment>()
        val context = mock<Context>()
        whenever(fragment.context).thenReturn(context)
        assertNotNull(fragment.contextNonNull())
    }

    @Test
    fun `return null context`() {
        val fragment = mock<Fragment>()
        whenever(fragment.context).thenReturn(null)
        exception.expect(NullPointerException::class.java)
        exception.expectMessage("Context cannot be null")
        fragment.contextNonNull()
    }

    @Test
    fun `return non null activity`() {
        val fragment = mock<Fragment>()
        val activity = mock<FragmentActivity>()
        whenever(fragment.activity).thenReturn(activity)
        assertNotNull(fragment.activityNonNull())
    }

    @Test
    fun `return null activity`() {
        val fragment = mock<Fragment>()
        whenever(fragment.activity).thenReturn(null)
        exception.expect(NullPointerException::class.java)
        exception.expectMessage("Activity cannot be null")
        fragment.activityNonNull()
    }

    @Test
    fun `return non null arguments`() {
        val fragment = mock<Fragment>()
        val args = mock<Bundle>()
        whenever(fragment.arguments).thenReturn(args)
        assertNotNull(fragment.argumentsNonNull())
    }

    @Test
    fun `return null arguments`() {
        val fragment = mock<Fragment>()
        whenever(fragment.arguments).thenReturn(null)
        exception.expect(NullPointerException::class.java)
        exception.expectMessage("Arguments cannot be null")
        fragment.argumentsNonNull()
    }
}