package com.uladzislau.pravalenak.artpixabaysearch.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.uladzislau.pravalenak.artpixabaysearch.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    private val searchViewModel by lazy { SearchViewModel() }

    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentSearchBinding.inflate(inflater).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycleScope.launchWhenStarted {
            searchViewModel
                .stateFlow
//                .flowWithLifecycle(lifecycle)
                .collect {
                    Toast.makeText(requireContext(), it.size.toString(), Toast.LENGTH_SHORT).show()
                }
        }

        binding.searchView.setOnSearchClickListener {
            searchViewModel.search(binding.searchView.query.toString())
        }
    }

}