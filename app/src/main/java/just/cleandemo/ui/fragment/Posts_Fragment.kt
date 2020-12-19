package just.cleandemo.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import just.cleandemo.R
import just.cleandemo.adapter.PostsAdapter
import just.cleandemo.databinding.FragmentPostsBinding
import just.cleandemo.interfaces.ItemClick
import just.cleandemo.model.databaseclass.PostsDB
import just.cleandemo.ui.activity.CommentDetailsActivity
import just.cleandemo.utils.NetworkHelper
import just.cleandemo.viewmodel.MainViewModel
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class Posts_Fragment : Fragment(), ItemClick {
    private lateinit var binding: FragmentPostsBinding
    private val postViewModel: MainViewModel by viewModels()
    var visitAdapter: PostsAdapter? = null
    var itemClick: ItemClick? = null
    var displayPosts = ArrayList<PostsDB>() as List<PostsDB>

    @Inject
    lateinit var networkHelper: NetworkHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts_, container, false)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemClick = this
        if (networkHelper.isNetworkConnected()) {
            postViewModel.getPostsInfoo().observe(viewLifecycleOwner, Observer {
                displayPosts = it
                if(displayPosts.size==0)
                {
                    postViewModel.delete()
                    setupObservers()

                }else{
                    setupUI()
                }
            })
        } else {
            setupUI()
        }

    }

    private fun setupObservers() {
        postViewModel.putPostsData().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                for (i in it.indices) {
                    var postsDB = PostsDB()
                    postsDB.body = it.get(i).body
                    postsDB.title = it.get(i).title
                    postsDB.userId = it.get(i).userId.toString()
                    postsDB.id = it.get(i).id.toString()
                    postsDB.favoriteFlag = "0"
                    postsDB.online = "0"
                    postViewModel.insertPost(postsDB)
                }
                setupUI()
            }
        })
    }

    private fun setupUI() {
        postViewModel.getPostsInfo().observe(viewLifecycleOwner, Observer {
            displayPosts = it
            visitAdapter =
                PostsAdapter(requireActivity(), displayPosts, itemClick as Posts_Fragment)
            binding.rcView.setLayoutManager(LinearLayoutManager(activity))
            binding.rcView.setAdapter(visitAdapter)

        })
    }

    override fun onClick(pos: PostsDB) {
        var ID = pos.id
        val i = Intent(activity, CommentDetailsActivity::class.java)
        i.putExtra("ID", ID)
        startActivity(i)

    }
}