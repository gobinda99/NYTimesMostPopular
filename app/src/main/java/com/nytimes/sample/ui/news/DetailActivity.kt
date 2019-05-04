package com.nytimes.sample.ui.news

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.nytimes.sample.R
import kotlinx.android.synthetic.main.activity_details.*

class DetailActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
       return when(item!!.itemId) {
           android.R.id.home -> {
               finish()
              return true
           }else -> super.onOptionsItemSelected(item)
        }
    }



}
