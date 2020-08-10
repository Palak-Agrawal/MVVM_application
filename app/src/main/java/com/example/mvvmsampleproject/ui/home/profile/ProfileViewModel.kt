package com.example.mvvmsampleproject.ui.home.profile

import androidx.lifecycle.ViewModel
import com.example.mvvmsampleproject.data.repository.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()

}