package com.cloudinteractive.noodeoassignment.ui.userInfo

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.cloudinteractive.noodeoassignment.R
import com.cloudinteractive.noodeoassignment.databinding.ActivityUserInfoBinding
import com.cloudinteractive.noodeoassignment.repository.NetworkResponse
import com.cloudinteractive.noodeoassignment.singleton.UserInfo
import com.cloudinteractive.noodeoassignment.ui.viewBinding
import java.lang.IllegalArgumentException

class UserInfoActivity : AppCompatActivity() {

    private val binding: ActivityUserInfoBinding by viewBinding(ActivityUserInfoBinding::inflate)
    private val viewModel: UserInfoViewModel by viewModels()
    private val timezoneList = (-12..14).toList()
    private val adapter by lazy { ArrayAdapter(this, R.layout.item_timezone, timezoneList) }

    private val timezoneTextWatcher by lazy {
        object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != UserInfo.info!!.timezone.toString()) {
                    binding.tlTimezone.editText!!.isEnabled = false
                    binding.pbLoading.visibility = VISIBLE
                    viewModel.updateTimezone(UserInfo.info!!.objectId, s.toString().toInt())
                }
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        UserInfo.info?.let { info ->
            binding.tvEmail.text = String.format(getString(R.string.format_email), info.reportEmail)
            binding.tlTimezone.editText?.apply {
                setText(info.timezone.toString())
                addTextChangedListener(timezoneTextWatcher)
            }
            (binding.tlTimezone.editText as? AutoCompleteTextView)?.setAdapter(adapter)
        } ?: throw IllegalArgumentException("user info null !")

        viewModel.updateTimezoneResponse.observe(this) { networkResponse ->
            when (networkResponse) {
                is NetworkResponse.Success ->
                    UserInfo.info!!.timezone = binding.tlTimezone.editText!!.text.toString().toInt()

                is NetworkResponse.ApiError ->
                    Toast.makeText(this@UserInfoActivity, networkResponse.body.error, Toast.LENGTH_SHORT).show()

                is NetworkResponse.NetworkError ->
                    Toast.makeText(this@UserInfoActivity, networkResponse.error.message ?: "Network Error", Toast.LENGTH_SHORT).show()

                is NetworkResponse.UnknownError ->
                    Toast.makeText(this@UserInfoActivity, networkResponse.error?.message ?: "Unknown Error", Toast.LENGTH_SHORT).show()
            }

            binding.tlTimezone.editText!!.isEnabled = true
            binding.pbLoading.visibility = GONE
        }
    }
}