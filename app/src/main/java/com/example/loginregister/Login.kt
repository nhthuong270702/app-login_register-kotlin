package com.example.loginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.vishnusivadas.advanced_httpurlconnection.PutData
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        registerText.setOnClickListener {
            // launch the login activity somehow
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener(View.OnClickListener {

            val username = username_login.text.toString()
            val password = password_login.text.toString()

            val handler = Handler()
            handler.post {
                //Starting Write and Read data with URL
                //Creating array for parameters
                val field = arrayOfNulls<String>(2)
                field[0] = "username"
                field[1] = "password"
                //Creating array for data
                val data = arrayOfNulls<String>(2)
                data[0] = username
                data[1] = password
                val putData = PutData(
                    "http://192.168.1.2/loginregister/login.php",
                    "POST",
                    field,
                    data
                )
                if (putData.startPut()) {
                    if (putData.onComplete()) {
                        val result = putData.result
//                            textView.setText(result)
                        if(result.equals("Login Success")){
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
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