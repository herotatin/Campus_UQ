package co.edu.uniquindio.android.electiva.campus_uq.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.campus_uq.R;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.DirectorioDependenciasFragment;
import co.edu.uniquindio.android.electiva.campus_uq.vo.Dependencia;

/**
 * Created by JoseOmar on 1/05/16.
 */
public class AdaptadorDeDependencias extends RecyclerView.Adapter<AdaptadorDeDependencias.DependenciaViewHolder> {


    private ArrayList<Dependencia> dependencias;
    private static OnClickAdaptadorDependencia listener;



    public AdaptadorDeDependencias(ArrayList<Dependencia> dependencias, DirectorioDependenciasFragment listaDepenFragments) {

        this.dependencias = dependencias;
        listener = (OnClickAdaptadorDependencia) listaDepenFragments;
    }


    @Override
    public DependenciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_de_dependencia, parent, false);
        DependenciaViewHolder dependenciaVH = new DependenciaViewHolder(itemView);
        return dependenciaVH;
    }

    @Override
    public void onBindViewHolder(DependenciaViewHolder holder, int position) {
        Dependencia depencia= dependencias.get(position);
        holder.binDependencia(depencia);
    }

    @Override
    public int getItemCount() {
        return dependencias.size();
    }

    public interface OnClickAdaptadorDependencia{
        public void onClickPosition(int pos);
    }

    public static class DependenciaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        protected TextView txtNombre;


        protected TextView txtUbicacion;


        public DependenciaViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNombre = (TextView) itemView.findViewById(R.id.txt_nombre_dep);
            txtUbicacion= (TextView) itemView.findViewById(R.id.txt_ubicacion_dep);


        }

        public void binDependencia(Dependencia d) {
            txtNombre.setText(d.getNombre());
            txtUbicacion.setText(d.getUbicacion());
        }

        @Override
        public void onClick(View v) {
            Log.d("TAG", "Element " + getAdapterPosition() + " clicked. " + txtNombre.getText());
            listener.onClickPosition(getAdapterPosition());
        }


    }
}