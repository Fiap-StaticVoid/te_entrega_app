package com.example.teentrega.ui.fragment.createaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.teentrega.R
import com.example.teentrega.databinding.FragmentCreateAccountFirstBinding
import com.example.teentrega.databinding.FragmentDashboardTrackingBinding
import com.example.teentrega.ui.AccountViewModel


class CreateAccountFirstFragment : Fragment() {
    private val viewModel: AccountViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCreateAccountFirstBinding.inflate(inflater)

        binding.usernameEdit.setText(viewModel.username.value)

        binding.usernameEdit.doAfterTextChanged {
            viewModel.setUsername(it.toString())
        }

        return binding.root
    }
}