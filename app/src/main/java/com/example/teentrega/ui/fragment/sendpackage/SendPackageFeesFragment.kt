package com.example.teentrega.ui.fragment.sendpackage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teentrega.databinding.FragmentSendPackageFeesBinding
import com.example.teentrega.model.PackageInfo
import com.example.teentrega.model.PackageType
import com.example.teentrega.model.ShippingType
import com.example.teentrega.ui.recyclerview.adapter.PackageListAdapter


class SendPackageFeesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSendPackageFeesBinding.inflate(inflater)

        // test shipping fees
        val fees: List<PackageInfo> = listOf(

            PackageInfo(ShippingType.ECONOMIC, PackageType.SEND, "20:00", "20/05/23 ", "12.32"),
            PackageInfo(ShippingType.EXPRESS, PackageType.SEND, "17:00", "06/05/23", "32.10"),
            PackageInfo(ShippingType.NONE, PackageType.SEND, "20:00", "14/05/23", "10.00")
        )

        binding.shippingOptions.adapter = PackageListAdapter(binding.shippingOptions.context, fees)

        return binding.root
    }
}