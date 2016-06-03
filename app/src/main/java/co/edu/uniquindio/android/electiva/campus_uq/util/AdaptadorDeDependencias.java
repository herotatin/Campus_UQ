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

    // Atributos de esta clase util
    private ArrayList<Dependencia> dependencias;
    private static OnClickAdaptadorDependencia listener;


    //Metodo constructor de la clase util AdaptadorDeDependencias
    public AdaptadorDeDependencias(ArrayList<Dependencia> dependencias, DirectorioDependenciasFragment listaDepenFragments) {

        this.dependencias = dependencias;
        listener = (OnClickAdaptadorDependencia) listaDepenFragments;
    }

    /**
     * Este método llama onCreateViewHolder (ViewGroup, int) para 
     * crear un nuevo RecyclerView.ViewHolder e inicializa algunos campos
     * privados para ser usados por RecyclerView.
     */ 
    @Override
    public DependenciaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_de_dependencia, parent, false);
        DependenciaViewHolder dependenciaVH = new DependenciaViewHolder(itemView);
        return dependenciaVH;
    }
    /**
     * Este metodo llama internamente onBindViewHolder (ViewHolder, int) para a
     * ctualizar el contenido RecyclerView.ViewHolder 
     * con el elemento en la posición dada y también establece
     * algunos campos privados para ser usados ​​por RecyclerView.
     */ 
    @Override
    public void onBindViewHolder(DependenciaViewHolder holder, int position) {
        Dependencia depencia= dependencias.get(position);
        holder.binDependencia(depencia);
    }
    /**
     * Metodo getItemCount
     * Devuelve el número total de elementos en el conjunto de datos de retención por el adaptador.
     */ 
    @Override
    public int getItemCount() {
        return dependencias.size();
    }
    /**
     * metodo OnClickAdaptadorDependencia
     * Metodo en el cual se le da el click a la dependencia
     */ 
    public interface OnClickAdaptadorDependencia{
        public void onClickPosition(int pos);
    }
    // Clase estatica DependenciaViewHolder que extiende de RecyclerView.ViewHolder
    public static class DependenciaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            //Atributos protegidos de esta clase
        protected TextView txtNombre;


        protected TextView txtUbicacion;


        public DependenciaViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtNombre = (TextView) itemView.findViewById(R.id.txt_nombre_dep);
            txtUbicacion= (TextView) itemView.findViewById(R.id.txt_ubicacion_dep);


        }
        /**
         * metodo inDependencia(
         * En cual se ectrae el nombre de la dependecia 
         */ 
        public void binDependencia(Dependencia d) {
            txtNombre.setText(d.getNombre());
            txtUbicacion.setText(d.getUbicacion());
        }
        /**
         * Metodo onClick
         * Metodo el cual recibe un view y le da la poscion del 
         * elemento que se le da click
         */
        @Override
        public void onClick(View v) {
            Log.d("TAG", "Element " + getAdapterPosition() + " clicked. " + txtNombre.getText());
            listener.onClickPosition(getAdapterPosition());
        }


    }
}
