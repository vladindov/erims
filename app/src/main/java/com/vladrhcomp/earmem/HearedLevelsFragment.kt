package com.vladrhcomp.earmem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment

class HearedLevelsFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.heared_levels_6,container,false)

        val sharPref = context?.getSharedPreferences("level", Context.MODE_PRIVATE)
        val max = sharPref?.getInt("maxHearedLevel", 1)
        val podpiska =
            view.context.getSharedPreferences("val", Context.MODE_PRIVATE).getInt("podpiska", 0)
        val language = view.context.getSharedPreferences("app", Context.MODE_PRIVATE)
            .getString("language", "RU")

        view.context.getSharedPreferences("timer", Context.MODE_PRIVATE).edit().putInt("hearedOn", System.currentTimeMillis().toInt()).apply()

        when(max){
            1 -> {
                if (language == "US") {
                    view.findViewById<TextView>(R.id.textView1).text = "Level1"
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                        .setImageResource(R.drawable.ic_just_level_us)
                }

                view.findViewById<TextView>(R.id.textView2).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                    .setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView3).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                    .setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView4).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                    .setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView5).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                    .setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).visibility = View.INVISIBLE
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).visibility = View.INVISIBLE
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).visibility = View.INVISIBLE
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).visibility = View.INVISIBLE
            }
            2 -> {
                if (language == "US") {
                    view.findViewById<TextView>(R.id.textView1).text = "Level1"
                    view.findViewById<TextView>(R.id.textView2).text = "Level2"
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                        .setImageResource(R.drawable.ic_just_level_us)
                }

                view.findViewById<TextView>(R.id.textView3).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                    .setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView4).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                    .setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView5).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                    .setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).visibility = View.INVISIBLE
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).visibility = View.INVISIBLE
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).visibility = View.INVISIBLE
            }
            3 -> {
                if (language == "US") {
                    view.findViewById<TextView>(R.id.textView1).text = "Level1"
                    view.findViewById<TextView>(R.id.textView2).text = "Level2"
                    view.findViewById<TextView>(R.id.textView3).text = "Level3"
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                        .setImageResource(R.drawable.ic_just_level_us)
                }

                view.findViewById<TextView>(R.id.textView4).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                    .setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView5).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                    .setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).visibility = View.INVISIBLE
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).visibility = View.INVISIBLE
            }
            4 -> {
                if (language == "US") {
                    view.findViewById<TextView>(R.id.textView1).text = "Level1"
                    view.findViewById<TextView>(R.id.textView2).text = "Level2"
                    view.findViewById<TextView>(R.id.textView3).text = "Level3"
                    view.findViewById<TextView>(R.id.textView4).text = "Level4"
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                        .setImageResource(R.drawable.ic_just_level_us)
                }

                view.findViewById<TextView>(R.id.textView5).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                    .setImageResource(R.drawable.ic_blocked_level)
                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).visibility = View.INVISIBLE
            }
            5 -> {
                if (language == "US") {
                    view.findViewById<TextView>(R.id.textView1).text = "Level1"
                    view.findViewById<TextView>(R.id.textView2).text = "Level2"
                    view.findViewById<TextView>(R.id.textView3).text = "Level3"
                    view.findViewById<TextView>(R.id.textView4).text = "Level4"
                    view.findViewById<TextView>(R.id.textView5).text = "Level5"
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                        .setImageResource(R.drawable.ic_just_level_us)
                }

                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).visibility =
                    View.INVISIBLE
            }
            6 -> {
                if (language == "US") {
                    view.findViewById<TextView>(R.id.textView1).text = "Level1"
                    view.findViewById<TextView>(R.id.textView2).text = "Level2"
                    view.findViewById<TextView>(R.id.textView3).text = "Level3"
                    view.findViewById<TextView>(R.id.textView4).text = "Level4"
                    view.findViewById<TextView>(R.id.textView5).text = "Level5"
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                        .setImageResource(R.drawable.ic_just_level_us)
                }

                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).visibility =
                    View.INVISIBLE
            }
            7 -> {
                if (language == "US") {
                    view.findViewById<TextView>(R.id.textView1).text = "Level1"
                    view.findViewById<TextView>(R.id.textView2).text = "Level2"
                    view.findViewById<TextView>(R.id.textView3).text = "Level3"
                    view.findViewById<TextView>(R.id.textView4).text = "Level4"
                    view.findViewById<TextView>(R.id.textView5).text = "Level5"
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                        .setImageResource(R.drawable.ic_just_level_us)
                }

                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).visibility =
                    View.INVISIBLE
            }
            8 -> {
                if (language == "US") {
                    view.findViewById<TextView>(R.id.textView1).text = "Level1"
                    view.findViewById<TextView>(R.id.textView2).text = "Level2"
                    view.findViewById<TextView>(R.id.textView3).text = "Level3"
                    view.findViewById<TextView>(R.id.textView4).text = "Level4"
                    view.findViewById<TextView>(R.id.textView5).text = "Level5"
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                        .setImageResource(R.drawable.ic_just_level_us)
                }

                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).visibility =
                    View.INVISIBLE
            }
            9 -> {
                if (language == "US") {
                    view.findViewById<TextView>(R.id.textView1).text = "Level1"
                    view.findViewById<TextView>(R.id.textView2).text = "Level2"
                    view.findViewById<TextView>(R.id.textView3).text = "Level3"
                    view.findViewById<TextView>(R.id.textView4).text = "Level4"
                    view.findViewById<TextView>(R.id.textView5).text = "Level5"
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                        .setImageResource(R.drawable.ic_just_level_us)
                }

                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).visibility =
                    View.INVISIBLE
            }
            10 -> {
                if (language == "US") {
                    view.findViewById<TextView>(R.id.textView1).text = "Level1"
                    view.findViewById<TextView>(R.id.textView2).text = "Level2"
                    view.findViewById<TextView>(R.id.textView3).text = "Level3"
                    view.findViewById<TextView>(R.id.textView4).text = "Level4"
                    view.findViewById<TextView>(R.id.textView5).text = "Level5"
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                        .setImageResource(R.drawable.ic_just_level_us)
                    view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                        .setImageResource(R.drawable.ic_just_level_us)
                }

                view.findViewById<TextView>(R.id.textView6).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView7).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView8).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).visibility =
                    View.INVISIBLE
                view.findViewById<TextView>(R.id.textView9).text = ""
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel9).visibility =
                    View.INVISIBLE
            }
        }

        if (podpiska == 0) {
            if (language == "US") {
                view.findViewById<TextView>(R.id.textView1).text = "Level1"
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel1)
                    .setImageResource(R.drawable.ic_just_level_us)
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                    .setImageResource(R.drawable.ic_just_level_us)
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                    .setImageResource(R.drawable.ic_just_level_us)
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                    .setImageResource(R.drawable.ic_just_level_us)
                view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                    .setImageResource(R.drawable.ic_just_level_us)
            }

            view.findViewById<TextView>(R.id.textView2).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel2)
                .setImageResource(R.drawable.ic_blocked_level)
            view.findViewById<TextView>(R.id.textView3).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel3)
                .setImageResource(R.drawable.ic_blocked_level)
            view.findViewById<TextView>(R.id.textView4).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel4)
                .setImageResource(R.drawable.ic_blocked_level)
            view.findViewById<TextView>(R.id.textView5).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel5)
                .setImageResource(R.drawable.ic_blocked_level)
            view.findViewById<TextView>(R.id.textView6).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel6).setImageResource(R.drawable.ic_blocked_level)
            view.findViewById<TextView>(R.id.textView7).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel7).setImageResource(R.drawable.ic_blocked_level)
            view.findViewById<TextView>(R.id.textView8).text = ""
            view.findViewById<ImageButton>(R.id.imageButtonHearedLevel8).setImageResource(R.drawable.ic_blocked_level)
            view.findViewById<TextView>(R.id.textView9).text = ""
        }

        return view
    }

    companion object{
        fun newInsance() = HearedLevelsFragment()
    }

}