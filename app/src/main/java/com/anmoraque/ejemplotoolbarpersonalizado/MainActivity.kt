package com.anmoraque.ejemplotoolbarpersonalizado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.SearchView
import android.widget.ShareActionProvider
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import com.anmoraque.ejemplotoolbarpersonalizado.R


/*
En esta actividad hemos hablado de:
Crear Toolbar personalizada
Añadirle un menu y escuchar sus items para hacer una accion con ellos
Configurar boton hacia atras
SearchView (Tipica barra de busqueda en el Toolbar)
Boton Share (Compartir) en el Toolbar
 */
class MainActivity : AppCompatActivity() {
    //Creamos la variable para el toolbar a null
    var toolbar:androidx.appcompat.widget.Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Inicializamos la toolbar
        toolbar = findViewById(R.id.toolbar)
        //Le ponemos un titulo desde el recurso String
        toolbar?.setTitle(R.string.app_name)
        //La añadimos como la actionbar por defecto
        setSupportActionBar(toolbar)
        //Inicializamos el boton
        var bIr = findViewById<Button>(R.id.boton)
        //Listener al boton con un intent a la otra pantalla
        bIr.setOnClickListener {
            val intent = Intent (this, MainActivity2::class.java)
            startActivity(intent)
        }
    }
    //Asociamos el menu a nuestro codigo para poder inflarlo
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        //Inflamos el menu
        menuInflater.inflate(R.menu.menu, menu)
        //Inicializamos el item compartir
        val itemCompartir: MenuItem = menu.findItem(R.id.share)
        //itemCompartir pide un objeto ActionProvider
        val shareActionProvider = MenuItemCompat.getActionProvider(itemCompartir) as androidx.appcompat.widget.ShareActionProvider
        //Compartimos mediante un Intent con lo cual creamos una funcion aparte para ello
        compartirIntent(shareActionProvider)


        //Inicializamos el item SearchView (boton de busqueda)
        val itemBusqueda = menu.findItem(R.id.busqueda)
        //Esta variable sirve para interactuar con el searchView
        var vistaBusqueda = itemBusqueda.actionView as SearchView
        //Ponemos un texto en el buscador SearchView
        vistaBusqueda.queryHint = "Escibe tu nombre..."
        //Añadimos los listener al searchView
        //El primero detecta que se activó el searchView
        vistaBusqueda.setOnQueryTextFocusChangeListener { view, b ->
            //Creamos un log para probar
            Log.d("ETIQUETA_LOG", "LISTENERFOCUS: " + b.toString())
        }
        //El segundo detecta lo que se va escribiendo en la busqueda para ir filtrandolo
        //y tambien cuando le da a enter para buscar
        //Este listener pide un objeto OnQueryTextListener
        vistaBusqueda.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            //Hay que implementar dos funciones para sobreescribir los metodos
            //Este metodo escucha cuando se va escribiendo
            override fun onQueryTextChange(p0: String?): Boolean {
                //Creamos un log para probar
                Log.d("ETIQUETA_LOG", "onQueryTextChange: " + p0.toString())
                //Tenemos que regresar true
                return true
            }
            //Este metodo escucha cuando le da a enter al terminar de escribir en la busqueda
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //Creamos un log para probar
                Log.d("ETIQUETA_LOG", "onQueryTextSubmit: " + p0.toString())
                //Tenemos que regresar true
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)



    }
    //Asociamos los eventos a cada item seleccionado del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.bFav -> {
                Toast.makeText(this, "Elemento añadido como favorito", Toast.LENGTH_LONG).show()
                return true
            }else -> {
            return super.onOptionsItemSelected(item)
            }
        }
    }
    //Creamos la funcion compartirIntent donde pedimod un objeto ShareActionProvider
    private fun compartirIntent(shareActionProvider: androidx.appcompat.widget.ShareActionProvider){
        //Uso un condicional para saber si mi ShareActionProvider tiene contenido
        if (shareActionProvider != null){
            //Creamos el intent a compartir, este no va a otra clase sino que envia una accion
            val intent = Intent(Intent.ACTION_SEND)
            //Especificamos el tipo de dato a mandar (en este caso texto plano
            intent.type = "text/plain"
            //Colocamos el contenido a mandar (En este caso un texto)
            intent.putExtra(Intent.EXTRA_TEXT, "Este es un mensaje compartido")
            //Ahora mandamos el intent al ShareActionProvider
            shareActionProvider.setShareIntent(intent)

        }
    }

}