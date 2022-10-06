package com.uladzislau.pravalenak.artpixabaysearch.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.uladzislau.pravalenak.artpixabaysearch.R
import com.uladzislau.pravalenak.artpixabaysearch.databinding.FragmentSearchBinding
import com.uladzislau.pravalenak.artpixabaysearch.presentation.activity.MainActivity
import com.uladzislau.pravalenak.artpixabaysearch.presentation.details.DetailsFragment
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn

class SearchFragment : Fragment() {

    private val searchViewModel by lazy { SearchViewModel() }
    private val adapter: SearchAdapter by lazy {
        SearchAdapter {
            val query =
                if (binding.searchET.text.isEmpty()) "fruits" else binding.searchET.text.toString()
            (requireActivity() as MainActivity).navigateToDetails(query, it)
            parentFragmentManager
                .commit {
                    val args = bundleOf(
                        DetailsFragment.DETAILS_QUERY_KEY to query,
                        DetailsFragment.DETAILS_ID_KEY to it,
                    )
                    replace(
                        R.id.container,
                        DetailsFragment::class.java,
                        args
                    )
                    addToBackStack("Details!")
                }
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