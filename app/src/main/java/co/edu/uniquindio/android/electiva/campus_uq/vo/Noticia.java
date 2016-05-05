package co.edu.uniquindio.android.electiva.campus_uq.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JoseOmar on 1/05/16.
 */
public class Noticia implements Parcelable {

    private String contenido;
    private String fecha;
    private String imagen;
    private String titulo;

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

        @Override
        public Noticia[] newArray(int size) {
            return new Noticia[size];
        }
    };

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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contenido);
        dest.writeString(imagen);
    }
}
