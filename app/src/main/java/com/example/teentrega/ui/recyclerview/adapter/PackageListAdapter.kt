package com.example.teentrega.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.teentrega.R
import com.example.teentrega.model.PackageInfo
import com.example.teentrega.model.PackageType
import com.example.teentrega.model.ShippingType

class PackageListAdapter (private val context: Context, private val packages: List<PackageInfo> ) : RecyclerView.Adapter<PackageListAdapter.ViewHolder >() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun link(product: PackageInfo) {
            itemView.findViewById<TextView>(R.id.name).text = product.packageName
            itemView.findViewById<TextView>(R.id.price).text = "R$ ${product.price}"

            val icon = itemView.findViewById<ImageView>(R.id.icon)
            if (product.type == PackageType.RECEIVE) {
                icon.setImageResource(R.drawable.receive)
            } else {
                icon.setImageResource(R.drawable.send)
            }

            val shippingIcon = itemView.findViewById<ImageView>(R.id.shipping_type_icon)
            val shippingText = itemView.findViewById<TextView>(R.id.shipping_type)
            when (product.shipping) {
                ShippingType.ECONOMIC -> {
                    shippingText.text = shippingText.context.getString(R.string.economic)
                    shippingText.setTextColor(ContextCompat.getColor(
                        shippingText.context, R.color.text_economic)
                    )
                    shippingIcon.setImageResource(R.drawable.economic)
                }
                ShippingType.EXPRESS -> {
                    shippingText.text = shippingText.context.getString(R.string.express)
                    shippingText.setTextColor(ContextCompat.getColor(
                        shippingText.context, R.color.text_express)
                    )
                    shippingIcon.setImageResource(R.drawable.express)
                }
                else -> {
                    shippingText.text = ""
                    shippingIcon.alpha = 0f
                }
            }

            itemView.findViewById<TextView>(R.id.date).text = product.date
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.package_information, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = packages[position]
        holder.link(item)
    }

    override fun getItemCount(): Int {
        return packages.size
    }

}