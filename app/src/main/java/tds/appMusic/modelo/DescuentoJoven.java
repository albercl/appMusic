package tds.appMusic.modelo;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class DescuentoJoven implements IDescuento {
    private static final float DESCUENTO = 0.80f;

    @Override
    public float getPrecioFinal(float precio) {
        return precio * DESCUENTO;
    }

    @Override
    public float getDescuento() {
        return DESCUENTO;
    }

    @Override
    public String getTextoDescuento() {
        DecimalFormat df = new DecimalFormat("#");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return "Descuento del " + df.format(((1f - DESCUENTO) * 100f)) + "% (usuario menor de 25 años)";
    }
}
