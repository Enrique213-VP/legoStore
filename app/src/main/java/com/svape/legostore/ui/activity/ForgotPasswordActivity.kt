package com.svape.legostore.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.svape.legostore.R
import com.svape.legostore.core.BaseActivity
import com.svape.legostore.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : BaseActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonForgot.setOnClickListener {
            forgotPassWord()
        }
    }

    private fun forgotPassWord() {

        //See the progressBar
        showProgressDialog(resources.getString(R.string.please_wait))

        val email: String = binding.sendEmail.text.toString().trim() { it <= ' ' }

        if (email.isEmpty()) {
            Toast.makeText(
                this@ForgotPasswordActivity,
                "Please enter your email",
                Toast.LENGTH_LONG
            ).show()
        } else {
            FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->

                    //hide the progressBar
                    hideProgressDialog()

                    if (task.isSuccessful) {
                        Toast.makeText(
                            this@ForgotPasswordActivity,
                            "Email sent successfully to reset your password, please, you must check your email address",
                            Toast.LENGTH_LONG
                        ).show()
                        finish()
                    } else {
                        //If the reset is generated a mistake
                        Toast.makeText(
                            this@ForgotPasswordActivity,
                            task.exception!!.message.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }
        }
    }
}