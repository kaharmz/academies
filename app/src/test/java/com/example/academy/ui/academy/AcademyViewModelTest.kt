package com.example.academy.ui.academy

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @Before
    fun setUp(){
        viewModel = AcademyViewModel()
    }

    @Test
    fun getCourses () {
        val courseEntities = viewModel.getCourse()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}