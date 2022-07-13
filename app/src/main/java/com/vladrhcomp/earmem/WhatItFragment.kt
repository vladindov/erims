package com.vladrhcomp.earmem

import android.content.Context
import android.media.MediaPlayer
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
import kotlin.concurrent.thread

class WhatItFragment: Fragment() {
    var mMediaPlayer: MediaPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.what_it_frag_5, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val max = view.context.getSharedPreferences("level", Context.MODE_PRIVATE).getInt("maxWhatItLevel", 1)
        val level = view.context.getSharedPreferences("level", Context.MODE_PRIVATE).getInt("whatItLevel", 1)
        val but1 = view.findViewById<Button>(R.id.button)
        val but2 = view.findViewById<Button>(R.id.button2)
        val but3 = view.findViewById<Button>(R.id.button3)
        val but4 = view.findViewById<Button>(R.id.button4)
        val but5 = view.findViewById<Button>(R.id.button5)
        val but6 = view.findViewById<Button>(R.id.button6)
        val but7 = view.findViewById<Button>(R.id.button7)
        val but8 = view.findViewById<Button>(R.id.button8)
        val but9 = view.findViewById<Button>(R.id.button9)
        val but10 = view.findViewById<Button>(R.id.button10)
        val imBut1 = view.findViewById<ImageButton>(R.id.imageButton1)
        val imBut2 = view.findViewById<ImageButton>(R.id.imageButton2)
        val imBut3 = view.findViewById<ImageButton>(R.id.imageButton3)
        val imBut4 = view.findViewById<ImageButton>(R.id.imageButton4)
        val imBut5 = view.findViewById<ImageButton>(R.id.imageButton5)
        val imBut6 = view.findViewById<ImageButton>(R.id.imageButton6)
        val imBut7 = view.findViewById<ImageButton>(R.id.imageButton7)
        val imBut8 = view.findViewById<ImageButton>(R.id.imageButton8)
        val text4 = view.findViewById<TextView>(R.id.questionTextOfWhatIt)
        val text8 = view.findViewById<TextView>(R.id.questionTextOfWhatIt8)

        view.findViewById<ConstraintLayout>(R.id.one).visibility = View.VISIBLE

        val mainThread = Handler(context?.mainLooper ?: Looper.getMainLooper())

        val runnable = Runnable {
            view.findViewById<ConstraintLayout>(R.id.one).visibility = View.GONE

            view.findViewById<LinearLayout>(R.id.first).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.second).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.third).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.forth).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.fort).visibility = View.VISIBLE
            view.findViewById<LinearLayout>(R.id.fifth).visibility = View.INVISIBLE

            when (level) {
                in 1..4 -> {
                    view.findViewById<LinearLayout>(R.id.fort).visibility = View.GONE

                    view.findViewById<ImageButton>(R.id.imageButton3).visibility = View.GONE
                    view.findViewById<ImageButton>(R.id.imageButton6).visibility = View.GONE
                    view.findViewById<TextView>(R.id.textView3).visibility = View.GONE
                    view.findViewById<TextView>(R.id.textView6).visibility = View.GONE

                    view.findViewById<TextView>(R.id.textView4).text = "3"
                    view.findViewById<TextView>(R.id.textView5).text = "4"
                }
                in 5..8 -> {
                    view.findViewById<ImageButton>(R.id.imageButton3).visibility = View.GONE
                    view.findViewById<ImageButton>(R.id.imageButton6).visibility = View.GONE
                    view.findViewById<ImageButton>(R.id.imageButton9).visibility = View.GONE

                    view.findViewById<TextView>(R.id.textView3).visibility = View.GONE
                    view.findViewById<TextView>(R.id.textView6).visibility = View.GONE
                    view.findViewById<TextView>(R.id.textView9).visibility = View.GONE

                    view.findViewById<TextView>(R.id.textView4).text = "3"
                    view.findViewById<TextView>(R.id.textView5).text = "4"
                    view.findViewById<TextView>(R.id.textView7).text = "5"
                    view.findViewById<TextView>(R.id.textView8).text = "6"
                }
            }
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
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
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
                view.findViewById<ImageButton>(R.id.imageButton5).setOnClickListener{
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
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
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
                view.findViewById<ImageButton>(R.id.imageButton5).setOnClickListener{
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
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
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
                view.findViewById<ImageButton>(R.id.imageButton5).setOnClickListener{
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
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
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
                view.findViewById<ImageButton>(R.id.imageButton5).setOnClickListener{
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
            5 -> {
                view.findViewById<ImageButton>(R.id.imageButton1).setOnClickListener {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.applods)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.applods)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton2).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.bird_fall)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.bird_fall)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.fire)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.fire)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton5).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.boom)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.boom)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton7).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.jingle_bells)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.jingle_bells)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton8).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.moy)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.moy)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
            }
            6 -> {
                view.findViewById<ImageButton>(R.id.imageButton1).setOnClickListener {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.boat_skrip)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.boat_skrip)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton2).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.camera_chick)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.camera_chick)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.booben)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.booben)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton5).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.coffe)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.coffe)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton7).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.signal)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.signal)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton8).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.more_boom)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.more_boom)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
            }
            7 -> {
                view.findViewById<ImageButton>(R.id.imageButton1).setOnClickListener {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.clean_polki)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.clean_polki)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton2).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.water_fall)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.water_fall)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.yula2)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.yula2)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton5).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.clean_snow)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.clean_snow)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton7).setOnClickListener{
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
                view.findViewById<ImageButton>(R.id.imageButton8).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.water_fall_out)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.water_fall_out)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
            }
            8 -> {
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
                        mMediaPlayer = MediaPlayer.create(context, R.raw.fire)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.fire)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener{
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
                view.findViewById<ImageButton>(R.id.imageButton5).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.hlopushki_fall)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.hlopushki_fall)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton7).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.zipp)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.zipp)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton8).setOnClickListener{
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.horse_fir)
                        mMediaPlayer!!.start()
                    } else  {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.horse_fir)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
            }
        }

        when (level) {
            in 1..4 -> {
                object : CountDownTimer(22000, 10) {
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
                        view.findViewById<LinearLayout>(R.id.fort).visibility = View.GONE
                    }
                }.start()
            }
            in 5..8 -> {
                object : CountDownTimer(32000, 10) {
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
                        view.findViewById<LinearLayout>(R.id.fort).visibility = View.GONE
                    }
                }.start()
            }
        }

        thread {
            when (level) {
                in 1..4 -> {
                    Thread.sleep(22000)
                }
                in 5..8 -> {
                    Thread.sleep(32000)
                }
            }


            if (mMediaPlayer?.isPlaying == true) mMediaPlayer!!.stop()

            when(level){
                in 1..4 -> {
                    mainThread.post {
                        view.findViewById<LinearLayout>(R.id.linearFinalWhatIt4).visibility = View.VISIBLE
                    }
                }
                in 5..8 -> {
                    mainThread.post {
                        view.findViewById<LinearLayout>(R.id.linearFinalWhatIt8).visibility = View.VISIBLE
                    }
                }
            }

            when(level){
                1 -> {
                    mainThread.post {
                        text4.text = "На какой аудиозаписи было слышно рёв тирекса?"
                        buttonCheck(view, level, but1, but2, but3, but4, 2)
                    }
                }
                2 -> {
                    mainThread.post {
                        text4.text = "На какой аудиозаписи было слышно ночь?"
                        buttonCheck(view, level, but1, but2, but3, but4, 3)
                    }
                }
                3 -> {
                    mainThread.post {
                        text4.text = "На какой аудиозаписи было слышно храп?"
                        buttonCheck(view, level, but1, but2, but3, but4, 2)
                    }
                }
                4 -> {
                    mainThread.post {
                        text4.text = "На какой аудиозаписи было слышно пролетающий самолёт?"
                        buttonCheck(view, level, but1, but2, but3, but4, 2)
                    }
                }
                5 -> {
                    mainThread.post {
                        text8.text = "На какой аудиозаписи было слышно взрыв?"
                        buttonCheckFor8(view, level, but5, but6, but7, but8, but9, but10, 4)
                    }
                }
                6 -> {
                    mainThread.post {
                        text8.text = "На какой аудиозаписи было слышно скрип лодки?"
                        buttonCheckFor8(view, level, but5, but6, but7, but8, but9, but10, 1)
                    }
                }
                7 -> {
                    mainThread.post {
                        text8.text = "На какой аудиозаписи было слышно звуки уборки пыли?"
                        buttonCheckFor8(view, level, but5, but6, but7, but8, but9, but10, 1)
                    }
                }
                8 -> {
                    mainThread.post {
                        text8.text = "На какой аудиозаписи было слышно фырканье лошади?"
                        buttonCheckFor8(view, level, but5, but6, but7, but8, but9, but10, 6)
                    }
                }
            }
        }
    }

    fun buttonCheck(view: View, level: Int, but1: Button, but2: Button, but3: Button, but4: Button, rightAns: Int){
        but1.No(view)
        but2.No(view)
        but3.No(view)
        but4.No(view)

        when(rightAns){
            1 -> {
                but1.Yes(view, level)
            }
            2 -> {
                but2.Yes(view, level)
            }
            3 -> {
                but3.Yes(view, level)
            }
            4 -> {
                but4.Yes(view, level)
            }
        }
    }

    fun buttonCheckFor8(view: View, level: Int, but1: Button, but2: Button, but3: Button, but4: Button, but5: Button, but6: Button, rightAns: Int){
        but1.No(view)
        but2.No(view)
        but3.No(view)
        but4.No(view)
        but5.No(view)
        but6.No(view)

        when(rightAns){
            1 -> {
                but1.Yes(view, level)
            }
            2 -> {
                but2.Yes(view, level)
            }
            3 -> {
                but3.Yes(view, level)
            }
            4 -> {
                but4.Yes(view, level)
            }
            5 -> {
                but5.Yes(view, level)
            }
            6 -> {
                but6.Yes(view, level)
            }
        }
    }

    fun Button.No(view: View) = setOnClickListener {
        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
        (activity as MainActivity).back(view)
    }

    fun Button.Yes(view: View, level: Int) = setOnClickListener {
        Toast.makeText(context, "Правильно", Toast.LENGTH_SHORT).show()
        (activity as MainActivity).back(view)
        context?.getSharedPreferences("level", Context.MODE_PRIVATE)?.edit()?.putInt("maxWhatItLevel", level + 1)?.apply()
        context?.getSharedPreferences("level", Context.MODE_PRIVATE)?.edit()?.putInt("Rating", context?.getSharedPreferences("level", Context.MODE_PRIVATE)?.getInt("Rating", 0)!! + 1)?.apply()
    }

    companion object{
        fun newInsance() = WhatItFragment()
    }

}