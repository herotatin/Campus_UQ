package co.edu.uniquindio.android.electiva.campus_uq.fragments;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.campus_uq.R;
import co.edu.uniquindio.android.electiva.campus_uq.activities.MainActivity;
import co.edu.uniquindio.android.electiva.campus_uq.util.AdaptadorDeDependencias;
import co.edu.uniquindio.android.electiva.campus_uq.vo.Dependencia;

/**
 * Esta es DirectorioDependenciasFragment del proyecto campus_UQ de la electiva de moviles
 * @author: Jose Omar Colorado y Jesus Alberto Onofre
 */
/**

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DirectorioDependenciasFragment} interface
 * to handle interaction events.
 * Use the {@link DirectorioDependenciasFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class DirectorioDependenciasFragment extends Fragment implements AdaptadorDeDependencias.OnClickAdaptadorDependencia{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView listadoDependencias;
    private AdaptadorDeDependencias adaptador;

    private ArrayList<Dependencia> dependencias = new ArrayList<>();
    private  OnDependenciaSeleccionadaListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DirectorioDependenciasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DirectorioDependenciasFragment newInstance(String param1, String param2) {
        DirectorioDependenciasFragment fragment = new DirectorioDependenciasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    /**
     * Metodo Metodo constructor
     */
    public DirectorioDependenciasFragment() {
        // Required empty public constructor
    }
    /**
     * Metodo onCreate
     * Es el metodo que se invoca cuando el sistema crea la actividad, se inicializan los
     * componentes basicos de la actividad.
     *
     */
    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity)getActivity()).getTxtToldBar().setText(R.string.menu_item_directory);
        ((MainActivity)getActivity()).setFragmentActual(2);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    /**
     * Metodo onCreateView
     * metodo que crea y devuelve la jerarquía vista asociada con el fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_directorio_dependencias, container, false);
    }


    /**
     * Metodo onActivityCreated
     * dice el fragmento que su actividad ha completado su propia Activity.onCreate () .
     */
    @Override
    public void onActivityCreated(@Nullable Bundle SavedInstance) {

        super.onCreate(SavedInstance);



        dependencias.add(new Dependencia("Rectoria","Bloque Administrativo")) ;
        dependencias.add(new Dependencia("Vicerrectoria","Bloque Administrativo")) ;
        dependencias.add(new Dependencia("Vicerrectoria Academica","Bloque Administrativo")) ;


        listadoDependencias = (RecyclerView) getView().findViewById(R.id.RecViewDirectory);
        adaptador = new AdaptadorDeDependencias(dependencias,this);
        listadoDependencias.setAdapter(adaptador);
        listadoDependencias.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
    /**
     * Metodo onButtonPressed
     * Es el metodo donde se conecta el clic que da el usuario en la interfaz 
     * con el fragment 
     * @param uri 
     */
    public void onButtonPressed(Uri uri) {

    }
    /**
     * Metodo onAttach
     * Metodo que Se llama cuando un fragmento se une primero en su contexto. 
     * @param context contexto de la actividad
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                mListener = (OnDependenciaSeleccionadaListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString() + " debe implementar la interfaz OnPeliculaSeleccionadaListener");
            }
        }
    }
    /**
     * Metodo onDetach
     * Metodo llama inmediatamente antes de la fragmento ya no estar asociado con su actividad
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClickPosition(int pos) {
        mListener.onDependenciaSeleccionada(pos);
    }

    public interface OnDependenciaSeleccionadaListener {
        void onDependenciaSeleccionada(int position);
    }
}
