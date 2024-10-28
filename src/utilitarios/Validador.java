package utilitarios;

import java.util.ArrayList;
import java.util.HashMap;

public class Validador {
    private String nomeJogador1;
    private String nomeJogador2;

    private ArrayList<Integer> quantTipoArvores;
    private ArrayList<Integer> quantFrutasChao;
    private int quantPedras;

    private int chanceBichadas;
    private int dimensao;
    private int espacoMochila;
    private int qtdTotalMaracujas;

    public Validador(){
        this.quantTipoArvores = new ArrayList<>(6);
        this.quantFrutasChao = new ArrayList<>(7);
        this.quantPedras = 0;
        this.chanceBichadas = 0;
        this.dimensao = 0;
        this.espacoMochila = 0;
    }

    public String validarInformacoes(HashMap<String, Object> dados){
        int passosValidados = 0;
        int totalMaracujas = (Integer) dados.get("totalMaracujas");

        for (String chave : dados.keySet()){
            switch (chave){
                case "nomeJogador1":{
                    String nomeJogador1 = (String) dados.get(chave);
                    if (nomeJogador1 == null || nomeJogador1.isEmpty()){
                        return "E01: O nome do jogador 1 está vazio ou inválido!";
                    }
                    this.nomeJogador1 = nomeJogador1;
                    passosValidados++;

                    break;
                }

                case "nomeJogador2":{
                    String nomeJogador2 = (String) dados.get(chave);
                    if (nomeJogador2 == null || nomeJogador2.isEmpty()){
                        return "E02: O nome do jogador 2 está vazio ou inválido!";
                    }
                    this.nomeJogador2 = nomeJogador2;

                    passosValidados++;
                    break;
                }

                case "qtdTipoArvores":{
                    int qtdArvoresGeral = 0;
                    if (dados.get(chave) instanceof ArrayList<?> quantidadeArvores){

                        if (quantidadeArvores.getFirst() instanceof Integer){
                            @SuppressWarnings("unchecked")
                            ArrayList<Integer> quantidadeArvoreInt = (ArrayList<Integer>) quantidadeArvores;

                            for( Integer qtdArvore :  quantidadeArvoreInt){
                                qtdArvoresGeral += qtdArvore;
                                if (qtdArvore < 0){
                                    return "E03: A quantidade das árvores não podem ser negativas!";
                                }
                            }
                            if (qtdArvoresGeral < 1){
                                return "E14: É necessário existir pelo menos 1 árvore!";
                            }
                            this.quantTipoArvores = quantidadeArvoreInt;

                            passosValidados++;
                        }
                        else{
                            return "E04: Quantidade de árvores não é do tipo inteiro!";
                        }
                    }
                    else{
                        return "E05: Quantidade de árvores não é uma lista!";
                    }
                    break;
                }
                case "qtdFrutasChao":{
                    if (dados.get(chave) instanceof ArrayList<?> quantidadeChao){
                        if (quantidadeChao.getFirst() instanceof Integer){
                            @SuppressWarnings("unchecked")
                            ArrayList<Integer> quantidadeChaoInt = (ArrayList<Integer>) quantidadeChao;
                            for( Integer qtdArvore :  quantidadeChaoInt){
                                if (qtdArvore < 0){
                                    return "E06: A quantidade das árvores não podem ser negativas!";
                                }
                            }
                            this.quantTipoArvores = quantidadeChaoInt;
                            passosValidados++;

                        }
                        else{
                            return "E07: Quantidade de frutas no chão não é do tipo inteiro!";
                        }
                    }
                    else{
                        return "E08: Quantidade de frutas no chão não é uma lista!";
                    }
                    break;
                }
                case "quantPedras":{
                    if ((Integer) dados.get(chave) < 0){
                        return "E09: A quantidade de pedras não pode ser negativas!";
                    }
                    this.quantPedras = (Integer) dados.get(chave);
                    passosValidados++;

                    break;
                }

                case "chanceBichadas":{
                    if ((Integer) dados.get(chave) < 0 || (Integer) dados.get(chave) > 100){
                        return "E10: A chance de frutas bichadas ultrapassa o limite! (Entre 0% e 100%)!";
                    }
                    this.chanceBichadas = (Integer) dados.get(chave);
                    passosValidados++;

                    break;
                }

                case "dimensao":{
                    if ((Integer) dados.get(chave) < 3 || (Integer) dados.get(chave) > 15){
                        return "E11: A dimensão informada ultrapassa o limite! (Entre 3 e 15)!";
                    }
                    this.dimensao = (Integer) dados.get(chave);
                    passosValidados++;

                    break;
                }

                case "totalMaracujas":{
                    if ((Integer) dados.get(chave) < 3){
                        return "E12: A quantidade de maracujás precisa ser no mínimo 3!";
                    }
                    this.qtdTotalMaracujas = (Integer) dados.get(chave);
                    passosValidados++;

                    break;
                }

                case "espacoMochila":{
                    if ((Integer) dados.get(chave) < totalMaracujas){
                        return "E13: O espaco da mochila não pode ser menor que a quantidade de maracujás!";
                    }
                    this.espacoMochila = (Integer) dados.get(chave);
                    passosValidados++;

                    break;
                }
            }

        }

        if (passosValidados == 9){
            return "Validado";
        }

        return "E12: Erro ao validar! Nem todos os campos foram passados para validação!";
    }

}
