package com.vladrhcomp.earmem

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jsoup.Jsoup
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    // а этот отдельный плеер нужен, чтобы у нас телефон не забывал родительский элемент и можно было удобно управлять всеми аудио приложения
    var mMediaPlayer: MediaPlayer? = null

    // Открываем главный экран и инициализируем нижнее меню
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        val language = getSharedPreferences("app", Context.MODE_PRIVATE).getString("language", "RU")

        val days = getSharedPreferences("level", Context.MODE_PRIVATE).getString(
            "Days",
            "0 0 0 0 0 0 0 11,11"
        )
        val editD = getSharedPreferences("level", Context.MODE_PRIVATE).edit()
        val nowDate = SimpleDateFormat("dd,MM").format(Calendar.getInstance().time)
        if (days!!.split(" ").toTypedArray()[7] != nowDate) {
            editD.putInt("Rating", 0)
        }
        editD.apply()

        try {
            val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            if (cm.activeNetworkInfo!!.isConnected) {
                forUpdate()
            }
        } catch (e: java.lang.NullPointerException) {
            if (language == "RU")
                AlertDialog.Builder(this).setMessage("Подключитесь к интернету")
                    .setCancelable(false)
                    .setPositiveButton("Ок", DialogInterface.OnClickListener { dialog, which ->
                        this.finishAffinity()
                    }).show()
            else if (language == "US")
                AlertDialog.Builder(this).setMessage("Connect to internet").setCancelable(false)
                    .setPositiveButton("Ок", DialogInterface.OnClickListener { dialog, which ->
                        this.finishAffinity()
                    }).show()
        }

        val sharPref = getSharedPreferences("level", Context.MODE_PRIVATE)

        val editor = sharPref.edit()
        if (sharPref.getInt("Id", 0) == 0) {
            thread {
                val idSite = Jsoup.connect("https://earmem.herokuapp.com/")
                    .data("to", "percentCheck").get()
                val id = idSite.select("td").first()?.text()!!.toInt() + 1

                editor.putInt("Id", id)
                editor.apply()
            }
        }
        editor.apply()

        val error = getSharedPreferences("error", Context.MODE_PRIVATE).getString("appError", "")

        if (error != "") {
            if (language == "RU")
                AlertDialog.Builder(this).setTitle("В прошлой сессии произошла ошибка")
                    .setMessage("Отправить отчёт об ошибке разработчикам?").setCancelable(false)
                    .setPositiveButton("Да", DialogInterface.OnClickListener { dialog, which ->
                        thread {
                            Jsoup.connect("https://earmem.herokuapp.com/")
                                .data("to", "errors")
                                .data("error", error!!)
                                .get()
                        }
                    }).setNegativeButton("Нет", null).show()
            else if (language == "US")
                AlertDialog.Builder(this).setTitle("An error occurred in the last session")
                    .setMessage("Send a bug report to developers?").setCancelable(false)
                    .setPositiveButton("Yes", DialogInterface.OnClickListener { dialog, which ->
                        thread {
                            Jsoup.connect("https://earmem.herokuapp.com/")
                                .data("to", "errors")
                                .data("error", error!!)
                                .get()
                        }
                    }).setNegativeButton("No", null).show()

            getSharedPreferences("error", Context.MODE_PRIVATE).edit().putString("appError", "")
                .apply()
        }

        Thread.setDefaultUncaughtExceptionHandler { paramThread, paramThrowable ->

            val errorReport = SpannableStringBuilder()
            errorReport.append("************ CAUSE OF ERROR ************\n\n")
            errorReport.append(paramThrowable.toString())
            errorReport.append("\n************ DEVICE INFORMATION ***********\n")
            errorReport.append("Brand: ")
            errorReport.append(Build.BRAND)
            errorReport.append("\n")
            errorReport.append("Device: ")
            errorReport.append(Build.DEVICE)
            errorReport.append("\n")
            errorReport.append("Model: ")
            errorReport.append(Build.MODEL)
            errorReport.append("\n")
            errorReport.append("Id: ")
            errorReport.append(Build.ID)
            errorReport.append("\n")
            errorReport.append("Product: ")
            errorReport.append(Build.PRODUCT)
            errorReport.append("\n")
            errorReport.append("\n************ FIRMWARE ************\n")
            errorReport.append("SDK: ")
            errorReport.append(Build.VERSION.SDK_INT.toString())
            errorReport.append("\n")
            errorReport.append("Release: ")
            errorReport.append(Build.VERSION.RELEASE)
            errorReport.append("\n")
            errorReport.append("Incremental: ")
            errorReport.append(Build.VERSION.INCREMENTAL)
            errorReport.append("\n")

            getSharedPreferences("error", Context.MODE_PRIVATE).edit().putString("appError", errorReport.toString()).apply()

            thread {
                Thread.sleep(100)
                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                this.startActivity(intent)
                if (this is Activity) {
                    (this as Activity).finish()
                }
                Runtime.getRuntime().exit(0)
            }
        }

        getSharedPreferences("timer", Context.MODE_PRIVATE).edit().putInt("appOn", System.currentTimeMillis().toInt()).apply()
        getSharedPreferences("timer", Context.MODE_PRIVATE).edit().putInt("hearedFull", -5000).apply()
        getSharedPreferences("timer", Context.MODE_PRIVATE).edit().putInt("whatItFull", -5000).apply()

        val vhod = getSharedPreferences("fir", Context.MODE_PRIVATE).getInt("vhod",0)
        if (vhod == 0){
            getSharedPreferences("fir", Context.MODE_PRIVATE).edit().putInt("vhod", 1).apply()
            getSharedPreferences("val", Context.MODE_PRIVATE).edit().putInt("podpiska", 0).apply()
        }

        val navig = findViewById<BottomNavigationView>(R.id.navigation_view)

        navig.itemIconSize = 150
        navig.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    back(findViewById<BottomNavigationView>(R.id.navigation_view))

                    findViewById<FragmentContainerView>(R.id.ratingContainer).visibility = View.GONE
                    findViewById<FragmentContainerView>(R.id.mainFragment).visibility = View.VISIBLE
                    val set = item.icon
                    set.mutate().setColorFilter(resources.getColor(R.color.blue), PorterDuff.Mode.SRC_IN)
                    item.icon = set
                    supportFragmentManager.beginTransaction().replace(R.id.mainFragment, StartFragment.newInsance()).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_dashboard -> {
                    if (language == "US")
                        return@setOnItemSelectedListener false
                    else if (language == "RU") {
                        back(findViewById<BottomNavigationView>(R.id.navigation_view))

                        findViewById<FragmentContainerView>(R.id.ratingContainer).visibility =
                            View.VISIBLE
                        findViewById<FragmentContainerView>(R.id.mainFragment).visibility =
                            View.GONE
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.ratingContainer, ChatFragment.newInsance()).commit()

                        return@setOnItemSelectedListener true
                    }
                }
                R.id.navigation_notifications -> {
                    if (language == "RU")
                        Toast.makeText(this, "Будет добавлено позже", Toast.LENGTH_SHORT).show()
                    else if (language == "US")
                        Toast.makeText(this, "Will be added later", Toast.LENGTH_SHORT).show()
                }
                R.id.navigation_stat -> {
                    back(findViewById<BottomNavigationView>(R.id.navigation_view))

                    findViewById<FragmentContainerView>(R.id.ratingContainer).visibility = View.VISIBLE
                    val set = item.icon
                    set.mutate().setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_IN)
                    item.icon = set
                    supportFragmentManager.beginTransaction().replace(R.id.ratingContainer, RatingFragment.newInsance()).commit()
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_more -> {
                    findViewById<FragmentContainerView>(R.id.ratingContainer).visibility = View.GONE
                    findViewById<FragmentContainerView>(R.id.mainFragment).visibility = View.VISIBLE
                    back(findViewById(R.id.mainFragment))
                    supportFragmentManager.beginTransaction().replace(R.id.mainFragment, SettingsFragment.newInsance()).commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        supportFragmentManager.beginTransaction().replace(R.id.mainFragment, StartFragment.newInsance()).commit()
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
        val language = getSharedPreferences("app", Context.MODE_PRIVATE).getString("language", "RU")

        if (language == "RU")
            when (num) {
                1 -> {
                    checker =
                        "53 года назад. Услыхав свое имя, помесь такса с дворняжкой вышла из-под верстака, где она спала на стружках, сладко потянулась и побежала за хозяином." +
                                " Заказчики Луки Александрыча жили ужасно далеко, так что, прежде чем дойти до каждого из них, столяр должен был по нескольку раз заходить" +
                                " в трактир и подкрепляться. Каштанка помнила, что по дороге она вела себя крайне неприлично"
                    m4 =
                        "53 года назад, Помесь такса с дворняжкой, Помесь таксы с дворняжкой, Каштанка, Коштанка, Трактир, Столяр, Она спала, Стружки, Стружках, Заказчики Луки".uppercase()
                    m3 =
                        "53 года назад, Помесь такса, Помесь таксы, Заказчики Луки, Каштанка, Коштанка, Трактир, Столяр, Спала".uppercase()
                    m2 =
                        "53 года назад, заказчики Луки, Каштанка, Коштанка, столяр, спала".uppercase()
                    m1 = "53 года, Луки, Каштанка, Коштанка".uppercase()
                }
                2 -> {
                    checker =
                        "Тема свободного человека – главная тема всего произведения, но в легенде о Данко она рассматривается в неожиданном ракурсе," +
                                " Для писателя понятие \"свобода\" связано с понятием \"правда\" и \"подвиг\", Горького интересует не \"свобода\" \"от чего-либо\"," +
                                " а свобода \"во имя\". Горький использует жанр литературной легенды, потому что он, как нельзя лучше, подходил для его замысла:" +
                                " кратко, взволновано, ярко воспеть все лучшее, что может быть в человеке. Более всего писатель негодовал против эгоизма, корыстолюбия," +
                                " самолюбования и гордыни"
                    m4 =
                        "свободного человека, главная тема, произведения, Данко, неожиданном ракурсе, \"свобода\", \"правда\" и \"подвиг\", свобода \"во имя\"," +
                                " Горький, жанр литературной легенды, кратко, взволновано, ярко воспеть все лучшее".uppercase()
                    m3 =
                        "воспеть все лучшее, воспеть всё лучшее, негодовал против эгоизма".uppercase()
                    m2 = "тема всего произведения, негодовал, против эгоизма".uppercase()
                    m1 = "свободного, человека, против, эгоизма".uppercase()
                }
                3 -> {
                    checker =
                        "Как раз в то время, когда почтальон с письмом поднимался по лестнице, у Чука с Геком был бой. Короче говоря," +
                                " они просто выли и дрались. Из-за чего началась эта драка, я уже позабыл. Но помнится мне, что или Чук стащил у" +
                                " Гека пустую спичечную коробку, или, наоборот, Гек стянул у Чука жестянку из-под ваксы. \n" +
                                "Только что оба эти брата, стукнув по разу друг друга кулаками, собирались стукнуть по второму," +
                                " как загремел звонок, и они с тревогой переглянулись. Они подумали, что пришла их мама! А у этой мамы был странный характер." +
                                " Она не ругалась за драку, не кричала, а просто разводила драчунов по разным комнатам и целый час, а то и два не позволяла им играть вместе." +
                                " А в одном часе – тик да так – целых шестьдесят минут. А в двух часах и того больше"
                    m4 =
                        "почтальон с письмом, поднимался, лестнице, у Чука с Геком был бой, выли и дрались, Гек стянул у Чука жестянку из-под, стукнуть второй," +
                                " с тревогой переглянулись, мама, странный характер, не ругалась, разводила драчунов по разным комнатам, играть вместе, целых шестьдесят минут".uppercase()
                    m3 =
                        "дрались, стащил спичечную коробку или стянул жестяную банку, второй, переглянулись, не ругалась".uppercase()
                    m2 = "Почтальон, письмом, лестнице, бой, разводила драчунов".uppercase()
                    m1 = "спичечную коробку, стукнуть, характер".uppercase()
                }
                4 -> {
                    checker =
                        "Во время моих посещений нью-йоркского рынка — мой отец и я в это время имели целый ряд" +
                                " небольших магазинов в Виргинии. Капитан Картер владел маленьким, но красивым коттеджем, расположенным на возвышенности," +
                                " с хорошим видом на реку. Во время одного из моих посещений я заметил, что он был очень занят писанием.\n" +
                                "Тогда же он сказал мне, что в случае какого-нибудь с ним несчастья, он хотел бы, чтоб я распорядился его имуществом;" +
                                " он дал мне ключ от шкафа в его кабинете, где я найду завещание и некоторые указания, которыми он просил меня выполнить с точностью."
                    m4 =
                        "нью-йоркского рынка, мой отец и я, магазинов, капитан, маленьким, красивым коттеджем, занят писанием, несчастья, распорядился, имуществом, ключ от шкафа, " +
                                "завещание, указания, выполнить с точностью".uppercase()
                    m3 = "".uppercase()
                    m2 = "рынка, коттеджем".uppercase()
                    m1 = "".uppercase()
                }
                5 -> {
                    checker =
                        "Настасья сиротой росла, не привыкла к такому богатству, да и не шибко любительница была моду выводить," +
                                " С первых годов, как жили со Степаном, надевывала, конечно, из этой шкатулки, Только не к душе ей пришлось," +
                                " Наденет кольцо, Ровно как раз впору, Но закованный палец-то, в конце нали посинеет, Серьги навесит - хуже того," +
                                " Уши так оттянет, что мочки распухнут, А на руки взять - не тяжелее тех, какие Настасья всегда носила"
                    m4 =
                        ("сиротой, росла, не привыкла, богатству, моду, Степаном, шкатулки, не к душе, кольцо, впору, закованный, серьги, уши, мочки, распухнут, " +
                                "не тяжелее, Настасья, носила").uppercase()
                    m3 = "".uppercase()
                    m2 = "".uppercase()
                    m1 = "".uppercase()
                }
            }
        if (language == "US")
            when (num) {
                1 -> {
                    checker =
                        "53 years ago. Hearing her name, a cross between a dachshund and a mongrel came out from under the workbench where" +
                                " she was sleeping on shavings, stretched sweetly and ran after the owner. Luka Alexandrycha's customers lived" +
                                " terribly far away, so before reaching each of them, the joiner had to go into the tavern several times and" +
                                " refresh himself. Kashtanka remembered that on the way she behaved extremely indecently."
                    m4 =
                        "53 years ago, a cross between a dachshund and a mongrel, Kashtanka, tavern, joiner, she was sleeping, shavings, Luka Alexandrycha's customers".uppercase()
                    m3 =
                        "53 years ago, a cross between a dachshund, Luka Alexandrycha's customers, Kashtanka, tavern, joiner, sleeping".uppercase()
                    m2 =
                        "53 years ago, Luka Alexandrycha's customers, Kashtanka, joiner, sleeping".uppercase()
                    m1 = "53 years, Luka, Kashtanka".uppercase()
                }
                2 -> {
                    checker =
                        "The theme of a free man is the main theme of the whole work, but in the legend of Danko it is viewed from an unexpected" +
                                " angle. For the writer, the concept of \"freedom\" is connected with the concept of \"truth\" and \"feat\"." +
                                " Gorky is not interested in \"freedom\" \"from anything\", but freedom \"in the name of\". Gorky uses the genre" +
                                " of literary legend, because it could not be better suited to his idea: briefly, excitedly, vividly sing all the" +
                                " best that can be in a person. Most of all, the writer was indignant against selfishness, greed, self-love and pride."
                    m4 =
                        "free man, the main theme of the whole work, Danko, unexpected angle, \"freedom\" is connected with the concept of \"truth\" and \"feat\"," +
                                " freedom \"in the name of\", Gorky, genre of literary legend, briefly, vividly sing all the best, indignant against selfishness".uppercase()
                    m3 = "sing all the best, indignant against selfishness".uppercase()
                    m2 = "main theme of the whole work, indignant, against selfishness".uppercase()
                    m1 = "free, man, against, selfishness".uppercase()
                }
                3 -> {
                    checker =
                        "Just at the time when the postman was coming up the stairs with the letter, Chuck and Huck had a fight. In short, they were just howling and fighting." +
                                " I've already forgotten what this fight started about. But I remember that either Chuck stole an empty matchbox from Huck, or, conversely," +
                                " Huck stole a tin can from Chuck. Just now, both of these brothers, having hit each other once with their fists, were about to hit the second" +
                                " one, when the bell rang, and they looked at each other anxiously. They thought their mom had come! And this mom had a strange character." +
                                " She did not swear at the fight, did not shout, but simply bred the brawlers in different rooms and did not allow them to play together " +
                                "for an hour or even two. And in one hour – tick and tock – as much as sixty minutes. And two hours and even more"
                    m4 =
                        "with the letter, coming up, the stairs, Chuck and Huck had a fight, howling and fighting, Huck stole a tin can from Chuck, hit the second," +
                                " looked at each other anxiously, mom, strange character, did not shout, bred the brawlers in different rooms, play together, as much as sixty minutes".uppercase()
                    m3 =
                        "fighting, stole an empty matchbox from Huck or conversely, second, looked at each other, did not shout".uppercase()
                    m2 = "postman, the letter, a fight, bred the brawlers".uppercase()
                    m1 = "matchbox, hit, character".uppercase()
                }
                4 -> {
                    checker =
                        "During my visits to the New York market - my father and I had a number of small shops in Virginia. Captain Carter owned a small but beautiful cottage," +
                                " located on a hill, with a good view of the river. During one of my visits, I noticed that he was very busy writing. At the same time he told me that" +
                                " in case of any misfortune with him, he would like me to dispose of his property; he gave me the key to the closet in his office, where I would find" +
                                " the will and some instructions that he asked me to follow with accuracy."
                    m4 =
                        "New York market, my father and I, shops, Captain, small, beautiful cottage, was very busy writing, misfortune, to dispose, property, key to the closet, " +
                                "the will, some instructions, follow with accuracy".uppercase()
                    m3 = "".uppercase()
                    m2 = "market, cottage".uppercase()
                    m1 = "".uppercase()
                }
                5 -> {
                    checker =
                        "Listing the things I transported from the ship, as already mentioned, in eleven receptions, I did not mention many little things. For example," +
                                " in the cabins of the captain and his assistant, I found ink, did not mention many little, three or four compasses, some astronomical instruments," +
                                " telescopes, geographical maps and a ship's log. I put all this in one of the trunks just in case, not even knowing if I would need any" +
                                " of these things. Then I came across several books in Portuguese. I picked them up, too."
                    m4 =
                        ("the things I transported, as already mentioned, I did not mention many little things, in the cabins of the captain and his assistant," +
                                " I found ink, pens and paper, three or four compasses, I put all this in one of the trunks, I came across several books in Portuguese").uppercase()
                    m3 = "".uppercase()
                    m2 =
                        "Listing the things, already mentioned, did not mention many little, did not mention many little, one of the trunks".uppercase()
                    m1 = "".uppercase()
                }
            }

        val f4 = m4.replace(Regex("""[,]"""), "").split(" ")
        val f3 = m3.replace(Regex("""[,]"""), "").split(" ")
        val f2 = m2.replace(Regex("""[,]"""), "").split(" ")
        val f1 = m1.replace(Regex("""[,]"""), "").split(" ")

        var n = similar(text, num!!)

        val spannableStringBuilder = SpannableStringBuilder()
        var acceptedWrods = 0

        for (i in text.uppercase().toWords()){
            if (f4.indexOf(i) > -1 && checker.uppercase().toWords().indexOf(i) > -1){
                acceptedWrods++
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GREEN), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE // choose your flag
                )
            } else if (f3.indexOf(i) > -1 && checker.uppercase().toWords().indexOf(i) > -1){
                acceptedWrods++
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GREEN), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE // choose your flag
                )
            } else if (f2.indexOf(i) > -1 && checker.uppercase().toWords().indexOf(i) > -1){
                acceptedWrods++
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GREEN), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE // choose your flag
                )
            } else if (f1.indexOf(i) > -1 && checker.uppercase().toWords().indexOf(i) > -1){
                acceptedWrods++
                spannableStringBuilder.append("$i ")
                spannableStringBuilder.setSpan(
                    ForegroundColorSpan(Color.GREEN), // color
                    spannableStringBuilder.length - i.length - 1, // start color from here
                    spannableStringBuilder.length, // end here
                    Spannable.SPAN_INCLUSIVE_EXCLUSIVE // choose your flag
                )
            } else if (checker.uppercase().toWords().indexOf(i) > -1) {
                acceptedWrods++
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

        val editor = sharPref.edit()
        if (sharPref.getInt("Write$num", 0) != 1000) {
            editor.putInt("Write$num", 1000)
            val percentage =
                (acceptedWrods / checker.uppercase().toWords().toTypedArray().size) * 100

            thread {
                Jsoup.connect("https://earmem.herokuapp.com/")
                    .data("to", "percentage")
                    .data("id", sharPref.getInt("Id", 0).toString())
                    .data("level", "write$num")
                    .data("percent", "$percentage").get()
            }
        }

        findViewById<EditText>(R.id.editTextHeared).text = spannableStringBuilder

        if (n > 0) {
            if (language == "RU")
                Toast.makeText(cont, "Ты сделал на $n баллов", Toast.LENGTH_SHORT).show()
            else if (language == "US")
                Toast.makeText(cont, "You did $n points", Toast.LENGTH_SHORT).show()
        } else {
            if (language == "RU")
                Toast.makeText(cont, "Ты сделал на 0 баллов", Toast.LENGTH_SHORT).show()
            else if (language == "US")
                Toast.makeText(cont, "You did 0 points", Toast.LENGTH_SHORT).show()
        }

        if (n > 10) {
            if (sharPref.getInt("maxHearedLevel",1) == num) {
                editor?.putInt("maxHearedLevel", num + 1)
            }
            editor?.putInt("Rating", sharPref.getInt("Rating", 0) + n)
            editor?.apply()
        }

        findViewById<Button>(R.id.hearedButton).text = "To home page"
        findViewById<Button>(R.id.hearedButton).setOnClickListener {
            back(it)
        }
    }

    // запускает нужное аудио
    fun Play(view: View) {
        val sharPref = getSharedPreferences("level", Context.MODE_PRIVATE)
        val num = sharPref.getInt("levelNum", 1)
        val b = findViewById<ImageButton>(R.id.imageButtonHeared)
        val language = getSharedPreferences("app", Context.MODE_PRIVATE).getString("language", "RU")

        if (mMediaPlayer?.isPlaying == true) return mMediaPlayer?.pause()!!

        if (language == "RU")
            when (num) {
                1 -> {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(this, R.raw.uroven1_chisto_ru)
                        mMediaPlayer!!.start()
                    }
                }
                2 -> {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(this, R.raw.uroven2_chisto_ru)
                        mMediaPlayer!!.start()
                    }
                }
                3 -> {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(this, R.raw.uroven3_chisto_ru)
                        mMediaPlayer!!.start()
                    }
                }
                4 -> {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(this, R.raw.uroven4_chisto_ru)
                        mMediaPlayer!!.start()
                    }
                }
                5 -> {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(this, R.raw.uroven5_chisto_ru)
                        mMediaPlayer!!.start()
                    }
                }
            }
        if (language == "US")
            when (num) {
                1 -> {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(this, R.raw.uroven1_chisto_us)
                        mMediaPlayer!!.start()
                    }
                }
                2 -> {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(this, R.raw.uroven2_chisto_us)
                        mMediaPlayer!!.start()
                    }
                }
                3 -> {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(this, R.raw.uroven3_chisto_us)
                        mMediaPlayer!!.start()
                    }
                }
                4 -> {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(this, R.raw.uroven4_chisto_us)
                        mMediaPlayer!!.start()
                    }
                }
                5 -> {
                    if (mMediaPlayer == null) {
                        mMediaPlayer = MediaPlayer.create(this, R.raw.uroven5_chisto_us)
                        mMediaPlayer!!.start()
                    }
                }
            }
        b.visibility = View.INVISIBLE
        thread {
            try {
                while (mMediaPlayer?.isPlaying == true) {
                    runOnUiThread {
                        try {
                            findViewById<EditText>(R.id.editTextHeared).isEnabled = false
                        } catch (e: NullPointerException) {
                            return@runOnUiThread
                        }
                    }
                }
            } catch (e: IllegalStateException){
                return@thread
            }
            runOnUiThread {
                findViewById<EditText>(R.id.editTextHeared).isEnabled = true
                mMediaPlayer?.release()!!
            }
        }
    }

    // отклик программы на нажание перехода к уровню
    fun playRepeat(view: View){
        val numb = resources.getResourceName(view.id)
        val num = numb.substring(numb.lastIndexOf('/') + 1).replace("imageButtonHearedLevel", "").toInt()
        val podpiska =
            view.context.getSharedPreferences("val", Context.MODE_PRIVATE).getInt("podpiska", 0)
        val language = getSharedPreferences("app", Context.MODE_PRIVATE).getString("language", "RU")

        val sharPref = getSharedPreferences("level", Context.MODE_PRIVATE)
        val editor = sharPref.edit()
        editor.putInt("levelNum", num)
        val max = sharPref.getInt("maxHearedLevel",1)

        if (num in 1..max) {
            if ((num == 1 && podpiska == 0) || podpiska == 1)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.mainFragment, HearedFragment.newInsance()).commit()
            else if (language == "RU")
                Toast.makeText(this, "Недоступно для вашего аккаунта", Toast.LENGTH_SHORT).show()
            else if (language == "US")
                Toast.makeText(this, "Unavailable for your account", Toast.LENGTH_SHORT).show()
        } else if (language == "RU")
            Toast.makeText(this, "Недоступно для вашего аккаунта", Toast.LENGTH_SHORT).show()
        else if (language == "US")
            Toast.makeText(this, "Unavailable for your account", Toast.LENGTH_SHORT).show()
        editor.apply()
    }

    // возвращение к главному экрану
    fun back(view: View?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainFragment, StartFragment.newInsance()).commit()
        try {
            if (mMediaPlayer?.isPlaying == true) {
                mMediaPlayer?.release()!!
                mMediaPlayer = null
            }
        } catch (e: java.lang.IllegalStateException) {
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
        val podpiska = getSharedPreferences("val", Context.MODE_PRIVATE).getInt("podpiska", 0)
        val language = getSharedPreferences("app", Context.MODE_PRIVATE).getString("language", "RU")

        when(level.toString()){
            "1" -> {
                if(max>0) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                }
            }
            "2" -> {
                if(max>1) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                }
            }
            "3" -> {
                if(max>2) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                }
            }
            "4" -> {
                if(max>3 && podpiska == 1) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                } else {
                    if (language == "RU")
                        Toast.makeText(this, "Недоступно для вашего аккаунта", Toast.LENGTH_SHORT)
                            .show()
                    else if (language == "US")
                        Toast.makeText(this, "Unavailable for your account", Toast.LENGTH_SHORT)
                            .show()
                }
            }
            "5" -> {
                if(max>4 && podpiska == 1) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                } else {
                    if (language == "RU")
                        Toast.makeText(this, "Недоступно для вашего аккаунта", Toast.LENGTH_SHORT)
                            .show()
                    else if (language == "US")
                        Toast.makeText(this, "Unavailable for your account", Toast.LENGTH_SHORT)
                            .show()
                }
            }
            "6" -> {
                if(max>5 && podpiska == 1) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                } else {
                    if (language == "RU")
                        Toast.makeText(this, "Недоступно для вашего аккаунта", Toast.LENGTH_SHORT)
                            .show()
                    else if (language == "US")
                        Toast.makeText(this, "Unavailable for your account", Toast.LENGTH_SHORT)
                            .show()
                }
            }
            "7" -> {
                if(max>6 && podpiska == 1) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                } else {
                    if (language == "RU")
                        Toast.makeText(this, "Недоступно для вашего аккаунта", Toast.LENGTH_SHORT)
                            .show()
                    else if (language == "US")
                        Toast.makeText(this, "Unavailable for your account", Toast.LENGTH_SHORT)
                            .show()
                }
            }
            "8" -> {
                if(max>7 && podpiska == 1) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                } else {
                    if (language == "RU")
                        Toast.makeText(this, "Недоступно для вашего аккаунта", Toast.LENGTH_SHORT)
                            .show()
                    else if (language == "US")
                        Toast.makeText(this, "Unavailable for your account", Toast.LENGTH_SHORT)
                            .show()
                }
            }
            "9" -> {
                if(max>8 && podpiska == 1) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                } else {
                    if (language == "RU")
                        Toast.makeText(this, "Недоступно для вашего аккаунта", Toast.LENGTH_SHORT)
                            .show()
                    else if (language == "US")
                        Toast.makeText(this, "Unavailable for your account", Toast.LENGTH_SHORT)
                            .show()
                }
            }
            "10" -> {
                if(max>9 && podpiska == 1) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.mainFragment, WhatItFragment.newInsance()).commit()
                } else {
                    if (language == "RU")
                        Toast.makeText(this, "Недоступно для вашего аккаунта", Toast.LENGTH_SHORT)
                            .show()
                    else if (language == "US")
                        Toast.makeText(this, "Unavailable for your account", Toast.LENGTH_SHORT)
                            .show()
                }
            }
        }
        if(level>max) {
            if (language == "RU")
                Toast.makeText(this, "Недоступно для вашего аккаунта", Toast.LENGTH_SHORT).show()
            else if (language == "US")
                Toast.makeText(this, "Unavailable for your account", Toast.LENGTH_SHORT).show()
        }
        sharPref.putInt("whatItLevel", level)
        sharPref.apply()
    }

    // на всём, чего нет))
    fun GoodBye(view: View) {
        val language = getSharedPreferences("app", Context.MODE_PRIVATE).getString("language", "RU")
        if (language == "RU")
            Toast.makeText(this, "Будет добавлено позже", Toast.LENGTH_SHORT).show()
        else if (language == "US")
            Toast.makeText(this, "Will be added later", Toast.LENGTH_SHORT).show()
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

    fun sendData(){
        val android_id = Settings.Secure.getString(contentResolver, "android_id")
        val android_name = Settings.Global.getString(contentResolver, "device_name")
        val time = System.currentTimeMillis().toInt()
        val on = getSharedPreferences("timer", MODE_PRIVATE).getInt("appOn", time)
        val what = getSharedPreferences("timer", MODE_PRIVATE).getInt("whatItFull", -5000)
        val heared = getSharedPreferences("timer", MODE_PRIVATE).getInt("hearedFull", -5000)
        val rating = getSharedPreferences("level", Context.MODE_PRIVATE).getInt("Rating", 0).toString()

        thread {
            if (rating != "0") {
                Jsoup.connect("https://earmem.herokuapp.com/")
                    .data("to", "info")
                    .data("id", "$android_id//$android_name")
                    .data("all", ((time - on)/1000).toString())
                    .data("what", (what/1000).toString())
                    .data("write", (heared/1000).toString())
                    .data("num", rating)
                    .get()
            } else {
                Jsoup.connect("https://earmem.herokuapp.com/")
                    .data("to", "info")
                    .data("id", "$android_id//$android_name")
                    .data("all", ((time - on) / 1000).toString())
                    .data("what", (what / 1000).toString())
                    .data("write", (heared / 1000).toString())
                    .data("num", "-5")
                    .get()
            }
        }

        var writes = 0
        var i = 1
        while (i < 6) {
            if (getSharedPreferences("level", Context.MODE_PRIVATE).getInt(
                    "Write$i",
                    1000
                ) != 1000
            ) {
                writes += getSharedPreferences("level", Context.MODE_PRIVATE).getInt("Write$i", 0)
                i++
            } else {
                break
            }
        }
        val wI = i
        var heareds = 0
        i = 1
        while (i < 10) {
            if (getSharedPreferences("level", Context.MODE_PRIVATE).getInt(
                    "whatit$i",
                    1000
                ) != 1000
            ) {
                heareds += getSharedPreferences("level", Context.MODE_PRIVATE).getInt("whatit$i", 0)
                i++
            } else {
                break
            }
        }
        thread {
            if (writes == 0) writes = -5
            if (heareds == 0) heareds = -5

            Jsoup.connect("https://earmem.herokuapp.com/")
                .data("to", "setAllLevels")
                .data(
                    "id",
                    getSharedPreferences("level", Context.MODE_PRIVATE).getInt("Id", 0).toString()
                )
                .data("write", (writes / wI).toString())
                .data("hear", (heareds / i).toString())
                .get()
        }
    }

    fun forUpdate(){
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val language = getSharedPreferences("app", Context.MODE_PRIVATE).getString("language", "RU")
        if (cm.activeNetworkInfo!!.isConnected) {
            thread {
                val ver =
                    Jsoup.connect("https://earmem.herokuapp.com/").get().select("p").text().trim()
                if (ver != BuildConfig.VERSION_NAME) {
                    runOnUiThread {
                        if (language == "RU")
                            AlertDialog.Builder(this).setCancelable(false)
                                .setMessage("Обновите приложение!!!")
                                .setPositiveButton(
                                    "Перейти к скачиванию",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        val browserIntent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("http://earmem.ru/#app")
                                        )
                                        startActivity(browserIntent)
                                    }).show()
                        else if (language == "US")
                            AlertDialog.Builder(this).setCancelable(false)
                                .setMessage("Update the app!!!")
                                .setPositiveButton(
                                    "Go to download",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        val browserIntent = Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse("http://earmem.ru/#app")
                                        )
                                        startActivity(browserIntent)
                                    }).show()
                    }
                }
            }
        }
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
        if ("P" in testerCode){
            getSharedPreferences("val", Context.MODE_PRIVATE).edit().putInt("podpiska", testerCode.replace("P", "").trim().toInt()).apply()
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

    //про версию купить
    fun proVer(view: View) {
        val v = this.layoutInflater.inflate(R.layout.pro_code, null)
        val language = getSharedPreferences("app", Context.MODE_PRIVATE).getString("language", "RU")

        if (language == "RU")
            AlertDialog.Builder(this).setMessage("Хотите купить подписку?")
                .setPositiveButton(
                    "Да (сайт оплаты)",
                    DialogInterface.OnClickListener { dialog, which ->
                        val browserIntent =
                            Intent(Intent.ACTION_VIEW, Uri.parse("http://earmem.ru/#podpiska"))
                        startActivity(browserIntent)
                    })
                .setNegativeButton(
                    "У меня уже есть код",
                    DialogInterface.OnClickListener { dialog, which ->
                        AlertDialog.Builder(this).setView(v)
                            .setPositiveButton("Активировать",
                                DialogInterface.OnClickListener { dialog, id ->
                                    val code =
                                        v.findViewById<EditText>(R.id.codeEdit).text.toString()
                                    val codes: Array<String> = arrayOf(
                                        "O4pK06gAVZ0s",
                                        "zK32jt9CIjeb",
                                        "0xCg18PbUQl9",
                                        "53Ed1zv97cZS",
                                        "Z9ak670XRtrp",
                                        "qK09LMv4q1Ep",
                                        "U48HCrT1Abn2",
                                        "a8R00905Naii",
                                        "mL6043m0rkGs",
                                        "J90t76F8D98k",
                                        "7YT09gVopXlo",
                                        "40F7sX3vqrry",
                                        "zJ484qMdk3Zc",
                                        "RL64hHw26s2I",
                                        "8Es5IOZTZ0bH",
                                        "In3t34gG2HhE",
                                        "Tl9BD938HQQ7",
                                        "6nSf38Ne4aJZ",
                                        "XLn21jJ0C09i",
                                        "Bs436Ta3NXnm"
                                    )
                                    if (code in codes) {
                                        AlertDialog.Builder(this)
                                            .setMessage("Вы успешно активировали подписку")
                                            .setPositiveButton("Ок", null).show()
                                        getSharedPreferences("val", Context.MODE_PRIVATE).edit()
                                            .putInt("podpiska", 1).apply()
                                    }
                                })
                            .setNegativeButton("Отмена",
                                DialogInterface.OnClickListener { dialog, id ->
                                    dialog.cancel()
                                }).show()
                    })
                .setNeutralButton("Отмена", DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                })
                .show()
        else if (language == "US")
            AlertDialog.Builder(this).setMessage("Want to buy a subscription?")
                .setPositiveButton(
                    "Yes (go to pay)",
                    DialogInterface.OnClickListener { dialog, which ->
                        val browserIntent =
                            Intent(Intent.ACTION_VIEW, Uri.parse("http://earmem.ru/#podpiska"))
                        startActivity(browserIntent)
                    })
                .setNegativeButton(
                    "I already have the code",
                    DialogInterface.OnClickListener { dialog, which ->
                        AlertDialog.Builder(this).setView(v)
                            .setPositiveButton("Activate",
                                DialogInterface.OnClickListener { dialog, id ->
                                    val code =
                                        v.findViewById<EditText>(R.id.codeEdit).text.toString()
                                    val codes: Array<String> = arrayOf(
                                        "O4pK06gAVZ0s",
                                        "zK32jt9CIjeb",
                                        "0xCg18PbUQl9",
                                        "53Ed1zv97cZS",
                                        "Z9ak670XRtrp",
                                        "qK09LMv4q1Ep",
                                        "U48HCrT1Abn2",
                                        "a8R00905Naii",
                                        "mL6043m0rkGs",
                                        "J90t76F8D98k",
                                        "7YT09gVopXlo",
                                        "40F7sX3vqrry",
                                        "zJ484qMdk3Zc",
                                        "RL64hHw26s2I",
                                        "8Es5IOZTZ0bH",
                                        "In3t34gG2HhE",
                                        "Tl9BD938HQQ7",
                                        "6nSf38Ne4aJZ",
                                        "XLn21jJ0C09i",
                                        "Bs436Ta3NXnm"
                                    )
                                    if (code in codes) {
                                        AlertDialog.Builder(this)
                                            .setMessage("You have successfully activated your subscription")
                                            .setPositiveButton("Ок", null).show()
                                        getSharedPreferences("val", Context.MODE_PRIVATE).edit()
                                            .putInt("podpiska", 1).apply()
                                    }
                                })
                            .setNegativeButton("Cancel",
                                DialogInterface.OnClickListener { dialog, id ->
                                    dialog.cancel()
                                }).show()
                    })
                .setNeutralButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                })
                .show()
    }

    // При сворачивании выключаем ненужное *надо знать life cycle
    override fun onStop() {
        if (mMediaPlayer != null) {
            mMediaPlayer!!.release()
            mMediaPlayer = null
        }
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm.activeNetworkInfo!!.isConnected) {
            sendData()
        }
        super.onStop()
    }

    override fun onDestroy() {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (cm.activeNetworkInfo!!.isConnected) {
            sendData()
        }
        super.onDestroy()
    }

    // при нажатии "назад" ничего не делаем
    override fun onBackPressed() {
        back(findViewById(R.id.mainFragment))
    }
}