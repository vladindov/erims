package com.vladrhcomp.earmem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment

class HearedLevelsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.heared_levels,container,false)

        val sharPref = context?.getSharedPreferences("level", Context.MODE_PRIVATE)
        val max = sharPref?.getInt("maxHearedLevel", 1)

        when(max){
            1 -> {
                view = inflater.inflate(R.layout.heared_levels,container,false)
            }
            2 -> {
                view = inflater.inflate(R.layout.heared_levels_2,container,false)
            }
            3 -> {
                view = inflater.inflate(R.layout.heared_levels_3,container,false)
            }
            4 -> {
                view = inflater.inflate(R.layout.heared_levels_4,container,false)
            }
            5 -> {
                view = inflater.inflate(R.layout.heared_levels_5,container,false)
            }
            6 -> {
                view = inflater.inflate(R.layout.heared_levels_5,container,false)
            }
        }

        return view
    }

    companion object{
        fun newInsance() = HearedLevelsFragment()
    }

}