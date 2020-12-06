package com.zenika.rennes.mbel

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VersionActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showDialog()
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Version d'Android ancienne")
        builder.setMessage("Votre smartphone est ancien. Pour bénéficier de toutes les fonctionnalités de l'application nous vous conseillons d'aller sur notre site internet. Souhaitez vous y accéder de suite ?")
        builder.setPositiveButton("oui") { _, _ -> gotoWebsite()}
        builder.setNeutralButton("non") { _, _ -> gotoMainActivity()}
        builder.show()
    }

    private fun gotoWebsite(){
        toast("ok, go to website")
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com")))
        finishAffinity()
    }

    private fun gotoMainActivity(){
        toast("ok, we continue")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}