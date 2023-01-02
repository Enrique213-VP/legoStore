package com.svape.legostore.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.svape.legostore.R
import com.svape.legostore.core.BaseActivity
import com.svape.legostore.core.Constants
import com.svape.legostore.core.GlideLoader
import com.svape.legostore.data.model.login.User
import com.svape.legostore.databinding.ActivityUsettingsBinding
import com.svape.legostore.firestore.FireStoreClass

class USettingsActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUsettingsBinding
    private lateinit var mUserDetails: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBar()

        binding.textViewEdit.setOnClickListener(this)
        binding.buttonLogout.setOnClickListener(this)
    }

    private fun setupActionBar() {

        setSupportActionBar(binding.toolbarSettings)
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
        binding.toolbarSettings.setNavigationOnClickListener { onBackPressed() }
    }

    private fun getUserDetails() {
        showProgressDialog(resources.getString(R.string.please_wait))
        FireStoreClass().getUserDetails(this)
    }

    fun userDetailsSuccess(user: User) {

        mUserDetails = user

        hideProgressDialog()

        GlideLoader(this@USettingsActivity).loadUserPicture(user.image, binding.imageViewUserPhoto)
        binding.textViewName.text = "${user.firstname} ${user.lastname}"
        binding.textViewGender.text = user.gender
        binding.textViewEmail.text = user.email
        binding.textViewPhoneNumber.text = "${user.mobile}"
    }

    override fun onResume() {
        super.onResume()
        getUserDetails()
    }

    override fun onClick(v: View?) {
        if(v != null) {
            when(v.id) {
                R.id.textViewEdit -> {
                    val intent = Intent(this@USettingsActivity, UserProfileActivity::class.java)
                    intent.putExtra(Constants.ExtraUserDetails, mUserDetails)
                    startActivity(intent)
                }
                R.id.buttonLogout -> {
                    FirebaseAuth.getInstance().signOut()
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(Intent(this@USettingsActivity, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}