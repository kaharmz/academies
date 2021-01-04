package com.example.academy.data.source.remote

import com.example.academy.data.source.remote.response.CourseResponse

interface LoadCoursesCallback {

    fun onAllCoursesReceived(courseResponses: List<CourseResponse>)
}