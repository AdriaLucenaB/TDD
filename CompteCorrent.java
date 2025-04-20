import java.math.BigDecimal;
import java.math.RoundingMode;

public class CompteCorrent {
    private BigDecimal saldo;
    private BigDecimal transferitAvui;

    public CompteCorrent() {
        this.saldo = BigDecimal.ZERO;
        this.transferitAvui = BigDecimal.ZERO;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public boolean ingressar(BigDecimal quantitat) {
        if (!esQuantitatValida(quantitat)) return false;
        if (quantitat.scale() > 2 || quantitat.compareTo(new BigDecimal("6000.00")) > 0) return false;

        saldo = saldo.add(quantitat);
        return true;
    }

    public boolean retirar(BigDecimal quantitat) {
        if (!esQuantitatValida(quantitat)) return false;
        if (quantitat.scale() > 2 || quantitat.compareTo(new BigDecimal("6000.00")) > 0) return false;
        if (quantitat.compareTo(saldo) > 0) return false;

        saldo = saldo.subtract(quantitat);
        return true;
    }

    public boolean transferir(BigDecimal quantitat, CompteCorrent desti) {
        if (!esQuantitatValida(quantitat)) return false;
        if (quantitat.scale() > 2) return false;

        BigDecimal nouTotalTransferit = transferitAvui.add(quantitat);
        if (nouTotalTransferit.compareTo(new BigDecimal("3000.00")) > 0) return false;
        if (quantitat.compareTo(saldo) > 0) return false;

        saldo = saldo.subtract(quantitat);
        desti.ingressar(quantitat);
        transferitAvui = transferitAvui.add(quantitat);
        return true;
    }

    public void resetTransferitAvui() {
        transferitAvui = BigDecimal.ZERO;
    }

    private boolean esQuantitatValida(BigDecimal quantitat) {
        return quantitat != null && quantitat.compareTo(BigDecimal.ZERO) > 0;
    }
}
