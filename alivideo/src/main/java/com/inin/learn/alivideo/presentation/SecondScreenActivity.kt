package com.inin.learn.alivideo.presentation

import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aliyun.player.AliPlayer
import com.aliyun.player.AliPlayerFactory
import com.aliyun.player.IPlayer
import com.aliyun.player.source.UrlSource
import com.inin.learn.alivideo.R
import com.inin.learn.alivideo.databinding.ActivitySecondScreenBinding
import com.inin.learn.baselibrary.LogUtils

class SecondScreenActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySecondScreenBinding
    private val displayAdapter by lazy { DisplayAdapter() }
    private lateinit var displayManager: DisplayManager
    private var presentation: KKLiveClassPresentation? = null

    private lateinit var aliPlayer: AliPlayer
    private lateinit var playerLayout: View
    private lateinit var surfaceView: SurfaceView
    private lateinit var surfaceViewContent: TextView

    private val displayListener: DisplayManager.DisplayListener = object : DisplayManager.DisplayListener {
        override fun onDisplayAdded(displayId: Int) {
            updateAdapterData()
        }

        override fun onDisplayChanged(displayId: Int) {
            updateAdapterData()
        }

        override fun onDisplayRemoved(displayId: Int) {
            updateAdapterData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_second_screen)
        displayManager = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        mBinding.displayRecycleView.adapter = displayAdapter
        initPlayerLayout()
        initAliPlay()
        initListener()
    }

    private fun initPlayerLayout() {
        playerLayout = layoutInflater.inflate(R.layout.aliplayer_surface_view_layout, null)
        LogUtils.asLogVerbose(playerLayout.toString())
        surfaceView = playerLayout.findViewById(R.id.surfaceView)
        surfaceViewContent = playerLayout.findViewById(R.id.surfaceViewContent)
        mBinding.container.addView(playerLayout)
    }

    private fun initAliPlay() {
        aliPlayer = AliPlayerFactory.createAliPlayer(this)

        val urlSource = UrlSource()
        urlSource.uri = "https://media.w3.org/2010/05/sintel/trailer.mp4"
        aliPlayer.setDataSource(urlSource)

        surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                aliPlayer.setSurface(holder.surface)
            }

            override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
                aliPlayer.surfaceChanged()
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                aliPlayer.setSurface(null)
            }
        })
        aliPlayer.setOnPreparedListener {
            LogUtils.asLogVerbose("KKPresentation -->  IPlayer.OnLoadingStatusListener --> setOnPreparedListener")
        }
        aliPlayer.setOnErrorListener {
            LogUtils.asLogVerbose("KKPresentation -->  IPlayer.OnLoadingStatusListener --> setOnErrorListener")
        }
        aliPlayer.setOnLoadingStatusListener(object : IPlayer.OnLoadingStatusListener {
            override fun onLoadingBegin() {
                LogUtils.asLogVerbose("KKPresentation -->  IPlayer.OnLoadingStatusListener --> onLoadingBegin")
            }

            override fun onLoadingProgress(p0: Int, p1: Float) {
                LogUtils.asLogVerbose("KKPresentation --> IPlayer.OnLoadingStatusListener --> onLoadingProgress")
            }

            override fun onLoadingEnd() {
                LogUtils.asLogVerbose("KKPresentation -->  IPlayer.OnLoadingStatusListener --> onLoadingEnd")
            }
        })

        aliPlayer.isLoop = true
        aliPlayer.isAutoPlay = true
        aliPlayer.prepare()
    }

    private fun initListener() {
        displayAdapter.setOnItemClickListener { adapter, view, position ->
            val displayInfoEntity = displayAdapter.data[position]
            showPresentation(displayInfoEntity.display)
        }
        mBinding.stopSecondScreen.setOnClickListener {
            presentation?.let {
                it.stopSecondScreen()
                mBinding.container.addView(playerLayout)
            }
        }
        mBinding.controlSecondScreen.setOnClickListener {
            presentation?.setCountView("--------")
            surfaceViewContent.text = "--------------"
        }
    }

    private fun showPresentation(display: Display) {
        mBinding.container.removeView(playerLayout)
        presentation = KKLiveClassPresentation(playerLayout, this, display)
        presentation?.show()
    }

    private fun hidePresentation() {
        presentation?.dismiss()
    }

    override fun onResume() {
        super.onResume()
        updateAdapterData()
        displayManager.registerDisplayListener(displayListener, null)
    }

    override fun onStop() {
        super.onStop()
        hidePresentation()
    }

    private fun updateAdapterData() {
        displayAdapter.setNewInstance(handlerDisplayInfo())
    }

    private fun handlerDisplayInfo(): ArrayList<DisplayInfoEntity> {
        val entityList = arrayListOf<DisplayInfoEntity>()
        displayManager.displays.forEach { display ->
            entityList.add(DisplayInfoEntity(display, display.displayId, getDisplayInfo(display)))
        }
        return entityList
    }

    private fun getDisplayInfo(display: Display): String {
        return "显示器：${display.name}  id = ${display.displayId} "
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, SecondScreenActivity::class.java)
            context.startActivity(starter)
        }
    }
}