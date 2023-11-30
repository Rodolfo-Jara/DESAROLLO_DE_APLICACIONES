package com.sistema.trailers.modelo;
public class ItemCarrito {
    private Productos producto;
    private int cantidad;

    public ItemCarrito(Productos producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // Otros métodos según tus necesidades

    public Productos getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
