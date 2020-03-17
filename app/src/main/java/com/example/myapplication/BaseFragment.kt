package com.example.myapplication

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.Fragment
import com.example.myapplication.interfaces.IBaseView
import com.example.myapplication.viewmodel.BaseViewModel


open class BaseFragment : Fragment(), IBaseView {

    override fun <T : BaseViewModel> createViewModel(viewModelClass: Class<T>): T {
        return ViewModelProviders.of(this).get(viewModelClass)
    }

    override fun registerViewModelObserver(baseViewModel: BaseViewModel) {
        baseViewModel.getSuccessLiveData().observe(this, Observer<Any> {
            if (it != null) {
                onApiSuccessCallBack(it)
            }
        })

        baseViewModel.getErrorLiveData().observe(this, Observer<Any> {
            if (it != null) {
                onApiErrorCallBack(it)
            }
        })
    }

    override fun onApiSuccessCallBack(any: Any) {
    }

    override fun onApiErrorCallBack(any: Any) {
    }

}
