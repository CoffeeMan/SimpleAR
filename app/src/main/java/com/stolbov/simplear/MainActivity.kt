package com.stolbov.simplear

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stolbov.simplear.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val MY_PERMISSIONS_REQUEST_CAMERA = 100
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        initRecycleView()
        checkPermissions()
    }

    private fun initRecycleView() {
        recyclerView = mainBinding.recyclerView
    }

    override fun onStart() {
        super.onStart()
        fillRecycleView()
    }

    private fun fillRecycleView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter =
                CustomRecyclerAdapter(resources.getStringArray(R.array.items_names).size)
        }

    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), MY_PERMISSIONS_REQUEST_CAMERA);
        }
        else{
            CustomRecyclerAdapter.hasPermission = true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_CAMERA -> {
                if (grantResults.isNotEmpty() && grantResults[0] === PackageManager.PERMISSION_GRANTED) {
                    CustomRecyclerAdapter.hasPermission = true
                } else {
                    Toast.makeText(this, "Использование приложения без данного разрешения невозможно", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

}