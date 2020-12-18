package just.cleandemo

import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import just.cleandemo.adapter.ViewPagerAdapter
import just.cleandemo.base.BaseActivity
import just.cleandemo.databinding.ActivityMainBinding
import just.cleandemo.db.JustCleanDB
import just.cleandemo.db.PostsDao
import just.cleandemo.model.databaseclass.PostsDB
import just.cleandemo.ui.fragment.Favorite_Fragment
import just.cleandemo.ui.fragment.Posts_Fragment
import just.cleandemo.viewmodel.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
@ActivityScoped
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        initView()
    }

    private fun initView() {
        setupViewPager(mViewBinding.viewPager)
        mViewBinding!!.tabLayout.setupWithViewPager(mViewBinding!!.viewPager);

       /* mViewModel.putPostsData().observe(this, Observer {
            if (it != null) {
                for (i in it.indices) {
                    var postsDB = PostsDB()
                    postsDB.id = it.get(i).id.toString()
                    postsDB.body = it.get(i).body
                    postsDB.title = it.get(i).title
                    postsDB.userId = it.get(i).userId.toString()
                    mViewModel.insertInto(postsDB)
                }
            } else {
                Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show()
            }

        })
*/
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Posts_Fragment(), getString(R.string.posts).toUpperCase())
        adapter.addFragment(Favorite_Fragment(), getString(R.string.favorite).toUpperCase())
        viewPager.adapter = adapter
    }

    override val mViewModel: MainViewModel by viewModels()

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

}