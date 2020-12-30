package com.example.academy.ui.bookmark

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Before
    fun setUp(){
        viewModel = BookmarkViewModel()
    }

    @Test
    fun getBookmark(){
        val courseEntities = viewModel.getBookmarks()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}