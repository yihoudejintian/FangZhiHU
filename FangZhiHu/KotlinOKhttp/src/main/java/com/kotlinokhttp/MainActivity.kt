package com.kotlinokhttp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kotlinokhttp.entity.ListData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import network.ApiManagerHelp
import network.BaseSubscriber
import network.api.ApiService
import network.entity.ApiResponse
import javax.inject.Inject

class MainActivity : BaseActivity<HomePresenterImpl>(),HomeContact.View {
    private val TAG:String = MainActivity::class.java.simpleName

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun injectActivity() {
        super.injectActivity()
        getActivityComponent().inject(this)
        var a = Sa(1)
        a.ss
    }


    override fun requestDataSuccess(homeDetailEntity: Any) {

    }


    fun requestData(view:View){
//        val loanService = ApiManagerHelp.getLoanService(ApiService::class.java)
//        Log.e(TAG,"=="+loanService)
//        val listInfo = loanService.getListInfo(1);
//        var subscriber = object :BaseSubscriber<ApiResponse<ListData>>(){
//            override fun onSuccess(t: ApiResponse<ListData>) {
//                Log.e(TAG,"==数据=="+t.toString())
//            }
//
//            override fun onFailure(error: ApiResponse<String>) {
//                Log.e(TAG,"==失败=="+error.toString())
//            }
//        }
//
//        listInfo.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribeWith(subscriber)
        mPresenter!!.getHomeData()

    }
}
