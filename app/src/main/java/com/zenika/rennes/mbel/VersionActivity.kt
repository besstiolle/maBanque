package com.zenika.rennes.mbel

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VersionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Question if < 17
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            showDialog()
        } else {
            gotoMainActivity()
        }

    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Version d'Android ancienne")
        builder.setMessage("Votre smartphone est ancien. Pour bénéficier de toutes les fonctionnalités " +
                "de l'application nous vous conseillons d'aller sur notre site internet. \n" +
                "Aller sur notre site ?")
        builder.setPositiveButton("oui") { _, _ ->
            toast("ok, go to website")
            gotoWebsite()
        }
        builder.setNeutralButton("non") { _, _ ->
            toast("ok, we continue")
            gotoMainActivity()
        }
        builder.setCancelable(false)
        builder.show()
    }

    private fun gotoWebsite(){
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")))
        finishAffinity()
    }

    private fun gotoMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finishAffinity()
    }

    private fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}