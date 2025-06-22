package comp5216.sydney.edu.au.snoozebusterr

import android.os.Bundle
import android.widget.Button
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import java.util.Calendar

class AddAlarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_alarm)

        val timePicker = findViewById<TimePicker>(R.id.timePicker)

        val saveButton = findViewById<Button>(R.id.saveAlarmButton)
        saveButton.setOnClickListener {
            val hour = timePicker.hour
            val minute = timePicker.minute

            // 1. Create a calendar with today's date and selected time
            val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)

                // If the time is in the past, add one day
                if (before(Calendar.getInstance())) {
                    add(Calendar.DATE, 1)
                }
            }

            // 2. Intent to launch the AlarmRingActivity (we'll create this next)
            val intent = Intent(this, AlarmRingActivity::class.java)

            // 3. PendingIntent is a wrapper that lets AlarmManager call it later
            //its like a permission slip from Android System so it wakes uo Android once and run this intent.

            val pendingIntent = PendingIntent.getActivity(
                this,
                0, //an ID to track this alarm
                intent, //intent tells what to do
                PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            )

            // 4. Get AlarmManager and set the alarm
            //getSystemService ask android to give thing sthat handle alarm
            //ALARM_SERVICE the build in alarm system
            val alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )

            // 5. Confirm it to the user
            Toast.makeText(this, "Alarm set for ${hour}:${minute}", Toast.LENGTH_SHORT).show()

            // Optionally: finish this screen
            finish()
        }
}}