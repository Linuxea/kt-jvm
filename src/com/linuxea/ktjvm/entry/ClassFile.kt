package com.linuxea.ktjvm.entry

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const
import java.io.DataInputStream

class ClassFile {

    var magic: Int = 0
    var minorVersion: Int = 0
    var majorVersion: Int = 0
    var constantPoolCount: Int = 0
    lateinit var constantPool: ConstantPool
    var accessFlags : Int = 0
    var thisClass: Int = 9
    var superClass: Int = 0
    var interfaceCount: Int = 0
    lateinit var interfaces: List<Int>
    var fieldCount = 0
    lateinit var fields: List<MemberInfo>
    var methodCount = 0
    lateinit var methods: List<MemberInfo>
    var attributeCount = 0
    lateinit var attributes: List<AttributeInfo>


    fun parse(dataInputStream: DataInputStream) {
        val classReader = ClassReader(dataInputStream)
        this.magic = classReader.readUint32()
        this.minorVersion = classReader.readUint16()
        this.majorVersion = classReader.readUint16()
        this.constantPoolCount = classReader.readUint16()
//        this.constantPool = classReader.readUint8()
        this.accessFlags = classReader.readUint16()
        this.thisClass = classReader.readUint16()
        this.superClass = classReader.readUint16()
        this.interfaceCount = classReader.readUint16()
//        this.interfaces
        this.fieldCount = classReader.readUint16()
//        this.fields = l
        this.methodCount = classReader.readUint16()
//        this.methods
        this.attributeCount = classReader.readUint16()
//        this.attributes
    }


    fun className(){
        this.constantPool
    }








}


class ConstantPool {
    fun getUtf8(nameIndex: Int) : String{
        TODO("Not yet implemented")
    }

}

class MemberInfo(private val classReader: ClassReader, private val constantPool: ConstantPool) {

    lateinit var cp : ConstantPool
    var accessFlags : Int = 0
    var nameIndex : Int = 0
    var descriptorIndex: Int = 0
    lateinit var attributes: List<AttributeInfo>


    fun readMembers(): List<MemberInfo> {
        val memberCount = this.classReader.readUint16()
        val mutableListOf = mutableListOf<MemberInfo>()
        repeat(memberCount) {
            mutableListOf += readMember()
        }
        return mutableListOf
    }

    fun readMember(): MemberInfo {
        val memberInfo = MemberInfo(this.classReader, this.constantPool)
        memberInfo.cp = this.constantPool
        memberInfo.accessFlags = this.classReader.readUint16()
        memberInfo.nameIndex = this.classReader.readUint16()
        memberInfo.descriptorIndex = this.classReader.readUint16()
        memberInfo.attributes = readAttribute(this.classReader, this.constantPool)
        return memberInfo
    }

    private fun readAttribute(classReader: ClassReader, constantPool: ConstantPool): List<AttributeInfo> {
        TODO("Not yet implemented")
    }

    fun name(): String {
        return this.constantPool.getUtf8(this.nameIndex)
    }


    fun descripter(): String {
        return this.constantPool.getUtf8(this.descriptorIndex)
    }


}

class AttributeInfo{

}