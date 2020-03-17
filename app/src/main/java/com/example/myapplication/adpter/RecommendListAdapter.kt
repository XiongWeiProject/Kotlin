package com.example.myapplication.adpter

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.example.myapplication.ApplicationContext
import com.example.myapplication.R
import com.example.myapplication.bean.HomeBean
import com.example.myapplication.bean.VideoBean
import com.example.myapplication.utils.loadNormalImage
import kotlinx.android.synthetic.main.recommendlistadapter_item.view.*

class RecommendListAdapter(private var dadaist: MutableList<HomeBean.IssueListBean.ItemListBean>) :
    RecyclerView.Adapter<RecommendListAdapter.MyViewholder>() {

    companion object {
        const val TAG = "RecommendListAdapter123"
    }

    lateinit var context: AppCompatActivity

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {
        context = parent.context as AppCompatActivity
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.recommendlistadapter_item, parent, false
        )
        return MyViewholder(binding)
    }

    override fun getItemCount(): Int {
        return dadaist.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        holder.binding.setVariable(com.example.myapplication.BR.itemListBean, dadaist[position])
        holder.binding.setVariable(com.example.myapplication.BR.recommendlistadapter, this)
        holder.binding.executePendingBindings()
        val bean = dadaist[position]
        //播放器相关设置
        holder.binding.root.gsy_player_recommend_item.run {
            setUp(bean.data!!.playUrl, false, null, null)

            val imageView = ImageView(context)
            loadNormalImage(imageView, bean.data?.cover?.feed)

            thumbImageView = imageView
                thumbImageView.transitionName =
                    ApplicationContext.getString(R.string.appbar_scrolling_view_behavior)
            setThumbPlay(true)
            titleTextView.text = bean.data!!.title
            //隐藏返回按钮
            backButton.visibility = View.GONE
            //设置全屏按键功能
            fullscreenButton.setOnClickListener { _ ->
                startWindowFullscreen(context, false, true)
            }
            //防止错位设置
            playTag = TAG
            playPosition = position
            //是否根据视频尺寸，自动选择竖屏全屏或者横屏全屏
            isAutoFullWithSize = true
            //音频焦点冲突时是否释放
            isReleaseWhenLossAudio = false
            //全屏动画
            isShowFullAnimation = true
            //小屏时不触摸滑动
            setIsTouchWiget(false)
        }

        holder.binding.root.setOnClickListener {
            topDetail(bean, holder, 0)
        }
        holder.binding.root.tv_reply_recommend_item.setOnClickListener {
            topDetail(bean, holder, 1)
        }
    }

    private fun topDetail(
        bean: HomeBean.IssueListBean.ItemListBean,
        holder: MyViewholder,
        showPositon: Int
    ) {
        val desc = bean.data?.description
        val duration = bean.data?.duration
        val playUrl = bean.data?.playUrl
        val blurred = bean.data?.cover?.blurred
        val collect = bean.data?.consumption?.collectionCount
        val share = bean.data?.consumption?.shareCount
        val reply = bean.data?.consumption?.replyCount
        val photo = bean.data?.cover?.feed
        val title = bean.data?.title
        val category = bean.data?.category
        val time = System.currentTimeMillis()
        val isPlaying = holder.binding.root.gsy_player_recommend_item.isInPlayingState
        val currentPosition = holder.binding.root.gsy_player_recommend_item.currentPosition
        val videoBean = VideoBean(
            bean.data?.id,
            photo,
            title,
            desc,
            duration,
            playUrl,
            category,
            blurred,
            collect,
            share,
            reply,
            time,
            isPlaying,
            currentPosition
        )
//        VideoDetailActivity.intentThere(
//            context,
//            videoBean,
//            showPositon,
//            holder.binding.root.gsy_player_recommend_item
//        )
    }

    class MyViewholder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}
