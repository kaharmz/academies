package com.example.academy.data.source.remote

import com.example.academy.data.source.remote.response.ContentResponse

interface LoadContentCallback {

    fun onContentReceived(contentResponse: ContentResponse)
}