package com.addapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.addapp.model.User
import com.addapp.network.RetrofitConfig
import com.addapp.ui.UserListAdapter
import com.addapp.utils.ApiStatus
import com.addapp.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.coroutineScope

class MainActivity : AppCompatActivity() {

    var userList = ArrayList<User>()
    private lateinit var userAdapter: UserListAdapter
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RetrofitConfig.initRetrofit()
        setContentView(R.layout.activity_main)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setRecyclerAdapter()
        
        setUserListObserver()
    }

    private fun setRecyclerAdapter() {

        val itemDivider = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        itemDivider.setDrawable(ContextCompat.getDrawable(this, R.drawable.divider)!!)
        userRecycler.addItemDecoration(itemDivider)
        userRecycler.layoutManager = LinearLayoutManager(this)
        userAdapter = UserListAdapter(userList)
        userRecycler.adapter = userAdapter
    }

    private fun setUserListObserver() {

        lifecycleScope.launchWhenCreated {

            userViewModel.getUserList1().observe(this@MainActivity, Observer {

                it?.let { resource ->
                    when(resource.status){
                        ApiStatus.SUCCESS -> {
                            userRecycler.visibility = VISIBLE
                            progress.visibility = GONE

                            val data  = resource.data
                            if (data != null){
                                val listData = data.data
                                userList.addAll(listData)
                                userAdapter.notifyDataSetChanged()
                            }

                        }
                        ApiStatus.ERROR -> {
                            userRecycler.visibility = VISIBLE
                            progress.visibility = GONE
                            Toast.makeText(this@MainActivity, resource.message, Toast.LENGTH_SHORT).show()
                        }
                        ApiStatus.LOADING -> {
                            userRecycler.visibility = VISIBLE
                            progress.visibility = VISIBLE

                        }
                    }
                }
            })
        }

    }
}