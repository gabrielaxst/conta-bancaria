package conta.model;

public abstract class Conta {
    private int numero;
    private int agencia;
    private int tipo;
    private String titular;
    private float saldo;

    public Conta(int numero, int agencia, int tipo, String titular, float saldo) {
        this.numero = numero;
        this.agencia = agencia;
        this.tipo = tipo;
        this.titular = titular;
        this.saldo = saldo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }
    public boolean sacar(float valor){
        if (this.getSaldo() >= valor){
            this.setSaldo(this.getSaldo() - valor);
            return true;
        }
        System.out.println();
        System.out.println("Saldo insuficiente!");
        return false;
    }
    public void depositar(float valor){
        this.setSaldo(this.getSaldo() + valor);
    }
    public void visualizar(){
        //Variável para transcrever o tipo int em string
        String tipo = "";

        //this.tipo é a váriavel int da classe
        switch (this.getTipo()){
            case 1:
                tipo = "Conta Corrente";
                break;
            case 2:
                tipo = "Conta Poupança";
            break;
        }
        System.out.println("\n\n***********************************************************");
        System.out.println("Dados da Conta:");
        System.out.println("***********************************************************");
        System.out.println("Numero da Conta: " + this.getNumero());
        System.out.println("Agência: " + this.getAgencia());
        System.out.println("Tipo da Conta: " + tipo);
        System.out.println("Titular: " + this.getTitular());
        System.out.println("Saldo: " + this.getSaldo());
    }
}
