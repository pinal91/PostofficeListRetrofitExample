package com.luxsh.reelimigration

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.luxsh.adapter.ItemListAdapter
import com.luxsh.model.PostOfficeItem
import com.luxsh.model.PostofficeListResponse
import com.luxsh.reelimigration.databinding.ActivityMainBinding
import com.luxsh.restclient.RestClient
import com.luxsh.restclient.WebServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  binding.tvUserName.text="Welcome"+" "+intent.getStringExtra("name")
       /* binding.visa.setOnClickListener {
            showSnackBar("You clicked on Visa")
        }
        binding.custom.setOnClickListener {
            showSnackBar("You clicked on CustomOfficer")
        }
        binding.passport.setOnClickListener {
            showSnackBar("You clicked on Passport")
        }
        binding.terminal.setOnClickListener {
            showSnackBar("You clicked on Terminal")
        }
        binding.varification.setOnClickListener {
            showSnackBar("You clicked on Verification")
        }
        binding.ticket.setOnClickListener {
            showSnackBar("You clicked on Ticket")
        }*/
        // calling this activity's function to
        // use ActionBar utility methods
        binding.logout.setOnClickListener {
            if (SharedPreferencesManager.getString(
                    this@MainActivity, Constants.PREFS_isUSER_LOGGED_IN, "").equals("yes")) {
                val dialogView = AlertDialog.Builder(this@MainActivity).create()
                dialogView.setMessage(resources.getString(R.string.logou_note))

                dialogView.setButton(AlertDialog.BUTTON_POSITIVE, resources.getString(R.string.yes)) { dialogInterface, i ->
                    dialogView.dismiss()
                    SharedPreferencesManager.writeString(this@MainActivity, Constants.PREFS_isUSER_LOGGED_IN, "no")
                    val i = Intent(this@MainActivity, LoginActivity::class.java)
                    startActivity(i)
                    finish()

                }

                dialogView.setButton(AlertDialog.BUTTON_NEGATIVE, resources.getString(R.string.no)) { dialogInterface, i ->
                    dialogView.dismiss()
                }
                dialogView.show()

            }else{
                val i = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(i)
            }
        }
        val layoutManager: androidx.recyclerview.widget.LinearLayoutManager =
            androidx.recyclerview.widget.LinearLayoutManager(this)
        binding.recyItem.layoutManager = layoutManager
        getOrder()
    }

    private fun getOrder() {

        disposable =
            RestClient.create().GetOrder("Vadodara")!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleLoginResponse, this::handleError)
    }

    private fun handleLoginResponse(result: PostofficeListResponse) {


        val adapterList = ItemListAdapter(result.PostOffice as ArrayList<PostOfficeItem>?, this@MainActivity)
        binding.recyItem.adapter = adapterList
        binding.recyItem.visibility=View.VISIBLE

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    fun handleError(error: Throwable) {
        error.message?.let { Log.d("ERROR", it) }

    }

    fun showSnackBar( message: String?) {
        val sb = Snackbar.make(binding.linMain, message!!, BaseTransientBottomBar.LENGTH_LONG)
        val snackbarView: View = sb.getView()
        val tv = snackbarView.findViewById(R.id.snackbar_text) as TextView
        tv.maxLines = 3
        sb.view.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))
        sb.show()
    }
}