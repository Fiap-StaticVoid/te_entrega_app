package com.example.teentrega.model

class PackageInfo (
    val shipping: ShippingType,
    val type: PackageType,
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