package com.example.firebase

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var btn:Button
    lateinit var btn2:Button
    lateinit var editTextemail: EditText
    lateinit var editTextpassward: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn=findViewById(R.id.btn)
        editTextemail=findViewById(R.id.emailEt)
        editTextpassward=findViewById(R.id.passwordEt)
        btn2=findViewById(R.id.btn2)
        btn2.setOnClickListener {
            intent= Intent(this,Ragistration::class.java)
            startActivity(intent)
        }


        val auth=FirebaseAuth.getInstance()

        btn.setOnClickListener {
            auth.signInWithEmailAndPassword(editTextemail.text.toString(),editTextpassward.text.toString())
                .addOnCompleteListener { task->
                if (task.isSuccessful){
                    Log.e("sucessfull","suseccesfull")
                    val result= task.result
                    Log.e("result>>>>",result.toString())
                }else{
                    Log.e("faile","faile")
                }

            }
        }

//        FirebaseApp.initializeApp(this)
//        val auth = FirebaseAuth.getInstance()
//        btn.setOnClickListener {
//
//            auth.createUserWithEmailAndPassword(editTextemail.text.toString(),editTextpassward.text.toString())
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        Log.e(TAG, "Successfully signed in with email link!")
//                        val result = task.result
//                        // You can access the new user via result.getUser()
//                        // Additional user info profile *not* available via:
//                        // result.getAdditionalUserInfo().getProfile() == null
//                        // You can check if the user is new or existing:
//                        // result.getAdditionalUserInfo().isNewUser()
//                    } else {
//                        Log.e(TAG, "Error signing in with email link", task.exception)
//                    }
//                }
//        }




    }
}