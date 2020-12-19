package just.cleandemo.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped
import just.cleandemo.MainActivity
import just.cleandemo.R
import just.cleandemo.adapter.FavAdapter
import just.cleandemo.adapter.PostsAdapter
import just.cleandemo.adapter.PostsCommentAdapter
import just.cleandemo.databinding.FragmentFavoriteBinding
import just.cleandemo.databinding.FragmentPostsBinding
import just.cleandemo.interfaces.ItemClick
import just.cleandemo.model.databaseclass.PostsDB
import just.cleandemo.ui.activity.CommentDetailsActivity
import just.cleandemo.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.ArrayList


@AndroidEntryPoint
class Favorite_Fragment : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private val favViewModel: MainViewModel by viewModels()

    var favAdapter: FavAdapter? = null
    var displayPosts = ArrayList<PostsDB>() as List<PostsDB>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorite_, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        favViewModel.getFavouritePostsInfo("1").observe(viewLifecycleOwner, Observer {
            if (it != null) {
                displayPosts = it
                favAdapter =
                    FavAdapter(requireActivity(), displayPosts)
                binding.rcFavView.setLayoutManager(LinearLayoutManager(activity))
                binding.rcFavView.setAdapter(favAdapter)
            }
        })

    }
}