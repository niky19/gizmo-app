package com.example.gizmoapp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.gizmoapp.repository.UserRepository

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
