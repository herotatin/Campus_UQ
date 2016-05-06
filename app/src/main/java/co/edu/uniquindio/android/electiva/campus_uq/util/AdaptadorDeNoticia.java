package co.edu.uniquindio.android.electiva.campus_uq.util;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import co.edu.uniquindio.android.electiva.campus_uq.R;
import co.edu.uniquindio.android.electiva.campus_uq.fragments.ListaDeNoticiasFragments;
import co.edu.uniquindio.android.electiva.campus_uq.vo.Noticia;

/**
 * Esta es la actividad principal del proyecto campus_UQ de la electiva de moviles
 * @author: Jose Omar Colorado y Jesus Alberto Onofre
 */
public class AdaptadorDeNoticia extends RecyclerView.Adapter<AdaptadorDeNoticia.NoticiaViewHolder> {

    //Atributos del adaptadorDeNoticia
    private ArrayList<Noticia> noticias;
    private static OnClickAdaptadorDeNoticia listener;

    /**
     * Constructor AdaptadorDeNoticia
     * @param noticia este parametro es una lista de noticias
     * @param listaDeNoticiasFragments Son todas las noticias en el fragmento
     */
    public AdaptadorDeNoticia(ArrayList<Noticia> noticias, ListaDeNoticiasFragments listaDeNoticiasFragments) {

        this.noticias = noticias;
        listener = (OnClickAdaptadorDeNoticia) listaDeNoticiasFragments;
    }

    /**
     * Metodo onCreateViewHolder
     * Metodo para para crear un nuevo RecyclerView.ViewHolder e inicializa 
     * algunos campos privados para ser usados ​​por RecyclerView.
     * @param parent 
     * @param viewType 
     */
    @Override
    public NoticiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_de_noticia, parent, false);
        NoticiaViewHolder noticiaVH = new NoticiaViewHolder(itemView);
        return noticiaVH;
    }
    /**
        *Metodo onBindViewHolder
        *Metodo para para actualizar los RecyclerView.ViewHolder contenidos con
        *el elemento en la posición dada y también establece algunos campos privados para ser usados ​​por RecyclerView.
        *@param holder Recibe un holder
        *@param position la posicion dada
    */
    @Override
    public void onBindViewHolder(NoticiaViewHolder holder, int position) {
        Noticia noticia = noticias.get(position);
        holder.binNoticia(noticia);
    }
    /**
     * Metodo getItemCount
     * Metodo Devuelve el número total de elementos en el 
     * conjunto de datos de retención por el adaptador.
     */
    @Override
    public int getItemCount() {
        return noticias.size();
    }
    /**
     * interface OnClickAdaptadorDeNotici
     */
    public interface OnClickAdaptadorDeNoticia{
    /**
     * Metodo onClickPosition ingresala posicion donde se da clic en la noticia
     */
        public void onClickPosition(int pos);
    }
    /**
     * class NoticiaViewHolder
     */
    public static class NoticiaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * Atributos de esta clase
         */ 
        private TextView txtTitulo;
        private TextView txtAnio;
        /**
         * Metodo constructor de la clase NoticiaViewHolder
         * 
         */ 
        public NoticiaViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtTitulo = (TextView) itemView.findViewById(R.id.titulo);
            txtAnio = (TextView) itemView.findViewById(R.id.anio);
        }
        /**
         * Metodo binNoticia
         * @param n noticia que ingresa al metodo
         */ 
        public void binNoticia(Noticia n) {
            txtTitulo.setText(n.getTitulo());
            txtAnio.setText(n.getFecha());
        }
        /**
         * Metodo onClick
         * Metodo que nos dice el clic que se dio sobre que noticia
         * @param v View que ingresa al metodo
         */ 
        @Override
        public void onClick(View v) {
            Log.d("TAG", "Element " + getAdapterPosition() + " clicked. " + txtTitulo.getText());
            listener.onClickPosition(getAdapterPosition());
        }


    }
}
