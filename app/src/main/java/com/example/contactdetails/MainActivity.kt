package com.example.contactdetails

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import java.util.HashMap

var etName: EditText? = null
var etNumber: EditText? = null
var etAddress: EditText? = null
var btn_save: Button? = null
var btn_view: Button? = null

var db: FirebaseFirestore = FirebaseFirestore.getInstance()
private var progressDialog: ProgressDialog?=null

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        etName = findViewById(R.id.contactName)
        etNumber = findViewById(R.id.contactNumber)
        etAddress = findViewById(R.id.contactAddress)
        btn_save = findViewById(R.id.btn_save)
        btn_view = findViewById(R.id.btn_view)

        btn_save!!.setOnClickListener {

            showDialog()

            val contactName: String = etName!!.text.toString()
            val contactNumber: String = etNumber!!.text.toString()
            val contactAddress: String = etAddress!!.text.toString()

            addContact(contactName,contactNumber,contactAddress)

            etName!!.text.clear()
            etNumber!!.text.clear()
            etAddress!!.text.clear()

        }

        btn_view!!.setOnClickListener {
            var i = Intent(this,ViewContacts::class.java)
            startActivity(i)
        }

    }

    fun addContact(name:String, number:String, address:String){

        val contact: MutableMap<String, Any> = HashMap()
        contact["contact_name"] = name
        contact["contact_number"] = number
        contact["contact_address"] = address

        db.collection("contacts")
            .add(contact)
            .addOnSuccessListener {
                Log.e("hala", "Data added successfully with ID: " + it.id)
                hideDialog()
            }
            .addOnFailureListener {
                Log.e("hala", "Failed to add to DB")
                hideDialog()
            }
    }

    private fun showDialog() {
        Log.e("hala","show dialog")
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("Adding contact ...")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }

    private fun hideDialog(){
        if(progressDialog!!.isShowing){
            Log.e("hala","hide dialog")
            progressDialog!!.dismiss()
        }

    }


}