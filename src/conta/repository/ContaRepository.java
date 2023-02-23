package conta.repository;

import conta.model.Conta;

public interface ContaRepository {
    //Crud da conta
    void procurarPorNumero(int numero);
    void cadastrar(Conta conta);
    void listarTodas();
    void atualizar(Conta conta);
    void deletar(int numero);

    // Métodos Bancários
    void sacar(int numero, float valor);
    void depositar(int numero, float valor);
    void transferir(int numeroOrigem, int numeroDestino, float valor);
}
