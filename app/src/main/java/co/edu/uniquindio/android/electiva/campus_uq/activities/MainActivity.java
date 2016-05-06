package co.edu.uniquindio.android.electiva.campus_uq.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.campus_uq.R;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.DetalleNoticiaFragment;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.DirectorioDependenciasFragment;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.HomeAplicacionFragment;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.ListaDeNoticiasFragments;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.SinConexionFragment;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.SugerenciasFragment;
import co.edu.uniquindio.android.electiva.campus_uq.util.Utilidades;
import co.edu.uniquindio.android.electiva.campus_uq.vo.Noticia;

/**
 * Esta es la actividad principal del proyecto campus_UQ de la electiva de moviles
 * @author: Jose Omar Colorado y Jesus Alberto Onofre
 */

public class MainActivity extends AppCompatActivity implements ListaDeNoticiasFragments.OnNoticiaSeleccionadaListener {

    // Atributos  de la Actividad
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;

    TextView txtToolbar;
    private ArrayList<Noticia> noticias = new ArrayList<>();
    private Utilidades utilidades;


    /**
     * Metodo onCreate
     * Es el metodo que se invoca cuando el sistema crea la actividad, se inicializan los
     * componentes basicos de la actividad.
     * @param sabedInstanceState informacion actual que se encuentra guardada de la actividad
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFragment(0);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        txtToolbar = (TextView) findViewById(R.id.toolbar_title);


        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }

        setupNavigationDrawerContent(navigationView);

        noticias.add(new Noticia("Esta noticia","25 Enero de 2016","imagen","Noticia1"));
        noticias.add(new Noticia("Esta noticia","25 Enero de 2016","imagen","Noticia2"));
        noticias.add(new Noticia("Esta noticia","25 Enero de 2016","imagen","Noticia3"));
        noticias.add(new Noticia("Esta noticia","25 Enero de 2016","imagen","Noticia4"));
        noticias.add(new Noticia("Esta noticia","25 Enero de 2016","imagen","Noticia5"));
        noticias.add(new Noticia("Esta noticia","25 Enero de 2016","imagen","Noticia6"));
        noticias.add(new Noticia("Esta noticia","25 Enero de 2016","imagen","Noticia7"));
        noticias.add(new Noticia("Esta noticia","25 Enero de 2016","imagen","Noticia8"));
        utilidades = new Utilidades();



        //Typeface font = Typeface.createFromAsset(getAssets(), "/fonts/korinabold.ttf");
        //txt = (TextView) findViewById(R.id.textView);
        // Log.d("MENU", "Cambio font");
        //txt.setTypeface(font);


    }

    /**
     * Metodo onCreateOptionsMenu
     * Metodo donde se especifican el menu de opciones que va a tener la actividad
     * @param menu le ingresa por parametro el menu de la actividad
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    /**
     * Metodo onOptionsItemSelected
     * Metodo para identificar el menu seleccionado  Puede identificar el elemento 
     * llamando GetItemID () , que devuelve el identificador único para el elemento de menú
     * @param item elemento identificado
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * Metodo setupNavigationDrawerContent
     * Metodo el cual recibe la navegacion del Drawer y donde se encuentran 
     * todas las opciones de este y cual es seleccionada para asi llamar 
     * al fragmento correspondiente
     * @param navigationView recibe una NavigationView
     */
    private void setupNavigationDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_home:
                                menuItem.setChecked(true);
                                txtToolbar.setText("Pantalla Inicio");
                                drawerLayout.closeDrawer(GravityCompat.START);
                                setFragment(0);
                                return true;
                            case R.id.item_navigation_drawer_news:
                                menuItem.setChecked(true);
                                txtToolbar.setText("Noticias");
                                setFragment(1);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_directory:
                                menuItem.setChecked(true);
                                txtToolbar.setText("Directorio");
                                setFragment(2);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_review:
                                menuItem.setChecked(true);
                                txtToolbar.setText("Sugerencias");
                                setFragment(3);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_languaje:
                                Toast.makeText(getApplicationContext(), "Cambio de lenguaje", Toast.LENGTH_LONG).show();
                                setFragment(0);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_page:
                                drawerLayout.closeDrawer(GravityCompat.START);
                                menuItem.setChecked(true);
                                Uri uri = Uri.parse("http://www.uniquindio.edu.co"); // missing 'http://' will cause crashed
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                                return true;

                        }
                        return true;
                    }
                });
    }

    /**
     * Metodo onNoticiaSeleccionada
     * Metodo Metodo en el cual la actividad escoge las noticias selecionadad
     * @param position la posicion de la noticia que se selecciona
     */
    @Override
    public void onNoticiaSeleccionada(int position) {
        setFragment(4);

        //((DetalleNoticiaFragment) getSupportFragmentManager().findFragmentById(R.id.fragment)).mostrarNoticia(noticias.get(position));

        Toast.makeText(getApplicationContext(), "Detalles " +noticias.get(position).getTitulo(), Toast.LENGTH_LONG).show();

    }


    /**
     * Metodo setFragment
     * Metodo en cual se compara que menu del NavigationDrawer fue seleccionado y lo manda para el fragmento 
     * correspondiente
     * @param position la posicion del menu seleccionado
     */
    public void setFragment(int position) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        if(utilidades.verificarConexion(this) == false) {
            Toast.makeText(getApplicationContext(), "Sin internet ", Toast.LENGTH_LONG).show();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            SinConexionFragment sinConexionFragment = new SinConexionFragment();
            fragmentTransaction.replace(R.id.fragment, sinConexionFragment);
            fragmentTransaction.commit();

        }

        else {
            switch (position) {
                case 0:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    HomeAplicacionFragment homeFragment = new HomeAplicacionFragment();
                    fragmentTransaction.replace(R.id.fragment, homeFragment);
                    fragmentTransaction.commit();
                    break;
                case 1:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    ListaDeNoticiasFragments noticiasFragments = new ListaDeNoticiasFragments();
                    noticiasFragments.setNoticias(noticias);
                    fragmentTransaction.replace(R.id.fragment, noticiasFragments);
                    fragmentTransaction.commit();
                    break;
                case 2:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    DirectorioDependenciasFragment directorioDependenciasFragment = new DirectorioDependenciasFragment();
                    fragmentTransaction.replace(R.id.fragment, directorioDependenciasFragment);
                    fragmentTransaction.commit();
                    break;
                case 3:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    SugerenciasFragment sugerenciasFragment = new SugerenciasFragment();
                    fragmentTransaction.replace(R.id.fragment, sugerenciasFragment);
                    fragmentTransaction.commit();
                    break;
                case 4:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    DetalleNoticiaFragment detalleNoticiaFragment = new DetalleNoticiaFragment();
                    //detalleNoticiaFragment.mostrarNoticia(noticias.get(position));
                    fragmentTransaction.replace(R.id.fragment, detalleNoticiaFragment);
                    fragmentTransaction.commit();
                    break;

            }
        }
    }

    /**
     * Metodo verificarConexion()
     * Metodo correspondiente para verificar la conexion a la red
     * y si no cuenta con ella mandar un mensaje de notificacion
     */
    public boolean verificarConexion(){
        ConnectivityManager ConnectionManager=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=ConnectionManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()==true )
        {
            return true;
        }
        else
        {
            return false;
        }


    }
}
