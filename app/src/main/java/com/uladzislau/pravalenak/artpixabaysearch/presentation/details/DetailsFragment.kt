package com.uladzislau.pravalenak.artpixabaysearch.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import com.uladzislau.pravalenak.artpixabaysearch.R
import com.uladzislau.pravalenak.artpixabaysearch.databinding.FragmentDetailsBinding
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = requireNotNull(_binding)

    private val viewModel by lazy { DetailsViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.find(
            requireArguments().getString(DETAILS_QUERY_KEY, ""),
            requireArguments().getInt(DETAILS_ID_KEY)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailsBinding.inflate(inflater).also { _binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {

        }
        viewModel.detailedHitStateFlow
            .combine(viewModel.loadingStateFlow) { hit: DetailedHitUI, isLoading: Boolean ->
                when (isLoading) {
                    false -> {
                        binding.root.visibility = View.VISIBLE

                        binding.commentsTV.text = hit.commentsCount.toString()
                        binding.downloadsTV.text = hit.downloadsCount.toString()
                        binding.likesTV.text = hit.likesCount.toString()
                        binding.userNameTV.text = hit.userName

                        binding.contentIV.load(hit.url)
                        binding.tagsTV.text = hit.tags
                    }
                    true -> binding.root.visibility = View.INVISIBLE
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    companion object {
        const val DETAILS_QUERY_KEY = "DETAILS_QUERY"
        const val DETAILS_ID_KEY = "DETAILS_ID"
    }
}