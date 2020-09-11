package com.example.ggtoolbarnomprenom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(ggtoolbar as Toolbar?)
        val view = findViewById<View>(R.id.mainConstraintLayout);
        registerForContextMenu(view);
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Toast.makeText(this, getString(R.string.menu_selectionne, item.title), Toast.LENGTH_SHORT)
          //  .show()
        when (item.itemId) {
            R.id.menu_toolbar_settings -> return true
            R.id.menu_toolbar_option1 -> return true

        }
        when(item.title){
            (ggtoolbar as Toolbar).title -> {

                Toast.makeText(this, "Vous avez cliqué sur l’icône de la barre d’outils", Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        info: ContextMenu.ContextMenuInfo?
    ) {
        when (v.id) {
            R.id.mainConstraintLayout -> menuInflater.inflate(R.menu.menu_contextuel, menu)
            else -> super.onCreateContextMenu(menu, v, info)
        }
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        Toast.makeText(this, getString(R.string.menu_selectionne, item.title), Toast.LENGTH_SHORT)
            .show()

        when (item.itemId) {
            R.id.menu_contextuel_option2, R.id.menu_contextuel_option3, R.id.menu_contextuel_option4, R.id.menu_contextuel_option5 -> {
                item.isChecked = !item.isChecked

                return true
            }
        }

        return super.onContextItemSelected(item)
    }


}