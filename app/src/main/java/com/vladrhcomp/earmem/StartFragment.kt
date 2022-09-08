package com.vladrhcomp.earmem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val language =
            context?.getSharedPreferences("app", Context.MODE_PRIVATE)?.getString("language", "RU")

        if (language == "US") {
            view.findViewById<TextView>(R.id.play).text = "Games"
            view.findViewById<TextView>(R.id.snow).text = "Snowball"
            view.findViewById<TextView>(R.id.what).text = "What\nwas that"
            view.findViewById<TextView>(R.id.simulator).text = "Simulators"
            view.findViewById<TextView>(R.id.type).text = "Write\nwhat you heard"
            view.findViewById<TextView>(R.id.choice).text = "Choice"
        }
    }

    companion object {
        fun newInsance() = StartFragment()
    }
}