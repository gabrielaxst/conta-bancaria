package conta.controller;

import conta.model.Conta;
import conta.repository.ContaRepository;
import java.util.ArrayList;

public class ContaController implements ContaRepository {
    ArrayList<Conta> listaContas = new ArrayList<>();

    //Métoda que vai receber um metodo que retorna uma conta e vai printar essa contar
    @Override
    public void procurarPorNumero(int numero) {
        var conta = buscarNaCollection(numero);
        if (conta != null){
            conta.visualizar();
        }
        else {
            System.out.println("\nA conta número: " + numero +" não foi encontrada!");
        }

    }
    @Override
    public void cadastrar(Conta conta) {
        this.listaContas.add(conta);
        System.out.println("\nA conta número: "+conta.getNumero()+" foi criada com sucesso!");
    }

    //esse métoda entra na lista e o visualizar mostra
    //visualizar mostra cada objeto do tipo conta add a lista
    @Override
    public void listarTodas() {
        for (var conta : listaContas){
            conta.visualizar();
        }
    }

    @Override
    public void atualizar(Conta conta) {
        var buscaConta = buscarNaCollection(conta.getNumero());
        if (buscaConta != null){
            listaContas.set(listaContas.indexOf(buscaConta), conta);
            System.out.println("\nA conta número: " + conta.getNumero() + " foi atualizada com sucesso!");
        }
        else {
            System.out.println("\nA conta némero: "+ conta.getNumero() + " não foi encontrada!");
        }
    }

    @Override
    public void deletar(int numero) {
        var conta = buscarNaCollection(numero);
        if (conta != null){
            if (listaContas.remove(conta) == true){
                System.out.println("\nA conta numero: " + numero + " foi deletada com sucesso!");
            }
        }
        else {
            System.out.println("\nA conta número: " + numero + " não foi encontrada!");
        }
    }

    @Override
    public void sacar(int numero, float valor) {
        var conta = buscarNaCollection(numero);
        if (conta != null){
           if(listaContas.get(listaContas.indexOf(conta)).sacar(valor) == true){
               System.out.println("\nO saque na conta conta de número "+ numero + " foi efetuado com sucesso!");
           }
        }
        else {
            System.out.println("\nA conta de número "+numero+" não foi encontrada!");
        }

    }

    @Override
    public void depositar(int numero, float valor) {
        var buscarConta = buscarNaCollection(numero);
        if (buscarConta != null){
            var indiceConta = listaContas.indexOf(buscarConta);
            listaContas.get(indiceConta).depositar(valor);
            System.out.println("\nO depósito na conta de número "+numero+" foi efetudo com sucesso!");
        }
        else {
            System.out.println("\nA Conta número de número "+numero+" não foi encontrada " +
                    "ou a Conta destino não é uma conta corrente!");
        }

    }

    @Override
    public void transferir(int numeroOrigem, int numeroDestino, float valor) {
        var buscaContaOrigem = buscarNaCollection(numeroOrigem);
        var buscaContaDestino = buscarNaCollection(numeroDestino);

        if (buscaContaOrigem != null && buscaContaDestino != null){
            if (listaContas.get(listaContas.indexOf(buscaContaOrigem)).sacar(valor) == true){
                listaContas.get(listaContas.indexOf(buscaContaDestino)).depositar(valor);
                System.out.println("\nA transferência foi realizada com sucesso!");
            }
        }
        else {
            System.out.println("\nA conta de Origem e/ou destino não foram encontradas!");
        }
    }
    //Esse método difine o número da conta
    public int getNumero(){
        return listaContas.size() + 1;
    }

    //Método que procura um elemento na lista com base no indice (que é o número)
    //esse método será usado dentro de outro método para retornar a conta encontrada
    public Conta buscarNaCollection(int numero){
        for (var conta : listaContas){
            if (conta.getNumero() == numero){
                return conta;
            }
        }
        return null;
    }
    public int retornaTipo(int numero){
        for (var conta : listaContas){
            if (conta.getNumero() == numero){
                return conta.getTipo();
            }
        }
        return 0;
    }
}
