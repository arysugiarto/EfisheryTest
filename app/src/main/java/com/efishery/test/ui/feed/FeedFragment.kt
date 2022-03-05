package com.efishery.test.ui.feed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.efishery.test.R
import com.efishery.test.databinding.FragmentFeedBinding
import com.efishery.test.util.viewBinding

class FeedFragment : Fragment(R.layout.fragment_feed) {

    private val binding by viewBinding<FragmentFeedBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}