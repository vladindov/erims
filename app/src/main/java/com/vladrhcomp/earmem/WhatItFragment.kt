package com.vladrhcomp.earmem

import android.content.Context
import android.media.MediaPlayer
import android.opengl.Visibility
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import org.w3c.dom.Text
import kotlin.concurrent.thread

class WhatItFragment: Fragment() {
    var mMediaPlayer: MediaPlayer? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.what_it_frag,container,false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val max = view.context.getSharedPreferences("level", Context.MODE_PRIVATE).getInt("maxWhatItLevel", 1)
        val level = view.context.getSharedPreferences("level", Context.MODE_PRIVATE).getInt("whatItLevel", 1)
        val but1 = view.findViewById<Button>(R.id.button)
        val but2 = view.findViewById<Button>(R.id.button2)
        val but3 = view.findViewById<Button>(R.id.button3)
        val but4 = view.findViewById<Button>(R.id.button4)
        val text = view.findViewById<TextView>(R.id.questionTextOfWhatIt)

        view.findViewById<ConstraintLayout>(R.id.one).visibility = View.VISIBLE

        val mainThread = Handler(context?.mainLooper ?: Looper.getMainLooper())

        val runnable = Runnable {
            view.findViewById<ConstraintLayout>(R.id.one).visibility = View.GONE

            view.findViewById<LinearLayout>(R.id.first).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.second).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.third).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.forth).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.fifth).visibility = View.INVISIBLE
        }

        object : CountDownTimer(3500, 1000) {
            val textV = view.findViewById<TextView>(R.id.what_first_count)

            override fun onTick(millisUntilFinished: Long) {
                textV.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                textV.text = "Начинаем!"
            }
        }.start()

        thread {
            Thread.sleep(3000)

            mainThread.post(runnable)
        }

        when(level){
            1 -> {
                view.findViewById<ImageButton>(R.id.imageButton1).setOnClickListener {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.wind_harsh_mountain_gust_04)
                        mMediaPlayer!!.start()
                    } else {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.wind_harsh_mountain_gust_04)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton2).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.t_rex_roar)
                        mMediaPlayer!!.start()
                    } else {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.t_rex_roar)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton3).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.summer_pole)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.summer_pole)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.skrip_door)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.skrip_door)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
            }
            2 -> {
                view.findViewById<ImageButton>(R.id.imageButton1).setOnClickListener {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.shipit_cat)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.shipit_cat)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton2).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.plane)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.plane)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton3).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.night)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.night)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.hrust_snega)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.hrust_snega)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
            }
            3 -> {
                view.findViewById<ImageButton>(R.id.imageButton1).setOnClickListener {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.hrapit_cat)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.hrapit_cat)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton2).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.glass)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.glass)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton3).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.fight_two_cats)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.fight_two_cats)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.door_boom)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.door_boom)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
            }
            4 -> {
                view.findViewById<ImageButton>(R.id.imageButton1).setOnClickListener {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.car)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.car)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton2).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.plane)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.plane)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton3).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.glass)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.glass)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.cat_eat)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.cat_eat)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
            }
        }

        object : CountDownTimer(30000, 10) {
            val textV = view.findViewById<Button>(R.id.qwert)

            override fun onTick(millisUntilFinished: Long) {
                textV.text = (millisUntilFinished / 1000).toString() + "." + (millisUntilFinished - (millisUntilFinished/1000).toString().toInt() * 1000).toString()
            }

            override fun onFinish() {
                textV.text = "0"
                view.findViewById<LinearLayout>(R.id.first).visibility = View.GONE
                view.findViewById<LinearLayout>(R.id.second).visibility = View.GONE
                view.findViewById<LinearLayout>(R.id.third).visibility = View.GONE
                view.findViewById<LinearLayout>(R.id.forth).visibility = View.GONE
                view.findViewById<LinearLayout>(R.id.fifth).visibility = View.GONE
                view.findViewById<LinearLayout>(R.id.linearFinalWhatIt).visibility = View.VISIBLE
            }
        }.start()

        thread {
            Thread.sleep(30000)

            mMediaPlayer == null

            when(level){
                1 -> {
                    mainThread.post {
                    text.text = "На какой аудиозаписи было слышно рёв тирекса?"
                    but1.setOnClickListener {
                        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                    }
                    but2.setOnClickListener {
                        Toast.makeText(context, "Правильно!", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                        context?.getSharedPreferences("level", Context.MODE_PRIVATE)?.edit()?.putInt("maxWhatItLevel", level + 1)?.apply()
                    }
                    but3.setOnClickListener {
                        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                    }
                    but4.setOnClickListener {
                        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                    }
                    }
                }
                2 -> {
                    mainThread.post {
                    text.text = "На какой аудиозаписи было слышно ночь?"
                    but1.setOnClickListener {
                        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                    }
                    but2.setOnClickListener {
                        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                    }
                    but3.setOnClickListener {
                        Toast.makeText(context, "Правильно!", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                        context?.getSharedPreferences("level", Context.MODE_PRIVATE)?.edit()?.putInt("maxWhatItLevel", level + 1)?.apply()
                    }
                    but4.setOnClickListener {
                        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                    }
                    }
                }
                3 -> {
                    mainThread.post {
                    text.text = "На какой аудиозаписи было слышно храп?"
                    but1.setOnClickListener {
                        Toast.makeText(context, "Правильно!", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                        context?.getSharedPreferences("level", Context.MODE_PRIVATE)?.edit()?.putInt("maxWhatItLevel", level + 1)?.apply()
                    }
                    but2.setOnClickListener {
                        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                    }
                    but3.setOnClickListener {
                        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                    }
                    but4.setOnClickListener {
                        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                    }
                    }
                }
                4 -> {
                    mainThread.post {
                    text.text = "На какой аудиозаписи было слышно пролетающий самолёт?"
                    but1.setOnClickListener {
                        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                    }
                    but2.setOnClickListener {
                        Toast.makeText(context, "Правильно!", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                        context?.getSharedPreferences("level", Context.MODE_PRIVATE)?.edit()?.putInt("maxWhatItLevel", level + 1)?.apply()
                    }
                    but3.setOnClickListener {
                        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                    }
                    but4.setOnClickListener {
                        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
                        (activity as MainActivity).back(view)
                    }
                    }
                }
            }
        }
    }

    companion object{
        fun newInsance() = WhatItFragment()
    }

}