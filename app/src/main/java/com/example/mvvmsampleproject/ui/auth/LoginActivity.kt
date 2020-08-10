package com.example.mvvmsampleproject.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmsampleproject.R
import com.example.mvvmsampleproject.data.db.entities.User
import com.example.mvvmsampleproject.databinding.ActivityLoginBinding
import com.example.mvvmsampleproject.ui.home.HomeActivity
import com.example.mvvmsampleproject.util.hide
import com.example.mvvmsampleproject.util.show
import com.example.mvvmsampleproject.util.snackbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity() , AuthListener , KodeinAware{


   override val kodein by kodein()


    private val factory :AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




        val binding : ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        val viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer {
            user-> if(user != null){
            Intent(this, HomeActivity::class.java).also{
                it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(it)
            }

        }
        })
    }

    override fun onStarted() {
        progress_bar.show()

    }

    override fun onSuccess(user: User) {

       /* loginResponse.observe(this, Observer {
            progress_bar.hide()
            toast(it) })*/
        progress_bar.hide()

    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar(message)
    }
}