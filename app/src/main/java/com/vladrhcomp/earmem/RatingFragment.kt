package com.vladrhcomp.earmem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*


class RatingFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.rating_fragment,container,false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = view.findViewById<ImageView>(R.id.ratingImage)
        val text = view.findViewById<TextView>(R.id.ratingText)
        val rating = view.context.getSharedPreferences("level", Context.MODE_PRIVATE).getInt("Rating", 0)
        val value = 100 // всего очков
        val days = view.context.getSharedPreferences("level", Context.MODE_PRIVATE).getString("Days", "0 0 0 0 0 0 0 11,11")

        val edit = view.context.getSharedPreferences("level", Context.MODE_PRIVATE).edit()
        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek: String = sdf.format(d)
        val nowDate = SimpleDateFormat("DD,MM").format(Date())

        when (dayOfTheWeek){
            "Monday" -> {
                edit.putString("Days", "0 0 0 0 0 0 0 $nowDate")
            }
            "Tuesday" -> {
                edit.putString("Days", "0 0 0 0 0 0 0 $nowDate")
            }
            "Wednesday" -> {
                edit.putString("Days", "0 0 0 0 0 0 0 $nowDate")
            }
            "Thursday" -> {
                edit.putString("Days", "0 0 0 0 0 0 0 $nowDate")
            }
            "Friday" -> {
                edit.putString("Days", "0 0 0 0 0 0 0 $nowDate")
            }
            "Saturday" -> {
                edit.putString("Days", "0 0 0 0 0 0 0 $nowDate")
            }
            "Sunday" -> {
                edit.putString("Days", "0 0 0 0 0 0 0 $nowDate")
            }
        }

        text.text = "$rating/$value очков"
        val ratingP = (rating/value)*100
        when(ratingP){
            in 0..5 -> {
                image.setImageResource(R.drawable.ic__0)
            }
            in 6..15 -> {
                image.setImageResource(R.drawable.ic__1)
            }
            in 16..25 -> {
                image.setImageResource(R.drawable.ic__2)
            }
            in 26..35 -> {
                image.setImageResource(R.drawable.ic__3)
            }
            in 36..45 -> {
                image.setImageResource(R.drawable.ic__4)
            }
            in 46..55 -> {
                image.setImageResource(R.drawable.ic__5)
            }
            in 56..65 -> {
                image.setImageResource(R.drawable.ic__6)
            }
            in 66..75 -> {
                image.setImageResource(R.drawable.ic__7)
            }
            in 76..85 -> {
                image.setImageResource(R.drawable.ic__8)
            }
            in 86..95 -> {
                image.setImageResource(R.drawable.ic__9)
            }
        }

        if(ratingP >= 96){
            image.setImageResource(R.drawable.ic__10)

            val Day = days!!.split(" ").toTypedArray()

            when(dayOfTheWeek){
                "Monday" -> {
                    Day[0] = "1"
                }
                "Tuesday" -> {
                    Day[1] = "1"
                }
                "Wednesday" -> {
                    Day[2] = "1"
                }
                "Thursday" -> {
                    Day[3] = "1"
                }
                "Friday" -> {
                    Day[4] = "1"
                }
                "Saturday" -> {
                    Day[5] = "1"
                }
                "Sunday" -> {
                    Day[6] = "1"
                }
            }

            edit.putString("Days", Day[0] + " " + Day[1] + " " + Day[2] + " " + Day[3] + " " + Day[4] + " " + Day[5] + " " + Day[6] + " " + Day[7])
        }
        edit.apply()

        val closedDays = view.context.getSharedPreferences("level", Context.MODE_PRIVATE).getString("Days", "0 0 0 0 0 0 0 11,11")
        val all = closedDays!!.split(" ").toTypedArray()

        if (days!!.split(" ").toTypedArray()[7] != all[7]){
            edit.putInt("Rating", 0)
        }

        if(all[0] == "1"){
            view.findViewById<ImageView>(R.id.imageMon).visibility = View.VISIBLE
        }
        if(all[1] == "1"){
            view.findViewById<ImageView>(R.id.imageTues).visibility = View.VISIBLE
        }
        if(all[2] == "1"){
            view.findViewById<ImageView>(R.id.imageWed).visibility = View.VISIBLE
        }
        if(all[3] == "1"){
            view.findViewById<ImageView>(R.id.imageThur).visibility = View.VISIBLE
        }
        if(all[4] == "1"){
            view.findViewById<ImageView>(R.id.imageFri).visibility = View.VISIBLE
        }
        if(all[5] == "1"){
            view.findViewById<ImageView>(R.id.imageSat).visibility = View.VISIBLE
        }
        if(all[6] == "1"){
            view.findViewById<ImageView>(R.id.imageSun).visibility = View.VISIBLE
        }
    }

    companion object{
        fun newInsance() = RatingFragment()
    }
}