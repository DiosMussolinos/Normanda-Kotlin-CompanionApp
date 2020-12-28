package Map.Neighbourhood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager.widget.ViewPager
import com.example.normanda_capp.MainActivity
import com.example.normanda_capp.R

private lateinit var  closeImage: ImageView

class Neighbourhood1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_neighbourhood1)


        closeImage = findViewById(R.id.BackToMap)

        closeImage.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}