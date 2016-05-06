package co.edu.uniquindio.android.electiva.campus_uq.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.edu.uniquindio.android.electiva.campus_uq.R;
import co.edu.uniquindio.android.electiva.campus_uq.vo.Noticia;

/**
 * Esta es DetalleNoticiaFragment del proyecto campus_UQ de la electiva de moviles
 * @author: Jose Omar Colorado y Jesus Alberto Onofre
 */
/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetalleNoticiaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetalleNoticiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleNoticiaFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    // atributos de DetalleNoticiaFragment
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Noticia noticia;
    private TextView titulo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DetalleNoticiaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleNoticiaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleNoticiaFragment newInstance(String param1, String param2) {
        DetalleNoticiaFragment fragment = new DetalleNoticiaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
     * Metodo onCreate
     * Es el metodo que se invoca cuando el sistema crea la actividad, se inicializan los
     * componentes basicos de la actividad.
     * @param sabedInstanceState informacion actual que se encuentra guardada de la actividad
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_noticia, container, false);
    }

    /**
     * Metodo onButtonPressed
     * Es el metodo donde se conecta el clic que da el usuario en la interfaz 
     * con el fragment 
     * @param uri 
     */
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    /**
     * Metodo onAttach
     * Metodo que Se llama cuando un fragmento se une primero en su contexto. 
     * @param context contexto de la actividad
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
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
     * Metodo mostrarNoticia
     * Metodo donde se carga la noticia que se va a mostrar en el fragment
     * @param noticia recibe una noticia la cual se va a mostrar en la interfaz
     */
    public void mostrarNoticia (Noticia noticia) {
        this.noticia = noticia;
        titulo = (TextView) getView().findViewById(R.id.titulo_de_detalle_noticia);
        titulo.setText(noticia.getTitulo());

    }
    @Override
    public void onClick(View v) {

    }
    /**
    * interface OnFragmentIteractionListener
    * @author: Jose Omar Colorado y Jesus Alberto Onofre
    */
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
}
