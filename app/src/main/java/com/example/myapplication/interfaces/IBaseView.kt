package com.example.myapplication.interfaces

import com.example.myapplication.viewmodel.BaseViewModel

interface IBaseView {

    fun <T : BaseViewModel> createViewModel(viewModelClass: Class<T>): T

    fun  registerViewModelObserver(baseViewModel: BaseViewModel)

    fun onApiSuccessCallBack(any: Any)

    fun onApiErrorCallBack(any: Any)
}