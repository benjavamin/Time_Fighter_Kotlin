package com.raywanderlich.timefighter

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var tapCounter = 0
    private var started = false

    internal val countDownTotal: Long = 5000
    internal val countDownInterval: Long = 1000

    internal lateinit var countDownTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeButton.setOnClickListener {
            increaseScore()
        }

        resetGame()
    }

    private fun increaseScore() {
        if (!started) {
            startGame()
        }

        tapCounter++
        scoreTextView.text = getString(R.string.score, tapCounter)
    }

    private fun startGame() {
        countDownTimer.start()
        started = true
    }

    private fun resetGame() {

        tapCounter = 0
        scoreTextView.text = getString(R.string.score, tapCounter)

        val initialTimeLeft = countDownTotal / 1000
        timeLeftTextView.text = getString(R.string.time, initialTimeLeft)

        countDownTimer = object : CountDownTimer(countDownTotal, countDownInterval) {

            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                timeLeftTextView.text = getString(R.string.time, timeLeft)
            }

            override fun onFinish() {
                endGame()
            }
        }

        started = false
    }

    private fun endGame() {
        Toast.makeText(
            application,
            getString(R.string.gameFinishedScore, tapCounter),
            Toast.LENGTH_LONG
        ).show()
        resetGame()
    }
}
