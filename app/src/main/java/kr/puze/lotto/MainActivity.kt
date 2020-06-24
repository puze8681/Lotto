package kr.puze.lotto

import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var BUTTON_STATE = 0
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MobileAds.initialize(this) {
            setAdBanner()
            setAdFull()
        }

        button.setOnClickListener {
            when (BUTTON_STATE) {
                0 -> {
                    startSound(-1)
                    BUTTON_STATE = 1
                    button.text = "번호 받는 중"
                    Handler().postDelayed({
                        getNumber()
                    }, 20000)
                }
                2 -> getNumberAgain()
            }
        }
    }

    private fun setAdBanner() {
        var test = "ca-app-pub-3940256099942544/6300978111"
        var adBannerId = "ca-app-pub-2463787284908256/8456892839"
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // 광고 로딩
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // 광고 실패
            }

            override fun onAdOpened() {
                // 광고 열림
            }

            override fun onAdClicked() {
                // 광고 클릭
            }

            override fun onAdLeftApplication() {
                // 다른 앱 호출
            }

            override fun onAdClosed() {
                // 광고 종료
            }
        }
        adView.loadAd(AdRequest.Builder().build())
    }

    private fun setAdFull() {
        mInterstitialAd = InterstitialAd(this)
        var test = "ca-app-pub-3940256099942544/1033173712"
        var adFullId = "ca-app-pub-2463787284908256/6616912140"
        mInterstitialAd.adUnitId = adFullId
        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                // 광고 로딩
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                // 광고 실패
            }

            override fun onAdOpened() {
                // 광고 열림
            }

            override fun onAdClicked() {
                // 광고 클릭
            }

            override fun onAdLeftApplication() {
                // 다른 앱 호출
            }

            override fun onAdClosed() {
                // 광고 종료
                mInterstitialAd.loadAd(AdRequest.Builder().build())
                BUTTON_STATE = 1
                button.text = "번호 받는 중"
                getNumber()
            }
        }
        mInterstitialAd.loadAd(AdRequest.Builder().build())
    }

    private fun getRandom(){

    }

    private fun getNumber() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        var num1: Int
        var num2: Int
        var num3: Int
        var num4: Int
        var num5: Int
        var num6: Int
        num1 = Random().nextInt(44) + 1
        do {
            num2 = Random().nextInt(44) + 1
        }while (num2 == num1)
        do {
            num3 = Random().nextInt(44) + 1
        }while (num3 == num1 || num3 == num2)
        do {
            num4 = Random().nextInt(44) + 1
        }while (num4 == num1 || num4 == num2 || num4 == num3)
        do {
            num5 = Random().nextInt(44) + 1
        }while (num5 == num1 || num5 == num2 || num5 == num3 || num5 == num4)
        do {
            num6 = Random().nextInt(44) + 1
        }while (num6 == num1 || num6 == num2 || num6 == num3 || num6 == num4 || num6 == num5)
        row1.visibility = View.VISIBLE
        number1.visibility = View.VISIBLE
        number1.background = setColor(num1)
        number1.text = num1.toString()
        startSound(num1)
        number1.startAnimation(animation)
        Handler().postDelayed({
            number2.visibility = View.VISIBLE
            number2.background = setColor(num2)
            number2.text = num2.toString()
            startSound(num2)
            number2.startAnimation(animation)
            Handler().postDelayed({
                number3.visibility = View.VISIBLE
                number3.background = setColor(num3)
                number3.text = num3.toString()
                startSound(num3)
                number3.startAnimation(animation)
                Handler().postDelayed({
                    row2.visibility = View.VISIBLE
                    number4.visibility = View.VISIBLE
                    number4.background = setColor(num4)
                    number4.text = num4.toString()
                    startSound(num4)
                    number4.startAnimation(animation)
                    Handler().postDelayed({
                        number5.visibility = View.VISIBLE
                        number5.background = setColor(num5)
                        number5.text = num5.toString()
                        startSound(num5)
                        number5.startAnimation(animation)
                        Handler().postDelayed({
                            number6.visibility = View.VISIBLE
                            number6.background = setColor(num6)
                            number6.text = num6.toString()
                            startSound(num6)
                            number6.startAnimation(animation)
                            Handler().postDelayed({
                                button.text = "번호 더 받기"
                                BUTTON_STATE = 2
                            }, 2200)
                        }, 2200)
                    }, 2200)
                }, 2200)
            }, 2200)
        }, 2200)
    }

    private fun resetNumber() {
        row1.visibility = View.GONE
        number1.visibility = View.INVISIBLE
        number2.visibility = View.INVISIBLE
        number3.visibility = View.INVISIBLE
        row2.visibility = View.GONE
        number4.visibility = View.INVISIBLE
        number5.visibility = View.INVISIBLE
        number6.visibility = View.INVISIBLE
        button.text = "번호 받기"
    }

    private fun getNumberAgain() {
        startSound(-2)
        Handler().postDelayed({
            startAdFull()
            resetNumber()
        }, 3000)
    }

    private fun startAdFull() {
        if (mInterstitialAd.isLoaded) {
            mInterstitialAd.show()
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.")
        }
    }

    private fun setColor(type: Int): Drawable {
        return ContextCompat.getDrawable(
            this, when (type) {
                in 1 until 11 -> R.drawable.view_circle_yellow
                in 11 until 21 -> R.drawable.view_circle_blue
                in 21 until 31 -> R.drawable.view_circle_red
                in 31 until 41 -> R.drawable.view_circle_gray
                in 41 until 46 -> R.drawable.view_circle_green
                else -> R.drawable.view_circle_yellow
            }
        )!!
    }

    private fun startSound(type: Int) {
        MediaPlayer.create(
            this@MainActivity, when (type) {
                -2 -> R.raw.sound2
                -1 -> R.raw.sound1
                1 -> R.raw.number1
                2 -> R.raw.number2
                3 -> R.raw.number3
                4 -> R.raw.number4
                5 -> R.raw.number5
                6 -> R.raw.number6
                7 -> R.raw.number7
                8 -> R.raw.number8
                9 -> R.raw.number9
                10 -> R.raw.number10
                11 -> R.raw.number11
                12 -> R.raw.number12
                13 -> R.raw.number13
                14 -> R.raw.number14
                15 -> R.raw.number15
                16 -> R.raw.number16
                17 -> R.raw.number17
                18 -> R.raw.number18
                19 -> R.raw.number19
                20 -> R.raw.number20
                21 -> R.raw.number21
                22 -> R.raw.number22
                23 -> R.raw.number23
                24 -> R.raw.number24
                25 -> R.raw.number25
                26 -> R.raw.number26
                27 -> R.raw.number27
                28 -> R.raw.number28
                29 -> R.raw.number29
                30 -> R.raw.number30
                31 -> R.raw.number31
                32 -> R.raw.number32
                33 -> R.raw.number33
                34 -> R.raw.number34
                35 -> R.raw.number35
                36 -> R.raw.number36
                37 -> R.raw.number37
                38 -> R.raw.number38
                39 -> R.raw.number39
                40 -> R.raw.number40
                41 -> R.raw.number41
                42 -> R.raw.number42
                43 -> R.raw.number43
                44 -> R.raw.number44
                45 -> R.raw.number45
                else -> R.raw.number1
            }
        ).start()
    }
}
