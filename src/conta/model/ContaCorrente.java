package conta.model;

public class ContaCorrente extends Conta {
    private float limite;
    public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
        super(numero, agencia, tipo, titular, saldo);
        this.limite = limite;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public boolean sacar(float valor) {
        if (this.getLimite() + this.getSaldo() >= valor){
            this.setSaldo(this.getSaldo() - valor);
            return true;
        }
        System.out.println();
        System.out.println("Saldo Insuficiente!");
        return false;
    }

    @Override
    public void visualizar() {
        super.visualizar();
        System.out.println("Limite de Crédito: " + this.getLimite());
    }
}
