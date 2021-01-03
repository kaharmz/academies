package com.example.academy.ui.academy

import AcademyRepository
import androidx.lifecycle.ViewModel
import com.example.academy.data.CourseEntity


class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getCourse(): List<CourseEntity> = academyRepository.getAllCourse()
}