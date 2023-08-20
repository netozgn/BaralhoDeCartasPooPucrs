import java.util.*;
public class App {
    public static void main(String[] args) throws Exception {
        Random ra = new Random();

        //Cria os jogadores e da os nomes
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do Jogador 1 e em seguida o nome do Jogador 2");
        String nomeJogador1 = sc.next();
        String nomeJogador2 = sc.next();
        Player player1 = new Player(nomeJogador1);
        Player player2 = new Player(nomeJogador2);
        String p1 = player1.getNome();
        String p2 = player2.getNome();

        System.out.println(player1 + " CONTRA " + player2);

        sc.close();
      
        
        // Cria o baralho e embaralha
        Baralho baralho = new Baralho();
        baralho.embaralha();
        // Cria os decks dos jogadores
        Deck jogador1 = new Deck();
        Deck jogador2 = new Deck();
        Deck empate = new Deck();
        
        // Distribui todas as cartas entre os dois jogadore
        while(!baralho.vazio()){
            jogador1.insereEmbaixo(baralho.retiraDeCima());
            jogador2.insereEmbaixo(baralho.retiraDeCima());
        }
        // Loop do jogo
        boolean acabou = false;
        int rodada = 1;
        while(!acabou){
            // Pega uma carta de cada jogador
            Carta cj1 = jogador1.retiraDeCima();
            Carta cj2 = jogador2.retiraDeCima();
            System.out.println("Rodada: "+rodada);
            System.out.println("Carta do "+player1 +": "+cj1.toString());
            System.out.println("Carta do "+player2 +": "+cj2.toString());

            //Verifica se ouve empate e adiciona as cartas ao deck do empate
            if(cj1.igual(cj2)){
            empate.insereEmbaixo(cj1);
            empate.insereEmbaixo(cj2);
            }

            boolean ordemaleatoria = ra.nextBoolean();
        
            // Se a carta do jogador1 é maior, ele fica com todas
            if (cj1.eMaior(cj2)){
                if(!empate.vazio()){
                    empate.distribuir(jogador1); //Caso tenha cartas no deck do empate, o jogador vencedor ganha elas de maneira aleatoria
                    empate = new Deck();
                }
                if(ordemaleatoria){
                    jogador1.insereEmbaixo(cj2);
                    jogador1.insereEmbaixo(cj1);
                }                                   // Usando a aleatoriadade da booleana a cada loop, o jogador recebe de maneira aleatoria as duas cartas da rodada
                else{
                    jogador1.insereEmbaixo(cj1);
                    jogador1.insereEmbaixo(cj2);
                }
                System.out.println(p1 + " ganhou a rodada");
            }
            else{ // Se a carta do jogador2 eh maior, ele fica com todas 
                if(!empate.vazio()){
                    empate.distribuir(jogador2);  //Caso tenha cartas no deck do empate, o jogador vencedor ganha elas de maneira aleatoria
                    empate = new Deck();
                }

                if(ordemaleatoria){
                    jogador2.insereEmbaixo(cj2);
                    jogador2.insereEmbaixo(cj1);
                }                                   // Usando a aleatoriadade da booleana a cada loop, o jogador recebe de maneira aleatoria as duas cartas da rodada
                else{
                    jogador2.insereEmbaixo(cj1);
                    jogador2.insereEmbaixo(cj2);
                }
                
                System.out.println(p2 +" ganhou a rodada");
            }
    }
            // Verifica se acabou
            if (jogador1.vazio() || jogador2.vazio()){
                acabou = true;
                if(jogador1.vazio()) System.out.println(p2 + " GANHOU!");
                else System.out.println(p1 + " GANHOU!");
            }
            // Incrementa a rodada
            rodada++;
        }
        System.out.println("Fim");
    }
}

