package co.edu.uniquindio.android.electiva.campus_uq.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.campus_uq.R;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.DirectorioPorDependenciaFragment;
import co.edu.uniquindio.android.electiva.campus_uq.vo.Dependencia;

/**
 * Created by JoseOmar on 1/05/16.
 */
public class AdaptadorDeTelefono extends RecyclerView.Adapter<AdaptadorDeTelefono.TelefonoViewHolder> {


    private ArrayList<Dependencia> dependencias;
    private static OnClickAdaptadorTelefono listener;



    public AdaptadorDeTelefono(ArrayList<Dependencia> dependencias, DirectorioPorDependenciaFragment listaDepenFragments) {

        this.dependencias = dependencias;
       listener = (OnClickAdaptadorTelefono) listaDepenFragments;
    }


    @Override
    public TelefonoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_de_telefono, parent, false);
        TelefonoViewHolder dependenciaVH = new TelefonoViewHolder(itemView);
        return dependenciaVH;
    }

    @Override
    public void onBindViewHolder(TelefonoViewHolder holder, int position) {
        Dependencia depencia= dependencias.get(position);
        holder.binDependencia(depencia);
    }

    @Override
    public int getItemCount() {
        return dependencias.size();
    }

    public interface OnClickAdaptadorTelefono{
        public void onClickPosition(int pos);
    }

    public static class TelefonoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        protected TextView txtNombre;
        protected TextView txtTelefono;


        public TelefonoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);



        }

        public void binDependencia(Dependencia d) {

        }

        @Override
        public void onClick(View v) {
            Log.d("TAG", "Element " + getAdapterPosition() + " clicked telefono" );
            listener.onClickPosition(getAdapterPosition());
        }


    }
}