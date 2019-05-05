package com.nytimes.sample.ui.news

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ShareActionProvider
import com.nytimes.sample.data.model.News
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*
import android.content.Intent
import androidx.core.view.MenuItemCompat
import com.nytimes.sample.R
import timber.log.Timber


class DetailActivity : AppCompatActivity() {

    private var shareProvider: ShareActionProvider? = null

    private lateinit var news: News;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)
        setSupportActionBar(detail_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        news = intent.getParcelableExtra<News>("news")

        details_data.text = news.title
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.details, menu)
        menu.findItem(R.id.action_share).also { menuItem ->
            shareProvider = MenuItemCompat.getActionProvider(menuItem)/*menuItem.actionProvider*/ as? ShareActionProvider
            /*menuItem.setOnMenuItemClickListener {
                val intent = Intent(Intent.ACTION_SEND).also {
                    it.type = "text/plain"
                    it.putExtra(Intent.EXTRA_TEXT, news.url)

                }
                shareProvider?.setShareIntent(intent)
                true
            }*/

        }

        return true
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Timber.d("Option item clicked")
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.action_share -> {
                val intent = Intent(Intent.ACTION_SEND).also {
                    it.type = "text/plain"
                    it.putExtra(Intent.EXTRA_TEXT, news.url)

                }
                shareProvider?.setShareIntent(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
