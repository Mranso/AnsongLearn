package com.inin.learn.jetpacklearn.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.inin.learn.jetpacklearn.R
import com.inin.learn.jetpacklearn.base.BaseFragment
import com.inin.learn.jetpacklearn.databinding.FragmentJetpackHomeBinding

class JetpackHomeFragment : BaseFragment() {

    private var _binding: FragmentJetpackHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentJetpackHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goFirstFragment.setOnClickListener {

            val action = JetpackHomeFragmentDirections.actionJetpackHomeFragmentToFirstFragment()
            findNavController().navigate(action)
        }
    }
}