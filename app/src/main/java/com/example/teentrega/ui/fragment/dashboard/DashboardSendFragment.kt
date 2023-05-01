package com.example.teentrega.ui.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teentrega.R
import com.example.teentrega.databinding.FragmentDashboardSendBinding
import com.example.teentrega.model.PackageInfo
import com.example.teentrega.model.PackageType
import com.example.teentrega.model.ShippingType
import com.example.teentrega.ui.recyclerview.adapter.PackageListAdapter


class DashboardSendFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDashboardSendBinding.inflate(inflater)

        // test packages
        val packages: List<PackageInfo> = listOf(
            PackageInfo(ShippingType.ECONOMIC, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.EXPRESS, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.NONE, PackageType.SEND, "Hello", "00/00/00", "10.00")
        )

        if (packages.size > 0) {
            binding.emptyShipments.visibility = View.GONE
            binding.shipmentsList.adapter = PackageListAdapter(binding.shipmentsList.context, packages)
        } else {
            binding.shipmentsList.visibility = View.GONE
        }
        return inflater.inflate(R.layout.fragment_dashboard_send, container, false)
    }
}