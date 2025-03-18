package example.getpassengers

//Zachary Surlow
//CS3013

import android.app.Activity
import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var listText: TextView
    private lateinit var getListButton: Button

    private val getPassengers = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            val data = result.data
            val count = data?.getStringExtra("COUNT")?.toInt() ?: 0
            listText.text = "Returned Passenger List:\n"
            for(i in 1..count){
                val passengerString = data?.getStringExtra("PASS$i") ?: ""
                listText.append("\n$passengerString")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listText = findViewById(R.id.show_list)
        getListButton = findViewById(R.id.get_list_button)

        getListButton.setOnClickListener {
            val intent = Intent(this, GetPassengers::class.java)
            getPassengers.launch(intent)
        }
    }

}

data class Passenger(val firstName: String, val lastName: String, val phone: String){
    override fun toString(): String{
        return "$firstName $lastName ($phone)"
    }
}