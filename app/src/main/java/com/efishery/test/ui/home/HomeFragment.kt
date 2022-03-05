package com.efishery.test.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.efishery.test.R
import com.efishery.test.databinding.FragmentHomeBinding
import com.efishery.test.util.viewBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    
    }

}