package com.anso.learn.alivideo

import android.app.Presentation
import android.content.Context
import android.content.Intent
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.aliyun.player.AliPlayerFactory
import com.aliyun.player.IPlayer
import com.aliyun.player.source.UrlSource
import com.anso.learn.alivideo.databinding.ActivityVideoMainBinding

class VideoMainActivity : AppCompatActivity() {

    private lateinit var mDisplayManager: DisplayManager
    private lateinit var mDisplayListAdapter: DisplayListAdapter
    private val mActivePresentations = SparseArray<RemotePresentation>()

    private val mDisplayListener: DisplayManager.DisplayListener = object : DisplayManager.DisplayListener {
        override fun onDisplayAdded(displayId: Int) {
            mDisplayListAdapter.updateContents()
        }

        override fun onDisplayChanged(displayId: Int) {
            mDisplayListAdapter.updateContents()
        }

        override fun onDisplayRemoved(displayId: Int) {
            mDisplayListAdapter.updateContents()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVideoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mDisplayManager = getSystemService(Context.DISPLAY_SERVICE) as DisplayManager

        mDisplayListAdapter = DisplayListAdapter(this)

        binding.displayList.adapter = mDisplayListAdapter
    }

    override fun onResume() {
        super.onResume()
        mDisplayListAdapter.updateContents()
        mDisplayManager.registerDisplayListener(mDisplayListener, null)
    }

    private fun showPresentation(display: Display) {
        val presentation = RemotePresentation(this, display)
        mActivePresentations.put(display.displayId, presentation)
        presentation.show()
    }

    private fun hidePresentation(display: Display) {
        val displayId: Int = display.displayId
        val presentation: RemotePresentation = mActivePresentations.get(displayId) ?: return
        presentation.dismiss()
        mActivePresentations.delete(displayId)
    }

    private inner class DisplayListAdapter(val mContext: Context) : ArrayAdapter<Display?>(mContext, R.layout.list_item) {

        private val mCheckedRemoteDisplay: CompoundButton.OnCheckedChangeListener =
            object : CompoundButton.OnCheckedChangeListener {
                override fun onCheckedChanged(view: CompoundButton, isChecked: Boolean) {
                    synchronized(this) {
                        val display: Display = view.tag as Display
                        if (isChecked) {
                            showPresentation(display)
                        } else {
                            hidePresentation(display)
                        }
                    }
                }
            }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val v: View = convertView ?: layoutInflater.inflate(R.layout.list_item, null)
            val display: Display = getItem(position) ?: return v

            var tv: TextView = v.findViewById(R.id.display_id) as TextView
            tv.text = display.name.toString() + "( ID: " + display.displayId + " )"

            tv = v.findViewById(R.id.display_desc) as TextView
            tv.text = display.toString()

            val cb: CheckBox = v.findViewById(R.id.display_cb) as CheckBox
            cb.tag = display
            cb.setOnCheckedChangeListener(mCheckedRemoteDisplay)
            return v
        }

        fun updateContents() {
            clear()
            addAll(*mDisplayManager.displays)
        }
    }

    private class RemotePresentation(context: Context?, display: Display?) :
        Presentation(context, display) {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.remote_display)
            val aliPlayer = AliPlayerFactory.createAliPlayer(context)
            val urlSource = UrlSource()
//            urlSource.uri = "rtmp://58.200.131.2:1935/livetv/hunantv"
//            urlSource.uri = "https://media.w3.org/2010/05/sintel/trailer.mp4"
//            urlSource.uri = "rtmp://test.live.pull.kuaikuaikeji.com/kk/live_kklld?auth_key=1677494486-qEQ2YHV3TLgQDWqvYEwKzE0a79GeX42y-0-2e1c46bc495b72c77a63a32f5057c7d8"
            urlSource.uri =
                "rtmp://test.live.pull.kuaikuaikeji.com/kk/live_kklud?auth_key=1677494486-Gerh2tRkTFBJIZ7Zlc3oJ9YWAiFvRfJ1-0-a2adbd81c2490b9ecfb7318ff8fff6f2"
            aliPlayer.setDataSource(urlSource)
            val surfaceView = findViewById<SurfaceView>(R.id.surfaceView)
            surfaceView.holder.addCallback(object : SurfaceHolder.Callback {
                override fun surfaceCreated(holder: SurfaceHolder) {
                    aliPlayer.setSurface(holder.surface)
                }

                override fun surfaceChanged(
                    holder: SurfaceHolder, format: Int, width: Int, height: Int
                ) {
                    aliPlayer.surfaceChanged()
                }

                override fun surfaceDestroyed(holder: SurfaceHolder) {
                    aliPlayer.setSurface(null)
                }
            })
            aliPlayer.setOnPreparedListener {
                Log.v("aliPlayer", "prepare")
            }
            aliPlayer.setOnErrorListener {
                Log.v("aliPlayer", "error === ${it.code} + ${it.msg}")
            }
            aliPlayer.setOnLoadingStatusListener(object : IPlayer.OnLoadingStatusListener {
                override fun onLoadingBegin() {
                    Log.v("aliPlayer", "onLoadingBegin")
                }

                override fun onLoadingProgress(p0: Int, p1: Float) {
                    Log.v("aliPlayer", "onLoadingProgress $p0 $p1")
                }

                override fun onLoadingEnd() {
                    Log.v("aliPlayer", "onLoadingEnd")
                }
            })
            aliPlayer.isLoop = true
            aliPlayer.isAutoPlay = true
            aliPlayer.prepare()
        }
    }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, VideoMainActivity::class.java)
            context.startActivity(starter)
        }
    }
}