package com.efishery.test.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.efishery.test.R
import com.efishery.test.databinding.FragmentMainBinding
import com.efishery.test.util.BackButtonBehaviour
import com.efishery.test.util.setupWithNavController
import com.efishery.test.util.viewBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

class MainFragment : Fragment(R.layout.fragment_main) {

    private val bottomNavSelectedItemIdKey = "BOTTOM_NAV_SELECTED_ITEM_ID_KEY"
    private var bottomNavSelectedItemId = R.id.home

    private val binding by viewBinding<FragmentMainBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let {
            bottomNavSelectedItemId = it.getInt(bottomNavSelectedItemIdKey, bottomNavSelectedItemId)
        }
        setUpBottomNavBar()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(bottomNavSelectedItemIdKey, bottomNavSelectedItemId)
        super.onSaveInstanceState(outState)
    }

    private fun setUpBottomNavBar() {
        binding.bottomNavigation.apply {
            background = null
            selectedItemId = bottomNavSelectedItemId
        }

        // Todo : Change graph when declared graph not used
        val navGraphIds = listOf(
            R.navigation.nav_home_graph,
            R.navigation.nav_feed_graph,
            R.navigation.nav_profile_graph
        )

        val controller = binding.bottomNavigation.setupWithNavController(
            fragmentManager = childFragmentManager,
            navGraphIds = navGraphIds,
            backButtonBehaviour = BackButtonBehaviour.POP_HOST_FRAGMENT,
            containerId = binding.navHostContainer.id,
            firstItemId = R.id.home,
            intent = requireActivity().intent
        )

        controller.observe(viewLifecycleOwner) { navController ->
            NavigationUI.setupWithNavController(binding.mainToolbar, navController)
            bottomNavSelectedItemId = navController.graph.id
        }
    }

    companion object {
        val Fragment?.parentToolbar: MaterialToolbar?
            get() {
                val fragment = if (this?.parentFragment is NavHostFragment) {
                    (parentFragment as? NavHostFragment)?.parentFragment as? MainFragment
                } else null

                return fragment?.binding?.mainToolbar
            }

        val Fragment?.parentNavigation: BottomNavigationView?
            get() {
                val fragment = if (this?.parentFragment is NavHostFragment) {
                    (parentFragment as? NavHostFragment)?.parentFragment as? MainFragment
                } else null

                return fragment?.binding?.bottomNavigation
            }

    }
}