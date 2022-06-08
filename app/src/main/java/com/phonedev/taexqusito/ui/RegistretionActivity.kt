package com.phonedev.taexqusito.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.phonedev.taexqusito.MainActivity
import com.phonedev.taexqusito.databinding.ActivityRegistretionBinding
import com.phonedev.taexqusito.entities.Constants

class RegistretionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistretionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistretionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar?.hide()

        setup()
    }

    private fun setup() {
        binding.btnLogin.setOnClickListener {
            if (binding.etName.text.isNotEmpty() && binding.etDireccion.text.isNotEmpty() && binding.etTelefono.text.isNotEmpty() && binding.etUser.text.isNotEmpty() && binding.etPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.etUser.text.toString().trim(),
                    binding.etPassword.text.toString().trim()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        val profileUpdates =
                            UserProfileChangeRequest.Builder()
                                .setDisplayName(binding.etName.text.toString().trim()).build()

                        user?.updateProfile(profileUpdates)

                        val userID = FirebaseAuth.getInstance().currentUser?.uid.toString()
                        val database = Firebase.database

                        val map: Map<String, String> = mapOf(
                            Pair("name", binding.etName.text.toString().trim()),
                            Pair("user", binding.etUser.text.toString().trim()),
                            Pair("direccion", binding.etDireccion.text.toString().trim()),
                            Pair("telefono", binding.etTelefono.text.toString().trim())
                        )

                        val userRef =
                            database.getReference(Constants.PATH_USERS).child(userID).setValue(map)
                                .addOnCompleteListener { userAdd ->
                                    if (userAdd.isSuccessful) {
                                        showHome()
                                        finish()
                                    } else {
                                        showAlert()
                                    }
                                }
                    } else {
                        showAlert()
                    }
                }
            } else {
                showAlert()
            }
        }
    }

    private fun showHome() {
        val homeIntent = Intent(this, MainActivity::class.java)
        startActivity(homeIntent)
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Error al registrar usuario.")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}