package com.linuxea.ktjvm.entry

import java.io.DataInputStream

class ClassReader(var dataInputStream: DataInputStream) {

    fun readUint8(): Int {
        return dataInputStream.readUnsignedByte()
    }


    fun readUint16(): Int {
        return dataInputStream.readUnsignedShort()
    }

    fun readUint32(): Int {
        return dataInputStream.readInt()
    }



}