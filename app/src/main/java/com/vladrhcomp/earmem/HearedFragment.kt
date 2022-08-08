package com.vladrhcomp.earmem

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.Fragment


class HearedFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.heared_text,container,false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val edit = view.findViewById<EditText>(R.id.editTextHeared)
        val sharPref = context?.getSharedPreferences("level", Context.MODE_PRIVATE)
        val num = sharPref?.getInt("levelNum", 0)

        view.findViewById<TextView>(R.id.textHearedLevel).text = "Уровень $num"

        view.findViewById<Button>(R.id.hearedButton).setOnClickListener {
            if (edit.text.toString().trim() != "") {
                (activity as MainActivity).checkText(view.context, edit.text.toString())
            }
        }

        edit.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            Toast.makeText(context, event.keyCode.toString(), Toast.LENGTH_SHORT).show()

            if (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER || actionId == EditorInfo.IME_ACTION_DONE) {
                (activity as MainActivity).checkText(view.context, edit.text.toString())
            }

            true
        })
    }

    override fun onStop() {
        val time = System.currentTimeMillis().toInt()
        val hearedTime = time - context?.getSharedPreferences("timer", Context.MODE_PRIVATE)!!.getInt("hearedOn", System.currentTimeMillis().toInt())
        context?.getSharedPreferences("timer", Context.MODE_PRIVATE)?.edit()?.putInt("hearedFull", hearedTime)?.apply()
        super.onStop()
    }

    override fun onPause() {
        val time = System.currentTimeMillis().toInt()
        val hearedTime = time - context?.getSharedPreferences("timer", Context.MODE_PRIVATE)!!.getInt("hearedOn", System.currentTimeMillis().toInt())
        context?.getSharedPreferences("timer", Context.MODE_PRIVATE)?.edit()?.putInt("hearedFull", hearedTime)?.apply()
        super.onPause()
    }

    override fun onDestroy() {
        val time = System.currentTimeMillis().toInt()
        val hearedTime = time - context?.getSharedPreferences("timer", Context.MODE_PRIVATE)!!.getInt("hearedOn", System.currentTimeMillis().toInt())
        context?.getSharedPreferences("timer", Context.MODE_PRIVATE)?.edit()?.putInt("hearedFull", hearedTime)?.apply()
        super.onDestroy()
    }

    companion object{
        fun newInsance() = HearedFragment()
    }
}