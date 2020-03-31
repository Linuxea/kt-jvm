package com.linuxea.ktjvm.entry


fun main() {
    val bytes = DirEntry("C:\\Users\\MI\\Desktop\\work\\kt-jvm\\out\\production\\kt-jvm\\com\\linuxea\\ktjvm\\entry")
        .find("MainKt.class")
    bytes?.run {
        println(String(bytes))
    }

}