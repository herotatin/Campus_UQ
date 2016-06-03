package co.edu.uniquindio.android.electiva.campus_uq.vo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by JoseOmar on 6/05/16.
 */
public class Dependencia implements Parcelable{



    private String nombre;
    private String ubicacion;
    /**
     * Metodo constructor de la clase dependencia
     */ 
    public Dependencia(String ubicacion, String nombre) {
        this.ubicacion = ubicacion;
        this.nombre = nombre;
    }

    protected Dependencia(Parcel in) {
        nombre = in.readString();
        ubicacion = in.readString();
    }
    // Clase estatica Creator de dependencia
    public static final Creator<Dependencia> CREATOR = new Creator<Dependencia>() {
        @Override
        public Dependencia createFromParcel(Parcel in) {
            return new Dependencia(in);
        }
        /**
         * Metodo newArray
         * Metodo que nos da una arreglo de las dependencias
         */ 
        @Override
        public Dependencia[] newArray(int size) {
            return new Dependencia[size];
        }
    };
    //Metodo get de esta clase
    public String getUbicacion() {
        return ubicacion;
    }

    public String getNombre() {
        return nombre;
    }
    /**
     * Metodo describeContents
     * cada bit representa un tipo de objeto considera que 
     * tienen potencial importancia especial cuando se calcula.
     */ 
    @Override
    public int describeContents() {
        return 0;
    }
    /**
     * Metodo writeToParcel
     * Acoplar este objeto en un paquete
     */ 
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(ubicacion);
    }
}
