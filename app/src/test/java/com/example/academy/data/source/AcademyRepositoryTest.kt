package com.example.academy.data.source

import FakeAcademyRepository
import com.example.academy.data.source.remote.RemoteDataSource
import com.example.academy.utils.DataDummy
import junit.framework.TestCase
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class AcademyRepositoryTest : TestCase() {

    private val remote = Mockito.mock(RemoteDataSource::class.java)

    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponse = DataDummy.generateRemoteDummyCourses()

    private val courseId = courseResponse[0].id

    private val moduleResponse = DataDummy.generateRemoteDummyModules(courseId)

    private val moduleId = moduleResponse[0].moduleId

    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun testGetAllCourse() {

        `when`(remote.getAllCourse()).thenReturn(courseResponse)

        val courseEntities = academyRepository.getAllCourse()

        verify(remote).getAllCourse()

        assertNotNull(courseEntities)

        assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun testGetBookmarkedCourse() {

        `when`(remote.getAllCourse()).thenReturn(courseResponse)

        val courseEntities = academyRepository.getBookmarkedCourse()

        verify(remote).getAllCourse()

        assertNotNull(courseEntities)

        assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun testGetCourseWithModules() {

        `when`(remote.getAllCourse()).thenReturn(courseResponse)

        val resultCourse = academyRepository.getCourseWithModules(courseId)

        verify(remote).getAllCourse()

        assertNotNull(resultCourse)

        assertEquals(courseResponse[0].title, resultCourse.title)

    }

    @Test
    fun testGetAllModuleByCourse() {

        `when`(remote.getModules(courseId)).thenReturn(moduleResponse)

        val moduleEntities = academyRepository.getAllModuleByCourse(courseId)

        verify(remote).getModules(courseId)

        assertNotNull(moduleEntities)

        assertEquals(moduleResponse.size.toLong(), moduleEntities.size.toLong())
    }

    @Test
    fun testGetContent() {

        `when`(remote.getModules(courseId)).thenReturn(moduleResponse)

        `when`(remote.getContent(moduleId)).thenReturn(content)

        val resultModule = academyRepository.getContent(courseId, moduleId)

        verify(remote).getContent(moduleId)

        assertNotNull(resultModule)

        assertEquals(content.content, resultModule.contentEntity?.content)
    }
}