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
import co.edu.uniquindio.android.electiva.campus_uq.util.AdaptadorDeTelefono;
import co.edu.uniquindio.android.electiva.campus_uq.vo.Dependencia;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DirectorioPorDependenciaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DirectorioPorDependenciaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DirectorioPorDependenciaFragment extends Fragment implements AdaptadorDeTelefono.OnClickAdaptadorTelefono {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Dependencia> dependencias = new ArrayList<>();
    private RecyclerView listadoDependencias;
    private AdaptadorDeTelefono adaptador;


    private OnFragmentInteractionListener mListener;
    private OnTelefonoSeleccionadoListener listener;

    public DirectorioPorDependenciaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DirectorioPorDependenciaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DirectorioPorDependenciaFragment newInstance(String param1, String param2) {
        DirectorioPorDependenciaFragment fragment = new DirectorioPorDependenciaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
     /**
     * Metodo onCreate
     * Es el metodo que se invoca cuando el sistema crea la actividad, se inicializan los
     * componentes basicos de la actividad.
     *
     * @param savedInstanceState informacion actual que se encuentra guardada de la actividad
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    /**
     * Metodo onActivityCreated
     * dice el fragmento que su actividad ha completado su propia Activity.onCreate () .
     */
    @Override
    public void onActivityCreated(@Nullable Bundle SavedInstance) {

        super.onCreate(SavedInstance);



        dependencias.add(new Dependencia("Rectoria1","31299878")) ;
        dependencias.add(new Dependencia("Vicerrectoria","Bloque Administrativo")) ;
        dependencias.add(new Dependencia("Vicerrectoria Academica","Bloque Administrativo")) ;


        listadoDependencias = (RecyclerView) getView().findViewById(R.id.RecViewDependencia);
       adaptador = new AdaptadorDeTelefono(dependencias,this);
       listadoDependencias.setAdapter(adaptador);
        listadoDependencias.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
      /**
     * Metodo onCreateView
     * crea y devuelve la jerarquía vista asociada con el fragmento.
     */ 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_directorio_por_dependencia, container, false);
    }
    /**
     * metodo onButtonPressed
     * Metodo en cual tenemos el boton precionado
     */ 
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    /**
     * Metodo onAttach
     * Metodo que Se llama cuando un fragmento se une primero en su contexto.
     *
     * @param context contexto de la actividad
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity;
        if (context instanceof Activity) {
            activity = (Activity) context;
            try {
                listener = (OnTelefonoSeleccionadoListener) activity;
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
    /**
     * Metodo onClickPosition
     * Metodo en que da la posicion del clic 
     * que hace el usuario
     */ 
    @Override
    public void onClickPosition(int pos) {
        listener.onTelefonoSeleccionado(pos);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    /**
     * Metodo mostrarNoticia
     * Metodo donde se carga la noticia que se va a mostrar en el fragment
     * @param dependencia recibe una noticia la cual se va a mostrar en la interfaz
     */
    public void mostrarNoticia(Dependencia dependencia) {
        //this.dependencias = dependencia;
        //titulo = (TextView) getView().findViewById(R.id.titulo_de_detalle_noticia);
        //titulo.setText(dependencia.getNombre());

    }
    /**
     * Metodo OnTelefonoSeleccionadoListener
     * Metodo en cual se da la posiccion del telefono 
     * seleccionado
     */ 
    public interface OnTelefonoSeleccionadoListener {
        void onTelefonoSeleccionado(int position);
    }
}
