package mx.tecnm.voaxaca.androidjetpack

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import mx.tecnm.voaxaca.androidjetpack.databinding.FragmentFinalBinding

class FinalFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : FragmentFinalBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_final, container, false)


        val argumentos: FinalFragmentArgs by navArgs()


        binding.nombre.text = argumentos.nombre

        binding.edad.text = argumentos.edad.toString()


        binding.nombre.setOnClickListener {
            it.findNavController().navigate(FinalFragmentDirections.actionFinalFragmentToInicioFragment())
        }


        return binding.root
    }

}