package just.cleandemo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import just.cleandemo.adapter.ViewPagerAdapter
import just.cleandemo.base.BaseActivity
import just.cleandemo.databinding.ActivityMainBinding
import just.cleandemo.ui.fragment.Favorite_Fragment
import just.cleandemo.ui.fragment.Posts_Fragment
import just.cleandemo.utils.NetworkHelper
import just.cleandemo.viewmodel.MainViewModel
import javax.inject.Inject


@AndroidEntryPoint
@ActivityScoped
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    @Inject
    lateinit var networkHelper: NetworkHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        initView()
    }

    private fun initView() {

        setupViewPager(mViewBinding.viewPager)
        mViewBinding!!.tabLayout.setupWithViewPager(mViewBinding!!.viewPager);
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Posts_Fragment(), getString(R.string.posts).toUpperCase())
        adapter.addFragment(Favorite_Fragment(), getString(R.string.favorite).toUpperCase())
        viewPager.adapter = adapter
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
            .make(findViewById(R.id.coordinator_layout), message, Snackbar.LENGTH_LONG)
        val sbView = snackbar.view
        snackbar.show()
    }

    override val mViewModel: MainViewModel by viewModels()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

}