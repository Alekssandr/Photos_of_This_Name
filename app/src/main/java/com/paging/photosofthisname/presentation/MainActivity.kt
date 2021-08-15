package com.paging.photosofthisname.presentation

import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.paging.photosofthisname.R
import com.paging.photosofthisname.databinding.ActivityMainBinding
import com.paging.photosofthisname.presentation.viewmodel.MainViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModel<MainViewModel>()
    private var searchJob: Job? = null

    private val adapter = PhotoAdapter {
        binding.progressBar.isVisible = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fab.setOnClickListener { view ->
            startForResult.launch()
        }
        initAdapter()
    }

    private fun initAdapter() {
        binding.list.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = searchItem.actionView as SearchView
        searchView.maxWidth = Integer.MAX_VALUE
        searchView.setOnQueryTextListener(queryTextListener)
        return super.onCreateOptionsMenu(menu)
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.PickContact()) { uri: Uri? ->
            uri?.let {
                val cursor = contentResolver.query(it, null, null, null, null)!!
                if (cursor.moveToFirst()) {
                    val contactName =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    search(contactName)
                }
            }

        }

    private fun search(contact: String) {
        // Make sure we cancel the previous job before creviewModelating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            binding.progressBar.isVisible = true
            viewModel.getRecentPhotos(contact).collectLatest {
                adapter.submitData(it)
            }
        }
    }


    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            query?.let { search(it) }
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    }
}