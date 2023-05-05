package com.example.teentrega.ui.fragment.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teentrega.databinding.FragmentDashboardSendBinding
import com.example.teentrega.model.PackageInfo
import com.example.teentrega.ui.activity.SendPackageActivity
import com.example.teentrega.ui.recyclerview.adapter.PackageListAdapter


class DashboardSendFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDashboardSendBinding.inflate(inflater)

        // test packages
        val packages: List<PackageInfo> = listOf(
            /*
            PackageInfo(ShippingType.ECONOMIC, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.EXPRESS, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.NONE, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.ECONOMIC, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.EXPRESS, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.NONE, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.ECONOMIC, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.EXPRESS, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.NONE, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.ECONOMIC, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.EXPRESS, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.NONE, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.ECONOMIC, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.EXPRESS, PackageType.SEND, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.NONE, PackageType.SEND, "Hello", "00/00/00", "10.00"),*/
        )

        binding.shipmentsList.adapter = PackageListAdapter(binding.shipmentsList.context, packages)

        binding.addressEditText.setOnClickListener {
            val intent = Intent(binding.address.context, SendPackageActivity::class.java)
            startActivity(intent)
        }

        if (packages.size > 0) {
            binding.emptyShipments.visibility = View.GONE
            binding.shipmentsList.visibility = View.VISIBLE
        } else {
            binding.emptyShipments.visibility = View.VISIBLE
            binding.shipmentsList.visibility = View.GONE
        }

        return binding.root
    }
}