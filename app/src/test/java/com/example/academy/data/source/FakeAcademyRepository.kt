import com.example.academy.data.ContentEntity
import com.example.academy.data.CourseEntity
import com.example.academy.data.ModuleEntity
import com.example.academy.data.source.AcademyDataSource
import com.example.academy.data.source.remote.RemoteDataSource

class FakeAcademyRepository (private val remoteDataSource: RemoteDataSource) :
        AcademyDataSource {

    override fun getAllCourse(): ArrayList<CourseEntity> {
        val courseResponses = remoteDataSource.getAllCourse()
        val courseList = ArrayList<CourseEntity>()
        courseResponses.mapTo(courseList) {
            CourseEntity(it.id,
                    it.title,
                    it.description,
                    it.date,
                    false,
                    it.imagePath)
        }
        return courseList
    }

    override fun getBookmarkedCourse(): ArrayList<CourseEntity> {
        val courseResponses = remoteDataSource.getAllCourse()
        val courseList = ArrayList<CourseEntity>()
        courseResponses.mapTo(courseList) {
            CourseEntity(
                    it.id,
                    it.title,
                    it.description,
                    it.date,
                    false,
                    it.imagePath)
        }
        return courseList
    }

    override fun getCourseWithModules(courseId: String): CourseEntity {
        val courseResponses = remoteDataSource.getAllCourse()
        lateinit var course: CourseEntity
        courseResponses
                .asSequence()
                .filter { it.id == courseId }
                .forEach {
                    course = CourseEntity(it.id,
                            it.title,
                            it.description,
                            it.date,
                            false,
                            it.imagePath)
                }
        return course
    }

    override fun getAllModuleByCourse(courseId: String): ArrayList<ModuleEntity> {
        val moduleResponses = remoteDataSource.getModules(courseId)
        val moduleList = ArrayList<ModuleEntity>()
        moduleResponses.mapTo(moduleList) {
            ModuleEntity(it.moduleId,
                    it.courseId,
                    it.title,
                    it.position,
                    false)
        }
        return moduleList
    }


    override fun getContent(courseId: String, moduleId: String): ModuleEntity {
        val moduleResponses = remoteDataSource.getModules(courseId)
        lateinit var module: ModuleEntity
        for (response in moduleResponses) {
            if (response.moduleId == moduleId) {
                module = ModuleEntity(response.moduleId,
                        response.courseId,
                        response.title,
                        response.position,
                        false)
                module.contentEntity = ContentEntity(remoteDataSource.getContent(moduleId).content)
                break
            }
        }
        return module
    }
}