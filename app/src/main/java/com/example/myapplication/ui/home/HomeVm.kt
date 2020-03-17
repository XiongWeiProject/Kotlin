package com.example.myapplication.ui.home


import com.example.myapplication.bean.BaseBean
import com.example.myapplication.bean.HomeBean
import com.example.myapplication.viewmodel.BaseViewModel
import java.util.regex.Pattern

class HomeVm : BaseViewModel() {

    var dadaist = mutableListOf<HomeBean.IssueListBean.ItemListBean>()
    var isLoadMore = false
    var date: String? = null

    fun getDatas(): MutableList<HomeBean.IssueListBean.ItemListBean> {
        return dadaist
    }

    fun loadData(isLoadMore: Boolean) {
        this.isLoadMore = isLoadMore
        if (baseModel == null) {
            baseModel = HomeModel()
        }
        (baseModel as HomeModel).isLoadMore = isLoadMore
        (baseModel as HomeModel).date = date.toString()
        loadData()
    }


    override fun onSuccess(any: Any) {
        if (any is HomeBean) {
            any.issueList?.get(0)?.itemList?.let {
                if (!isLoadMore) {
                    dadaist.clear()
                }
                for (i in it) {
                    if ("video" == i.type) {
                        dadaist.add(i)
                    }
                }
            }
            super.onSuccess(dadaist)

            val regEx = "[^0-9]"
            val p = Pattern.compile(regEx)
            val m = p.matcher(any.nextPageUrl.toString())
            date = m.replaceAll("").subSequence(1, m.replaceAll("").length - 1).toString()
        }
    }

    override fun onFail(any: Any) {
        if (any is BaseBean) {
            super.onFail(any.errorMessage)
        }
    }
}