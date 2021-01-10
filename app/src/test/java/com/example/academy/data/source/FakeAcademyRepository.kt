import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.academy.data.source.AcademyDataSource
import com.example.academy.data.source.local.entity.ContentEntity
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.data.source.local.entity.ModuleEntity
import com.example.academy.data.source.remote.RemoteDataSource
import com.example.academy.data.source.remote.response.ContentResponse
import com.example.academy.data.source.remote.response.CourseResponse
import com.example.academy.data.source.remote.response.ModuleResponse

class FakeAcademyRepository(private val remoteDataSource: RemoteDataSource) :
    AcademyDataSource {

    override fun getAllCourse(): LiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourse(object : LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                courseResponses.mapTo(courseList) {
                    CourseEntity(
                        it.id,
                        it.title,
                        it.description,
                        it.date,
                        false,
                        it.imagePath
                    )
                }
                courseResults.postValue(courseList)
            }
        })
        return courseResults
    }

    override fun getBookmarkedCourse(): LiveData<List<CourseEntity>> {
        val courseResults = MutableLiveData<List<CourseEntity>>()
        remoteDataSource.getAllCourse(object : LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                val courseList = ArrayList<CourseEntity>()
                courseResponses.mapTo(courseList) {
                    CourseEntity(
                        it.id,
                        it.title,
                        it.description,
                        it.date,
                        false,
                        it.imagePath
                    )
                }
                courseResults.postValue(courseList)
            }
        })
        return courseResults
    }

    override fun getCourseWithModules(courseId: String): LiveData<CourseEntity> {
        val courseResult = MutableLiveData<CourseEntity>()
        remoteDataSource.getAllCourse(object : LoadCoursesCallback {
            override fun onAllCoursesReceived(courseResponses: List<CourseResponse>) {
                lateinit var course: CourseEntity
                courseResponses
                    .asSequence()
                    .filter { it.id == courseId }
                    .forEach {
                        course = CourseEntity(
                            it.id,
                            it.title,
                            it.description,
                            it.date,
                            false,
                            it.imagePath
                        )
                    }
                courseResult.postValue(course)
            }
        })
        return courseResult
    }

    override fun getAllModuleByCourse(courseId: String): LiveData<List<ModuleEntity>> {
        val moduleResults = MutableLiveData<List<ModuleEntity>>()
        remoteDataSource.getModules(courseId, object : LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                val moduleList = ArrayList<ModuleEntity>()
                moduleResponses.mapTo(moduleList) {
                    ModuleEntity(
                        it.moduleId,
                        it.courseId,
                        it.title,
                        it.position,
                        false
                    )
                }
                moduleResults.postValue(moduleList)
            }
        })
        return moduleResults
    }

    override fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity> {
        val moduleResult = MutableLiveData<ModuleEntity>()
        remoteDataSource.getModules(courseId, object : LoadModulesCallback {
            override fun onAllModulesReceived(moduleResponses: List<ModuleResponse>) {
                lateinit var module: ModuleEntity
                loop@ for (response in moduleResponses) {
                    when (response.moduleId) {
                        moduleId -> {
                            module = ModuleEntity(
                                response.moduleId,
                                response.courseId,
                                response.title,
                                response.position,
                                false
                            )
                            remoteDataSource.getContent(moduleId, object : LoadContentCallback {
                                override fun onContentReceived(contentResponse: ContentResponse) {
                                    module.contentEntity = ContentEntity(contentResponse.content)
                                    moduleResult.postValue(module)
                                }
                            })
                            break@loop
                        }
                    }
                }
            }
        })
        return moduleResult
    }
}