package com.example.mvvmsampleproject.data.repository


import androidx.room.Database
import com.example.mvvmsampleproject.data.db.AppDataBase
import com.example.mvvmsampleproject.data.db.entities.User
import com.example.mvvmsampleproject.data.network.MyApi
import com.example.mvvmsampleproject.data.network.SafeApiRequest
import com.example.mvvmsampleproject.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api : MyApi,
    private val db: AppDataBase
) : SafeApiRequest(){

    suspend fun userLogin(email : String, password : String) : AuthResponse {

       /* val loginResponse = MutableLiveData<String>()
         MyApi().userLogin(email,password).enqueue(object :Callback<ResponseBody>{
             override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                loginResponse.value = t.message
             }

             override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                 if(response.isSuccessful){
                     loginResponse.value = response.body()?.toString()
                 }else{
                     loginResponse.value = response.errorBody()?.toString()
                  }


             }
         })
        return loginResponse
         //bad practice use injection*/
        return apiRequest{ api.userLogin(email,password) }
    }

    suspend fun userSignup(
        name: String,
        email :String,
        password: String
    ):AuthResponse{
        return apiRequest { api.userSignup(name,email,password) }
    }

    suspend fun saveUser(user : User) = db.getUserDao().upsert(user)

    fun getUser()= db.getUserDao().getUser()
}