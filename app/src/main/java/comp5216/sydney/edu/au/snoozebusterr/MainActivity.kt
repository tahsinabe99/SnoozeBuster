package comp5216.sydney.edu.au.snoozebusterr


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var alarmRecyclerView: RecyclerView
    private lateinit var addAlarmButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        alarmRecyclerView = findViewById(R.id.alarmRecyclerView)
        addAlarmButton = findViewById(R.id.addAlarmButton)

        alarmRecyclerView.layoutManager = LinearLayoutManager(this)
        // Later: set adapter with saved alarms

        addAlarmButton.setOnClickListener {
            // Later: Start AddAlarmActivity
        }
    }
}
