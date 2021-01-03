package com.example.academy.ui.bookmark

import AcademyRepository
import androidx.lifecycle.ViewModel
import com.example.academy.data.CourseEntity


class BookmarkViewModel (private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): List<CourseEntity> = academyRepository.getBookmarkedCourse()
}