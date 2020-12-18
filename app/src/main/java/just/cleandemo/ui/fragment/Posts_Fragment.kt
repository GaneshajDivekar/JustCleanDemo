package just.cleandemo.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import just.cleandemo.R
import just.cleandemo.databinding.FragmentPostsBinding
import just.cleandemo.viewmodel.MainViewModel
import just.cleandemo.viewmodel.PostViewModel
import kotlinx.coroutines.MainScope


@AndroidEntryPoint
class Posts_Fragment : Fragment() {
    private lateinit var binding: FragmentPostsBinding
    private val postViewModel: PostViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_posts_, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        postViewModel.putPostsData().observe(viewLifecycleOwner, Observer {
            System.out.println("Result"+it)
        })
    }
}