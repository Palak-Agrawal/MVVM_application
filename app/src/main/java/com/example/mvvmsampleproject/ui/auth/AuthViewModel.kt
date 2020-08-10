package com.example.mvvmsampleproject.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleproject.data.repository.UserRepository
import com.example.mvvmsampleproject.util.ApiException
import com.example.mvvmsampleproject.util.Coroutines
import com.example.mvvmsampleproject.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var name:String? = null
    var email : String? = null
    var password : String? = null
    var authListener :AuthListener? = null
    var passwordConfirm :String? = null
    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view : View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty())
        {
           authListener?.onFailure("Invaild email Id or password")
            return
        }
        Coroutines.main {

              try{
                  val authResponse = repository.userLogin(email!!,password!!)//badPractice dependency ,tight coupling , dependency injection
                  authResponse?.user?.let {
                          authListener?.onSuccess(it)
                            repository.saveUser(it)
                            return@main
                  }
                  authListener?.onFailure(authResponse.message!!)
            }catch(e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch(e: NoInternetException){
                  authListener?.onFailure(e.message!!)
              }

        }


    }

    fun onSignup(view : View){
        Intent(view.context, SignUpActivity:: class.java).also{
            view.context.startActivity(it)
        }
    }

    fun login(view: View){
        Intent(view.context,LoginActivity::class.java).also{
            view.context.startActivity(it)
        }
    }




    fun onSignupButtonClick(view : View){
        authListener?.onStarted()
        if(name.isNullOrEmpty())
        {
            authListener?.onFailure("Name is required")
            return
        }
        if(email.isNullOrEmpty() )
        {
            authListener?.onFailure("email is required")
        }
        if(password.isNullOrEmpty())
        {
            authListener?.onFailure("Please Enter the password")
        }
        if( password != passwordConfirm)
        {
            authListener?.onFailure("Password did not Match")
        }

        Coroutines.main {

            try{
                val authResponse = repository.userSignup(name!!,email!!,password!!)//badPractice dependency ,tight coupling , dependency injection
                authResponse?.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)
            }catch(e: ApiException){
                authListener?.onFailure(e.message!!)
            }catch(e: NoInternetException){
                authListener?.onFailure(e.message!!)
            }

        }


    }
}