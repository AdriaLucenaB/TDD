import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        CompteCorrent compte1 = new CompteCorrent();
        CompteCorrent compte2 = new CompteCorrent();

        // Test creació
        System.out.println("Saldo inicial compte1: " + compte1.getSaldo()); // Esperat: 0

        // Test ingressos
        System.out.println("Ingressar 100.00: " + compte1.ingressar(new BigDecimal("100.00"))); // true
        System.out.println("Saldo després d'ingressar 100: " + compte1.getSaldo()); // 100.00

        System.out.println("Intentar ingressar -100: " + compte1.ingressar(new BigDecimal("-100"))); // false
        System.out.println("Saldo després d'intentar ingressar -100: " + compte1.getSaldo()); // 100.00

        System.out.println("Ingressar 100.457: " + compte1.ingressar(new BigDecimal("100.457"))); // false
        System.out.println("Saldo després d'intentar ingressar 100.457: " + compte1.getSaldo()); // 100.00

        // Test retirades
        System.out.println("Retirar 50: " + compte1.retirar(new BigDecimal("50.00"))); // true
        System.out.println("Saldo després de retirar 50: " + compte1.getSaldo()); // 50.00

        System.out.println("Retirar 100 (més del saldo): " + compte1.retirar(new BigDecimal("100"))); // false
        System.out.println("Saldo després de retirar massa: " + compte1.getSaldo()); // 50.00

        // Test transferència vàlida
        compte1.ingressar(new BigDecimal("1000"));
        System.out.println("Transferir 300 a compte2: " + compte1.transferir(new BigDecimal("300"), compte2)); // true
        System.out.println("Saldo compte1: " + compte1.getSaldo()); // 750.00
        System.out.println("Saldo compte2: " + compte2.getSaldo()); // 300.00

        // Test transferència que supera el límit diari
        compte1.transferir(new BigDecimal("2700"), compte2); // fa un total de 3000
        System.out.println("Transferir 100 més (sobrepassa límit): " +
                compte1.transferir(new BigDecimal("100"), compte2)); // false

        // Mostrar saldos finals
        System.out.println("Saldo final compte1: " + compte1.getSaldo());
        System.out.println("Saldo final compte2: " + compte2.getSaldo());
    }
}
