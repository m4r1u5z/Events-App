package com.baddevelopergames.eventsapp.utils

import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

internal class CustomDividerItemDecoratorTest {
    @Mock
    private lateinit var divider: Drawable

    @Mock
    private lateinit var canvas: Canvas

    @Mock
    private lateinit var parent: RecyclerView

    @Mock
    private lateinit var state: RecyclerView.State

    private lateinit var decorator: CustomDividerItemDecorator

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        decorator = CustomDividerItemDecorator(divider)
    }

    @Test
    fun `create divider if item is not last`() {
        whenever(parent.childCount).thenReturn(2)
        val child = mock<View>()
        whenever(parent.getChildAt(any())).thenReturn(child)
        val lp = mock<RecyclerView.LayoutParams>()
        whenever(child.layoutParams).thenReturn(lp)
        decorator.onDraw(canvas, parent, state)
        verify(divider).draw(canvas)
    }

    @Test
    fun `do not create divider if item is last`() {
        whenever(parent.childCount).thenReturn(1)
        verify(divider, times(0)).draw(canvas)
    }
}