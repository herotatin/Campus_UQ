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
 * Created by JoseOmar on 1/05/16.
 */
public class AdaptadorDeNoticia extends RecyclerView.Adapter<AdaptadorDeNoticia.NoticiaViewHolder> {


    private ArrayList<Noticia> noticias;
    private static OnClickAdaptadorDeNoticia listener;


    public AdaptadorDeNoticia(ArrayList<Noticia> noticias, ListaDeNoticiasFragments listaDeNoticiasFragments) {

        this.noticias = noticias;
        listener = (OnClickAdaptadorDeNoticia) listaDeNoticiasFragments;
    }


    @Override
    public NoticiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resumen_de_noticia, parent, false);
        NoticiaViewHolder noticiaVH = new NoticiaViewHolder(itemView);
        return noticiaVH;
    }

    @Override
    public void onBindViewHolder(NoticiaViewHolder holder, int position) {
        Noticia noticia = noticias.get(position);
        holder.binNoticia(noticia);
    }

    @Override
    public int getItemCount() {
        return noticias.size();
    }

    public interface OnClickAdaptadorDeNoticia{
        public void onClickPosition(int pos);
    }

    public static class NoticiaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtTitulo;
        private TextView txtAnio;

        public NoticiaViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtTitulo = (TextView) itemView.findViewById(R.id.titulo);
            txtAnio = (TextView) itemView.findViewById(R.id.anio);
        }

        public void binNoticia(Noticia n) {
            txtTitulo.setText(n.getTitulo());
            txtAnio.setText(n.getFecha());
        }

        @Override
        public void onClick(View v) {
            Log.d("TAG", "Element " + getAdapterPosition() + " clicked. " + txtTitulo.getText());
            listener.onClickPosition(getAdapterPosition());
        }


    }
}