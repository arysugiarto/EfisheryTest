package com.efishery.test.ui.login

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.efishery.test.R
import com.efishery.test.databinding.FragmentSplashScreenBinding
import com.efishery.test.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenFragment : Fragment(R.layout.fragment_splash_screen) {

    private val binding by viewBinding<FragmentSplashScreenBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apply {
            activity?.window?.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
                statusBarColor = context.color(R.color.white)
            }
        }

        binding.ivSplashScreen.loadImage(R.drawable.splashscreen)

        Handler().postDelayed({
            navController.navigateOrNull(
                SplashScreenFragmentDirections.actionSplashFragmentToMainFragment()
            )
        },2000)

    }

}
