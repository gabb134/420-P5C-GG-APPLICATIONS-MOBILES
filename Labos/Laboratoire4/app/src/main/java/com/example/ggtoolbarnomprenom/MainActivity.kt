package com.example.ggtoolbarnomprenom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(ggtoolbar as Toolbar?)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, getString(R.string.menu_selectionne, item.title), Toast.LENGTH_SHORT ).show()
        when (item.itemId) {
            R.id.menu_toolbar_settings -> return true
            R.id.menu_toolbar_option1 -> return true
        }
        return super.onOptionsItemSelected(item)
    }
}