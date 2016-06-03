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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.campus_uq.R;
import co.edu.uniquindio.android.electiva.campus_uq.activities.MainActivity;
import co.edu.uniquindio.android.electiva.campus_uq.util.AdaptadorDeNoticia;
import co.edu.uniquindio.android.electiva.campus_uq.vo.Noticia;
/**
 * Esta es DetalleNoticiaFragment del proyecto campus_UQ de la electiva de moviles
 * @author: Jose Omar Colorado y Jesus Alberto Onofre
 */
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaDeNoticiasFragments.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaDeNoticiasFragments#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaDeNoticiasFragments extends Fragment implements AdaptadorDeNoticia.OnClickAdaptadorDeNoticia{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView listadoNoticias;
    private ArrayList<Noticia> noticias;
    private AdaptadorDeNoticia adaptador;
    private OnNoticiaSeleccionadaListener listener;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public ListaDeNoticiasFragments() {
        // Required empty public constructor
    }

    public void setNoticias(ArrayList<Noticia> noticias) {
        this.noticias = noticias;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaDeNoticiasFragments.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaDeNoticiasFragments newInstance(String param1, String param2) {
        ListaDeNoticiasFragments fragment = new ListaDeNoticiasFragments();
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
        listadoNoticias = (RecyclerView) getView().findViewById(R.id.RecViewNews);
        adaptador = new AdaptadorDeNoticia(noticias,this);
        listadoNoticias.setAdapter(adaptador);
        listadoNoticias.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }
    /**
     * Metodo onCreateView
     * Es el metodo crea y devuelve la jerarquía vista asociada con el fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lista_de_noticias_fragments, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
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
                listener = (OnNoticiaSeleccionadaListener) activity;
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

    }
    /**
     * Metodo onClickPosition
     * Metodo donde se le da clic a una noticia 
     * @param pos posicion de la noticia seleccionada
     */
    @Override
    public void onClickPosition(int pos) {
        listener.onNoticiaSeleccionada(pos);
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
     * metodo onCreateOptionsMenu 
     * Inicializar el contenido del menú de opciones estándar de la activida
     */ 
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }
    
    /**
     * Metodo onOptionsItemSelected 
     * Este gancho se llama cada vez que se selecciona un elemento en el menú de opciones.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
    // interfaz OnNoticiaSeleccionadaListener 
    // en la cual le ingresa la posicion de la noticia seleccionada
    public interface OnNoticiaSeleccionadaListener {
        void onNoticiaSeleccionada(int position);
    }
    /**
     * Metodo onStart()
     * hace que el fragmento visible para el usuario (sobre la base de su actividad que contiene de ser iniciado).
     */ 
    @Override
    public void onStart() {
        super.onStart();
        //((MainActivity)getActivity()).getSupportActionBar().setTitle("Noticias");
        ((MainActivity)getActivity()).getTxtToldBar().setText(R.string.menu_item_news);
        ((MainActivity)getActivity()).setFragmentActual(1);
    }
}
