package co.edu.uniquindio.android.electiva.campus_uq.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JoseOmar on 6/05/16.
 */
public class Dependencia implements Parcelable{



    private String nombre;
    private String ubicacion;

    public Dependencia(String ubicacion, String nombre) {
        this.ubicacion = ubicacion;
        this.nombre = nombre;
    }

    protected Dependencia(Parcel in) {
        nombre = in.readString();
        ubicacion = in.readString();
    }

    public static final Creator<Dependencia> CREATOR = new Creator<Dependencia>() {
        @Override
        public Dependencia createFromParcel(Parcel in) {
            return new Dependencia(in);
        }

        @Override
        public Dependencia[] newArray(int size) {
            return new Dependencia[size];
        }
    };

    public String getUbicacion() {
        return ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(ubicacion);
    }
}
