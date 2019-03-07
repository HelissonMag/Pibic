
       
     /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package implementacaoaprend2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ImplementacaoAprend2 {
 
        public static int[] Aprender(int[][] rede_social ,int[][] matriz_solucoes,int [][]matriz_custo,int[] demanda, int capacidade, int rounds,int custo_car){
            int[] aprendeu=new int[1];
            Random num =new Random();
            int capTotal=0;
            int posic_solucao;
            int var;
            int nub;
            int [] aux;
            List<Integer> adicionados,rota;
            int cont=0;
            rota = new ArrayList();
          for (int l = 0; l < rounds; l++) {
            for (int i = 0; i < rede_social.length; i++) {
                for (int j = 0; j < rede_social[i].length; j++) {
                    adicionados= new ArrayList();
                    posic_solucao=rede_social[i][j];
                    if(posic_solucao!=-1){
                        rota.add(0);
                       
                        nub=num.nextInt(matriz_solucoes[posic_solucao].length-2);
                        if(matriz_solucoes[posic_solucao][nub]!=0 && demanda[(matriz_solucoes[posic_solucao][nub])-1]+capTotal <=capacidade ) {
                            rota.add(matriz_solucoes[posic_solucao][nub]);
                            adicionados.add(matriz_solucoes[posic_solucao][nub]);
                            capTotal=demanda[matriz_solucoes[posic_solucao][nub]-1]+capTotal;
                        }
                        if(matriz_solucoes[posic_solucao][nub+1]!=0 && demanda[(matriz_solucoes[posic_solucao][nub+1])-1]+capTotal <=capacidade ) {
                            rota.add(matriz_solucoes[posic_solucao][nub+1]);
                            adicionados.add(matriz_solucoes[posic_solucao][nub+1]);
                            capTotal=demanda[matriz_solucoes[posic_solucao][nub+1]-1]+capTotal;
                        }
                         
                        
                      //  System.out.println("Valor I:"+i);
                      //  System.out.println("Valor J:"+j);
                        for (int k = 1; k < matriz_solucoes[i].length; k++) {
                                var=matriz_solucoes[i][k];

                                if(var!=0 && !(adicionados.contains(var))){
                                        if(demanda[var-1]+capTotal<=capacidade){
                                          rota.add(var);
                                           capTotal=demanda[var-1]+capTotal;
                                           adicionados.add(var);

                                        }
                                }
                                if(k==matriz_solucoes[i].length-1 && (adicionados.size()<demanda.length)){
                                    rota.add(0);
                                    k=0;
                                    capTotal=0;
                                }
                        }
                  
                        capTotal=0;
                        int z=0;
                        rota.add(0);
                    
                        if(j==0){
                            aux=new int[rota.size()];
                            
                            while(z<rota.size()){
                                aux[z]=rota.get(z);
                                z++; 
                            }
                            
                         //   aprendeu=new int[aux.length];
                         //   aprendeu=aux;
                        /*   System.out.println("========================================");
                            mostrarAprendizagem(aux);
                            System.out.println("CUSTO:"+calcular_custoNo(aux,matriz_custo));
                            mostrarAprendizagem(matriz_solucoes[i]);
                            System.out.println("*CUSTO:"+calcular_custoNo(matriz_solucoes[i],matriz_custo));
                           System.out.println("========================================"); */
                           rota.clear();
                         if(calcular_custoNo(aux,matriz_custo)<calcular_custoNo(matriz_solucoes[i],matriz_custo)){
                                matriz_solucoes[i]=aux;
                            }
                        }else{
                            aux=new int[rota.size()];
                            while(z<rota.size()){
                                    
                                aux[z]=rota.get(z);
                                z++;  
                            }
                     /*   System.out.println("========================================");
                            mostrarAprendizagem(aux);
                            System.out.println("CUSTO:"+calcular_custoNo(aux,matriz_custo));
                            mostrarAprendizagem(matriz_solucoes[i]);
                            System.out.println("*CUSTO:"+calcular_custoNo(matriz_solucoes[i],matriz_custo));
                           System.out.println("========================================"); 
                            System.out.println("_______________________________________________________________________");*/
                            rota.clear();
                            if(calcular_custoNo(aux,matriz_custo)<calcular_custoNo(matriz_solucoes[i],matriz_custo)){
                                matriz_solucoes[i]=aux;
                            }
                            
                        }
                    }
                }
            }
            int rnd=l+1;
              System.out.println("Valor no round "+rnd);
            //mostraMatriz(matriz_solucoes);
            calcular_custo(matriz_solucoes,matriz_custo,custo_car);
              System.out.println("000000000000000000000000000000000000000000");
              if(l==rounds-1 && cont<150){
                  rede_social=criar_rede(100,4);
                  l=-1;
                  cont++;
              }
         }
          mostraMatriz(matriz_solucoes);
            return aprendeu;
        }

        public static int[] aprendendo_(int[] no, int[] amigo ){
            Random random  = new Random();
            int aleatorio=random.nextInt(amigo.length);
            System.out.println("aleatorio :"+aleatorio);
            int [] aprendendo;
            aprendendo = new int [amigo.length];
            aprendendo[aleatorio]=amigo[aleatorio];
            System.out.println("amigo[aleatorio]:"+aprendendo[aleatorio]);
            for (int i = 0; i < no.length; i++) {
                if(no[i]!=amigo[aleatorio]){
                    aprendendo[i]=no[i];
                }
                
            }
            for (int i = 0; i < aprendendo.length; i++) {
                System.out.println("____________________");
                System.out.println(aprendendo[i]);
            }
            return aprendendo;
        }
    
	public static int[][] Algoritmo(int[][] M) {
		for (int k = 0; k < M.length; k++) {
			for (int i = 0; i < M.length; i++) {
				for (int j = 0; j < M.length; j++) {
					if (M[i][k] + M[k][j] < M[i][j]) {
						M[i][j] = M[i][k] + M[k][j];
                                              
					}
				}
			}
		}
        
		return M;
	}

        // Essa funçao apenas imprime a matriz
	public static void mostraMatriz(int[][] Matriz) {
		System.out.print("\n\t");
		for (int j = 0; j <Matriz.length; j++) {
			System.out.print("|="+j +"=|"+ "\t");
		}
		System.out.println();
		for (int j = 0; j < 125; j++) {
			System.out.print("_");
		}
		System.out.println();
		for (int i = 0; i < Matriz.length; i++) {
			System.out.print("|="+i + "=|\t");
			for (int j = 0; j < Matriz[i].length; j++) {
				System.out.print("  "+Matriz[i][j]);
                               
				System.out.print("\t");
			}
			System.out.println("\n");
		}

	}
        
       public static void mostrarAprendizagem(int[] aprendeu){
          for(int i=0;i<aprendeu.length;i++){
              if(i!=aprendeu.length-1){
                 System.out.print(aprendeu[i]+",");
              }else{
                  System.out.println(aprendeu[i]+" ");
              }
          }
       }
       

       public static int [][] deixar_1(int x, int y){
           int[][] socialNet =new int[x][y];
           for (int i = 0; i < x; i++) {
               for (int j = 0; j< y; j++) {
                   socialNet[i][j]=-1;
               }
           }
           return socialNet;
       }
       
       
       public static int[][] criar_rede(int no , int amigo){
           List<Integer> numeros;
            numeros = new ArrayList();
            for (int p = 0; p <no ; p++) { 
                numeros.add(p);
            }
           boolean vaga=false;
           int v=1;
           int q;
           int aleatorio;
           int[][] socialNet;
           int cont=0;
           socialNet=deixar_1(no,amigo);
           socialNet[no-1][0]=no-2;
          for (int i = 0; i <no-1 ; i++) {
                    q=0; 
                     Collections.shuffle(numeros);
               for (int j = 0; j < amigo; j++) {
                   if(j==0){
                       if(i!=no){
                           if (i==3000){
                               System.out.println("chegou aqui sim");
                               System.out.println(j);
                           }
                           cont++;
                           socialNet[i][j]=i+1;
                        }
                       
                   }else{
                         while(vaga==false && q<no && socialNet[i][j]==-1 ){
                            aleatorio=numeros.get(q);
                            q++;
                            if(aleatorio>i+1){
                                while(v<amigo &&vaga==false){
                                     if(socialNet[aleatorio][v]==-1 ){
                                         vaga=true;
                                     }
                                     v++;
                                 }
                             }
                             if(vaga==true ){
                                socialNet[i][j]=aleatorio;
                                 socialNet[aleatorio][v-1]=i;
                             }
                             v=1;  
                        }
                        vaga=false;
                 }
                  
               }
             }
           System.out.println("Cont "+cont);
           return socialNet;    
       }
          
       
       public static int[][] gerar_solucoes(int no, int NCidade,int[] demanda, int capacidade){
            List<Integer> numeros,rota, adicionados;
            int var;
            int capTotal=0;
            int[][] solucoes=new int[no][];
            numeros = new ArrayList();
            rota =new ArrayList();
            adicionados=new ArrayList();
            for (int p = 1; p <=NCidade ; p++) {
                numeros.add(p);
            }

            for (int i = 0; i < no; i++) {
                rota.add(0);
                Collections.shuffle(numeros);  
                for (int j = 0; j < numeros.size(); j++) {
                
                }
                for (int j = 0; j < NCidade; j++) {
                    var=numeros.get(j);
                        if(demanda[var-1]+capTotal<=capacidade && !(adicionados.contains(var))){
                           rota.add(var);
                           adicionados.add(var);
//                   System.out.println("Valor do adicionado "+adicionados.get(cont));
                           capTotal=demanda[var-1]+capTotal;
                         
                           if(capTotal==capacidade){
                 
                               j=NCidade-1;
                           }
                          
                        } 
                   int v=NCidade-1;
                     if(adicionados.size()<NCidade && j==(v)){
                        rota.add(0);
                        j=0;
                        capTotal=0;
                     }
                      
                }
              
                capTotal=0; 
                rota.add(0);
                solucoes[i]= new int[rota.size()];
                int z=0;
                while(z<rota.size()){
                    solucoes[i][z]=rota.get(z);
                    z++;
                }
                rota.clear();
                adicionados.clear();
           }
            return solucoes;
       }
       
      public static int calcular_custoNo(int []no,int [][]matriz_custo){
          int custoTotal=0;
          for (int i = 0; i < no.length-1; i++) {
         
              custoTotal+=matriz_custo[no[i]][no[i+1]];
          }
          
          
          return custoTotal;
       }
       
       public static void calcular_custo(int matriz_solucao[][],int matriz_custo[][],int custo_car){
           int custoTotal=0;    
           int menor=999;
           int var=0;
           int media=0;
           for(int i=0;i<matriz_solucao.length;i++){
               for (int j = 0; j < matriz_solucao[i].length-1; j++) {
                 //  System.out.println("Custo da cidade "+matriz_solucao[i][j]+" até a cidade "+matriz_solucao[i][j+1]+" é "+matriz_custo[matriz_solucao[i][j]][matriz_solucao[i][j+1]]);
                    if(matriz_solucao[i][j]==0 ){
                        custoTotal=custoTotal+matriz_custo[matriz_solucao[i][j]][matriz_solucao[i][j+1]]+custo_car;
                    }else{
                        custoTotal=custoTotal+matriz_custo[matriz_solucao[i][j]][matriz_solucao[i][j+1]];
                    }
                   
               }
               if(custoTotal<menor){
                   var=i;
                   menor=custoTotal;
               }
               media=custoTotal+media;
            //   System.out.println("| Custo da soluçao "+i+" é "+custoTotal+" |");     
             //  System.out.println("________________________________________________________________");
               custoTotal=0;
           }
           media=media/matriz_solucao.length;
           System.out.println("Media de custos das soluçoes iniciais é :"+media);
           System.out.println("Soluçao inicial com o menor curso é a Soluçao "+var+" com o valor "+ menor);
   
       }

        static int[] demanda={3,2,3,4,1,2,5,2,5,4, 3, 4, 2, 2, 6, 5, 4, 2, 2, 1, 2, 4, 5, 2, 4, 3, 6, 2, 1, 2};
                           // 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
        static int[][] custos  = {
                                   {0,5,1,2,3,2,1,5,6,2,3,5,2,1,2,5,4,4,4,2,3,2,1,2,4,2,3,2,2,5,2},
                                   {5,0,3,4,1,2,3,1,2,2,2,3,1,4,2,5,2,3,3,3,2,1,1,2,2,1,2,3,3,2,1},
                                   {1,3,0,4,2,1,3,3,3,2,5,4,4,5,2,1,2,2,3,4,2,3,2,1,2,2,1,4,5,2,1},
                                   {2,4,4,0,2,2,1,1,3,2,2,6,5,2,3,3,1,3,2,1,3,1,2,4,2,1,3,2,3,1,5},
                                   {3,1,2,2,0,1,2,2,2,3,1,6,5,2,3,3,1,3,2,1,3,1,2,4,2,1,3,2,3,1,5},
                                   {2,2,1,2,1,0,4,3,2,1,1,6,5,2,3,3,1,3,2,1,3,1,2,4,2,1,3,2,3,1,5},
                                   {1,3,3,1,2,4,0,2,2,1,3,6,5,2,3,3,1,3,2,1,3,1,2,4,2,1,3,2,3,1,5},
                                   {5,1,3,1,2,3,2,0,5,2,3,6,5,2,3,3,1,3,2,1,3,1,2,4,2,1,3,2,3,1,5},
                                   {6,2,3,3,2,2,2,5,0,2,3,6,5,2,3,3,1,3,2,1,3,1,2,4,2,1,3,2,3,1,5},
                                   {2,2,2,2,3,1,1,2,2,0,3,6,5,2,3,3,1,3,2,1,3,1,2,4,2,1,3,2,3,1,5},
                                   {3,2,5,2,1,1,3,3,3,3,0,6,5,2,3,3,1,3,2,1,3,1,2,4,2,1,3,2,3,1,5},
                                   {5,3,4,6,6,6,6,6,6,6,6,0,3,2,1,2,2,3,4,5,5,6,4,5,2,3,1,2,3,2,2},
                                   {2,1,4,5,5,5,5,5,5,5,5,3,0,1,1,2,5,4,4,2,3,5,6,9,2,1,2,3,2,4,4},
                                   {1,4,5,2,2,2,2,2,2,2,2,2,1,0,2,2,4,5,6,7,8,2,1,2,2,2,1,2,3,4,4},
                                   {2,2,2,3,3,3,3,3,3,3,3,1,1,2,0,2,1,4,5,1,2,2,3,4,1,2,4,2,5,6,5},
                                   {5,5,1,3,3,3,3,3,3,3,3,2,2,2,2,0,4,2,1,2,1,1,2,3,4,2,5,6,1,4,1},
                                   {4,2,2,1,1,1,1,1,1,1,1,2,5,4,1,4,0,4,5,4,2,1,2,3,5,7,8,4,5,1,2},
                                   {4,3,2,3,3,3,3,3,3,0,3,3,4,5,4,2,4,0,4,2,3,4,2,1,2,7,2,5,1,2,1},
                                   {4,3,3,2,2,2,2,2,2,2,2,4,4,6,5,1,5,4,0,1,1,1,1,1,1,1,1,1,1,1,1},
                                   {2,3,4,1,1,1,1,1,1,1,1,5,2,7,1,2,4,2,1,0,2,2,2,2,2,2,2,2,2,2,2},
                                   {3,2,2,3,3,3,3,3,3,3,3,5,3,8,2,1,2,3,1,2,0,1,2,3,4,5,6,7,8,9,1},
                                   {2,1,3,1,1,1,1,1,1,1,1,6,5,2,2,1,1,4,1,2,1,0,2,4,6,2,4,6,2,4,6},
                                   {1,1,2,2,2,2,2,2,2,2,2,4,6,1,3,2,2,2,1,2,2,2,0,3,6,9,3,6,9,3,6},
                                   {2,2,1,4,4,4,4,4,4,4,4,5,9,2,4,3,3,1,1,2,3,4,3,0,1,2,3,4,5,6,7},
                                   {4,2,2,2,2,2,2,2,2,2,2,2,2,2,1,4,5,2,1,2,4,6,6,1,0,2,3,5,6,7,8},
                                   {2,1,2,1,1,1,1,1,1,1,1,3,1,2,2,2,7,7,1,2,5,2,9,2,2,0,2,2,2,2,2},
                                   {3,2,1,3,3,3,3,3,3,3,3,1,2,1,4,5,8,2,1,2,6,4,3,3,3,2,0,1,2,3,4},
                                   {2,3,4,2,2,2,2,2,2,2,2,2,3,2,2,6,4,5,1,2,7,6,6,4,5,2,1,0,3,2,1},
                                   {2,3,5,3,3,3,3,3,3,3,3,3,2,3,5,1,5,1,1,2,8,2,9,5,6,2,2,3,0,9,2},
                                   {5,2,2,1,1,1,1,1,1,1,1,2,4,4,6,4,1,2,1,2,9,4,3,6,7,2,3,2,9,0,1},
                                   {2,1,1,5,5,5,5,5,5,5,5,2,4,4,5,1,2,1,1,2,1,6,6,7,8,2,4,1,2,1,0},
                     
                                   };
    public static void main(String[] args) {
      
     Scanner e =new Scanner(System.in);
     int no,amigo,capacidade;
       // System.out.println("Numero de No na rede social: ");
       // no=e.nextInt();
       // System.out.println("Numero de amigos na Rede Social: ");
        //amigo=e.nextInt();
        //System.out.println("Capacidade do veiculo: ");
        //capacidade=e.nextInt();
     int[][] mat2;
          
    int[][] mat;
     int[] v;
     int[][]betterMat;
   
    mat=criar_rede(100,4);
    betterMat=Algoritmo(custos);
    System.out.println("\t \t \tREDE SOCIAL.");
        mostraMatriz(mat);
        System.out.println("\t");
        mat2=gerar_solucoes(100,30,demanda,12);
         
        System.out.println("\t \t \t SOLUÇOES INICIAIS.");
        mostraMatriz(mat2);
      //  System.out.println("\t \t \tMATRIZ DE CUSTO DEPOIS DO ALGORITMO.");
       // mostraMatriz(betterMat);
        calcular_custo(mat2,betterMat,4);
          System.out.println("**********************************");
      v=Aprender(mat,mat2,betterMat,demanda,12,5,4);
      
       System.out.println("R E S U L T A D O :");

        for (int i = 0; i < v.length; i++) {
          if(i==v.length-1){
                System.out.print(v[i]+" ");
            }else{
                System.out.print(v[i]+",");
            }
        }
        
        System.out.println("Valor da aprendizagem achada:"+calcular_custoNo(v,betterMat));  
  /*   
 int[][] mat;
    mat=Algoritmo(custos);
        System.out.println("*********************************");
         mostraMatriz(mat);
        System.out.println("============================================");
        System.out.println(atual.length);;;;
        System.out.println("custo:"+calcular_custoNo(atual,mat));
*/
    }
  
    
}
