package com.luxsh.reelimigration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.luxsh.reelimigration.databinding.ActivityLoginBinding
import java.util.regex.Matcher
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener {
            if (!binding.edtUserName.text.toString()
                    .isNullOrEmpty() && !binding.edtPassword.text.toString().isNullOrEmpty()
            )
            {
                if (binding.edtPassword.text.toString().length < 8 && isValidPassword(binding.edtPassword.text.toString())) {
                    SharedPreferencesManager.writeString(
                        this@LoginActivity,
                        Constants.PREFS_isUSER_LOGGED_IN,
                        "yes"
                    )
                    Intent(this@LoginActivity, MainActivity::class.java).putExtra(
                        "name",
                        binding.edtUserName.text.toString()
                    ).apply {

                        startActivity(this)
                        finish()
                    }
                } else {
                    showSnackBar("Password validation not matched")
                }
            } else {
                showSnackBar("Please Enter Username and Password")
            }
        }
    }

    private fun isValidPassword(password: String?): Boolean {
        val pattern: Pattern
        val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$"
        pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher: Matcher = pattern.matcher(password)
        return matcher.matches()
    }

    private fun showSnackBar(message: String?) {
        val sb = Snackbar.make(binding.linMain, message!!, BaseTransientBottomBar.LENGTH_LONG)
        val snackbarView: View = sb.getView()
        val tv = snackbarView.findViewById(R.id.snackbar_text) as TextView
        tv.maxLines = 3
        sb.view.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))
        sb.show()
    }
}