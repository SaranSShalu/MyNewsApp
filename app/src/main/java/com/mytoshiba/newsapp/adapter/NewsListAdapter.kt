package com.mytoshiba.newsapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mytoshiba.newsapp.NewsDetailsActivity
import com.mytoshiba.newsapp.R
import com.mytoshiba.newsapp.data.Article
import com.mytoshiba.newsapp.data.data

class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.MyViewHolder>(){

    private lateinit var context: Context
    private var newsList: List<Article>? = null

    fun setNewsList(newsList: List<Article>?){
        this.newsList = newsList
    }
    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): NewsListAdapter.MyViewHolder {
        context = parent.context
       val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list_row,parent,false)
       return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsListAdapter.MyViewHolder, position: Int) {
       holder.bind(newsList?.get(position)!!)

        holder.itemView.setOnClickListener{
//        Toast.makeText(context, newsList?.get(position)!!.author, Toast.LENGTH_LONG).show()
            val details = Intent(context, NewsDetailsActivity::class.java)
                details.putExtra("author", newsList?.get(position)!!.author)
                details.putExtra("title", newsList?.get(position)!!.title)
                details.putExtra("date", newsList?.get(position)!!.publishedAt)
                details.putExtra("desc", newsList?.get(position)!!.description)
                details.putExtra("imageurl", newsList?.get(position)!!.urlToImage)
                details.putExtra("source",newsList?.get(position)!!.source.name)
                details.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(details)}

    }

    override fun getItemCount(): Int {
        if (newsList == null)return 0
        else return newsList?.size!!
    }

    class MyViewHolder(view : View):RecyclerView.ViewHolder(view){

        val newsTitle: TextView = view.findViewById(R.id.news_title)
        val newsAuthor: TextView = view.findViewById(R.id.news_author)
        val newsDate: TextView =view.findViewById(R.id.news_date)
        val newsImage: ImageView = view.findViewById(R.id.news_image)

        fun bind(data: Article){

            newsTitle.text="${data.title}"
            newsAuthor.text="${data.author}"
            newsDate.text="${data.publishedAt}"

            Glide.with(itemView.context).load(data.urlToImage).into(newsImage)

        }
    }
}