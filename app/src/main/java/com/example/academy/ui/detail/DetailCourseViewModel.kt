package com.example.academy.ui.detail

import AcademyRepository
import androidx.lifecycle.ViewModel
import com.example.academy.data.CourseEntity
import com.example.academy.data.ModuleEntity

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): CourseEntity = academyRepository.getCourseWithModules(courseId)

    fun getModules(): List<ModuleEntity> = academyRepository.getAllModuleByCourse(courseId)
}