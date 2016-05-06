package co.edu.uniquindio.android.electiva.campus_uq.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import co.edu.uniquindio.android.electiva.campus_uq.R;
/**
 * Esta es la actividad Noticas del proyecto campus_UQ de la electiva de moviles
 * @author: Jose Omar Colorado y Jesus Alberto Onofre
 */
public class Noticias_Activity extends AppCompatActivity {
    /**
     * Metodo onCreate
     * Es el metodo que se invoca cuando el sistema crea la actividad, se inicializan los
     * componentes basicos de la actividad.
     * @param sabedInstanceState informacion actual que se encuentra guardada de la actividad
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias_);
    }
}
