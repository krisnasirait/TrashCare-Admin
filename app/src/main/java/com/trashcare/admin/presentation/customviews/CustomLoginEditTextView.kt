package com.trashcare.admin.presentation.customviews

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Patterns
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.trashcare.admin.R

class CustomLoginEditTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: MaterialButton

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (s.hashCode() == etEmail.text.hashCode() && !TextUtils.isEmpty(email) && !email.isValidEmail()) {
                etEmail.error = "Please enter a valid email address"
            } else {
                etEmail.error = null
            }

            if (s.hashCode() == etPassword.text.hashCode() && password.isNotEmpty() && password.length < 8) {
                etPassword.error = "Password must be at least 8 characters"
            } else {
                etPassword.error = null
            }

            if (validateForm(email, password)) {
                btnLogin.isEnabled = true
                btnLogin.setBackgroundColor(ContextCompat.getColor(context, R.color.green_600))
            } else {
                btnLogin.isEnabled = false
                btnLogin.setBackgroundColor(ContextCompat.getColor(context, R.color.inactive))
            }
            btnLogin.isEnabled = validateForm(email, password)
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_login_edit_text, this, true)
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        etEmail.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)

        btnLogin.isEnabled = false
        btnLogin.setBackgroundColor(ContextCompat.getColor(context, R.color.inactive))
        btnLogin.setOnClickListener {
            clearFocus()
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            inputMethodManager?.hideSoftInputFromWindow(windowToken, 0)
            rootView.requestFocus()
        }
    }

    fun validateForm(email: String, password: String): Boolean {
        return email.isValidEmail() && password.length >= 8
    }

    private fun String.isValidEmail(): Boolean {
        return !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this)
            .matches()
    }

    fun getEmail(): String {
        return etEmail.text.toString().trim()
    }

    fun getPassword(): String {
        return etPassword.text.toString().trim()
    }

    fun setOnLoginClickListener(listener: OnClickListener) {
        btnLogin.setOnClickListener(listener)
    }
}