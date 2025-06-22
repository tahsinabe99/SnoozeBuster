package comp5216.sydney.edu.au.snoozebusterr

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class AlarmRingActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    private var correctAnswer: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_ring)

        // 1. Play alarm sound
        mediaPlayer = MediaPlayer.create(this, android.provider.Settings.System.DEFAULT_ALARM_ALERT_URI)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        // 2. Set up math problem
        val num1 = Random.nextInt(1, 20)
        val num2 = Random.nextInt(1, 20)
        correctAnswer = num1 + num2

        val questionText = findViewById<TextView>(R.id.mathQuestion)
        val answerInput = findViewById<EditText>(R.id.answerInput)
        val submitButton = findViewById<Button>(R.id.submitAnswerButton)

        questionText.text = "Solve: $num1 + $num2"

        // 3. Handle submit button
        submitButton.setOnClickListener {
            val userAnswer = answerInput.text.toString().toIntOrNull()

            if (userAnswer == correctAnswer) {
                mediaPlayer.stop()
                mediaPlayer.release()
                Toast.makeText(this, "Correct! Alarm stopped.", Toast.LENGTH_SHORT).show()
                finish() // Close the alarm screen
            } else {
                Toast.makeText(this, "Wrong answer. Try again!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}
