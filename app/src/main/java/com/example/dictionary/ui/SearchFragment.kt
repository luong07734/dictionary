package com.example.dictionary.ui

import MeaningAdapter
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
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

    private lateinit var meaningList: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.onSearch("hello")
//        lifecycle.coroutineScope.launch {
//            viewModel.WordDetailsList.collect {
//                Log.i("datax", it.toString())
//            }
//        }
        meaningList = binding.meaningList
        meaningList.layoutManager = LinearLayoutManager(requireContext())
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
                        binding.wordTv.text = "null"
                    }
                    else{
                        Log.i("queryx", it)
                        viewModel.onSearch(it)
                        lifecycle.coroutineScope.launch {
                            viewModel.WordDetailsList.collect { it2 ->
                                if(it2.isEmpty()){
                                    binding.wordTv.text = "No Word Found"
                                    binding.phoneticsTv.text = ""
                                    binding.meaningList.visibility = View.GONE
                                }
                                else{
                                    binding.wordTv.text = it2[0].word;
                                    if(it2[0].phonetics?.isEmpty()!! || it2[0].phonetics?.get(0)?.text == null){
                                        binding.phoneticsTv.text = ""
                                    }else{
                                        binding.phoneticsTv.text = it2[0].phonetics?.get(0)?.text.toString()
                                    }
                                    if(it2[0].meanings.isNotEmpty()) {
                                        val meaningAdapter = MeaningAdapter(it2[0].meanings[0].definitions.map { it.definition })
                                        binding.meaningList.adapter = meaningAdapter
                                        binding.meaningList.visibility = View.VISIBLE
                                    } else {
                                        binding.meaningList.visibility = View.GONE
                                    }

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