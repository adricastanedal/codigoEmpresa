import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        double dato =0;
        int count = 0;
        do {
            try {
                dato = Double.parseDouble(JOptionPane.showInputDialog(null, "sdfg"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error");
            }
            count++;
        } while (dato == 0 && count < 3);

        System.out.println(dato == 0 ? "NULL" : dato);
    }
}
