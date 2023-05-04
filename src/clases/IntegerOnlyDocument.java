package clases;
import javax.swing.text.*;

public class IntegerOnlyDocument extends PlainDocument {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
        if (str == null) {
            return;
        }

        // Permitir que solo se ingresen dígitos
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return;
            }
        }

        // Insertar la cadena solo si contiene dígitos
        super.insertString(offs, str, a);
    }
}
