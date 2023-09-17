package com.example.teentrega.ui.fragment.sendpackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.example.teentrega.databinding.FragmentSendPackageLocationBinding
import com.example.teentrega.viewmodel.PackageViewModel


class SendPackageLocationFragment : Fragment(), LifecycleOwner {

    private val viewModel: PackageViewModel by activityViewModels()
    /*private lateinit var adapter: ArrayAdapter<String>
    private val acronyms = mutableListOf<String>()*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSendPackageLocationBinding.inflate(inflater)

        viewModel.setCEP("")
        viewModel.setAddress("")
        viewModel.setNumber("")

        binding.cepEdit.doAfterTextChanged {
            viewModel.setCEP(it.toString())
        }

        binding.addressEdit.doAfterTextChanged {
            viewModel.setAddress(it.toString())
        }

        binding.numberEdit.doAfterTextChanged {
            viewModel.setNumber(it.toString())
        }

        /*fun updateStates(states: JSONArray) {
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
         */

        return binding.root
    }
}
