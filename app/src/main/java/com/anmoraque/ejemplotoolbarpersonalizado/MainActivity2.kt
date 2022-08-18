package com.anmoraque.ejemplotoolbarpersonalizado

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.anmoraque.ejemplotoolbarpersonalizado.R

class MainActivity2 : AppCompatActivity() {
    //Creamos la variable para el toolbar a null
    var toolbar:androidx.appcompat.widget.Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //Inicializamos la toolbar
        toolbar = findViewById(R.id.toolbar)
        //Le ponemos un titulo desde el recurso String
        toolbar?.setTitle(R.string.app_name)
        //La añadimos como la actionbar por defecto
        setSupportActionBar(toolbar)

        //Boton de ir atras
        //Creo una nueva variable
        var actionbar = supportActionBar

        actionbar?.setDisplayHomeAsUpEnabled(true)

    }
    //Asociamos el menu a nuestro codigo para poder inflarlo
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_dos, menu)
        return super.onCreateOptionsMenu(menu)
    }
}