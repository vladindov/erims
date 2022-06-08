package com.vladrhcomp.earmem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment


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

        text.text = rating.toString()
        when(rating){
            0 -> {
                image.setImageResource(R.drawable.ic__0)
            }
            1 -> {
                image.setImageResource(R.drawable.ic__1)
            }
            2 -> {
                image.setImageResource(R.drawable.ic__2)
            }
            3 -> {
                image.setImageResource(R.drawable.ic__3)
            }
            4 -> {
                image.setImageResource(R.drawable.ic__4)
            }
            5 -> {
                image.setImageResource(R.drawable.ic__5)
            }
            6 -> {
                image.setImageResource(R.drawable.ic__6)
            }
            7 -> {
                image.setImageResource(R.drawable.ic__7)
            }
            8 -> {
                image.setImageResource(R.drawable.ic__8)
            }
            9 -> {
                image.setImageResource(R.drawable.ic__9)
            }
        }

        if(rating >= 10){
            image.setImageResource(R.drawable.ic__10)
        }
    }

    companion object{
        fun newInsance() = RatingFragment()
    }
}