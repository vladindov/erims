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
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.KeyEvent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.text.color
import androidx.fragment.app.FragmentContainerView
import com.amulyakhare.textdrawable.TextDrawable
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.concurrent.thread


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
    fun checkText(cont: Context, text: String) {
        var checker = ""
        var m4 = ""
        var m3 = ""
        var m2 = ""
        var m1 = ""
        val sharPref = cont.getSharedPreferences("level", Context.MODE_PRIVATE)
        val num = sharPref?.getInt("levelNum", 0)

        when (num) {
            1 -> {
                checker =
                    "53 года назад. Услыхав свое имя, помесь такса с дворняжкой вышла из-под верстака, где она спала на стружках, сладко потянулась и побежала за хозяином. Заказчики Луки Александрыча жили ужасно далеко, так что, прежде чем дойти до каждого из них, столяр должен был по нескольку раз заходить в трактир и подкрепляться. Каштанка помнила, что по дороге она вела себя крайне неприлично"
                m4 =
                    "53 года назад, Помесь такса с дворняжкой, Помесь таксы с дворняжкой, Каштанка, Коштанка, Трактир, Столяр, Она спала, Стружки, Стружках, Заказчики Луки".uppercase()
                m3 =
                    "53 года назад, Помесь такса, Помесь таксы, Заказчики Луки, Каштанка, Коштанка, Трактир, Столяр, Спала".uppercase()
                m2 = "53 года назад, заказчики Луки, Каштанка, Коштанка, столяр, спала".uppercase()
                m1 = "53 года, Луки, Каштанка, Коштанка".uppercase()
            }
            2 -> {
                checker =
                    "Тема свободного человека – главная тема всего произведения, но в легенде о Данко она рассматривается в неожиданном ракурсе, Для писателя понятие \"свобода\" связано с понятием \"правда\" и \"подвиг\", Горького интересует не \"свобода\" \"от чего-либо\", а свобода \"во имя\". Горький использует жанр литературной легенды, потому что он, как нельзя лучше, подходил для его замысла: кратко, взволновано, ярко воспеть все лучшее, что может быть в человеке. Более всего писатель негодовал против эгоизма, корыстолюбия, самолюбования и гордыни"
                m4 =
                    "свободный человек, главная тема произведения, Данко, неожиданный ракурс, свобода - подвиг и правда, \"свобода во имя\" Горький , жанр литературная легенда, кратко, ярко и взволнованно воспеть хорошее в человеке, негодовал против эгоизма".uppercase()
                m3 =
                    "свободный человек, главная тема произведения, Данко, свобода - подвиг и правда, \"свобода во имя\" Горький, жанр литературная легенда воспеть хорошее в человеке, негодовал против эгоизма".uppercase()
                m2 = "свободный человек, тема произведения  свобода - подвиг и правда, \"свобода во имя\" Горький, воспеть хорошее в человеке, негодовал против эгоизма".uppercase()
                m1 = "свободный человек, \"свобода во имя\" Горький, воспеть хорошее в человеке, негодовал против эгоизма".uppercase()
            }
            3 -> {
                checker =
                    "Как раз в то время, когда почтальон с письмом поднимался по лестнице, у Чука с Геком был бой. Короче говоря, они просто выли и дрались. Из-за чего началась эта драка, я уже позабыл. Но помнится мне, что или Чук стащил у Гека пустую спичечную коробку, или, наоборот, Гек стянул у Чука жестянку из-под ваксы. \n" +
                            "Только что оба эти брата, стукнув по разу друг друга кулаками, собирались стукнуть по второму, как загремел звонок, и они с тревогой переглянулись. Они подумали, что пришла их мама! А у этой мамы был странный характер. Она не ругалась за драку, не кричала, а просто разводила драчунов по разным комнатам и целый час, а то и два не позволяла им играть вместе. А в одном часе – тик да так – целых шестьдесят минут. А в двух часах и того больше"
                m4 =
                    "Почтальон с письмами, поднимался, лестница, у Чука с Геком был бой, дрались и выли, Чук спичечную коробку или Гек жестяную банку, стукнули по разу, стукнуть второй, с тревогой переглянулись, мама, характер странный, не ругалась, разводила по комнатам и не позволяла, играть вместе, час - шестьдесят минут".uppercase()
                m3 =
                    "Почтальон с письмами, поднимался, лестница, у Чука с Геком был бой, дрались, стащил спичечную коробку или стянул жестяную банку, стукнули по разу, второй, переглянулись, мама, характер странный, не ругалась, разводила по комнатам и не позволяла, играть вместе".uppercase()
                m2 = "Почтальон, письма, лестница, бой, стащил спичечную коробку или стянул жестяную банку, стукнули по разу, второй, мама, характер странный, разводила по комнатам, играть вместе".uppercase()
                m1 = "Почтальон, лестница, бой, спичечная коробка, стукнуть, мама, характер, играть вместе".uppercase()
            }
            4 -> {
                checker =
                    "Во время моих посещений нью-йоркского рынка — мой отец и я в это время имели целый ряд небольших магазинов в Виргинии. Капитан Картер владел маленьким, но красивым коттеджем, расположенным на возвышенности, с хорошим видом на реку. Во время одного из моих посещений я заметил, что он был очень занят писанием.\n" +
                            "Тогда же он сказал мне, что в случае какого-нибудь с ним несчастья, он хотел бы, чтоб я распорядился его имуществом; он дал мне ключ от шкафа в его кабинете, где я найду завещание и некоторые указания, которыми он просил меня выполнить с точностью."
                m4 =
                    "Нью-Йоркский Рынок, я и мой отец, магазины, капитан, маленький, красивый коттедж, занят писанием, несчастье, распорядился, имущество, ключ от шкафа, завещание, указания, выполнить с точностью".uppercase()
                m3 =
                    "Нью-Йоркский Рынок, я и мой отец, магазины, маленький, красивый коттедж, занят писанием, несчастье, распорядился, имущество, ключ от шкафа, завещание".uppercase()
                m2 = "Рынок, я и мой отец, магазины, коттедж, занят писанием, несчастье, имущество, ключ от шкафа, завещание".uppercase()
                m1 = "Рынок, магазины, коттедж, занят писанием, имущество, завещание".uppercase()
            }
            5 -> {
                checker =
                    "Настасья сиротой росла, не привыкла к такому богатству, да и не шибко любительница была моду выводить, С первых годов, как жили со Степаном, надевывала, конечно, из этой шкатулки, Только не к душе ей пришлось, Наденет кольцо, Ровно как раз впору, Но закованный палец-то, в конце нали посинеет, Серьги навесит - хуже того, Уши так оттянет, что мочки распухнут, А на руки взять - не тяжелее тех, какие Настасья всегда носила"
                m4 =
                    "Сирота, росла, не привыкла, богатство, мода, Степан, шкатулка, не к душе, кольцо, впору, закованный, серьги, уши, мочки, распухнут, не тяжелее, Настасья, носила".uppercase()
                m3 =
                    "Сирота, росла, не привыкла, богатство, мода, Степан, шкатулка, кольцо, впору, закованный, серьги, уши, мочки, Настасья".uppercase()
                m2 = "Сирота, росла, богатство, мода, шкатулка, кольцо, впору, закованный, серьги, уши, мочки, Настасья".uppercase()
                m1 = "Сирота, богатство, мода, шкатулка, кольцо, серьги, уши, Настасья".uppercase()
            }
        }

        val f4 = m4.replace(Regex("""[,]"""), "").split(" ")
        val f3 = m3.replace(Regex("""[,]"""), "").split(" ")
        val f2 = m2.replace(Regex("""[,]"""), "").split(" ")
        val f1 = m1.replace(Regex("""[,]"""), "").split(" ")

        var n = similar(text, num!!)

        val spannableStringBuilder = SpannableStringBuilder()

        for (i in text.uppercase().toWords()){
            if (f4.indexOf(i) > -1 && checker.uppercase().toWords().indexOf(i) > -1){
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GREEN), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE // choose your flag
                )
            } else if (checker.uppercase().toWords().indexOf(i) > -1) {
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GRAY), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE // choose your flag
                )
            } else if (f3.indexOf(i) > -1 && checker.uppercase().toWords().indexOf(i) > -1){
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GREEN), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE // choose your flag
                )
            } else if (checker.uppercase().toWords().indexOf(i) > -1) {
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GRAY), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE // choose your flag
                )
            } else if (f2.indexOf(i) > -1 && checker.uppercase().toWords().indexOf(i) > -1){
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GREEN), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE // choose your flag
                )
            } else if (checker.uppercase().toWords().indexOf(i) > -1) {
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GRAY), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE // choose your flag
                )
            } else if (f1.indexOf(i) > -1 && checker.uppercase().toWords().indexOf(i) > -1){
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GREEN), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE // choose your flag
                )
            } else if (checker.uppercase().toWords().indexOf(i) > -1) {
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GRAY), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE // choose your flag
                )
            } else {
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.RED), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE // choose your flag
                )
            }
        }

        findViewById<EditText>(R.id.editTextHeared).text = spannableStringBuilder

        Toast.makeText(cont, "Ты сделал на $n баллов", Toast.LENGTH_SHORT).show()
        val editor = sharPref.edit()
        if (n>10) {
            if (sharPref.getInt("maxHearedLevel",1) == num) {
                editor?.putInt("maxHearedLevel", num + 1)
            }
            editor?.putInt("Rating", sharPref.getInt("Rating", 0) + n)
            editor?.apply()
        }

        findViewById<Button>(R.id.hearedButton).text = "На главную"
        findViewById<Button>(R.id.hearedButton).setOnClickListener {
            back(it)
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
        thread {
            while (mMediaPlayer?.isPlaying == true){
                findViewById<EditText>(R.id.editTextHeared).isEnabled = false
            }
        }
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
        val level = v.substring(v.lastIndexOf('/') + 1).replace("imageButtonHearedLevel","").toInt()
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
            "5" -> {
                if(level<6) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                }
            }
            "6" -> {
                if(level<7) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                }
            }
            "7" -> {
                if(level<8) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                }
            }
            "8" -> {
                if(level<9) {
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

    fun similar(text: String, num: Int): Int{
        var fr10 = "".uppercase().split(", ")
        var fr9 = "".uppercase().split(", ")
        var fr8 = "".uppercase().split(", ")
        var fr7 = "".uppercase().split(", ")
        var fr6 = "".uppercase().split(", ")
        var fr5 = "".uppercase().split(", ")
        var fr4 = "".uppercase().split(", ")
        var fr3 = "".uppercase().split(", ")
        var fr2 = "".uppercase().split(", ")
        var wr3 = "".uppercase().split(", ")
        var wr2 = "".uppercase().split(", ")
        var wr1 = "".uppercase().split(", ")

        var count = 0

        when (num){
            1 -> {
                fr5 = "Сладко потянулась и побежала за хозяином".uppercase().split(", ")
                fr4 = "53 года назад, Заказчики Луки Александрыча, Прежде чем дойти, Столяр заходить в трактир, столяр должен был заходить в трактир и подкрепляться".uppercase().split(", ")
                fr3 = "Сладко потянулась, Спала на стружках, Вела крайне неприлично".uppercase().split(", ")
                fr2 = "Побежала за хозяином, Каштанка помнишь".uppercase().split(", ")

                wr3 = "такса, дворняжкой, заказчики".uppercase().split(", ")
                wr2 = "53, трактир, столяр".uppercase().split(", ")
                wr1 = "года, Луки, Каштанка, спал, назад, помесь, она, стружки, сладко".uppercase().split(", ")
            }
            2 -> {
                fr5 = "Рассматривается в неожиданном ракурсе, Горького интересует не свобода, Правда и подвиг, Ярко воспеть все лучшее, Жанр литературная легенда, Воспеть хорошее в человеке".uppercase().split(", ")
                fr4 = "самолюбования, Свобода во имя, Негодовал против эгоизма, Литературная легенда, От чего-либо, Ярко воспеть, Тема свободного человека".uppercase().split(", ")
                fr3 = "Тема произведения, Свободный человек".uppercase().split(", ")
                fr2 = "Как нельзя лучше".uppercase().split(", ")

                wr3 = "корыстолюбие, воспеть".uppercase().split(", ")
                wr2 = "гордыня, гордыни, взволнованно, подходил, замысел, замысла, ракурс, Данко, правда, подвиг, воспеть, Горький, во имя".uppercase().split(", ")
                wr1 = "потому что, как нельзя, легенда, литературная, жанр, понятием, писателя, неожиданном, рассматривается, она, ярко, кратко, произведение, тема, эгоизм, свобода, человек, свободный".uppercase().split(", ")
            }
            3 -> {
                fr7 = "Гек стянул у Чука жестянку из-под ваксы, А просто разводила драчунов по разным комнатам".uppercase().split(", ")
                fr6 = "Из-за чего началась эта драка я уже позабыл, Чук стащил у Гека пустую спичечную коробку, Собирались стукнуть по второму, Она не ругалась за д�аку, не кричала, А то и два не позволяла им играть вместе".uppercase().split(", ")
                fr5 = "Пустая спичечная коробка, Как раз в то время, Почтальон с письмом поднимался по лестниц�, У Чука с Геком был бой, Стукнув по разу друг друга к�лаками".uppercase().split(", ")
                fr4 = "Гек стянул у Чука жестянку, А у этой мамы был странный характер, Разводила драчунов по к�мнатам".uppercase().split(", ")

                wr3 = "У Чука, Чука, С Геком, Гек, Они выли и дрались, Жестянку, Из-под, Переглянулись, Драчунов, Тик да так, Шестьдесят минут, Почтальон с письмом, Под�имался по лестнице, Началась эта драка, Я уже позабы�, Но помнится мне, Собирались стукнуть, Они с тревогой переглянулись".uppercase().split(", ")
                wr2 = "Бой, Спичечная, Стукнуть, Характер, Вместе, Короче говоря, Стянул, Жестяную/жестяная, Стукнули, По разу, Второй, Мама, Странный, Разводила, Они выли, Дралис�, Позабыл, Ваксы, Оба брата, Загремел, С тревогой, Стр�нный, Кричала, Разводила, Час, Два, Короче говоря, Пришла их мама, Целый час".uppercase().split(", ")
                wr1 = "Почтальон, Лестница, Коробка, Мама, Играть, Письма, Письмом, Поднимался, По лестнице, Стащил, Банку/банка, Характер, Комнатам, Играть, Вместе, Началась, Драка, Помнится, Братья, Стукнули, Стукнув, Друг друга, Кулаками, Стукнуть, По второму, Дважды, Звонок, Подумали, Пришла, Ругалась, Драку, Драка, Разным комнатам".uppercase().split(", ")
                }
            4 -> {
                fr10 = "Во время моих посещений нью-йоркского рынка мой отец и я в это время имели целый ряд небольших магазинов в Виргинии, Во время одного из моих посещений я заметил, что он был очень занят писанием, Тогда же он сказал мне, что в случае какого-нибудь с ним несчастья, он хотел бы, чтоб я распорядился его имуществом".uppercase().split(", ")

                fr5 = "В это время имели целый ряд небольших магазинов, Капитан Картер, у которого был маленькой и красивый коттедж, Во время одного из моих посещений".uppercase().split(", ")
                fr4 = "Во время моих посещений, Капитан, у которого был маленький коттедж, Капитан Картер, у которого был маленький коттедж, Капитан Картер, у которого был коттедж, С хорошим видом на реку, В случае какого-нибудь несчастья, Дал мне ключ от шкафа, Где я найду его завещание, Он просил меня выполнить с точностью".uppercase().split(", ")

                wr3 = "Моих посещений, Нью-йоркского рынка, Мой отец и я, В это время имели целый ряд, Небольших магазинов, Виргинии, Капитан Картер, Владел маленьким коттеджем, У него был маленький коттедж, Хорошим видом, Одного из моих посещений, Я заметил, что, Он был очень занят, Он занят писанием, В его кабинете, Он просил меня выполнить".uppercase().split(", ")
                wr2 = "Нью-йоркский рынок, Мой отец, Картер, Красивым коттеджем, Коттеджем, Хороший вид, С видом на реку, Я заметил, Он был занят, Он сказал мне, Дал мне ключ, В кабинете, завещание, Некоторые указания, Просил выполнить".uppercase().split(", ")
                wr1 = "Во время, Посещений, Рынок, В это время, Целый ряд, Магазины, Магазин, Капитан, Коттедж, Расположенным, На возвышенности, Владел, Вид, Вид на реку, Река, Во время, Писание, Тогда, Сказал, В случае, Какого-нибудь, Несчастья, Несчастье, С ним, Он хотел бы, Чтоб я, Распорядился, Имуществом, Ключ, От шкафа, Где я найду, Просил".uppercase().split(", ")
            }
            5 -> {
                fr8 = "Настасья сиротой росла, не привыкла к такому богатству, да не и шибко любительница".uppercase().split(", ")
                fr7 = "С первых годов, как жили со Степаном, надевывала из этой шкатулки".uppercase().split(", ")
                fr6 = "Только не к душе ей пришлось, В конце нали посинеет, Серьги навесит - хуже того".uppercase().split(", ")

                fr4 = "Моду выводить, Только не к душе, Ровно как раз впору, закованный палец-то, закованный палец то, В конце нали, Серьги навесит, Уши оттянет, Мочки распухнут".uppercase().split(", ")

                wr3 = "Да и не шибко, С первых годов, Жили со Степаном, Жила со Степаном, Надевывала, Не к душе, Наденет кольцо, Ровно впору, Как раз впору - 3, Закованный, Не тяжелее тех, Настасья всегда носила".uppercase().split(", ")
                wr2 = "К такому богатству, Не шибко, Любительница, Выводить, Из этой шкатулки, Нали, Серьги, Оттянет, Распухнут, Не тяжелее".uppercase().split(", ")
                wr1 = "Настасья, Сиротой, Росла, Не привыкла, К богатству, Моду, Жили, Жила, Конечно, Шкатулки, Кольцо, Наденет, Впору, Ровно, Палец, Палец-то, В конце, Посинеет, Уши, Мочки, На руки, Взять".uppercase().split(", ")
            }
        }

        val w = text.trim().replace(Regex("""[$,.;:'"-]"""), "").uppercase()

        if (fr10.isNotEmpty()) {
            for (s in fr10) {
                if (s in w) {
                    w.replace(s, "").trim()
                    count += 10
                }
            }
        }
        if (fr9.isNotEmpty())
        for (s in fr9){
            if (s in w){
                w.replace(s, "").trim()
                count += 6
            }
        }
        if (fr8.isNotEmpty())
        for (s in fr8){
            if (s in w){
                w.replace(s, "").trim()
                count += 6
            }
        }
        if (fr7.isNotEmpty())
        for (s in fr7){
            if (s in w){
                w.replace(s, "").trim()
                count += 6
            }
        }
        if (fr6.isNotEmpty())
        for (s in fr6){
            if (s in w){
                w.replace(s, "").trim()
                count += 6
            }
        }
        if (fr5.isNotEmpty())
        for (s in fr5){
            if (s in w){
                w.replace(s, "").trim()
                count += 5
            }
        }
        if (fr4.isNotEmpty())
        for (s in fr4){
            if (s in w){
                w.replace(s, "").trim()
                count += 4
            }
        }
        if (fr3.isNotEmpty())
        for (s in fr3){
            if (s in w){
                w.replace(s, "").trim()
                count += 3
            }
        }
        w.uppercase().replace("Сладко потянулась".uppercase(), "")
        if (wr3.isNotEmpty())
            for (s in wr3){
                if (s in w){
                    w.replace(s, "").trim()
                    count += 3
                }
            }
        if (fr2.isNotEmpty())
        for (s in fr2){
            if (s in w){
                w.replace(s, "").trim()
                count += 2
            }
        }
        if (wr2.isNotEmpty())
        for (s in wr2){
            if (s in w){
                w.replace(s, "").trim()
                count += 2
            }
        }
        if (wr1.isNotEmpty())
        for (s in wr1){
            if (s in w){
                w.replace(s, "").trim()
                count += 1
            }
        }

        return count-34
    }

    fun clearAllData(view: View){
        val ed = getSharedPreferences("level", Context.MODE_PRIVATE).edit()
        val testerCode = findViewById<EditText>(R.id.testerCodeText).text.toString()

        when (testerCode) {
            "clearALLdata" -> {
                ed.clear()
            }
        }
        if ("AllTo" in testerCode){
            ed.putInt("maxWhatItLevel", testerCode.replace("AllTo", "").trim().toInt())
            ed.putInt("maxHearedLevel", testerCode.replace("AllTo", "").trim().toInt())
        }
        if ("WhatIt" in testerCode){
            ed.putInt("maxWhatItLevel", testerCode.replace("WhatIt", "").trim().toInt())
        }
        if ("Heared" in testerCode){
            ed.putInt("maxHearedLevel", testerCode.replace("Heared", "").trim().toInt())
        }
        if ("setRating" in testerCode){
            ed.putInt("Rating", testerCode.replace("setRating", "").trim().toInt())
        }

        if ("setOn" in testerCode) {
            val days = getSharedPreferences("level", Context.MODE_PRIVATE).getString("Days", "0 0 0 0 0 0 0 11,11")
            val Day = days!!.split(" ").toTypedArray()

            when (testerCode.replace("setOn","")) {
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

            ed.putString(
                "Days",
                Day[0] + " " + Day[1] + " " + Day[2] + " " + Day[3] + " " + Day[4] + " " + Day[5] + " " + Day[6] + " " + Day[7]
            )
        }

        if ("setOff" in testerCode) {
            val days = getSharedPreferences("level", Context.MODE_PRIVATE).getString("Days", "0 0 0 0 0 0 0 11,11")
            val Day = days!!.split(" ").toTypedArray()

            when (testerCode.replace("setOff","")) {
                "Monday" -> {
                    Day[0] = "0"
                }
                "Tuesday" -> {
                    Day[1] = "0"
                }
                "Wednesday" -> {
                    Day[2] = "0"
                }
                "Thursday" -> {
                    Day[3] = "0"
                }
                "Friday" -> {
                    Day[4] = "0"
                }
                "Saturday" -> {
                    Day[5] = "0"
                }
                "Sunday" -> {
                    Day[6] = "0"
                }
            }

            ed.putString(
                "Days",
                Day[0] + " " + Day[1] + " " + Day[2] + " " + Day[3] + " " + Day[4] + " " + Day[5] + " " + Day[6] + " " + Day[7]
            )
        }

        findViewById<EditText>(R.id.testerCodeText).setText("")
        ed.apply()
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
}