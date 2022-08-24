package com.example.dictionary.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.example.dictionary.R
import com.example.dictionary.databinding.FragmentSearchBinding
import com.example.dictionary.presentation.WordDetailViewModel
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    /**
     * Initialize view model
     */
    private val viewModel: WordDetailViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        ViewModelProvider(
            this,
            WordDetailViewModel.WordDetailViewModelFactory(activity.application)
        )[WordDetailViewModel::class.java]
    }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.onSearch("hello")
//        lifecycle.coroutineScope.launch {
//            viewModel.WordDetailsList.collect {
//                Log.i("datax", it.toString())
//            }
//        }
        setupSearch()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSearch(){
        val searchSv = binding.fragmentSearchSvSearchBar
        searchSv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(newText: String?): Boolean {
                newText?.let {
                    if(it == ""){
                        binding.testTv.text = "null"
                    }
                    else{
                        Log.i("queryx", it)
                        viewModel.onSearch(it)
                        lifecycle.coroutineScope.launch {
                            viewModel.WordDetailsList.collect { it2 ->
                                if(it2.isEmpty()){
                                    binding.testTv.text = "null"
                                }
                                else{
                                    binding.testTv.text = it2.toString()
                                }

                            }
                        }
                    }

                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }

        })
    }

}