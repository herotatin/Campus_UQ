package co.edu.uniquindio.android.electiva.campus_uq.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.edu.uniquindio.android.electiva.campus_uq.R;
import co.edu.uniquindio.android.electiva.campus_uq.activities.MainActivity;
import co.edu.uniquindio.android.electiva.campus_uq.util.Utilidades;
import co.edu.uniquindio.android.electiva.campus_uq.vo.Noticia;
import io.fabric.sdk.android.Fabric;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetalleNoticiaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetalleNoticiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleNoticiaFragment extends Fragment implements View.OnClickListener, View.OnCreateContextMenuListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Noticia noticia;
    private TextView titulo;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Utilidades utilidades;

    @BindView(R.id.btn_hacer_tuit)
    protected Button btnCompartirTwitter;

    @BindView(R.id.twitter_login_button)
    protected TwitterLoginButton btnloginTwitter;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        utilidades = new Utilidades();
        View v = inflater.inflate(R.layout.fragment_detalle_noticia, container, false);
        ButterKnife.bind(this, v);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(utilidades.TWITTER_KEY, utilidades.TWITTER_SECRET);
        Fabric.with(super.getActivity(), new TwitterCore(authConfig), new TweetComposer());



        // Inflate the layout for this fragment


        btnCompartirTwitter.setOnClickListener(this);
        btnloginTwitter.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                TwitterSession session = result.data;
                String msg = "@" + session.getUserName() + " logged in! (#" +
                        session.getUserId() + ")";
                Utilidades.mostrarMensajeConsola(msg);
                btnloginTwitter.setVisibility(View.INVISIBLE);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.d("TwitterKit", "Login with Twitter failure", exception);
            }
        });


        return v;

    }



    @OnClick(R.id.btn_hacer_tuit)
    public void tuit(View view) {

        verifyLoginTw();
        if (TweetComposer.getInstance() != null)
        {

        }

    }

    /**
     * Metodo onButtonPressed
     * Es el metodo donde se conecta el clic que da el usuario en la interfaz
     * con el fragment
     *
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
     *
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
     *
     * @param noticia recibe una noticia la cual se va a mostrar en la interfaz
     */
    public void mostrarNoticia(Noticia noticia) {
        this.noticia = noticia;
        titulo = (TextView) getView().findViewById(R.id.titulo_de_detalle_noticia);
        titulo.setText(noticia.getTitulo());


    }

    @Override
    public void onClick(View v) {

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

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) getActivity()).getTxtToldBar().setText(R.string.menu_item_news);
        ((MainActivity) getActivity()).setFragmentActual(4);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent
            data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) {
            Bundle bundle = data.getExtras();
            String fbData = bundle.toString();
            Utilidades.mostrarMensajeConsola("I am inside resultcode " + fbData);
        } else {
            Utilidades.mostrarMensajeConsola("I have no idea what is happening :( " + data.getExtras().toString());
        }
    }

    public boolean verifyLoginTw(){
        boolean ans = true;
        TwitterSession session = Twitter.getSessionManager().getActiveSession();
        if (session != null) {
            btnloginTwitter.setVisibility(View.INVISIBLE);
            //btnloginTwitter.setPressed(true);
            Utilidades.mostrarMensajeConsola("Sesion iniciada por:" + session.getUserName());
        } else {
            Utilidades.mostrarMensajeConsola("No se inició la sesión");
            ans = false;

        }

        return ans;
    }


}
