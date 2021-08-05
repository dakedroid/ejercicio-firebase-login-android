package mx.tecnm.voaxaca.androidjetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import mx.tecnm.voaxaca.androidjetpack.databinding.FragmentInicioBinding


class InicioFragment : Fragment() {

    val db = Firebase.firestore
    private lateinit var binding :  FragmentInicioBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_inicio, container, false)

        binding.botonSiguiente.setOnClickListener {
           // subirUsuarioFirebase()
            editarNombre()
        }

        binding.botonLeer.setOnClickListener {
            obtenerDatosFirebase()
        }

        binding.botonBorrar.setOnClickListener {
            borrarDatos()
        }

        return binding.root
    }

    fun editarNombre (){
        db.collection("usuarios")
            .document("NhBl3uGxfiUT0mi7iMYb")
            .update("nombre", binding.nombreEdtx.text.toString())
            .addOnSuccessListener {
                Toast.makeText(context, "Actualizado", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {}

    }

    fun borrarDatos (){
        db.collection("usuarios").document("NhBl3uGxfiUT0mi7iMYb")
            .delete()
            .addOnSuccessListener {
                Toast.makeText(context, "Borrado", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
            }
    }

    fun obtenerDatosFirebase (){

        db.collection("usuarios")
            .document("NhBl3uGxfiUT0mi7iMYb")
            .addSnapshotListener { doc, e ->

                if (e != null) {
                    // Mandar mensaje de rror
                    return@addSnapshotListener
                }

                val nombre = doc?.data?.get("nombre").toString()
                val apellido = doc?.data?.get("apellido").toString()
                val edad = doc?.data?.get("edad").toString()

                binding.txvNombre.text = nombre
                binding.txvApellido.text = apellido
                binding.txvEdad.text = edad

            }

    }

    fun subirUsuarioFirebase () {

        val usuario = hashMapOf(
            "nombre" to "Stephanie",
            "apellido" to "Martinez",
            "semestre" to 8,
            "edad" to 21,
            "creacion" to Timestamp.now()
        )

        db.collection("usuarios")
            .add(usuario)
            .addOnSuccessListener {
                Toast.makeText(context, "La informacion se subio", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener{ e ->
                Toast.makeText(context, "Hubo un error $e", Toast.LENGTH_SHORT).show()
            }
    }

}