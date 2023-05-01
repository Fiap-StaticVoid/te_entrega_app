package com.example.teentrega.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.teentrega.R
import com.example.teentrega.databinding.PackageInformationBinding
import com.example.teentrega.model.PackageInfo
import com.example.teentrega.model.PackageType
import com.example.teentrega.model.ShippingType

class PackageListAdapter (private val context: Context, private val packages: List<PackageInfo> ) : RecyclerView.Adapter<PackageListAdapter.ViewHolder >() {

    class ViewHolder(binding: PackageInformationBinding) : RecyclerView.ViewHolder(binding.root) {

        private val packageName = binding.name
        private val price = binding.price
        private val icon = binding.icon
        private val shippingType = binding.shippingType
        private val shippingTypeIcon = binding.shippingTypeIcon
        private val date = binding.date

        fun link(product: PackageInfo) {
            packageName.text = product.packageName
            price.text = "R$ ${product.price}"

            if (product.packageType == PackageType.RECEIVE) {
                icon.setImageResource(R.drawable.receive)
            } else {
                icon.setImageResource(R.drawable.send)
            }

            when (product.shippingType) {
                ShippingType.ECONOMIC -> {
                    shippingType.text = shippingType.context.getString(R.string.economic)
                    shippingType.setTextColor(ContextCompat.getColor(
                        shippingType.context, R.color.text_economic)
                    )
                    shippingTypeIcon.setImageResource(R.drawable.economic)
                }
                ShippingType.EXPRESS -> {
                    shippingType.text = shippingType.context.getString(R.string.express)
                    shippingType.setTextColor(ContextCompat.getColor(
                        shippingType.context, R.color.text_express)
                    )
                    shippingTypeIcon.setImageResource(R.drawable.express)
                }
                else -> {
                    shippingType.text = ""
                    shippingTypeIcon.alpha = 0f
                }
            }

            date.text = product.date
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PackageInformationBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = packages[position]
        holder.link(item)
    }

    override fun getItemCount(): Int {
        return packages.size
    }

}