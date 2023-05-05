package com.example.teentrega.ui.fragment.sendpackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.teentrega.R
import com.example.teentrega.common.Httpx
import com.example.teentrega.databinding.FragmentSendPackageLocationBinding
import org.json.JSONArray


class SendPackageLocationFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSendPackageLocationBinding.inflate(inflater)

        fun update(states: JSONArray) {

            var statesList = arrayOf<String>()

            for (i in 0 until states.length()) {
                statesList += states.getJSONObject(i).getString("nome")
            }

            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                binding.statesDropdown.context, R.layout.activity_send_package, statesList
            )

            binding.statesDropdown.adapter = adapter
        }

        val httpx = Httpx(::update)
        httpx.getStates()

        return binding.root
    }
}