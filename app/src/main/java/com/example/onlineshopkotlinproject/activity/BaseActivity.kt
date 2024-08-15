package com.example.onlineshopkotlinproject.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.onlineshopkotlinproject.R

import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


/* this activity enables full control over the screen's content by
  utilizing flags that allow the layout to extend into areas usually occupied
  by system UI elements like the status bar and navigation bar.

  * Other activities can extend this class to inherit these capabilities and
  streamline their own code, ensuring a consistent look and feel across
  the application.

*/
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // window refers to the window that the activity  associated with
        // setFlags : method takes two arguments (flags ,mask) that nto areas typically reserved for system UI,
        // such as the status bar and navigation bar, enabling a full-screen user experience.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}