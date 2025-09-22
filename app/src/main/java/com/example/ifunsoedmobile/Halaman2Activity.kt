package com.example.ifunsoedmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.ifunsoedmobile.databinding.ActivityHalaman2Binding

class Halaman2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityHalaman2Binding

    private val latitude = "-7.429427"
    private val longitude = "109.338082"
    private val gMapsUrl = "http://maps.google.com/maps?q=loc:"
    private val packageMaps = "com.google.android.apps.maps"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHalaman2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ 5F: panggil kedua function di sini
        initLayout()
        initListener()
    }

    private fun initLayout() {
        binding.layoutLocation.let {
            it.imgIcon.setImageResource(R.drawable.ic_location)
            it.tvLayout.setText(R.string.alamat)
        }

        binding.layoutEmail.let {
            it.imgIcon.setImageResource(R.drawable.ic_email)
            it.tvLayout.setText(R.string.email)
        }

        binding.layoutIg.let {
            it.imgIcon.setImageResource(R.drawable.ic_himpunan)
            it.tvLayout.setText(R.string.ig_himpunan)
        }

        binding.layoutPhone.let {
            it.imgIcon.setImageResource(R.drawable.ic_phone)
            it.tvLayout.setText(R.string.telepon)
        }
    }

    private fun initListener() {
        // Lokasi
        binding.layoutLocation.root.setOnClickListener {
            try {
                val gMapsIntentUri = "$gMapsUrl$latitude,$longitude".toUri()
                val mapIntent = Intent(Intent.ACTION_VIEW, gMapsIntentUri).apply {
                    setPackage(packageMaps)
                }
                startActivity(mapIntent)
            } catch (e: Exception) {
                startActivity(Intent(Intent.ACTION_VIEW, "$gMapsUrl$latitude,$longitude".toUri()))
            }
        }

        // IG
        binding.layoutIg.root.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, getString(R.string.ig_himpunan).toUri())
                startActivity(intent)
            } catch (_: Exception) {
            }
        }

        // Email
        binding.layoutEmail.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = "mailto:${getString(R.string.email)}".toUri()
            }
            startActivity(intent)
        }

        // Telepon
        binding.layoutPhone.root.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = "tel:${getString(R.string.telepon)}".toUri()
            }
            startActivity(intent)
        }

        // Back
        binding.btnBack.setOnClickListener { finish() }
    }
}