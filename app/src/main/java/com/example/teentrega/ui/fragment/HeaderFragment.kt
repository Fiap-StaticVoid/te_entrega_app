package com.example.teentrega.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teentrega.R
import com.example.teentrega.databinding.FragmentHeaderBinding


class HeaderFragment : Fragment() {
    private var texto: String = "Voltar"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = FragmentHeaderBinding.inflate(layoutInflater)
        binding.textView.text = this.texto
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_header, container, false)
    }
}