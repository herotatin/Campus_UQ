package co.edu.uniquindio.android.electiva.campus_uq.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JoseOmar on 1/05/16.
 */
public class Noticia implements Parcelable {

    // Atributos de la clase noticia

    private String contenido;
    private String fecha;
    private String imagen;
    private String titulo;

    /**
     * Metodo Metodo constructor de la clase noticia
     * Se inicializan todos los atributos de esta clase
     *
     */

    public Noticia(String contenido, String fecha, String imagen,String titulo) {
        this.contenido = contenido;
        this.fecha = fecha;
        this.imagen = imagen;
        this.titulo = titulo;
    }

    protected Noticia(Parcel in) {
        contenido = in.readString();
        imagen = in.readString();
    }

    public static final Creator<Noticia> CREATOR = new Creator<Noticia>() {
        @Override
        public Noticia createFromParcel(Parcel in) {
            return new Noticia(in);
        }
        /**
         * Metodo para crar una arreglo de noticias
         */ 
        @Override
        public Noticia[] newArray(int size) {
            return new Noticia[size];
        }
    };

    /**
     * Metodos get de la clase noticia
     */

    public String getContenido() {
        return contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }
    /**
     * Metodos set de la clase noticia
     */
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    /**
     * Metodo writeToParcel
     * metodo en cual se carga el contenido de la noticia
     * y su imagen
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contenido);
        dest.writeString(imagen);
    }
}
