package net.qingmowan.test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import net.qingmowan.test.databinding.FragmentShowBinding

class ShowFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentShowBinding.inflate(layoutInflater, container, false)
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
        return binding.root
    }
}