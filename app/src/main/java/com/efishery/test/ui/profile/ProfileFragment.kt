package com.efishery.test.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.efishery.test.R
import com.efishery.test.databinding.FragmentProfileBinding
import com.efishery.test.util.viewBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding<FragmentProfileBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}