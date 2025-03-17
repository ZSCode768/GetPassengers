package example.getpassengers

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList

class GetPassengers : AppCompatActivity() {
    private lateinit var textFirst: EditText
    private lateinit var textLast: EditText
    private lateinit var textPhone: EditText
    private lateinit var textPut: TextView
    private lateinit var addButton: Button
    private lateinit var backToMainButton: Button
    private val passengerList = mutableListOf<Passenger>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_passengers)

        textFirst = findViewById(R.id.first_name)
        textLast = findViewById(R.id.last_name)
        textPhone = findViewById(R.id.phone_number)
        textPut = findViewById(R.id.accum_list)
        addButton = findViewById(R.id.add_button)
        backToMainButton = findViewById(R.id.back_to_main)

        addButton.setOnClickListener { enterPassenger() }
        backToMainButton.setOnClickListener { backToMain() }

    }


    private fun enterPassenger() {
        val firstName = textFirst.text.toString().trim()
        val lastName = textLast.text.toString().trim()
        val phoneNumber = textPhone.text.toString().trim()

        if (firstName.isNotEmpty() && lastName.isNotEmpty() && phoneNumber.isNotEmpty()) {
            val passenger = Passenger(firstName, lastName, phoneNumber)
            passengerList.add(passenger)

            textPut.append("\n${passenger.toString()}")

            textFirst.text.clear()
            textLast.text.clear()
            textPhone.text.clear()
        }
    }

    private fun backToMain() {
        val intent = Intent()
        intent.putExtra("COUNT", passengerList.size.toString())
        for (i in passengerList.indices) {
            intent.putExtra("PASS${i+1}", passengerList[i].toString())
        }
        setResult(RESULT_OK, intent)
        finish()
    }
}
