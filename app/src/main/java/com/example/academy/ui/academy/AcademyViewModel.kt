package com.example.academy.ui.academy

import AcademyRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.academy.data.CourseEntity


class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    fun getCourse(): LiveData<List<CourseEntity>> = academyRepository.getAllCourse()
}