package com.example.myapplication.bean

import java.io.Serializable

data class HomeBean(
    var nextPageUrl: String?, var nextPublishTime: Long,
    var newestIssueType: String?, var dialog: Any?,
    var issueList: List<IssueListBean>?
) : BaseBean() {
    data class IssueListBean(
        var releaseTime: Long, var type: String?,
        var date: Long, var publishTime: Long, var count: Int,
        var itemList: List<ItemListBean>?
    ) {
        data class ItemListBean(var type: String?, var data: DataBean?, var tag: Any?) :
            Serializable {

            data class DataBean(
                var dataType: String?,
                var id: Int,
                var title: String?,
                var description: String?,
                var image: String?,
                var actionUrl: String?,
                var adTrack: Any?,
                var isShade: Boolean,
                var label: Any?,
                var labelList: Any?,
                var header: Any?,
                var category: String?,
                var duration: Long?,
                var playUrl: String,
                var cover: CoverBean?,
                var author: AuthorBean?,
                var releaseTime: Long?,
                var consumption: ConsumptionBean?,
                var playInfo: List<PlayInfoBean>
            ) : Serializable {
                data class CoverBean(
                    var feed: String?, var detail: String?,
                    var blurred: String?, var sharing: String?, var homepage: String?
                ) : Serializable {}

                data class ConsumptionBean(
                    var collectionCount: String,
                    var shareCount: String,
                    var replyCount: String
                ) : Serializable {
                }

                data class AuthorBean(var icon: String) : Serializable {}
            }
        }
    }
}