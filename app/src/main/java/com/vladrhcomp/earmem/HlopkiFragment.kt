package com.vladrhcomp.earmem

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import java.io.File


class HlopkiFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.clap_handler,container,false)

        return view
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val start = view.findViewById<Button>(R.id.butstat)
        val stop = view.findViewById<Button>(R.id.butstop)
        val path = activity?.filesDir.toString() + "/my/fir.3gp"
        val dir = File(path)
        if (!dir.exists()) {
            dir.mkdirs()
        }
        val mr = MediaRecorder()

        //(activity as MainActivity).hlopkiStart(view)

        start.setOnClickListener {
            mr.setAudioSource(MediaRecorder.AudioSource.MIC)
            mr.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            mr.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
            mr.setOutputFile(path)
            mr.prepare()
            mr.start()
            stop.isEnabled = true
            start.isEnabled = false
        }

        stop.setOnClickListener {
            mr.stop()
            start.isEnabled = true
            stop.isEnabled = false
        }

        view.findViewById<Button>(R.id.playbutt).setOnClickListener {
            val mp = MediaPlayer()
            mp.setDataSource(path)
            mp.prepare()
            mp.start()
        }
    }

    companion object{
        fun newInsance() = HlopkiFragment()
    }
}