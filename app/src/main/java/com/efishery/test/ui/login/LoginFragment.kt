package com.efishery.test.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.efishery.test.R
import com.efishery.test.databinding.FragmentLoginBinding
import com.efishery.test.util.navController
import com.efishery.test.util.navigateOrNull
import com.efishery.test.util.viewBinding

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding<FragmentLoginBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}