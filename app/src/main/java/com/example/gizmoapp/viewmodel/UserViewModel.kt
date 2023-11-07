package com.example.gizmoapp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gizmoapp.model.LoginRequest
import com.example.gizmoapp.model.RegisterRequest
import com.example.gizmoapp.model.User
import com.example.gizmoapp.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserViewModel (private val userRepository: UserRepository) : ViewModel() {
    private val _users = MutableLiveData<List<User>?>()
    val users: MutableLiveData<List<User>?> = _users

    private val _registeredUsers = MutableLiveData<User?>()
    val registeredUsers: MutableLiveData<User?> = _registeredUsers

    private val _registerStatus = MutableLiveData<Boolean>()
    val registerStatus: LiveData<Boolean> = _registerStatus


    // Devuelve la lista de usuarios
    fun getUsers() {
        print("hola")
        CoroutineScope(Dispatchers.IO).launch {
            val response = userRepository.getUsers()
            withContext(Dispatchers.Main) {
                _users.value = response
            }
        }
    }

    //Registro de nuevo usuario
    fun registerUser(username: String, email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = userRepository.registerUser(RegisterRequest(username, email, password))
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _registeredUsers.value = response.body()
                }
            }
        }
    }

    fun loginUser(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = userRepository.findUser(LoginRequest(username, password))
            if (user != null) {

            } else {
                //tostadita de error que pondre en LoginForm
            }
        }
    }

}