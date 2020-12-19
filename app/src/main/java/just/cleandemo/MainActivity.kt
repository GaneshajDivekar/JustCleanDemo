package just.cleandemo

import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.ActivityScoped
import just.cleandemo.adapter.ViewPagerAdapter
import just.cleandemo.base.BaseActivity
import just.cleandemo.broadcastrecvier.ConnectivityReceiver
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

    private var connectivityReceiver: ConnectivityReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mViewBinding.root)
        initView()
    }

    override fun onStart() {
        super.onStart()
        try {
            val filter = IntentFilter()
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
            registerReceiver(connectivityReceiver, filter)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /*
    private void clearSession() {
        sessionManager.setVisitTypeIdentifierHome("");
        sessionManager.setInitiateHomeTrip("");
        sessionManager.setHomeReachedSite("");
        sessionManager.setInitiateTrip("");
        sessionManager.setVisitTypeIdentifier("");
        sessionManager.setInitiateTripDateTime("");
        sessionManager.setReachedSite("");
        sessionManager.setFinishTripDateTime("");
        sessionManager.setStartWork("");
        sessionManager.setStartWorkDateTime("");
        sessionManager.setAddExpense("");
        sessionManager.setSessionSiteCode("");
        sessionManager.setKEY_FrequencyID("");
        sessionManager.setVisit_TicketID("");
        sessionManager.setEndTrip("");
        sessionManager.setCHECKLISTJson("");
        sessionManager.setEndWork("");
        sessionManager.setEndWorkDateTime("");
        sessionManager.setSingnature("");
        sessionManager.setReviewNext("");
        sessionManager.setCompareSessionVisitTicketID("");
        sessionManager.setstrCompletedLastVisited("");
        sessionManager.setstrCompletedNoOfVisit("");
        sessionManager.setstrCompletedATMID("");
        sessionManager.setstrCompletedAddress("");
        sessionManager.setstrCompletedTicketNO("");
        sessionManager.setstrCompletedHasAccept("");
        sessionManager.setAddExpense("");
        sessionManager.setSetBackStack("");
        sessionManager.setVisitUniqueSessionID("");
        sessionManager.setAtmid("");
        sessionManager.setVisitticketidsessionnew("");
        sessionManager.setCheckListId("");
        sessionManager.setKEY_VisitUniqueID("");
        sessionManager.setCurrentCompleteUniqueId("");
        sessionManager.setRLatitude("");
        sessionManager.setAppmenucode("");
        sessionManager.setRlongitude("");
        sessionManager.setILatitude("");
        sessionManager.setIlongitude("");
        sessionManager.setNewChecklistid("");
        sessionManager.setIsexpenseapplicable("");
    }*/

    override fun onStop() {
        super.onStop()
        if (connectivityReceiver != null) {
            unregisterReceiver(connectivityReceiver)
        }
    }


    private fun initView() {
        connectivityReceiver = ConnectivityReceiver()
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