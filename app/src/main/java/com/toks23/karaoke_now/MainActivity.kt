package com.toks23.karaoke_now

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.toks23.karaoke_now.ui.main.SectionsPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.AdapterView
import com.toks23.karaoke_now.model.SongList
import java.util.ArrayList
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private val myImageNameList = arrayOf("Benz", "Bike", "Car", "Carrera", "Ferrari", "Harly", "Lamborghini", "Silver")
    private lateinit var listview: ListView

    private val mainSongList : List<List<SongList>> = ArrayList<ArrayList<SongList>>()
    private var exit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        tabs.tabMode = TabLayout.MODE_SCROLLABLE

        listview = this.findViewById(R.id.list_view) as ListView

        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        when (item.itemId) {
            R.id.connect_wifi -> {
                showDialog(this, listview)
                return true
            }
            R.id.connect_wifi_direct -> {
                showDialog(this, listview)
                return true
            }
            R.id.connect_bluetooth -> {
                showDialog(this, listview)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (exit) {
            finish() // finish activity
            Handler().postDelayed({ exitProcess(0) }, (3 * 1000).toLong())
        } else {
            Toast.makeText(
                this, "Press Back again to Exit.",
                Toast.LENGTH_SHORT
            ).show()
            exit = true
            Handler().postDelayed({ exit = false }, (3 * 1000).toLong())
        }
    }

    private fun showDialog(activity: Activity, listView: ListView?) {
        val dialog = Dialog(activity)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_listview)

        val btndialog = dialog.findViewById(R.id.btn_dialog) as Button
        btndialog.setOnClickListener { dialog.dismiss() }


        val arrayAdapter = ArrayAdapter(this, R.layout.list_item, R.id.tv, myImageNameList)
        listview.adapter = arrayAdapter

        listView?.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
           // Toast.makeText(activity,"You have clicked : " + myImageNameList[position],Toast.LENGTH_LONG).show()
            dialog.dismiss()
        }
        dialog.show()
    }
}