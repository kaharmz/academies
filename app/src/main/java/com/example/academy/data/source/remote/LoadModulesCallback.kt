package com.example.academy.data.source.remote


import com.example.academy.data.source.remote.response.ModuleResponse

interface LoadModulesCallback {

    fun onAllModulesReceived(moduleResponses: List<ModuleResponse>)
}