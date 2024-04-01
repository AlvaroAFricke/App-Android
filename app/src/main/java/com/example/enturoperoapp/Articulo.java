package com.example.enturoperoapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Articulo implements Parcelable {
    private int id;
    private int precio;

    public Articulo(int id, int precio) {
        this.id = id;
        this.precio = precio;
    }

    protected Articulo(Parcel in) {
        id = in.readInt();
        precio = in.readInt();
    }

    public static final Creator<Articulo> CREATOR = new Creator<Articulo>() {
        @Override
        public Articulo createFromParcel(Parcel in) {
            return new Articulo(in);
        }

        @Override
        public Articulo[] newArray(int size) {
            return new Articulo[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(precio);
    }
}
