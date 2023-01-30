package com.geektech.lesson1kyrs4.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.geektech.lesson1kyrs4.data.Pref
import com.geektech.lesson1kyrs4.databinding.FragmentOnBoardingBinding
import com.geektech.lesson1kyrs4.ui.onBoarding.adapter.OnBoardingAdapter
import me.relex.circleindicator.CircleIndicator3


class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var pref: Pref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())

        val adapter = OnBoardingAdapter {
            pref.saveSeen()
            findNavController().navigateUp()
        }
        binding.viewpager.adapter = adapter
        attachIndicator()
    }

    private fun attachIndicator() {
        val indicator: CircleIndicator3 = binding.indicator
        val viewPager = binding.viewpager
        indicator.setViewPager(viewPager)
    }
}