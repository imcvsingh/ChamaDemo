package com.cvsingh.chamademo.view

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.cvsingh.chamademo.R
import com.cvsingh.chamademo.databinding.ActivityMainBinding
import com.cvsingh.chamademo.utils.AppPreferences
import com.cvsingh.chamademo.utils.ManagePermissions
import com.cvsingh.chamademo.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val PERMISSION_REQUEST_CODE = 1
    private lateinit var managePermissions: ManagePermissions

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        /*viewModel.permissionRequest.observe(this, Observer {
            // Initialize a new instance of ManagePermissions class
            managePermissions = ManagePermissions(this, listOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE)
            managePermissions.checkPermissions()
        })*/

        managePermissions = ManagePermissions(this, listOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE)
        managePermissions.checkPermissions()

        binding.viewModel = viewModel
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        AppPreferences.firstTimePermission=true
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    explain("Allow Chama App to access your location even when you are not using the app?",
                            "Your location is used to show dishes around you that our Chefs love")
                } else {

                }
            }
        }
    }

    private fun explain(title: String, msg: String) {
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
        dialog.setTitle(title)
        dialog.setMessage(msg)
            .setPositiveButton("Allow") { paramDialogInterface, paramInt ->
                  managePermissions.checkPermissions()
            }
            .setNegativeButton("Don't Allow") { paramDialogInterface, paramInt -> finish() }
        dialog.show()
    }

    override fun onClick(v: View?) {
        AppPreferences.name = "ok"
    }

}

// Extension function to show toast message
fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}