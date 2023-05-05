package com.example.teentrega.ui.fragment.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teentrega.databinding.FragmentDashboardTrackingBinding
import com.example.teentrega.model.PackageInfo
import com.example.teentrega.ui.recyclerview.adapter.PackageListAdapter


class DashboardTrackingFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentDashboardTrackingBinding.inflate(inflater)

        // test packages
        val packages: List<PackageInfo> = listOf(
            /*
            PackageInfo(ShippingType.ECONOMIC, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.EXPRESS, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.NONE, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.ECONOMIC, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.EXPRESS, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.NONE, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.ECONOMIC, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.EXPRESS, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.NONE, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.ECONOMIC, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.EXPRESS, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.NONE, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.ECONOMIC, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.EXPRESS, PackageType.RECEIVE, "Hello", "00/00/00", "10.00"),
            PackageInfo(ShippingType.NONE, PackageType.RECEIVE, "Hello", "00/00/00", "10.00")*/
        )

        binding.trackingList.adapter = PackageListAdapter(binding.trackingList.context, packages)

        /*
        binding.trackCodeEditText.setOnClickListener {
            val intent = Intent(binding.trackCodeEditText.context, SendPackageActivity::class.java)
            startActivity(intent)
        }*/

        if (packages.size > 0) {
            binding.emptyTracking.visibility = View.GONE
            binding.trackingList.visibility = View.VISIBLE
        } else {
            binding.emptyTracking.visibility = View.VISIBLE
            binding.trackingList.visibility = View.GONE
        }

        return binding.root
    }
}