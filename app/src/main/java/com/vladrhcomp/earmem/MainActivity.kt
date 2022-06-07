package com.vladrhcomp.earmem

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.media.AudioFormat
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import be.tarsos.dsp.io.jvm.AudioDispatcherFactory
import be.tarsos.dsp.onsets.OnsetHandler
import be.tarsos.dsp.onsets.PercussionOnsetDetector
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


class MainActivity : AppCompatActivity() {
    var mMediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        findViewById<BottomNavigationView>(R.id.navigation_view).setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.navigation_home -> Toast.makeText(this, "Будет добавлено позже", Toast.LENGTH_SHORT).show()
                R.id.navigation_dashboard -> Toast.makeText(this, "Будет добавлено позже", Toast.LENGTH_SHORT).show()
                R.id.navigation_notifications -> Toast.makeText(this,"Будет добавлено позже",Toast.LENGTH_SHORT).show()
                R.id.navigation_stat -> Toast.makeText(this,"Будет добавлено позже",Toast.LENGTH_SHORT).show()
            }
            false
        }

        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, StartFragment.newInsance()).commit()
    }

    fun hlopkiStart(view: View){
        val mDispatcher = AudioDispatcherFactory.fromDefaultMicrophone(48000, 1024, 0)

        val threshold = 8.0
        val sensitivity = 20.0

        val mPercussionDetector = PercussionOnsetDetector(22050.toFloat(), 1024,
            OnsetHandler { time, salience -> Toast.makeText(this, "Clap!", Toast.LENGTH_SHORT).show() }, sensitivity, threshold
        )

        mDispatcher.addAudioProcessor(mPercussionDetector)
        Thread(mDispatcher, "Audio Dispatcher").start()
    }

    fun toHlop(view: View){
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, HlopkiFragment.newInsance()).commit()
    }

    fun checkText(cont: Context, text: String){
        var checker = ""
        val sharPref = cont.getSharedPreferences("level", Context.MODE_PRIVATE)
        val num = sharPref?.getInt("levelNum", 0)

        when(num){
            1 -> {
                checker = "53 года назад. Услыхав свое имя, помесь такса с дворняжкой вышла из-под верстака, где она спала на стружках, сладко потянулась и побежала за хозяином. Заказчики Луки Александрыча жили ужасно далеко, так что, прежде чем дойти до каждого из них, столяр должен был по нескольку раз заходить в трактир и подкрепляться. Каштанка помнила, что по дороге она вела себя крайне неприлично"
            }
            2 -> {
                checker = "Тема свободного человека – главная тема всего произведения, но в легенде о Данко она рассматривается в неожиданном ракурсе. Для писателя понятие «свобода» связано с понятием «правда» и «подвиг». Горького интересует не «свобода» «от чего-либо», а свобода «во имя». Горький использует жанр литературной легенды, потому что он, как нельзя лучше, подходил для его замысла: кратко, взволновано, ярко воспеть все лучшее, что может быть в человеке. Более всего писатель негодовал против эгоизма, корыстолюбия, самолюбования и гордыни."
            }
            3 -> {
                checker = "Как раз в то время, когда почтальон с письмом поднимался по лестнице, у Чука с Геком был бой. Короче говоря, они просто выли и дрались. Из-за чего началась эта драка, я уже позабыл. Но помнится мне, что или Чук стащил у Гека пустую спичечную коробку, или, наоборот, Гек стянул у Чука жестянку из-под ваксы. \n" +
                        "Только что оба эти брата, стукнув по разу друг друга кулаками, собирались стукнуть по второму, как загремел звонок, и они с тревогой переглянулись. Они подумали, что пришла их мама! А у этой мамы был странный характер. Она не ругалась за драку, не кричала, а просто разводила драчунов по разным комнатам и целый час, а то и два не позволяла им играть вместе. А в одном часе – тик да так – целых шестьдесят минут. А в двух часах и того больше."
            }
            4 -> {
                checker = "Во время моих посещений нью-йоркского рынка — мой отец и я в это время имели целый ряд небольших магазинов в Виргинии. Капитан Картер владел маленьким, но красивым коттеджем, расположенным на возвышенности, с хорошим видом на реку. Во время одного из моих посещений я заметил, что он был очень занят писанием.\n" +
                        "Тогда же он сказал мне, что в случае какого-нибудь с ним несчастья, он хотел бы, чтоб я распорядился его имуществом; он дал мне ключ от шкафа в его кабинете, где я найду завещание и некоторые указания, которыми он просил меня выполнить с точностью."
            }
            5 -> {
                checker = "Настасья сиротой росла, не привыкла к такому богатству, да и не шибко любительница была моду выводить. С первых годов, как жили со Степаном, надевывала, конечно, из этой шкатулки. Только не к душе ей пришлось. Наденет кольцо... Ровно как раз впору. Но закованный палец-то, в конце нали посинеет. Серьги навесит - хуже того. Уши так оттянет, что мочки распухнут. А на руки взять - не тяжелее тех, какие Настасья всегда носила."
            }
        }

        if(text != null && text == checker){
            Toast.makeText(cont, "Молодец!", Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.mainFragment, StartFragment.newInsance()).commit()
            if (mMediaPlayer?.isPlaying == true) return mMediaPlayer?.stop()!!
            val editor = sharPref?.edit()
            editor?.putInt("maxHearedLevel", num!!+1)
            editor?.apply()
        }else if(text != null && text.toWords() == checker.toWords()){
            Toast.makeText(cont, "Не плохой результат", Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.mainFragment, StartFragment.newInsance()).commit()
            if (mMediaPlayer?.isPlaying == true) return mMediaPlayer?.stop()!!
            val editor = sharPref?.edit()
            editor?.putInt("maxHearedLevel", num!!+1)
            editor?.apply()
        }else{
            return Toast.makeText(cont, "Попробуй снова", Toast.LENGTH_SHORT).show()
        }
    }

    fun Play(view: View){
        val sharPref = getSharedPreferences("level", Context.MODE_PRIVATE)
        val num = sharPref.getInt("levelNum", 1)

        if (mMediaPlayer?.isPlaying == true) return mMediaPlayer?.pause()!!

        when(num){
            1 -> {
                if (mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.uroven1_chisto)
                    mMediaPlayer!!.start()
                } else mMediaPlayer!!.start()
            }
            2 -> {
                if (mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.uroven2_chisto)
                    mMediaPlayer!!.start()
                } else mMediaPlayer!!.start()
            }
            3 -> {
                if (mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.uroven3_chisto)
                    mMediaPlayer!!.start()
                } else mMediaPlayer!!.start()
            }
            4 -> {
                if (mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.uroven4_chisto)
                    mMediaPlayer!!.start()
                } else mMediaPlayer!!.start()
            }
            5 -> {
                if (mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.uroven5_chisto)
                    mMediaPlayer!!.start()
                } else mMediaPlayer!!.start()
            }
        }
    }

    fun playRepeat(view: View){
        val numb = resources.getResourceName(view.id)
        val num = numb.substring(numb.lastIndexOf('/') + 1).replace("imageButtonHearedLevel", "").toInt()

        val sharPref = getSharedPreferences("level", Context.MODE_PRIVATE)
        val editor = sharPref.edit()
        editor.putInt("levelNum", num)
        val max = sharPref.getInt("maxHearedLevel",1)

        if (num in 1..max){
            supportFragmentManager.beginTransaction().replace(R.id.mainFragment, HearedFragment.newInsance()).commit()
        }else{
            Toast.makeText(this, "Недоступно для вашего аккаунта", Toast.LENGTH_SHORT).show()
        }
        editor.apply()
    }

    fun back(view: View){
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, StartFragment.newInsance()).commit()
        if (mMediaPlayer?.isPlaying == true){
            mMediaPlayer?.stop()!!
            mMediaPlayer = null
        }
    }

    fun toWhatIt(view: View){
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, WhatItLevelsFragment.newInsance()).commit()
    }

    fun toWhatItGame(view: View){
        val v = resources.getResourceName(view.id)
        val level = v.substring(v.lastIndexOf('/') + 1).replace("imageButtonWhatItLevel","").toInt()
        val sharPref = getSharedPreferences("level", Context.MODE_PRIVATE).edit()
        val max = getSharedPreferences("level", Context.MODE_PRIVATE).getInt("maxWhatItLevel", 1)

        when(max.toString()){
            "1" -> {
                if(level<2) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                }
            }
            "2" -> {
                if(level<3) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                }
            }
            "3" -> {
                if(level<4) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                }
            }
            "4" -> {
                if(level<5) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                }
            }
        }
        sharPref.putInt("whatItLevel", level)
        sharPref.apply()
    }

    fun Say(view: View){
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }

        // Get the Intent action
        val sttIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        // Language model defines the purpose, there are special models for other use cases, like search.
        sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        // Adding an extra language, you can use any language from the Locale class.
        sttIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        // Text that shows up on the Speech input prompt.
        sttIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now!")
        try {
            // Start the intent for a result, and pass in our request code.
            startActivityForResult(sttIntent, 1)
        } catch (e: ActivityNotFoundException) {
            // Handling error when the service is not available.
            e.printStackTrace()
            Toast.makeText(this, "Your device does not support STT.", Toast.LENGTH_LONG).show()
        }
    }

    fun GoodBye(view: View){
        Toast.makeText(this,"Будет добавлено позже",Toast.LENGTH_SHORT).show()
    }

    fun toPlay(view: View){
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, HearedLevelsFragment.newInsance()).commit()
    }

    fun String.toWords() = trim().replace(Regex("""[$,.;:'"-]"""), "").filter { !it.isWhitespace() }.toList()

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            // Handle the result for our request code.
            1 -> {
                // Safety checks to ensure data is available.
                if (resultCode == Activity.RESULT_OK && data != null) {
                    // Retrieve the result array.
                    val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                    // Ensure result array is not null or empty to avoid errors.
                    if (!result.isNullOrEmpty()) {
                        // Recognized text is in the first position.
                        val recognizedText = result[0]
                        // Do what you want with the recognized text.
                        findViewById<EditText>(R.id.editTextHeared).setText(recognizedText)
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    override fun onBackPressed() {
    }
}