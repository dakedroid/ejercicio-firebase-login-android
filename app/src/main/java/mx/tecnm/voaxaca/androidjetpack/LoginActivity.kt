package mx.tecnm.voaxaca.androidjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import mx.tecnm.voaxaca.androidjetpack.databinding.ActivityLoginBinding
import mx.tecnm.voaxaca.androidjetpack.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        auth = Firebase.auth

        binding.botonRegistro.setOnClickListener {
            crearUsuario()
        }

        binding.botonIniciarSesion.setOnClickListener {
            iniciarSesion()
        }

        binding.botonCerrarSesion.setOnClickListener {
            cerrarSesion()
        }

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val usuarioActual = auth.currentUser

        if(usuarioActual != null){
            actualizarUILogeado(usuarioActual.uid, usuarioActual.email!!)
        }else {
            actualizarUICerrar()
        }
    }

    fun cerrarSesion(){
        Firebase.auth.signOut()
        actualizarUICerrar()
    }

    fun iniciarSesion(){

        val nombre = binding.edtxCorreo.text.toString()
        val contrasena =  binding.edtxContrasena.text.toString()

        auth.signInWithEmailAndPassword(nombre, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val usuarioActual = auth.currentUser
                    actualizarUILogeado(usuarioActual!!.uid, usuarioActual.email!!)
                } else {

                    Toast.makeText(baseContext, "Fallo",
                        Toast.LENGTH_SHORT).show()
                    actualizarUICerrar()
                }
            }
    }

    fun crearUsuario(){

        val nombre = binding.edtxCorreo.text.toString()
        val contrasena =  binding.edtxContrasena.text.toString()

        auth.createUserWithEmailAndPassword(nombre, contrasena)
            .addOnCompleteListener { task ->

                if (task.isSuccessful){

                    val usuarioActual = auth.currentUser

                    actualizarUILogeado(usuarioActual!!.uid, usuarioActual.email!!)

                } else {
                    Toast.makeText(baseContext, "Fallo", Toast.LENGTH_SHORT).show()
                    actualizarUICerrar()
                }

            }
    }

    fun actualizarUILogeado (id: String, correo: String){

        binding.apply {
            txvCorreo.visibility = View.VISIBLE
            txvId.visibility = View.VISIBLE

            txvCorreo.text = correo
            txvId.text = id

            edtxCorreo.visibility = View.GONE
            edtxContrasena.visibility = View.GONE

            botonIniciarSesion.visibility = View.GONE
            botonRegistro.visibility = View.GONE

            botonCerrarSesion.visibility = View.VISIBLE

        }
    }

    fun actualizarUICerrar (){

        binding.apply {
            txvCorreo.visibility = View.GONE
            txvId.visibility = View.GONE

            txvCorreo.text = ""
            txvId.text = ""

            edtxCorreo.visibility = View.VISIBLE
            edtxContrasena.visibility = View.VISIBLE

            botonIniciarSesion.visibility = View.VISIBLE
            botonRegistro.visibility = View.VISIBLE

            botonCerrarSesion.visibility = View.GONE

        }
    }
}