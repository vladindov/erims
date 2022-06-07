package com.vladrhcomp.earmem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class WhatItLevelsFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.what_levels_4,container,false)
        val max = view.context.getSharedPreferences("level", Context.MODE_PRIVATE).getInt("maxWhatItLevel", 1)

        when(max){
            1 -> {
                view = inflater.inflate(R.layout.what_levels_1,container,false)
                return view
            }
            2 -> {
                view = inflater.inflate(R.layout.what_levels_2,container,false)
                return view
            }
            3 -> {
                view = inflater.inflate(R.layout.what_levels_3,container,false)
                return view
            }
           4 -> {
                view = inflater.inflate(R.layout.what_levels_4,container,false)
                return view
            }
        }
        return view
    }

    companion object{
        fun newInsance() = WhatItLevelsFragment()
    }
}