package com.mytoshiba.newsapp

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class NewsDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)

        val newsTitle: TextView = findViewById(R.id.tv_title)
        val newsAuthor: TextView = findViewById(R.id.tv_author)
        val newsDate: TextView =findViewById(R.id.tv_date)
        val newsDesc: TextView =findViewById(R.id.tv_desc)
        val newsSource: TextView=findViewById(R.id.source)
        val newsImage: ImageView = findViewById(R.id.imageview)
        val backButton: ImageView = findViewById(R.id.Goback);

        if (intent != null) {
                newsTitle.text=intent.getStringExtra("title")
                newsAuthor.text=intent.getStringExtra("author")
            newsDate.text=intent.getStringExtra("date")
            newsDesc.text=intent.getStringExtra("desc")
            newsSource.text=intent.getStringExtra("source")
            val imgurl = intent.getStringExtra("imageurl")
            Glide.with(this).load(imgurl).into(newsImage)

            }

        backButton.setOnClickListener(){
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }
        }
    }

