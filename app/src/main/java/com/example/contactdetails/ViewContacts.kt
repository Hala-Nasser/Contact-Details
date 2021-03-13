package com.example.contactdetails

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

var rv_contacts: RecyclerView? = null
private var progressDialog: ProgressDialog?=null

class ViewContacts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_contacts)



        rv_contacts = findViewById(R.id.rv_contacts)
        showDialog()

        getContact()
    }

    fun getContact(){
        val contactList= mutableListOf<Contact>()
        db.collection("contacts")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.e("hala", "${document.id} -> ${document.get("contact_name")}")
                        val id = document.id
                        val data = document.data
                        val contactName = data["contact_name"] as String?
                        val contactNumber = data["contact_number"] as String?
                        val contactAddress = data["contact_address"] as String?
                        contactList.add(Contact(id, contactName, contactNumber,contactAddress))
                    }
                    rv_contacts!!.layoutManager =
                        LinearLayoutManager(this, RecyclerView.VERTICAL, false)
                    rv_contacts!!.setHasFixedSize(true)
                    val contactsAdapter = ContactAdapter(this, contactList)
                    rv_contacts!!.adapter = contactsAdapter
                }
                hideDialog()
            }
    }

    private fun showDialog() {
        Log.e("hala","show dialog")
        progressDialog = ProgressDialog(this)
        progressDialog!!.setMessage("View contacts ...")
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