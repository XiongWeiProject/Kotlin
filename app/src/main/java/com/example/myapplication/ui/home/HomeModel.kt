package com.example.myapplication.ui.home

import com.example.myapplication.bean.HomeBean
import io.reactivex.Observable

class HomeModel : BaseModel() {
    var isLoadMore = false
    lateinit var date: String

    override fun getObservable(): Observable<HomeBean> {
        return if (isLoadMore) getRestService().getHomeMoreData(
            date,
            "3"
        ) else getRestService().getHomeData()
    }

}