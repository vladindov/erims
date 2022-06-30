package com.vladrhcomp.earmem

import android.Manifest
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Html
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.text.color
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*


class MainActivity : AppCompatActivity() {
    // а этот отдельный плеер нужен, чтобы у нас телефон не забывал родительский элемент и можно было удобно управлять всеми аудио приложения
    var mMediaPlayer: MediaPlayer? = null

    // Открываем главный экран и инициализируем нижнее меню
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        findViewById<BottomNavigationView>(R.id.navigation_view).setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    findViewById<FragmentContainerView>(R.id.ratingContainer).visibility = View.GONE
                    findViewById<FragmentContainerView>(R.id.mainFragment).visibility = View.VISIBLE
                    val set = item.icon
                    set.mutate().setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_IN)
                    item.icon = set
                    supportFragmentManager.beginTransaction().replace(R.id.mainFragment, StartFragment.newInsance()).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    findViewById<FragmentContainerView>(R.id.ratingContainer).visibility = View.VISIBLE
                    findViewById<FragmentContainerView>(R.id.mainFragment).visibility = View.GONE
                    supportFragmentManager.beginTransaction().replace(R.id.ratingContainer, ChatFragment.newInsance()).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    Toast.makeText(this, "Будет добавлено позже", Toast.LENGTH_SHORT).show()
                }
                R.id.navigation_stat -> {
                    findViewById<FragmentContainerView>(R.id.ratingContainer).visibility = View.VISIBLE
                    val set = item.icon
                    set.mutate().setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_IN)
                    item.icon = set
                    supportFragmentManager.beginTransaction().replace(R.id.ratingContainer, RatingFragment.newInsance()).commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, StartFragment.newInsance()).commit()
    }

    // проверка пермишенов и включение игры
    fun hlopkiStart(view: View){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE), 121)
        }else{
            findViewById<Button>(R.id.butstat).isEnabled = true
        }
    }

    // будет открывать игру
    fun toHlop(view: View){
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, HlopkiFragment.newInsance()).commit()
    }

    // чисто проверка текста и начисление баллов
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

        if (num == 1){
            val f4 =
                "53 года назад, помесь такса с дворняжкой, помесь таксы с дворняжкой, Помесь такса с дворняжкой, Помесь таксы с дворняжкой, Каштанка, Коштанка, каштанка, коштанка, трактир, Трактир, столяр, Столяр, она спала, Она спала, Стружки, Стружках, Стружки, Стружках, заказчики Луки, Заказчики Луки".replace(Regex("""[,]"""), "").split(
                    " "
                )
            val f3 =
                "53 года назад, помесь такса, помесь таксы, Помесь такса, Помесь таксы, заказчики Луки, Заказчики Луки, Каштанка, Коштанка, каштанка, коштанка, трактир, Трактир, Столяр, Спала, столяр, спала".replace(Regex("""[,]"""), "").split(
                    " "
                )
            val f21 = "53 года назад, заказчики Луки, Каштанка, Коштанка, каштанка, коштанка, столяр, спала".replace(Regex("""[,]"""), "").split(" ")
            val f1 = "53 года, Луки, Каштанка, Коштанка, каштанка, коштанка".replace(Regex("""[,]"""), "").split(" ")

            val n = similar(text, num)

            val red = "#ff0000"
            val green = "#008000"
            val grey = "#7d7f7d"
            val spanSB = SpannableStringBuilder()
            var fiText = ""

            for (i in text.toWords()){
                when (n){
                    4 -> {
                        fiText += if (f4.indexOf(i) > -1 && checker.toWords().indexOf(i) > -1){
                            "<font color=$green>$i</font> "
                        } else if (checker.toWords().indexOf(i) > -1) {
                            "<font color=$grey>$i</font> "
                        } else {
                            fiText += "<font color=$red>$i</font> "
                        }
                    }
                    3 -> {
                        fiText += if (f3.indexOf(i) > -1 && checker.toWords().indexOf(i) > -1){
                            "<font color=$green>$i</font> "
                        } else if (checker.toWords().indexOf(i) > -1) {
                            "<font color=$grey>$i</font> "
                        } else {
                            fiText += "<font color=$red>$i</font> "
                        }
                    }
                    2 -> {
                        fiText += if (f21.indexOf(i) > -1 && checker.toWords().indexOf(i) > -1){
                            "<font color=$green>$i</font> "
                        } else if (checker.toWords().indexOf(i) > -1) {
                            "<font color=$grey>$i</font> "
                        } else {
                            fiText += "<font color=$red>$i</font> "
                        }
                    }
                    1 -> {
                        fiText += if (f1.indexOf(i) > -1 && checker.toWords().indexOf(i) > -1){
                            "<font color=$green>$i</font> "
                        } else if (checker.toWords().indexOf(i) > -1) {
                            "<font color=$grey>$i</font> "
                        } else {
                            fiText += "<font color=$red>$i</font> "
                        }
                    }
                }
            }

            findViewById<EditText>(R.id.editTextHeared).setText(Html.fromHtml(fiText))

//            when(n){
//                4 -> {
//                    for (i in text.toWords()){
//                        var k = 0
//                        for (s in f4){
//                            if (i == s){
//                                k++
//                                return
//                            }
//                        }
//                        if (k > 0){
//                            spanSB.append("$i ")
//                            spanSB.setSpan(green, spanSB.length - 1 - i.length,spanSB.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                            return
//                        } else {
//                            spanSB.append("$i ")
//                            spanSB.setSpan(grey, spanSB.length - 1 - i.length,spanSB.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                        }
//                    }
//                }
//                3 -> {
//                    for (i in text.toWords()){
//                        var k = 0
//                        for (s in f3){
//                            if (i == s){
//                                k++
//                                return
//                            }
//                        }
//                        if (k > 0){
//                            spanSB.append("$i ")
//                            spanSB.setSpan(green, spanSB.length - 1 - i.length,spanSB.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                            return
//                        } else {
//                            spanSB.append("$i ")
//                            spanSB.setSpan(grey, spanSB.length - 1 - i.length,spanSB.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                        }
//                    }
//                }
//                2 -> {
//                    for (i in text.toWords()){
//                        var k = 0
//                        for (s in f21){
//                            if (i == s){
//                                k++
//                                return
//                            }
//                        }
//                        if (k > 0){
//                            spanSB.append("$i ")
//                            spanSB.setSpan(green, spanSB.length - 1 - i.length,spanSB.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                            return
//                        } else {
//                            spanSB.append("$i ")
//                            spanSB.setSpan(grey, spanSB.length - 1 - i.length,spanSB.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                        }
//                    }
//                }
//                1 -> {
//                    for (i in text.toWords()){
//                        var k = 0
//                        for (s in f1){
//                            if (i == s){
//                                k++
//                                return
//                            }
//                        }
//                        if (k > 0){
//                            spanSB.append("$i ")
//                            spanSB.setSpan(green, spanSB.length - 1 - i.length,spanSB.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                            return
//                        } else {
//                            spanSB.append("$i ")
//                            spanSB.setSpan(grey, spanSB.length - 1 - i.length,spanSB.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
//                        }
//                    }
//                }
//            }

//            for (s in text){
//                for (i in f4){
//                    for (ir in i){
//                        if (s == ir){
//                            spanSB.append("$s ")
//                        }
//                    }
//                }
//            }

            Toast.makeText(cont, "Ты сделал на $n/4", Toast.LENGTH_SHORT).show()
            val editor = sharPref.edit()
            editor?.putInt("maxHearedLevel", num + 1)
            editor?.putInt("Rating", sharPref.getInt("Rating", 0) + n)
            editor?.apply()
        }
        else if(similarity(text, checker) >= 0.4){
            Toast.makeText(cont, "Молодец!", Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.mainFragment, StartFragment.newInsance()).commit()
            if (mMediaPlayer?.isPlaying == true) return mMediaPlayer?.stop()!!
            val editor = sharPref?.edit()
            editor?.putInt("maxHearedLevel", num!!+1)
            editor?.putInt("Rating", sharPref.getInt("Rating", 0) + 5)
            editor?.apply()
        }else if(similarity(text, checker) >= 0.15){
            Toast.makeText(cont, "Не плохой результат", Toast.LENGTH_SHORT).show()
            supportFragmentManager.beginTransaction().replace(R.id.mainFragment, StartFragment.newInsance()).commit()
            if (mMediaPlayer?.isPlaying == true) return mMediaPlayer?.stop()!!
            val editor = sharPref?.edit()
            editor?.putInt("maxHearedLevel", num!!+1)
            editor?.putInt("Rating", sharPref.getInt("Rating", 0) + 3)
            editor?.apply()
        }else{
            return Toast.makeText(cont, "Попробуй снова", Toast.LENGTH_SHORT).show()
        }
    }

    // запускает нужное аудио
    fun Play(view: View){
        val sharPref = getSharedPreferences("level", Context.MODE_PRIVATE)
        val num = sharPref.getInt("levelNum", 1)
        val b = findViewById<ImageButton>(R.id.imageButtonHeared)

        if (mMediaPlayer?.isPlaying == true) return mMediaPlayer?.pause()!!

        when(num){
            1 -> {
                if (mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.uroven1_chisto)
                    mMediaPlayer!!.start()
                }
            }
            2 -> {
                if (mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.uroven2_chisto)
                    mMediaPlayer!!.start()
                }
            }
            3 -> {
                if (mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.uroven3_chisto)
                    mMediaPlayer!!.start()
                }
            }
            4 -> {
                if (mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.uroven4_chisto)
                    mMediaPlayer!!.start()
                }
            }
            5 -> {
                if (mMediaPlayer == null) {
                    mMediaPlayer = MediaPlayer.create(this, R.raw.uroven5_chisto)
                    mMediaPlayer!!.start()
                }
            }
        }
        b.visibility = View.INVISIBLE
    }

    // отклик программы на нажание перехода к уровню
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

    // возвращение к главному экрану
    fun back(view: View) {
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, StartFragment.newInsance()).commit()
        if (mMediaPlayer?.isPlaying == true){
            mMediaPlayer?.stop()!!
            mMediaPlayer = null
        }
    }

    // переход в "что это было"
    fun toWhatIt(view: View){
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, WhatItLevelsFragment.newInsance()).commit()
    }

    // запуск нужного уровня
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
        if(level>max){
            Toast.makeText(this, "Недоступно для вашего аккаунта", Toast.LENGTH_SHORT).show()
        }
        sharPref.putInt("whatItLevel", level)
        sharPref.apply()
    }

    // говоришь никому всякое
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

    // на всём, чего нет))
    fun GoodBye(view: View){
        Toast.makeText(this,"Будет добавлено позже",Toast.LENGTH_SHORT).show()
    }

    // уход в "напишите услышанное"
    fun toPlay(view: View){
        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, HearedLevelsFragment.newInsance()).commit()
    }

    // очистка строки от любых символов
    fun String.toWords() = trim().replace(Regex("""[$,.;:'"-]"""), "").split(" ")

    // Подсчёт схожести двух стрингов (от 0 до 1)
    fun similarity(s1: String, s2: String): Double {
        var longer = s1
        var shorter = s2
        if (s1.length < s2.length) { // longer should always have greater length
            longer = s2
            shorter = s1
        }
        val longerLength = longer.length
        return if (longerLength == 0) {
            1.0 /* both strings are zero length */
        } else (longerLength - editDistance(longer, shorter)) / longerLength.toDouble()
        /* // If you have Apache Commons Text, you can use it to calculate the edit distance:
    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    return (longerLength - levenshteinDistance.apply(longer, shorter)) / (double) longerLength; */
    }

    // Example implementation of the Levenshtein Edit Distance
    // See http://rosettacode.org/wiki/Levenshtein_distance#Java
    fun editDistance(s1: String, s2: String): Int {
        var s1 = s1
        var s2 = s2
        s1 = s1.lowercase(Locale.getDefault())
        s2 = s2.lowercase(Locale.getDefault())
        val costs = IntArray(s2.length + 1)
        for (i in 0..s1.length) {
            var lastValue = i
            for (j in 0..s2.length) {
                if (i == 0) costs[j] = j else {
                    if (j > 0) {
                        var newValue = costs[j - 1]
                        if (s1[i - 1] != s2[j - 1]) newValue = Math.min(
                            Math.min(newValue, lastValue),
                            costs[j]
                        ) + 1
                        costs[j - 1] = lastValue
                        lastValue = newValue
                    }
                }
            }
            if (i > 0) costs[s2.length] = lastValue
        }
        return costs[s2.length]
    }

    // схожеть 2
    fun similar(s1: String, s2: Int): Int{
        if (s2 == 1) {
            var sum = 0
            val f4 =
                "53 года назад, помесь такса с дворняжкой, помесь таксы с дворняжкой, Помесь такса с дворняжкой, Помесь таксы с дворняжкой, Каштанка, Коштанка, каштанка, коштанка, трактир, Трактир, столяр, Столяр, она спала, Она спала, Стружки, Стружках, Стружки, Стружках, заказчики Луки, Заказчики Луки".split(
                    ", "
                )
            val f3 =
                "53 года назад, помесь такса, помесь таксы, Помесь такса, Помесь таксы, заказчики Луки, Заказчики Луки, Каштанка, Коштанка, каштанка, коштанка, трактир, Трактир, Столяр, Спала, столяр, спала".split(
                    ", "
                )
            val f21 = "53 года назад, заказчики Луки, Каштанка, Коштанка, каштанка, коштанка, столяр, спала".split(", ")
            val f22 = "53 года назад, заказчики Луки, Каштанка, Коштанка, каштанка, коштанка, спала".split(", ")
            val f1 = "53 года, Луки, Каштанка, Коштанка, каштанка, коштанка".split(", ")

            for (i in f4){
                if (i in s1) sum++
            }
            if (sum >= 8){
                return 4
            } else sum = 0

            for (i in f3){
                if (i in s1) sum++
            }
            if (sum >= 7){
                return 3
            } else sum = 0

            for (i in f21){
                if (i in s1) sum++
            }
            if (sum >= 5){
                return 2
            } else sum = 0

            for (i in f22){
                if (i in s1) sum++
            }
            if (sum >= 4){
                return 2
            } else sum = 0

            for (i in f1){
                if (i in s1) sum++
            }
            if (sum >= 3){
                return 1
            } else sum = 0
        }

        return 0
    }

    // наговоил лишнего, а теперь принимем
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

    // При сворачивании выключаем ненужное *надо знать life cycle
    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
    }

    // при нажатии "назад" ничего не делаем
    override fun onBackPressed() {
    }

    // тот самый чек пермишенов
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 121 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            findViewById<Button>(R.id.butstat).isEnabled = true
        }
    }
}