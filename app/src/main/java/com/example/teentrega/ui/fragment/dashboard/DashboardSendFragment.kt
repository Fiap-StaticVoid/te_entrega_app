package com.example.teentrega.ui.fragment.dashboard

import android.content.Intent
import android.os.Bundle
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
import com.example.teentrega.ui.activity.FinishSendPackageActivity
import com.example.teentrega.ui.activity.SendPackageActivity
import com.example.teentrega.ui.recyclerview.adapter.PackageListAdapter
import com.example.teentrega.utils.Constants
import com.example.teentrega.viewmodel.AccountViewModel
import org.json.JSONObject


class DashboardSendFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentDashboardSendBinding.inflate(inflater)

        fun update(info: JSONObject) {
            val packages: List<PackageInfo> = listOf()

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
        mutable[CallBackOrigin("entregas/", Method.POST)] = mutableListOf(::update)

        val shippingAPI = ShippingAPI(Constants.IP, mutable)

        shippingAPI.list()

        binding.addressEditText.setOnClickListener {
            val intent = Intent(binding.address.context, SendPackageActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}