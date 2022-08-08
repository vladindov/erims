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
import java.lang.NullPointerException
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
        val but1 = view.findViewById<ImageButton>(R.id.button)
        val but2 = view.findViewById<ImageButton>(R.id.button2)
        val but3 = view.findViewById<ImageButton>(R.id.button3)
        val but4 = view.findViewById<ImageButton>(R.id.button4)
        val but5 = view.findViewById<ImageButton>(R.id.button5)
        val but6 = view.findViewById<ImageButton>(R.id.button6)
        val but7 = view.findViewById<ImageButton>(R.id.button7)
        val but8 = view.findViewById<ImageButton>(R.id.button8)
        val but9 = view.findViewById<ImageButton>(R.id.button9)
        val but10 = view.findViewById<ImageButton>(R.id.button10)
        val but11 = view.findViewById<ImageButton>(R.id.button11)
        val but12 = view.findViewById<ImageButton>(R.id.button12)
        val but13 = view.findViewById<ImageButton>(R.id.button13)
        val but14 = view.findViewById<ImageButton>(R.id.button14)
        val but15 = view.findViewById<ImageButton>(R.id.button15)
        val imBut1 = view.findViewById<ImageButton>(R.id.imageButton1)
        val imBut2 = view.findViewById<ImageButton>(R.id.imageButton2)
        val imBut3 = view.findViewById<ImageButton>(R.id.imageButton3)
        val imBut4 = view.findViewById<ImageButton>(R.id.imageButton4)
        val imBut5 = view.findViewById<ImageButton>(R.id.imageButton5)
        val imBut6 = view.findViewById<ImageButton>(R.id.imageButton6)
        val imBut7 = view.findViewById<ImageButton>(R.id.imageButton7)
        val imBut8 = view.findViewById<ImageButton>(R.id.imageButton8)
        val text4 = view.findViewById<TextView>(R.id.questionTextOfWhatIt)
        val text5 = view.findViewById<TextView>(R.id.questionTextOfWhatIt5)
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
                in 1..2 -> {
                    view.findViewById<LinearLayout>(R.id.fort).visibility = View.GONE

                    view.findViewById<LinearLayout>(R.id.f3).visibility = View.GONE
                    view.findViewById<LinearLayout>(R.id.f6).visibility = View.GONE

                    view.findViewById<TextView>(R.id.textView4).text = "3"
                    view.findViewById<TextView>(R.id.textView5).text = "4"
                }
                in 3..5 -> {
                    view.findViewById<LinearLayout>(R.id.f3).visibility = View.GONE
                    view.findViewById<LinearLayout>(R.id.f6).visibility = View.GONE
                    view.findViewById<LinearLayout>(R.id.f8).visibility = View.GONE
                    view.findViewById<LinearLayout>(R.id.f9).visibility = View.GONE

                    view.findViewById<TextView>(R.id.textView4).text = "3"
                    view.findViewById<TextView>(R.id.textView5).text = "4"
                    view.findViewById<TextView>(R.id.textView7).text = "5"
                }
                in 6..9 -> {
                    view.findViewById<LinearLayout>(R.id.f3).visibility = View.GONE
                    view.findViewById<LinearLayout>(R.id.f6).visibility = View.GONE
                    view.findViewById<LinearLayout>(R.id.f9).visibility = View.GONE

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
                view.findViewById<ImageButton>(R.id.imageButton7).setOnClickListener{
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
                view.findViewById<ImageButton>(R.id.imageButton7).setOnClickListener{
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
            9 -> {
                view.findViewById<ImageButton>(R.id.imageButton1).setOnClickListener {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.bird_fall)
                        mMediaPlayer!!.start()
                    } else {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.bird_fall)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton2).setOnClickListener {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.door_boom)
                        mMediaPlayer!!.start()
                    } else {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.door_boom)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton4).setOnClickListener {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.coffe)
                        mMediaPlayer!!.start()
                    } else {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.coffe)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton5).setOnClickListener {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.more_boom)
                        mMediaPlayer!!.start()
                    } else {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.more_boom)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton7).setOnClickListener {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(context, R.raw.glass)
                        mMediaPlayer!!.start()
                    } else {
                        mMediaPlayer!!.stop()
                        mMediaPlayer = MediaPlayer.create(context, R.raw.glass)
                        mMediaPlayer!!.start()
                    }
                    it.visibility = View.INVISIBLE
                }
                view.findViewById<ImageButton>(R.id.imageButton8).setOnClickListener {
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
            }
        }

        when (level) {
            in 1..2 -> {
                object : CountDownTimer(15000, 10) {
                    val textV = view.findViewById<Button>(R.id.qwert)

                    override fun onTick(millisUntilFinished: Long) {
                        textV.text = (millisUntilFinished / 1000).toString() + "." + (millisUntilFinished - (millisUntilFinished/1000).toString().toInt() * 1000).toString()
                    }

                    override fun onFinish() {
                        try {
                            textV.text = "0"
                            view.findViewById<LinearLayout>(R.id.first).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.second).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.third).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.forth).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.fifth).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.fort).visibility = View.GONE
                            mMediaPlayer?.release()!!
                        } catch (e: NullPointerException){
                            return
                        }
                    }
                }.start()
            }
            in 3..5 -> {
                object : CountDownTimer(20000, 10) {
                    val textV = view.findViewById<Button>(R.id.qwert)

                    override fun onTick(millisUntilFinished: Long) {
                        textV.text = (millisUntilFinished / 1000).toString() + "." + (millisUntilFinished - (millisUntilFinished/1000).toString().toInt() * 1000).toString()
                    }

                    override fun onFinish() {
                        try {
                            textV.text = "0"
                            view.findViewById<LinearLayout>(R.id.first).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.second).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.third).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.forth).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.fifth).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.fort).visibility = View.GONE
                            mMediaPlayer?.release()!!
                        } catch (e: NullPointerException){
                            return
                        }
                    }
                }.start()
            }
            in 6..9 -> {
                object : CountDownTimer(25000, 10) {
                    val textV = view.findViewById<Button>(R.id.qwert)

                    override fun onTick(millisUntilFinished: Long) {
                        textV.text = (millisUntilFinished / 1000).toString() + "." + (millisUntilFinished - (millisUntilFinished/1000).toString().toInt() * 1000).toString()
                    }

                    override fun onFinish() {
                        try {
                            textV.text = "0"
                            view.findViewById<LinearLayout>(R.id.first).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.second).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.third).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.forth).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.fifth).visibility = View.GONE
                            view.findViewById<LinearLayout>(R.id.fort).visibility = View.GONE
                            mMediaPlayer?.release()!!
                        } catch (e: NullPointerException){
                            return
                        }
                    }
                }.start()
            }
        }

        thread {
            when (level) {
                in 1..2 -> {
                    Thread.sleep(15000)
                }
                in 3..5 -> {
                    Thread.sleep(20000)
                }
                in 6..9 -> {
                    Thread.sleep(25000)
                }
            }


            if (mMediaPlayer?.isPlaying == true) mMediaPlayer!!.stop()

            when(level){
                in 1..2 -> {
                    mainThread.post {
                        view.findViewById<LinearLayout>(R.id.linearFinalWhatIt4).visibility = View.VISIBLE
                    }
                }
                in 3..5 -> {
                    mainThread.post {
                        view.findViewById<LinearLayout>(R.id.linearFinalWhatIt5).visibility = View.VISIBLE
                    }
                }
                in 6..9 -> {
                    mainThread.post {
                        view.findViewById<LinearLayout>(R.id.linearFinalWhatIt8).visibility = View.VISIBLE
                    }
                }
            }

            when(level){
                1 -> {
                    mainThread.post {
                        text4.text = "Где вы услышали рёв тирекса?"
                        buttonCheck(view, level, but1, but2, but3, but4, 2)
                    }
                }
                2 -> {
                    mainThread.post {
                        text4.text = "Где вы услышали ночь?"
                        buttonCheck(view, level, but1, but2, but3, but4, 3)
                    }
                }
                3 -> {
                    mainThread.post {
                        text5.text = "Где вы услышали храп?"
                        buttonCheckFor5(view, level, but11, but12, but13, but14, but15, 1)
                    }
                }
                4 -> {
                    mainThread.post {
                        text5.text = "Где вы услышали пролетающий самолёт?"
                        buttonCheckFor5(view, level, but11, but12, but13, but14, but15, 2)
                    }
                }
                5 -> {
                    mainThread.post {
                        text5.text = "Где вы услышали взрыв?"
                        buttonCheckFor5(view, level, but11, but12, but13, but14, but15, 4)
                    }
                }
                6 -> {
                    mainThread.post {
                        text8.text = "Где вы услышали лодки?"
                        buttonCheckFor8(view, level, but5, but6, but7, but8, but9, but10, 1)
                    }
                }
                7 -> {
                    mainThread.post {
                        text8.text = "Где вы услышали звуки уборки пыли?"
                        buttonCheckFor8(view, level, but5, but6, but7, but8, but9, but10, 1)
                    }
                }
                8 -> {
                    mainThread.post {
                        text8.text = "Где вы услышали фырканье лошади?"
                        buttonCheckFor8(view, level, but5, but6, but7, but8, but9, but10, 6)
                    }
                }
                9 -> {
                    mainThread.post {
                        text8.text = "Где вы услышали разбивающееся стекло?"
                        buttonCheckFor8(view, level, but5, but6, but7, but8, but9, but10, 5)
                    }
                }
            }
        }
    }

    fun buttonCheck(view: View, level: Int, but1: ImageButton, but2: ImageButton, but3: ImageButton, but4: ImageButton, rightAns: Int){
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

    fun buttonCheckFor5(view: View, level: Int, but1: ImageButton, but2: ImageButton, but3: ImageButton, but4: ImageButton, but5: ImageButton, rightAns: Int){
        but1.No(view)
        but2.No(view)
        but3.No(view)
        but4.No(view)
        but5.No(view)

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
        }
    }

    fun buttonCheckFor8(view: View, level: Int, but1: ImageButton, but2: ImageButton, but3: ImageButton, but4: ImageButton, but5: ImageButton, but6: ImageButton, rightAns: Int){
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

    fun ImageButton.No(view: View) = setOnClickListener {
        Toast.makeText(context, "Не правильно", Toast.LENGTH_SHORT).show()
        (activity as MainActivity).back(view)
    }

    fun ImageButton.Yes(view: View, level: Int) = setOnClickListener {
        Toast.makeText(context, "Правильно", Toast.LENGTH_SHORT).show()
        (activity as MainActivity).back(view)
        context?.getSharedPreferences("level", Context.MODE_PRIVATE)?.edit()?.putInt("maxWhatItLevel", level + 1)?.apply()
        context?.getSharedPreferences("level", Context.MODE_PRIVATE)?.edit()?.putInt("Rating", context?.getSharedPreferences("level", Context.MODE_PRIVATE)?.getInt("Rating", 0)!! + 1)?.apply()
    }

    override fun onStop() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
        val time = System.currentTimeMillis().toInt()
        val whatTime = time - context?.getSharedPreferences("timer", Context.MODE_PRIVATE)!!.getInt("whatItOn", System.currentTimeMillis().toInt())
        context?.getSharedPreferences("timer", Context.MODE_PRIVATE)?.edit()?.putInt("whatItFull", whatTime)?.apply()
        super.onStop()
    }

    override fun onPause() {
        val time = System.currentTimeMillis().toInt()
        val whatTime = time - context?.getSharedPreferences("timer", Context.MODE_PRIVATE)!!.getInt("whatItOn", System.currentTimeMillis().toInt())
        context?.getSharedPreferences("timer", Context.MODE_PRIVATE)?.edit()?.putInt("whatItFull", whatTime)?.apply()
        super.onPause()
    }

    override fun onDestroy() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
        val time = System.currentTimeMillis().toInt()
        val whatTime = time - context?.getSharedPreferences("timer", Context.MODE_PRIVATE)!!.getInt("whatItOn", System.currentTimeMillis().toInt())
        context?.getSharedPreferences("timer", Context.MODE_PRIVATE)?.edit()?.putInt("whatItFull", whatTime)?.apply()
        super.onDestroy()
    }

    companion object{
        fun newInsance() = WhatItFragment()
    }

}