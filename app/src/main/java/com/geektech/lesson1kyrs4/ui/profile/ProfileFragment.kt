package com.geektech.lesson1kyrs4.ui.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.geektech.lesson1kyrs4.data.Pref
import com.geektech.lesson1kyrs4.databinding.FragmentProfileBinding
import com.geektech.lesson1kyrs4.utils.loadImage


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var pref: Pref
    private lateinit var pref1: Pref
    private lateinit var pref2: Pref

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(requireContext())
        pref1 = Pref(requireContext())
        pref2 = Pref(requireContext())
        binding.etProfile.setText(pref.getName())
        binding.etProfileAge.setText(pref1.getAge())
        binding.circleIv.loadImage(pref2.getImage())

        binding.btnSavepref.setOnClickListener {
            pref.saveName(binding.etProfile.text.toString())
            pref1.saveAge(binding.etProfileAge.text.toString())
        }

        binding.circleIv.setOnClickListener {
            createIntent()
        }
    }

    private fun createIntent() {
        val intent =
            Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).apply {
                type = "image/*"
            }
        launcher.launch(intent)
    }

    //set profile photo
    private val launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK
            && result.data != null
        ) {
            val photoUri: Uri? = result.data?.data
            pref.saveImage(photoUri.toString())
            binding.circleIv.loadImage(photoUri.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}





