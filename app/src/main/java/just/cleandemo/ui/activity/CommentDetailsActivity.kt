package just.cleandemo.ui.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import just.cleandemo.MainActivity
import just.cleandemo.R
import just.cleandemo.adapter.PostsCommentAdapter
import just.cleandemo.base.BaseActivity
import just.cleandemo.databinding.ActivityCommentDetailsBinding
import just.cleandemo.interfaces.ItemClickComment
import just.cleandemo.model.postcommentResponse.PostCommentResponse
import just.cleandemo.utils.NetworkHelper
import just.cleandemo.viewmodel.MainViewModel
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
@ActivityScoped
class CommentDetailsActivity : BaseActivity<MainViewModel, ActivityCommentDetailsBinding>(),
    ItemClickComment {

    var postsCommentAdapter: PostsCommentAdapter? = null
    var itemClick: ItemClickComment? = null
    var displayPosts = ArrayList<PostCommentResponse>() as List<PostCommentResponse>

    @Inject
    lateinit var networkHelper: NetworkHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        initView()
    }

    private fun initView() {
        itemClick=this
        var ID = intent.getStringExtra("ID")
        mViewModel.getCommentResponse(ID).observe(this, Observer {
            if (it != null) {
                postsCommentAdapter =
                    PostsCommentAdapter(this, it, itemClick as CommentDetailsActivity)
                mViewBinding.rcCommentView.setLayoutManager(LinearLayoutManager(this))
                mViewBinding.rcCommentView.setAdapter(postsCommentAdapter)
            }
        })
        mViewBinding.imageView2.setOnClickListener {
            mViewModel.updateStatusPost(ID,"1")
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    internal object Foo {
        fun a(): Int {
            return 1
        }
    }

    override fun onResume() {
        super.onResume()
        showSnack(networkHelper.isNetworkConnected())
    }
    fun showSnack(isConnected: Boolean) {
        val message: String
        val color: Int
        if (isConnected) {
            message = "Good! Connected to Internet"
            color = Color.WHITE
        } else {
            message = "Sorry! Not connected to internet"
            color = Color.RED
        }
        val snackbar = Snackbar
            .make(findViewById(R.id.coordinatorr_layout), message, Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        snackbar.show()
    }
    override val mViewModel: MainViewModel by viewModels()
    override fun getViewBinding(): ActivityCommentDetailsBinding =
        ActivityCommentDetailsBinding.inflate(layoutInflater)

    override fun onClick(pos: PostCommentResponse) {
        var pos_Id= pos.get(0).id

    }
}