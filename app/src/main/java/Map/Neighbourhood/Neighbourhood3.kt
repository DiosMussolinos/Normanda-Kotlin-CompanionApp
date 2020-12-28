package Map.Neighbourhood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.normanda_capp.MainActivity
import com.example.normanda_capp.R

private lateinit var  closeImage: ImageView

class Neighbourhood3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_neighbourhood3)

        closeImage = findViewById(R.id.BackToMap3)

        closeImage.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}