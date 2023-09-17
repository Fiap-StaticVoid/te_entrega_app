package com.example.teentrega.ui.fragment.createaccount

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.teentrega.databinding.FragmentCreateAccountSecondBinding
import com.example.teentrega.viewmodel.AccountViewModel


class CreateAccountSecondFragment : Fragment() {
    private val viewModel: AccountViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCreateAccountSecondBinding.inflate(inflater)

        binding.passwordEdit.setText(viewModel.password.value)
        binding.confirmPasswordEdit.setText(viewModel.password.value)

        fun savePassword(password: String) {
            if (binding.passwordEdit.text.toString() == binding.confirmPasswordEdit.text.toString()) {
                viewModel.setPassword(password)
            } else {
                viewModel.setPassword("")
            }
        }

        binding.confirmPasswordEdit.doAfterTextChanged {
            savePassword(it.toString())
        }

        binding.passwordEdit.doAfterTextChanged {
            savePassword(it.toString())
        }

        return binding.root
    }
}