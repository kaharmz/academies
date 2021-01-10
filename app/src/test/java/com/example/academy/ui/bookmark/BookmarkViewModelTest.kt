package com.example.academy.ui.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.utils.DataDummy
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BookmarkViewModelTest {

    private lateinit var viewModel: BookmarkViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<List<CourseEntity>>

    @Before
    fun setUp() {
        viewModel = BookmarkViewModel(academyRepository)
    }

    @Test
    fun getBookmark() {
        val dummyCourse = DataDummy.generateDummyCourse()
        val course = MutableLiveData<List<CourseEntity>>()
        course.value = dummyCourse
        `when`(academyRepository.getBookmarkedCourse()).thenReturn(course)
        val courseEntities = viewModel.getBookmarks().value
        verify(academyRepository).getBookmarkedCourse()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)
        viewModel.getBookmarks().observeForever(observer)
        verify(observer).onChanged(dummyCourse)
    }
}