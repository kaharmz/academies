package com.example.academy.data.source

import androidx.lifecycle.LiveData
import com.example.academy.data.source.local.entity.CourseEntity
import com.example.academy.data.source.local.entity.CourseWithModule
import com.example.academy.data.source.local.entity.ModuleEntity
import com.example.academy.vo.Resource

interface AcademyDataSource {

    fun getAllCourse(): LiveData<Resource<List<CourseEntity>>>

    fun getBookmarkedCourse(): LiveData<List<CourseEntity>>

    fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>>

    fun getAllModuleByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>>

    fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>>

    fun setCourseBookmark(course: CourseEntity, state: Boolean)

    fun setReadModule(module: ModuleEntity)
}