package mx.tecnm.voaxaca.androidjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import mx.tecnm.voaxaca.androidjetpack.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }
}