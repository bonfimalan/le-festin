public class Parismap {
    
    //distance between stations
    private int[][] distance = {
        {0,11,20,27,40,43,39,28,18,10,18,30,30,32},
        {11,0,9,16,29,32,28,19,11,4,17,23,21,24},
        {20,9,0,7,20,22,19,15,10,11,21,21,13,18},
        {27,16,7,0,13,16,12,13,13,18,26,21,11,17},
        {40,29,20,13,0,3,2,21,25,31,38,27,16,20},
        {43,32,22,16,3,0,4,23,28,33,41,30,17,20},
        {39,28,19,12,2,4,0,22,25,29,38,28,13,17},
        {28,19,15,13,21,23,22,0,9,22,18,7,25,30},
        {18,11,10,13,25,28,25,9,0,13,12,12,23,28},
        {10,4,11,18,31,33,29,22,13,0,20,27,20,23},
        {18,17,21,26,38,41,38,18,12,20,0,15,35,39},
        {30,23,21,21,27,30,28,7,12,27,15,0,31,37},
        {30,21,13,11,16,17,13,25,23,20,35,31,0,5},
        {32,24,18,17,20,20,17,30,28,23,39,37,5,0}
    };

    //existing connections

    private int[][] connections = {
        {0,1,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,0,1,0,0,0,0,0,1,1,0,0,0,0},
        {0,1,0,1,0,0,0,0,1,0,0,0,1,0},
        {0,0,1,0,1,0,0,1,0,0,0,0,1,0},
        {0,0,0,1,0,1,1,1,0,0,0,0,0,0},
        {0,0,0,0,1,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,1,0,0,0,0,0,0,0,0,0},
        {0,0,0,1,1,0,0,0,1,0,0,1,0,0},
        {0,1,1,0,0,0,0,1,0,0,1,0,0,0},
        {0,1,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,1,0,0,0,0,0},
        {0,0,0,0,0,0,0,1,0,0,0,0,0,0},
        {0,0,1,1,0,0,0,0,0,0,0,0,0,1},
        {0,0,0,0,0,0,0,0,0,0,0,0,1,0}
    };

    //train line colors, where
    // 1 --> Blue
    // 2 --> Yellow
    // 3 --> Red
    // 4 --> Green
    private int[][] trainLinecolors = {
        {0,1,0,0,0,0,0,0,0,0,0,0,0,0},
        {1,0,1,0,0,0,0,0,2,2,0,0,0,0},
        {0,1,0,1,0,0,0,0,3,0,0,0,3,0},
        {0,0,1,0,1,0,0,4,0,0,0,0,4,0},
        {0,0,0,1,0,1,2,2,0,0,0,0,0,0},
        {0,0,0,0,1,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,2,0,0,0,0,0,0,0,0,0},
        {0,0,0,4,2,0,0,0,2,0,0,4,0,0},
        {0,2,3,0,0,0,0,2,0,0,3,0,0,0},
        {0,2,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,3,0,0,0,0,0},
        {0,0,0,0,0,0,0,4,0,0,0,0,0,0},
        {0,0,3,4,0,0,0,0,0,0,0,0,0,4},
        {0,0,0,0,0,0,0,0,0,0,0,0,4,0}
    };

    public void busca(int origem, int destino){
        int posicao = 0;
        int max = 30;
        int tempo_total = 0; //total do percurso
        int contador = 0; //quantidade de conexoes do no
        int no_atual = 0;
        int no_pai = origem;

        int[] no_visitado = new int[max];
        int[] tempo_viagem = new int[max]; //tempo entre duas estacoes adjacentes 

        int[] ordem_estacoes = new int[max]; //ordem das estacoes do percurso
        int[] linha_estacao = new int[max]; //linha para o melhor no

        int f = 0, g = 0, h = 0;

        int f_melhorno = 1000; //chuto valor alto pra depois diminuir
        int no_melhor = 0;
        int linha_no_melhor = 0;

        for(int i = 0; i<max; i++){
            no_visitado[i] = 0;
            tempo_viagem[i] = 0;
            ordem_estacoes[i] = 0;
            linha_estacao[i] = 0;
        }

        no_visitado[origem] = 1;
        ordem_estacoes[posicao] = origem;
        tempo_viagem[posicao] = 0;
        linha_estacao[posicao] = 0;
        posicao = 1;

        while(ordem_estacoes[posicao] != destino){
            for(no_atual = 0; no_atual < 14; no_atual++ ){
                if( no_atual != destino ){
                    contador = 0;
                    for (int i = 0; i < 14; i++){
                        if( connections[no_atual][i]  == 1)
                            contador++;
                            
                    }
                    if (contador == 1)
                        no_atual++;
                }
                System.out.println(ordem_estacoes[posicao] + " asasas" + no_pai + "jkkkk" + no_atual);
                //System.out.println("ordem estaçoes: " + ordem_estacoes[posicao]  + " DESTINO: " + destino);
                if(connections[no_pai][no_atual] == 1 && no_visitado[no_atual] == 0 ){
                    
                    g = time(no_pai, no_atual);
                    if(( trainLinecolors[no_pai][no_atual] != linha_estacao[posicao - 1]  ) && ( linha_estacao[posicao - 1] != 0 && trainLinecolors[no_pai][no_atual] != 0)){
                        g+=4;
                    }
                    h = time(no_atual, destino);
                    f = g + h;
                    if(f_melhorno >= f){
                        f_melhorno = f;
                        
                        no_melhor = no_atual;
                        linha_no_melhor = trainLinecolors[no_pai][no_atual];
                        no_visitado[no_melhor] = 1;
                        
                    }
                }
            
           tempo_viagem[posicao-1] = time(no_pai, no_melhor);
           tempo_total += tempo_viagem[posicao-1];
           linha_estacao[posicao] = linha_no_melhor; 
           if(( linha_estacao[posicao] != linha_estacao[posicao - 1]) && (linha_estacao[posicao] != 0 && linha_estacao[posicao - 1] !=0) ){
                tempo_total += 4;
           }
           System.out.println("Estacao: " + (no_pai+1) + " --> Estacao: " + (no_melhor+1) + " Tempo: " + tempo_viagem[posicao - 1]);
           no_pai = no_melhor;
           posicao++;
           ordem_estacoes[posicao] = no_pai;
        }
        }
        System.out.println("Tempo Total: " + tempo_total);
    }

    private int time(int originNode, int actualNode){
        return (int)(distance[originNode][actualNode]*0.5);
    }
}