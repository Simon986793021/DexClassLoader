package com.sw.dex

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import dalvik.system.DexClassLoader
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.lang.Exception
import java.lang.reflect.Method

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivityClassLoader"
    private val SHOWSTRINGCLASS: String = "hellos.jar"
    private val SHOWSTRINGCLASS_PATH: String = "HelloWorld"
    private val DEX: String = "dex"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //
        // loadMyDex()
        tv_hello.setOnClickListener {
            val intent = Intent(this, DexActivity::class.java)
            startActivity(intent)
        }

    }

}
