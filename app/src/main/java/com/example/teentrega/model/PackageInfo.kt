package com.example.teentrega.model

class PackageInfo (
    val shippingType: ShippingType,
    val packageType: PackageType,
    val packageName: String,
    val date: String,
    val price: String)

enum class PackageType {
    SEND,
    RECEIVE
}

enum class ShippingType {
    EXPRESS,
    ECONOMIC,
    NONE
}