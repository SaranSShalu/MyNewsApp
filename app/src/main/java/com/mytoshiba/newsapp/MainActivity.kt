package com.mytoshiba.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.AbsListView
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mytoshiba.newsapp.adapter.NewsListAdapter
import com.mytoshiba.newsapp.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var recyclerAdapter: NewsListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)
        initRecyclerView()
        initViewModel()
    }
    private fun initRecyclerView(){
        val recyclerview = findViewById<RecyclerView>(R.id.my_recycler_view)
        recyclerAdapter = NewsListAdapter()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = recyclerAdapter
    }
    private fun initViewModel(){
        val viewModel: MainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this, Observer {
            if(it != null){
                recyclerAdapter.setNewsList(it)
                recyclerAdapter.notifyDataSetChanged()
            }else{
                Toast.makeText(this,"Error ",Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeAPIcall()
    }

}