package com.vladrhcomp.earmem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment

class WhatItLevelsFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.heared_levels_6,container,false)
        val max = view.context.getSharedPreferences("level", Context.MODE_PRIVATE).getInt("maxWhatItLevel", 1)
        val podpiska = view.context.getSharedPreferences("val", Context.MODE_PRIVATE).getInt("podpiska", 0)

        view.context.getSharedPreferences("timer", Context.MODE_PRIVATE).edit().putInt("whatItOn", System.currentTimeMillis().toInt()).apply()

        view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1).setOnClickListener { (activity as MainActivity).toWhatItGame(view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1)) }
        view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2).setOnClickListener { (activity as MainActivity).toWhatItGame(view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)) }
        view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3).setOnClickListener { (activity as MainActivity).toWhatItGame(view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)) }
        view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4).setOnClickListener { (activity as MainActivity).toWhatItGame(view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)) }
        view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5).setOnClickListener { (activity as MainActivity).toWhatItGame(view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)) }
        view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).setOnClickListener { (activity as MainActivity).toWhatItGame(view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6)) }
        view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).setOnClickListener { (activity as MainActivity).toWhatItGame(view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7)) }
        view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).setOnClickListener { (activity as MainActivity).toWhatItGame(view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8)) }
        view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).setOnClickListener { (activity as MainActivity).toWhatItGame(view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9)) }
        when(max){
            1 -> {
                view.findViewById<TextView>(R.id.textView2).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView3).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView4).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView5).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).setImageResource(R.drawable.ic_blocked_level)
            }
            2 -> {
                view.findViewById<TextView>(R.id.textView3).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView4).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView5).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).setImageResource(R.drawable.ic_blocked_level)
            }
            3 -> {
                view.findViewById<TextView>(R.id.textView4).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView5).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).setImageResource(R.drawable.ic_blocked_level)
            }
            4 -> {
                view.findViewById<TextView>(R.id.textView5).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).setImageResource(R.drawable.ic_blocked_level)
            }
            5 -> {
                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).setImageResource(R.drawable.ic_blocked_level)
            }
            6 -> {
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).setImageResource(R.drawable.ic_blocked_level)
            }
            7 -> {
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).setImageResource(R.drawable.ic_blocked_level)
            }
            8 -> {
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).setImageResource(R.drawable.ic_blocked_level)
            }
            9 -> {
            }
            10 -> {
            }
        }

        if (podpiska == 0){
            view.findViewById<TextView>(R.id.textView4).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4).setImageResource(R.drawable.ic_blocked_level)
            view.findViewById<TextView>(R.id.textView5).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5).setImageResource(R.drawable.ic_blocked_level)
            view.findViewById<TextView>(R.id.textView6).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).setImageResource(R.drawable.ic_blocked_level)
            view.findViewById<TextView>(R.id.textView7).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).setImageResource(R.drawable.ic_blocked_level)
            view.findViewById<TextView>(R.id.textView8).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).setImageResource(R.drawable.ic_blocked_level)
            view.findViewById<TextView>(R.id.textView9).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).setImageResource(R.drawable.ic_blocked_level)
        }

        return view
    }

    companion object{
        fun newInsance() = WhatItLevelsFragment()
    }
}