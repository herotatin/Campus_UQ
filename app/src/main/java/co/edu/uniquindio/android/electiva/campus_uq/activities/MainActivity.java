package co.edu.uniquindio.android.electiva.campus_uq.activities;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.edu.uniquindio.android.electiva.campus_uq.R;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.DetalleDeNoticiaFragmentLand;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.DetalleDeSugerenciaFragment;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.DetalleNoticiaFragment;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.DirectorioDependenciasFragment;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.DirectorioPorDependenciaFragment;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.HomeAplicacionFragment;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.ListaDeNoticiasFragments;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.SinConexionFragment;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.SugerenciasFragment;
import co.edu.uniquindio.android.electiva.campus_uq.util.CustomTypefaceSpan;
import co.edu.uniquindio.android.electiva.campus_uq.util.Utilidades;
import co.edu.uniquindio.android.electiva.campus_uq.vo.Dependencia;
import co.edu.uniquindio.android.electiva.campus_uq.vo.Noticia;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements ListaDeNoticiasFragments.OnNoticiaSeleccionadaListener, DirectorioDependenciasFragment.OnDependenciaSeleccionadaListener,DirectorioPorDependenciaFragment.OnTelefonoSeleccionadoListener{

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.



    // Atributos  de la Actividad
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBar actionBar;

    TextView txtToolbar;
    private ArrayList<Noticia> noticias = new ArrayList<>();
    private ArrayList<Dependencia> dependencias = new ArrayList<>();
    private Utilidades utilidades;
    private Typeface font;
    private int fragmentActual = 0;
    private Menu menuMain;

    private CallbackManager  callbackManager;
    private ShareDialog shareDialog;
    private DetalleNoticiaFragment detalleNoticiaFragment;
    private DetalleDeNoticiaFragmentLand detalleDeNoticiaFragmentLand;


    public void setFragmentActual(int fragmentActual) {
        this.fragmentActual = fragmentActual;
        verificarMenuItems();
    }


    /**
     * Metodo onCreate
     * Es el metodo que se invoca cuando el sistema crea la actividad, se inicializan los
     * componentes basicos de la actividad.
     *
     * @param savedInstanceState informacion actual que se encuentra guardada de la actividad
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(utilidades.TWITTER_KEY, utilidades.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        Utilidades.obtenerLenguaje(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);



        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/korinabold.ttf");

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

        noticias.add(new Noticia("Esta noticia", "25 Enero de 2016", "imagen", "Noticia1"));
        noticias.add(new Noticia("Esta noticia", "25 Enero de 2016", "imagen", "Noticia2"));
        noticias.add(new Noticia("Esta noticia", "25 Enero de 2016", "imagen", "Noticia3"));
        noticias.add(new Noticia("Esta noticia", "25 Enero de 2016", "imagen", "Noticia4"));
        noticias.add(new Noticia("Esta noticia", "25 Enero de 2016", "imagen", "Noticia5"));
        noticias.add(new Noticia("Esta noticia", "25 Enero de 2016", "imagen", "Noticia6"));
        noticias.add(new Noticia("Esta noticia", "25 Enero de 2016", "imagen", "Noticia7"));
        noticias.add(new Noticia("Esta noticia", "25 Enero de 2016", "imagen", "Noticia8"));



        dependencias.add(new Dependencia("Rectoria","Bloque Administrativo")) ;
        dependencias.add(new Dependencia("Vicerrectoria","Bloque Administrativo")) ;
        dependencias.add(new Dependencia("Vicerrectoria Academica","Bloque Administrativo")) ;


        utilidades = new Utilidades();


        TextView txt = (TextView) findViewById(R.id.toolbar_title);
        txt.setTypeface(font);

        setFontMenu();


        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }
            @Override
            public void onCancel() {
                // App code
            }
            @Override
            public void onError(FacebookException exception) {
                // App code
            } });

        shareDialog = new ShareDialog(this);

    }

    /**
     * Metodo onCreateOptionsMenu
     * Metodo donde se especifican el menu de opciones que va a tener la actividad
     *
     * @param menu le ingresa por parametro el menu de la actividad
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        this.menuMain = menu;
        tintAllIcons(menuMain, Color.WHITE);
        setFragment(0);
        verificarMenuItems();
        return true;
    }




    /**
     * Metodo onOptionsItemSelected
     * Metodo para identificar el menu seleccionado  Puede identificar el elemento
     * llamando GetItemID () , que devuelve el identificador único para el elemento de menú
     *
     * @param item elemento identificado
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.menu_item_share:
                View menuItemView = findViewById(R.id.menu_item_share);
                PopupMenu popupMenu = new PopupMenu(this,menuItemView);
                //popupMenu.inflate(R.menu.menu_share);
                popupMenu.show();
                return true;
            case R.id.menu_item_send:
                ((DetalleDeSugerenciaFragment) getSupportFragmentManager().findFragmentById(R.id.fragment)).setTextTxtSug("");
                Toast.makeText(this, R.string.review_message, Toast.LENGTH_LONG).show();
                return true;
            case R.id.share_facebook:
                verifyAndShareFace();
                return true;
            case R.id.share_twitter:
                try {
                    URL url = new URL("https://www.youtube.com/watch?v=zSWdZVtXT7E");
                    TweetComposer.Builder builder = new
                            TweetComposer.Builder(this)
                            .text("fr")
                            .url(url);
                    builder.show();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.share_google:
                shareIt("com.facebook.katana");
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo setupNavigationDrawerContent
     * Metodo el cual recibe la navegacion del Drawer y donde se encuentran
     * todas las opciones de este y cual es seleccionada para asi llamar
     * al fragmento correspondiente
     *
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
                                drawerLayout.closeDrawer(GravityCompat.START);
                                fragmentActual = 0;
                                setFragment(fragmentActual);
                                return true;
                            case R.id.item_navigation_drawer_news:
                                menuItem.setChecked(true);
                                fragmentActual = 1;
                                setFragment(fragmentActual);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_directory:
                                menuItem.setChecked(true);
                                fragmentActual = 2;
                                setFragment(fragmentActual);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_review:
                                menuItem.setChecked(true);
                                fragmentActual = 3;
                                setFragment(fragmentActual);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_languaje:
                                Toast.makeText(getApplicationContext(), "Cambio de lenguaje", Toast.LENGTH_LONG).show();
                                fragmentActual = 0;
                                setFragment(fragmentActual);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                Utilidades.cambiarIdioma(getBaseContext());
                                Intent intent2 = getIntent();
                                finish();
                                startActivity(intent2);
                                return true;
                            case R.id.item_navigation_drawer_page:
                                drawerLayout.closeDrawer(GravityCompat.START);
                                menuItem.setChecked(true);
//                                Uri uri = Uri.parse("http://www.uniquindio.edu.co"); // missing 'http://' will cause crashed
//                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                                startActivity(intent);
                                Intent intent = new Intent(Intent.ACTION_SEND);
                                intent.setType("text/plain");
                                intent.putExtra(Intent.EXTRA_TEXT, "El mejor blog de android http://javaheros.blogspot.pe/");
                                intent.setPackage("com.facebook.katana");
                                startActivity(intent);
                                return true;

                        }
                        return true;
                    }
                });
    }



    /**
     * Metodo setFragment
     * Metodo en cual se compara que menu del NavigationDrawer fue seleccionado y lo manda para el fragmento
     * correspondiente
     *
     * @param position la posicion del menu seleccionado
     */
    public void setFragment(int position) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;

        if (utilidades.verificarConexion(this) == false) {
            Toast.makeText(getApplicationContext(), "Sin internet ", Toast.LENGTH_LONG).show();
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            SinConexionFragment sinConexionFragment = new SinConexionFragment();
            fragmentTransaction.replace(R.id.fragment, sinConexionFragment);
            fragmentTransaction.commit();

        } else {

            verificarMenuItems();
            switch (position) {
                case 0:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    HomeAplicacionFragment homeFragment = new HomeAplicacionFragment();
                    fragmentTransaction.replace(R.id.fragment, homeFragment).addToBackStack(null);
                    fragmentTransaction.commit();
                    break;
                case 1:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    ListaDeNoticiasFragments noticiasFragments = new ListaDeNoticiasFragments();
                    noticiasFragments.setNoticias(noticias);
                    fragmentTransaction.replace(R.id.fragment, noticiasFragments).addToBackStack(null);
                    fragmentTransaction.commit();
                    break;
                case 2:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    DirectorioDependenciasFragment directorioDependenciasFragment = new DirectorioDependenciasFragment();
                    fragmentTransaction.replace(R.id.fragment, directorioDependenciasFragment).addToBackStack(null);
                    fragmentTransaction.commit();
                    break;
                case 3:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    SugerenciasFragment sugerenciasFragment = new SugerenciasFragment();
                    fragmentTransaction.replace(R.id.fragment, sugerenciasFragment).addToBackStack(null);
                    fragmentTransaction.commit();
                    break;
                case 4:

                    WindowManager wm = getWindowManager();
                    Display d = wm.getDefaultDisplay();

                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    if (d.getRotation() == Surface.ROTATION_90){

                        detalleDeNoticiaFragmentLand = new DetalleDeNoticiaFragmentLand();
                        fragmentTransaction.replace(R.id.fragment, detalleDeNoticiaFragmentLand).addToBackStack(null);
                        fragmentTransaction.commit();

                    }else {

                        detalleNoticiaFragment = new DetalleNoticiaFragment();
                        fragmentTransaction.replace(R.id.fragment, detalleNoticiaFragment).addToBackStack(null);
                        fragmentTransaction.commit();
                    }

                    break;
                case 5:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    DetalleDeSugerenciaFragment detalleDeSugerenciaFragment = new DetalleDeSugerenciaFragment();
                    fragmentTransaction.replace(R.id.fragment, detalleDeSugerenciaFragment).addToBackStack(null);
                    fragmentTransaction.commit();
                    break;
                case 6:
                    fragmentManager = getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction();
                    DirectorioPorDependenciaFragment directorioPorDependenciaFragment = new DirectorioPorDependenciaFragment();
                    fragmentTransaction.replace(R.id.fragment, directorioPorDependenciaFragment).addToBackStack(null);
                    fragmentTransaction.commit();
                    break;


            }
        }


    }

    public TextView getTxtToldBar() {
        return txtToolbar;
    }
    /**
     * Metodo setFontMenu
     * Metodo en el cual se le da la fuente al menu 
     */ 
    public void setFontMenu() {
        NavigationView navView = (NavigationView) findViewById(R.id.navigation_view);
        Menu m = navView.getMenu();
        for (int i = 0; i < m.size(); i++) {
            MenuItem mi = m.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
    }
    /**
     * Metodo applyFontToMenuItem
     */ 
    private void applyFontToMenuItem(MenuItem mi) {

        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);

    }
    /**
     * Metodo shareIt
     * Metodo que sirve para realizar una conexion
     * y tranferir
     */ 
    private void shareIt(String s) {
//sharing implementation here

        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "El mejor blog de android http://javaheros.blogspot.pe/");
        intent.setPackage("com.facebook.katana");
        this.startActivity(intent);

    }
    /**
     * Metodo verifyAndShareFace
     * Metodo en el cual se verifica que el usuario tenga 
     * la session activa en el facebook
     */ 
    public void verifyAndShareFace(){
        if(LoginManager.getInstance() == null)
        {
            LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile", "user_friends"));
        }

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            if (ShareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentTitle("UQ RULES")
                        .setContentUrl(Uri.parse("https://www.youtube.com/embed/zSWdZVtXT7E")) .setContentDescription("Descripción")
                        .build();
                shareDialog.show(content);
            }
        }
    }


    /**
     * Metodo  verifyAndShareTw
     * metodo en el cual se verifica que el usuario
     * tenga la session activa en twitter
     */ 
    public void verifyAndShareTw(MenuView.ItemView itemView){


        TwitterSession session = Twitter.getSessionManager().getActiveSession();
        if( session == null ){


        }else {
            Utilidades.mostrarMensajeConsola("No se inició la sesión");
        }
    }
    /**
     * Metodo onActivityResult
     * Cuando el usuario se realiza con la actividad
     * y vuelve posterior, el sistema llama metodo de su actividad onActivityResult (). 
     * Este metodo incluye tres argumentos:
     */ 
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        detalleNoticiaFragment.onActivityResult(requestCode, resultCode,
                data); }


    /**
     * metodo share
     * metodo en el cual se le da las referencias a lo que se va a 
     * igresar o visualizar en la app
     */ 
    public void share(String nameApp, String imagePath, String message) {
        try {
            List<Intent> targetedShareIntents = new ArrayList<Intent>();
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("image/jpeg");
            List<ResolveInfo> resInfo = getPackageManager()
                    .queryIntentActivities(share, 0);
            if (!resInfo.isEmpty()) {
                for (ResolveInfo info : resInfo) {
                    Intent targetedShare = new Intent(
                            android.content.Intent.ACTION_SEND);
                    targetedShare.setType("image/jpeg"); // put here your mime
                    // type
                    if (info.activityInfo.packageName.toLowerCase().contains(
                            nameApp)
                            || info.activityInfo.name.toLowerCase().contains(
                            nameApp)) {
                        targetedShare.putExtra(Intent.EXTRA_SUBJECT,
                                "Sample Photo");
                        targetedShare.putExtra(Intent.EXTRA_TEXT, message);
                        //targetedShare.putExtra(Intent.EXTRA_STREAM,
                               // Uri.fromFile(new File(imagePath)));
                        targetedShare.setPackage(info.activityInfo.packageName);
                        targetedShareIntents.add(targetedShare);
                    }
                }
                Intent chooserIntent = Intent.createChooser(
                        targetedShareIntents.remove(0), "Select app to share");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                        targetedShareIntents.toArray(new Parcelable[] {}));
                startActivity(chooserIntent);
            }
        } catch (Exception e) {
            Log.v("VM",
                    "Exception while sending image on " + nameApp + " "
                            + e.getMessage());
        }
    }
    /**
     * Metodo verificarMenuItem
     * en este metodo se verifican los items que tiene el menu 
     * de ka app
     */ 
    private void verificarMenuItems() {
        if (menuMain != null) {
            for (int i = 0; i < menuMain.size(); i++) {
                MenuItem item = menuMain.getItem(i);

                if (fragmentActual == 5 && item.getItemId() == R.id.menu_item_send) {
                    item.setVisible(true);
                } else if (fragmentActual == 4 && item.getItemId() == R.id.menu_item_share) {
                    item.setVisible(true);
                } else
                    item.setVisible(false);

            }
        }
    }
    /**
     * Metodo tintAllIcons
     * Metodo donde se le da el color a todos los inonos del menu
     */ 
    public void tintAllIcons(Menu menu, final int color) {
        for (int i = 0; i < menu.size(); ++i) {
            final MenuItem item = menu.getItem(i);
            tintMenuItemIcon(color, item);

        }
    }
    /**
     * Metodo tintMenuItemIcon
     * se utiliza para darles los iconos del menu tint
     * el color de estos iconos
     */ 
    private void tintMenuItemIcon(int color, MenuItem item) {
        final Drawable drawable = item.getIcon();
        if (drawable != null) {
            final Drawable wrapped = DrawableCompat.wrap(drawable);
            drawable.mutate();
            DrawableCompat.setTint(wrapped, color);
            item.setIcon(drawable);
        }
    }


    /**
     * Metodo onNoticiaSeleccionada
     * Metodo Metodo en el cual la actividad escoge las noticias selecionadas
     * @param position la posicion de la noticia que se selecciona
     */
    @Override
    public void onNoticiaSeleccionada(int position) {
        fragmentActual = 4;
        setFragment(fragmentActual);

    }
    /**
     * Metodo onDependenciaSeleccionada
     * Se le ingresa la posicion de la dependencia y  
     * se le da el fragmento actual
     */ 
    @Override
    public void onDependenciaSeleccionada(int position) {
        fragmentActual = 6;
        setFragment(fragmentActual);
    }
    /**
        *Metodo onTelefonoSeleccionado
        *Metodo que se le ingresa la posicion del telefono que se va a llamar
        *Y realiza la llamada
    */

    @Override
    public void onTelefonoSeleccionado(int position) {
        Toast.makeText(this, "Llamando", Toast.LENGTH_LONG).show();
        Intent i = new Intent(android.content.Intent.ACTION_DIAL,
                Uri.parse("tel:+573003730222")); //
        startActivity(i);
    }
}
