package com.inin.learn.jetpacklearn.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.inin.learn.jetpacklearn.R
import com.inin.learn.jetpacklearn.base.BaseFragment
import com.inin.learn.jetpacklearn.databinding.FragmentFourthBinding


class FourthFragment : BaseFragment() {
    private var _binding: FragmentFourthBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goHomeFragment.setOnClickListener {
            findNavController().navigate(R.id.action_fourthFragment_to_jetpackHomeFragment)
        }
    }
}