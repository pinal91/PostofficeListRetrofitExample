package com.luxsh.reelimigration

import android.content.Intent
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        val window = window
        window.setFormat(PixelFormat.RGBA_8888)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        startAnimation()
    }

    private fun startAnimation() {
        val anim_alpha = AnimationUtils.loadAnimation(this, R.anim.alpha)
        anim_alpha.reset()
        val l = findViewById<LinearLayout>(R.id.lin_lay)
        l.clearAnimation()
        l.startAnimation(anim_alpha)
        val anim_translate = AnimationUtils.loadAnimation(this, R.anim.translate)
        anim_translate.reset()
        val iv = findViewById<TextView>(R.id.imageViewLogo)
        iv.clearAnimation()
        iv.startAnimation(anim_translate)
        anim_translate.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {

                if (SharedPreferencesManager.getString(
                        this@SplashScreen, Constants.PREFS_isUSER_LOGGED_IN, "").equals("yes")) {
                    val healthCareIntent = Intent(this@SplashScreen, MainActivity::class.java)
                    startActivity(healthCareIntent)
                    finish()
                }else {
                    val healthCareIntent = Intent(this@SplashScreen, LoginActivity::class.java)
                    startActivity(healthCareIntent)
                    finish()
                }
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
    }
}