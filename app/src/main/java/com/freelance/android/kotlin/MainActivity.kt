package com.freelance.android.kotlin

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.freelance.android.kotlin.features.news.NewsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            changeFragment(NewsFragment())
        }
    }

    fun changeFragment(f: Fragment, cleanStack: Boolean = false) {
        val mSFM = supportFragmentManager.beginTransaction();
        if (cleanStack) {
            clearBackStack();
        }
        mSFM.setCustomAnimations(
                R.anim.abc_fade_in, R.anim.abc_fade_out, R.anim.abc_popup_enter, R.anim.abc_popup_exit);
        mSFM.replace(R.id.activity_base_content, f);
        mSFM.addToBackStack(null);
        mSFM.commit();
    }

    fun clearBackStack() {
        val mSFM = supportFragmentManager;
        if (mSFM.backStackEntryCount > 0) {
            val first = mSFM.getBackStackEntryAt(0);
            mSFM.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }
}
/*
 //numberLoop()
 //characterLoop()
 fun characterLoop(){
     var char = 'A'..'Z'

     val text: TextView = findViewById(R.id.number) as TextView
     for (a in char) {
         text.setText(a.toString())
     }
 }

 fun numberLoop() {
     var nums = intArrayOf(1,2,3,4,5)

     for ((i,a) in nums.withIndex()) {
         val text: TextView = findViewById(R.id.number) as TextView
         text.setText("$i : $a")
     }
 }*/
