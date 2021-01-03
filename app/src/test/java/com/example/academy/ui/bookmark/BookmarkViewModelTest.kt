package com.example.academy.ui.bookmark

import AcademyRepository
import com.example.academy.data.CourseEntity
import com.example.academy.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp(){
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmark(){
       `when`(academyRepository.getBookmarkedCourse()).thenReturn(DataDummy.generateDummyCourse())
        val courseEntities = viewModel.getBookmarks()
        verify(academyRepository).getBookmarkedCourse()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities.size)
    }
}