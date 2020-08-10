package com.example.mvvmsampleproject.data.network.responses

import com.example.mvvmsampleproject.data.db.entities.User

data class AuthResponse(
    val isSuccessful : Boolean?,
    val message : String?,
    val user : User?
)