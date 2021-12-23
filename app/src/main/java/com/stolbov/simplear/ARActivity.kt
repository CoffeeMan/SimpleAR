package com.stolbov.simplear

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.sceneform.ux.ArFragment
import com.stolbov.simplear.databinding.ActivityArBinding

class ARActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arBinding = ActivityArBinding.inflate(layoutInflater)
        setContentView(arBinding.root)
        supportActionBar?.hide()

        show3DModel()
    }

    private fun show3DModel() {
        val modelName = this.intent.getStringExtra(CustomRecyclerAdapter.KEY_EXTRA)
        (supportFragmentManager.findFragmentById(R.id.arFragment) as ArFragment)
            .setOnTapPlaneGlbModel(modelName)
    }
}