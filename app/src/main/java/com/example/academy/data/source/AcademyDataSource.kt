package com.example.academy.data.source

import androidx.lifecycle.LiveData
import com.example.academy.data.CourseEntity
import com.example.academy.data.ModuleEntity

interface AcademyDataSource {

    fun getAllCourse(): LiveData<List<CourseEntity>>

    fun getBookmarkedCourse(): LiveData<List<CourseEntity>>

    fun getCourseWithModules(courseId: String): LiveData<CourseEntity>

    fun getAllModuleByCourse(courseId: String): LiveData<List<ModuleEntity>>

    fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity>
}