package com.example.academy.data.source

import com.example.academy.data.CourseEntity
import com.example.academy.data.ModuleEntity

interface AcademyDataSource {

    fun getAllCourse(): List<CourseEntity>

    fun getBookmarkedCourse(): List<CourseEntity>

    fun getCourseWithModules(courseId: String): CourseEntity

    fun getAllModuleByCourse(courseId: String): List<ModuleEntity>

    fun getContent(courseId: String, moduleId: String): ModuleEntity
}