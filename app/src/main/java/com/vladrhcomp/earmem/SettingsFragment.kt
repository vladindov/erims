package com.vladrhcomp.earmem

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val language =
            context?.getSharedPreferences("app", Context.MODE_PRIVATE)?.getString("language", "RU")

        if (language == "US") {
            view.findViewById<TextView>(R.id.settings).text = "SETTINGS"
            view.findViewById<TextView>(R.id.subscript).text = "BUY A SUBSCRIPTION"
            view.findViewById<TextView>(R.id.lang).text = "CHANGE LANGUAGE"
        }
        view.findViewById<TextView>(R.id.lang).setOnClickListener {
            if (language == "RU")
                context?.getSharedPreferences("app", Context.MODE_PRIVATE)!!.edit()
                    .putString("language", "US").apply()
            else if (language == "US")
                context?.getSharedPreferences("app", Context.MODE_PRIVATE)!!.edit()
                    .putString("language", "RU").apply()
            (activity as MainActivity).finishAffinity()
        }
    }

    companion object {
        fun newInsance() = SettingsFragment()
    }

}