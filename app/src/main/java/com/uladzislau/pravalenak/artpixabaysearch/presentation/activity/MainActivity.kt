package com.uladzislau.pravalenak.artpixabaysearch.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import com.uladzislau.pravalenak.artpixabaysearch.R
import com.uladzislau.pravalenak.artpixabaysearch.presentation.details.DetailsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun navigateToDetails(query: String, id: Int) {
        supportFragmentManager
            .commit {
                val args = bundleOf(
                    DetailsFragment.DETAILS_QUERY_KEY to query,
                    DetailsFragment.DETAILS_ID_KEY to id,
                )
                replace(
                    findViewById<FragmentContainerView>(R.id.container).id,
                    DetailsFragment::class.java,
                    args
                )
                addToBackStack("Details!")
            }
    }
}