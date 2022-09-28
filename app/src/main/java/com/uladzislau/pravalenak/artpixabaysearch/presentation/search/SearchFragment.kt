package com.uladzislau.pravalenak.artpixabaysearch.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.uladzislau.pravalenak.artpixabaysearch.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val searchViewModel by lazy { SearchViewModel() }
    private val adapter: SearchAdapter by lazy { SearchAdapter() }

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSearchBinding.inflate(inflater).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.imagesRV.adapter = this.adapter

        lifecycleScope.launchWhenStarted {
            searchViewModel
                .stateFlow
//                .flowWithLifecycle(lifecycle)
                .collect {
                    adapter.update(it)
                }
        }

        binding.searchBtn.setOnClickListener {
            searchViewModel.search(binding.searchET.text.toString())
        }
    }
}