package com.uladzislau.pravalenak.artpixabaysearch.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.uladzislau.pravalenak.artpixabaysearch.databinding.FragmentSearchBinding
import com.uladzislau.pravalenak.artpixabaysearch.presentation.activity.MainActivity
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn

class SearchFragment : Fragment() {

    private val searchViewModel by lazy { SearchViewModel() }
    private val adapter: SearchAdapter by lazy {
        SearchAdapter {
            val query =
                if (binding.searchET.text.isEmpty()) "fruits" else binding.searchET.text.toString()
            (requireActivity() as MainActivity).navigateToDetails(query, it)
        }
    }

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

        searchViewModel.stateFlow
            .combine(searchViewModel.loadingStateFlow) { hits: List<HitUI>, isLoading: Boolean ->
                when (isLoading) {
                    false -> adapter.update(hits)
                    true -> adapter.update(emptyList())
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.searchBtn.setOnClickListener {
            searchViewModel.search(binding.searchET.text.toString())
        }
    }
}