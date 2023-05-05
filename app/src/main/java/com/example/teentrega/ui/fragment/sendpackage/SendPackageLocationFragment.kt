package com.example.teentrega.ui.fragment.sendpackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.teentrega.common.Httpx
import com.example.teentrega.databinding.FragmentSendPackageLocationBinding
import org.json.JSONArray


class SendPackageLocationFragment : Fragment() {

    private lateinit var adapter: ArrayAdapter<String>
    private val acronyms = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSendPackageLocationBinding.inflate(inflater)

        fun updateStates(states: JSONArray) {
            val statesList = mutableListOf<String>()

            for (i in 0 until states.length()) {
                statesList.add(states.getJSONObject(i).getString("nome"))
                acronyms.add(states.getJSONObject(i).getString("sigla"))
            }

            activity?.runOnUiThread {
                adapter = ArrayAdapter(
                    binding.statesDropdown.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    statesList
                )
                binding.statesDropdown.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }

        fun updateCities(cities: JSONArray) {
            val citiesList = mutableListOf<String>()

            for (i in 0 until cities.length()) {
                citiesList.add(cities.getJSONObject(i).getString("nome"))
            }

            activity?.runOnUiThread {
                adapter = ArrayAdapter(
                    binding.citiesDropdown.context,
                    android.R.layout.simple_spinner_dropdown_item,
                    citiesList
                )
                binding.citiesDropdown.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }

        binding.statesDropdown.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {
                    Httpx(::updateCities).getCities(acronyms[pos])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        Httpx(::updateStates).getStates()

        return binding.root
    }
}
