package com.codberg.exception

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        btn_tset.setOnClickListener {
//            try {
//                CodbergCrash().TestCrash()
//            }
//            catch (e: Exception) {
//                CodbergException().tryCatchException(e, true)
//            }
//        }
    }
}
