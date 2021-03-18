package com.jjh.basictabbedapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.jjh.basictabbedapp.databinding.MainFragmentBinding

class MainFragment : Fragment() {

  private var _binding: MainFragmentBinding? = null
  private val binding get() = _binding!!

  override fun onCreateView(inflater: LayoutInflater,
                            container: ViewGroup?,
                            savedInstanceState: Bundle?): View {
    _binding = MainFragmentBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    // Set up the ViewPager with adapter
    binding.viewPager.adapter = TabPagerAdapter(requireActivity())

    // Link the tabLayout with the viewpager and handle tab text
    TabLayoutMediator(binding.tabLayout, binding.viewPager,
      TabLayoutMediator.TabConfigurationStrategy { tab, position ->
        when (position) {
          0 -> tab.text = "Monday"
          1 -> tab.text = "Tuesday"
          2 -> tab.text = "Wednesday"
        }
      }).attach()

  }

}
