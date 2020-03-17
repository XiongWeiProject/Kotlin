package com.example.myapplication.interfaces

import com.example.myapplication.bean.BaseBean

interface IDatasListener {

    fun getSuccess(baseBean: BaseBean)

    fun getFaild(baseBean: BaseBean)
}