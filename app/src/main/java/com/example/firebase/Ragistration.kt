package com.example.firebase

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class Ragistration : AppCompatActivity() {
    lateinit var btn20:Button
    lateinit var btn21:Button
    lateinit var Name: EditText
    lateinit var Emailid: EditText
    lateinit var Password: EditText
    lateinit var address: EditText
    lateinit var phone: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ragistration)
        btn20=findViewById(R.id.btn20)
        btn21=findViewById(R.id.btn21)
        Name=findViewById(R.id.Name)
        Emailid=findViewById(R.id.Emailid)
        Password=findViewById(R.id.Password)
        address=findViewById(R.id.address)
        phone=findViewById(R.id.phone)

        btn21.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        val auth= FirebaseAuth.getInstance()

        btn20.setOnClickListener {
            auth.createUserWithEmailAndPassword(Emailid.text.toString(),Password.text.toString())
                .addOnCompleteListener { task->
                    if (task.isSuccessful){
                        storedata()
                        Log.e("sucessfull","suseccesfull")
                        val result= task.result
                        Log.e("result>>>>",result.toString())
                    }else{
                        Log.e("faile","faile")
                    }

                }
        }
    }

    private fun storedata() {
        val modal=DataModal()
        modal.name=Name.text.toString()
        modal.email=Emailid.text.toString()
        modal.password=Password.text.toString()
        modal.phone=phone.text.toString()
        modal.address=address.text.toString()

        val dp = FirebaseFirestore.getInstance()
        dp.collection("user").add(modal).addOnSuccessListener {
            Log.d(TAG,"DocumentSnapshot added with ID: ${it.id}")
        }.addOnFailureListener {
            Log.w(TAG,"Error adding document",it)
        }
    }
}