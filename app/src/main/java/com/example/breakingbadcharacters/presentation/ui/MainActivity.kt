package com.example.breakingbadcharacters.presentation.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.breakingbadcharacters.R
import com.example.breakingbadcharacters.domain.model.BBCharacter
import com.example.breakingbadcharacters.presentation.datastate.MainDataState
import com.example.breakingbadcharacters.presentation.viewmodel.MainStateEvent
import com.example.breakingbadcharacters.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val myAdapter = MainAdapter()
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        subscribeObserver()
        updateSummary()
        viewModel.setStateEvent((MainStateEvent.Chaacters))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        menu.findItem(R.id.action_search)?.apply {
            val searchView: SearchView = this.actionView as SearchView

            searchView.setOnCloseListener {
                updateSummary()
                false
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                override fun onQueryTextSubmit(p0: String?): Boolean {
                    updateSummary()
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    myAdapter.filter.filter(newText.toString())
                    updateSummary()
                    return false
                }
            })

            checkBox1.setOnCheckedChangeListener { _, isChecked -> updateScreen(1,isChecked) }
            checkBox2.setOnCheckedChangeListener { _, isChecked -> updateScreen(2,isChecked) }
            checkBox3.setOnCheckedChangeListener { _, isChecked -> updateScreen(3,isChecked) }
            checkBox4.setOnCheckedChangeListener { _, isChecked -> updateScreen(4,isChecked) }
            checkBox5.setOnCheckedChangeListener { _, isChecked -> updateScreen(5,isChecked) }

        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun updateScreen(index : Int, isChecked: Boolean ){
        myAdapter.setSeason(index,isChecked)
        updateSummary()
    }

    private fun updateSummary() {
        txtSummary.text = getResource(R.string.main_activity_list_size_text) + " " + myAdapter.itemCount.toString()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed(); true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(MyDecorator(30))
            adapter = myAdapter
        }
    }

    private fun subscribeObserver() {
        viewModel.state.observe(
            this, {
                when (it) {
                    is MainDataState.Loading -> {
                        displayLoading(true)
                    }
                    is MainDataState.Success -> {
                        displayLoading(false)
                        displayData(it)
                    }
                    is MainDataState.Error -> {
                        displayLoading(false)
                        displayError(it.exception.message.toString())
                    }
                }
            }
        )
    }

    private fun displayError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun displayData(data: MainDataState.Success<List<BBCharacter>>) {
        myAdapter.setData(data.data)
        updateSummary()
    }

    private fun displayLoading(status: Boolean) {
        progress_bar.visibility = if (status) View.VISIBLE else View.GONE
    }
}