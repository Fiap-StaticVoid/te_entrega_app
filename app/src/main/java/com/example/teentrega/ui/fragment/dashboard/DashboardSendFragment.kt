package com.example.teentrega.ui.fragment.dashboard

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.teentrega.api.CallBackOrigin
import com.example.teentrega.api.CallBackPerOrigin
import com.example.teentrega.api.Method
import com.example.teentrega.api.ShippingAPI
import com.example.teentrega.databinding.FragmentDashboardSendBinding
import com.example.teentrega.model.PackageInfo
import com.example.teentrega.model.PackageType
import com.example.teentrega.model.ShippingType
import com.example.teentrega.ui.activity.FinishSendPackageActivity
import com.example.teentrega.ui.activity.SendPackageActivity
import com.example.teentrega.ui.recyclerview.adapter.PackageListAdapter
import com.example.teentrega.utils.Constants
import com.example.teentrega.viewmodel.AccountViewModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class DashboardSendFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentDashboardSendBinding.inflate(inflater)

        fun update(info: Any) {
            if (info !is JSONArray) return

            val packages = mutableListOf<PackageInfo>()

            for (i in 0 until info.length()) {
                val data = info.getJSONObject(i)
                packages.add(
                    PackageInfo(
                        ShippingType.NONE,
                        PackageType.SEND,
                        "Encomenda",
                        data.getString("data_da_solicitacao"),
                        ""
                    )
                )
            }

            if (packages.size > 0) {
                binding.emptyShipments.visibility = View.GONE
                binding.shipmentsList.visibility = View.VISIBLE
            } else {
                binding.emptyShipments.visibility = View.VISIBLE
                binding.shipmentsList.visibility = View.GONE
            }

            binding.shipmentsList.adapter = PackageListAdapter(binding.shipmentsList.context, packages)
        }

        binding.emptyShipments.visibility = View.VISIBLE
        binding.shipmentsList.visibility = View.GONE

        val mutable : CallBackPerOrigin = mutableMapOf()
        mutable[CallBackOrigin("entregas/", Method.GET)] = mutableListOf(::update)

        val shippingAPI = ShippingAPI(Constants.IP, mutable)
        sharedPreferences = requireActivity().getSharedPreferences(Constants.SHARED_PREF, Context.MODE_PRIVATE)

        val token = sharedPreferences.getString(Constants.BEARER_TOKEN, null)

        shippingAPI.token = token

        shippingAPI.list()

        binding.addressEditText.setOnClickListener {
            val intent = Intent(binding.address.context, SendPackageActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}