package tds.appMusic.modelo;

public interface IDescuento {
    float getPrecioFinal(float precio);
    float getDescuento();
    String getTextoDescuento();
}
