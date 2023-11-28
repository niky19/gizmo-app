package com.example.gizmoapp.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gizmoapp.model.LoginRequest
import com.example.gizmoapp.model.RegisterRequest
import com.example.gizmoapp.model.User
import com.example.gizmoapp.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


open class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<List<User>?>()
    val users: MutableLiveData<List<User>?> = _users

    enum class State {
        LOADING, SUCCESS, ERROR
    }

    private val _registerResult = MutableLiveData<User?>()
    val registerResult: LiveData<User?> = _registerResult

    private val _registerState = MutableLiveData<State>()
    val registerState: LiveData<State> = _registerState

    private val _registerMessage = MutableLiveData<String>()
    val registerMessage: LiveData<String> = _registerMessage

    private val _viewUserProfile = MutableLiveData<User?>()
    val viewUserProfile: LiveData<User?> = _viewUserProfile

    //Registro de nuevo usuario
    fun registerUser(username: String, email: String, password: String) {
        viewModelScope.launch {
            _registerState.value = State.LOADING
            try {
                val response =
                    userRepository.registerUser(RegisterRequest(username, email, password))
               // println("ola")
                println(response)
                if (response.isSuccessful) {
                    _registerResult.value = response.body()
                    _registerState.value = State.SUCCESS
                } else {
                    _registerMessage.value = response.message()
                    _registerState.value = State.ERROR
                }
            } catch (e: Exception) {
                _registerMessage.value = e.message ?: "Unknown error"
                _registerState.value = State.ERROR
            }
        }
    }

    //comunicar el resultado
    private val _loginResult = MutableLiveData<User?>()
    val loginResult: LiveData<User?> = _loginResult

    private val _loginState = MutableLiveData<State>()
    val loginState: LiveData<State> = _loginState

    private val _loginMessage = MutableLiveData<String>()
    val loginMessage: LiveData<String> = _loginMessage

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            _loginState.value = State.LOADING
           // println("wow! mira!")
            println("username: $username")

            val user = userRepository.findUser(LoginRequest(username, password))
            println("user: $user")
            if (user != null) {
                _loginResult.value = user
                _loginState.value = State.SUCCESS
            } else {
                _loginMessage.value = "Usuario o contraseña incorrectos"
                _loginState.value = State.ERROR
            }
        }
    }
}
