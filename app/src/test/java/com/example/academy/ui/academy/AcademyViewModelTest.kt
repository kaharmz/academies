package com.example.academy.ui.academy

import AcademyRepository
import com.example.academy.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AcademyViewModelTest {

    private lateinit var viewModel: AcademyViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository



    @Before
    fun setUp(){
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourses () {
        `when`(academyRepository.getAllCourse()).thenReturn(DataDummy.generateDummyCourse())
        val courseEntities = viewModel.getCourse()
        verify(academyRepository).getAllCourse()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}