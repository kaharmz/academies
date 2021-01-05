import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.academy.data.source.remote.LoadContentCallback
import com.example.academy.data.source.remote.LoadCoursesCallback
import com.example.academy.data.source.remote.LoadModulesCallback
import com.example.academy.data.source.remote.RemoteDataSource
import com.example.academy.utils.DataDummy
import com.example.academy.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class AcademyRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remote)
    private val courseResponses = DataDummy.generateRemoteDummyCourses()
    private val courseId = courseResponses[0].id
    private val moduleResponses = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponses[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LoadCoursesCallback)
                    .onAllCoursesReceived(courseResponses)
            null
        }.`when`(remote).getAllCourse(any())
        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllCourse())
        verify(remote).getAllCourse(any())
        assertNotNull(courseEntities)
        assertEquals(courseResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getAllModulesByCourse() {
        doAnswer { invocation ->
            (invocation.arguments[1] as LoadModulesCallback)
                    .onAllModulesReceived(moduleResponses)
            null
        }.`when`(remote).getModules(eq(courseId), any())
        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllModuleByCourse(courseId))
        verify(remote).getModules(eq(courseId), any())
        assertNotNull(courseEntities)
        assertEquals(moduleResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getBookmarkedCourses() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LoadCoursesCallback)
                    .onAllCoursesReceived(courseResponses)
            null
        }.`when`(remote).getAllCourse(any())
        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourse())
        verify(remote).getAllCourse(any())
        assertNotNull(courseEntities)
        assertEquals(courseResponses.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getContent() {
        doAnswer { invocation ->
            (invocation.arguments[1] as LoadModulesCallback)
                    .onAllModulesReceived(moduleResponses)
            null
        }.`when`(remote).getModules(eq(courseId), any())
        doAnswer { invocation ->
            (invocation.arguments[1] as LoadContentCallback)
                    .onContentReceived(content)
            null
        }.`when`(remote).getContent(eq(moduleId), any())
        val courseEntitiesContent = LiveDataTestUtil.getValue(academyRepository.getContent(courseId, moduleId))
        verify(remote)
                .getModules(eq(courseId), any())
        verify(remote)
                .getContent(eq(moduleId), any())
        assertNotNull(courseEntitiesContent)
        assertNotNull(courseEntitiesContent.contentEntity)
        assertNotNull(courseEntitiesContent.contentEntity?.content)
        assertEquals(content.content, courseEntitiesContent.contentEntity?.content)
    }

    @Test
    fun getCourseWithModules() {
        doAnswer { invocation ->
            (invocation.arguments[0] as LoadCoursesCallback)
                    .onAllCoursesReceived(courseResponses)
            null
        }.`when`(remote).getAllCourse(any())
        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))
        verify(remote).getAllCourse(any())
        assertNotNull(courseEntities)
        assertNotNull(courseEntities.title)
        assertEquals(courseResponses[0].title, courseEntities.title)
    }
}


