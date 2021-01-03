package com.example.academy.ui.reader.list

import AcademyRepository
import androidx.lifecycle.ViewModel
import com.example.academy.data.ModuleEntity

class CourseReaderViewModel (private val academyRepository: AcademyRepository) : ViewModel() {

    private lateinit var courseId: String

    private lateinit var moduleId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun setSelectedModule(moduleId: String) {
        this.moduleId = moduleId
    }

    fun getModules(): List<ModuleEntity> = academyRepository.getAllModuleByCourse(courseId)

    fun getSelectedModule(): ModuleEntity = academyRepository.getContent(courseId, moduleId)

}