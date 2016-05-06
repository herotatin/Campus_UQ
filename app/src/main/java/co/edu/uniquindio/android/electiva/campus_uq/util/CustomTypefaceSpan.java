package co.edu.uniquindio.android.electiva.campus_uq.util;


import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;


/**
 * Esta es la clase CustomTypefaceSpan del proyecto campus_UQ de la electiva de moviles
 * @author: Jose Omar Colorado y Jesus Alberto Onofre
 */
public class CustomTypefaceSpan extends TypefaceSpan {
    // TypeFace de esta clase
    private final Typeface newType;
    /**
     * Metodo constructor de la clase CustomTypefaceSpan
     */
    public CustomTypefaceSpan(String family, Typeface type) {
        super(family);
        newType = type;
    }
    /**
     * Metodo que hace  que el texto subrayado y en el color de los enlaces.
     * @ds texto que se subraya
     */ 
    @Override
    public void updateDrawState(TextPaint ds) {
        applyCustomTypeFace(ds, newType);
    }
    /**
     * Este metodo no hace nada, ya que ReplacementSpans se miden de forma explícita en lugar de
     * afectar a las propiedades de la pintura.
     */ 
    @Override
    public void updateMeasureState(TextPaint paint) {
        applyCustomTypeFace(paint, newType);
    }
    /**
     * Este metodo cambia la letra
     */ 
    private static void applyCustomTypeFace(Paint paint, Typeface tf) {
        int oldStyle;
        Typeface old = paint.getTypeface();
        if (old == null) {
            oldStyle = 0;
        } else {
            oldStyle = old.getStyle();
        }

        int fake = oldStyle & ~tf.getStyle();
        if ((fake & Typeface.BOLD) != 0) {
            paint.setFakeBoldText(true);
        }

        if ((fake & Typeface.ITALIC) != 0) {
            paint.setTextSkewX(-0.25f);
        }

        paint.setTypeface(tf);
    }
}
