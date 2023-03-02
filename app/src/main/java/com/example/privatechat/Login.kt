package com.example.privatechat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var edtemail:EditText
    private lateinit var edtpassword:EditText
    private lateinit var btnlogin:Button
    private lateinit var btnsignup:Button
    private lateinit var mAuth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth= FirebaseAuth.getInstance()
        edtemail=findViewById(R.id.edtemail)
        edtpassword=findViewById(R.id.edtpassword)
        btnlogin=findViewById(R.id.btnlogin)
        btnsignup=findViewById(R.id.btnsignup)

        btnsignup.setOnClickListener {
            val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            val email=edtemail.text.toString()
            val password=edtpassword.text.toString()
             login(email,password);
        }

    }
    private fun login(email:String,password:String)
    {//logic for logging in
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent=Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)


                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Login,"user does not exist",Toast.LENGTH_SHORT).show()


                }
            }

    }
}