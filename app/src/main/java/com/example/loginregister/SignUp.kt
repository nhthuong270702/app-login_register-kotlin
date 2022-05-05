package com.example.loginregister

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vishnusivadas.advanced_httpurlconnection.PutData
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        loginText.setOnClickListener {
            // launch the login activity somehow
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

        buttonSignUp.setOnClickListener(View.OnClickListener {

            val fullname = fullname_signup.text.toString()
            val email = email_signup.text.toString()
            val username = username_signup.text.toString()
            val password = password_signup.text.toString()

            progress.setVisibility(View.VISIBLE)
            val handler = Handler()
            handler.post {
                //Starting Write and Read data with URL
                //Creating array for parameters
                val field = arrayOfNulls<String>(4)
                field[0] =" fullname"
                field[1] = "username"
                field[2] = "password"
                field[3] = "email"
                //Creating array for data
                val data = arrayOfNulls<String>(4)
                data[0] = fullname
                data[1] = username
                data[2] = password
                data[3] = email
                val putData = PutData(
                    "http://192.168.1.2/loginregister/signup.php",
                    "POST",
                    field,
                    data
                )
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        progress.setVisibility(View.GONE)
                        val result = putData.result
//                            textView.setText(result)
                        if(result.equals("Sign Up Success")){
                            val intent = Intent(this, Login::class.java)
                            startActivity(intent)
                            finish()
                        }else{
                            Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                //End Write and Read data with URL
            }
        })

    }
}