package com.example.teentrega.ui.fragment.createaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.teentrega.databinding.FragmentCreateAccountThirdBinding
import com.example.teentrega.viewmodel.AccountViewModel


class CreateAccountThirdFragment : Fragment() {
    private val viewModel: AccountViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCreateAccountThirdBinding.inflate(inflater)

        binding.firstNameEdit.setText(viewModel.firstName.value)
        binding.lastNameEdit.setText(viewModel.lastName.value)

        binding.firstNameEdit.doAfterTextChanged {
            viewModel.setFirstName(it.toString())
        }

        binding.lastNameEdit.doAfterTextChanged {
            viewModel.setLastName(it.toString())
        }

        return binding.root
    }
}