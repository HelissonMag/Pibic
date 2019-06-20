/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetopibic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author helis
 */
public class ProjetoPibic {


        static List<Integer> listaPiores;
        public static int[] Aprender(int[][] rede_social ,int[][] matriz_solucoes,int [][]matriz_custo,int[] demanda, int capacidade, int rounds,int custo_car,int numbNo,int numbAmigo,int numRep,int tolerancia,double alfa,double beta){
            int[] aprendeu=new int[1];
            Random num =new Random();
            int capTotal=0;
            int posic_solucao,menor,menorCont=0;
            int var,ale;
            int menorTeste = 0;
            int nub,nub2;
            int [] aux;
            List<Integer> adicionados,rota;
            int cont=0;
            rota = new ArrayList();
          for (int l = 0; l < rounds; l++) {
              ale=7;
             //ale=(int)Math.round(8)+3;
            for (int i = 0; i < rede_social.length; i++) {
                for (int j = 0; j < rede_social[i].length; j++) {
                    adicionados= new ArrayList();
                    posic_solucao=rede_social[i][j];
                    if(posic_solucao!=-1){
                        rota.add(0);
      
                        nub=num.nextInt(matriz_solucoes[posic_solucao].length-2)+1;
                  
                        if(matriz_solucoes[posic_solucao][nub]!=0 && demanda[(matriz_solucoes[posic_solucao][nub])-1]+capTotal <=capacidade ) {
                            rota.add(matriz_solucoes[posic_solucao][nub]);
                            adicionados.add(matriz_solucoes[posic_solucao][nub]);
                            capTotal=demanda[matriz_solucoes[posic_solucao][nub]-1]+capTotal;
                        }
                        int contador=0;
                        //int controle=0;
                        int vbl=nub+1;
                        while(contador<ale && vbl!=nub){
                            if(vbl==matriz_solucoes[posic_solucao].length-1){
                                //System.out.println("aqu2 "+vbl);
                                vbl=0;
                            }
                            if(matriz_solucoes[posic_solucao][vbl]!=0 && demanda[(matriz_solucoes[posic_solucao][vbl])-1]+capTotal <=capacidade && !(adicionados.contains(vbl))) {
                                rota.add(matriz_solucoes[posic_solucao][vbl]);
                                adicionados.add(matriz_solucoes[posic_solucao][vbl]);
                                capTotal=demanda[matriz_solucoes[posic_solucao][vbl]-1]+capTotal;
                                contador++;
                                
                            }
                            
                            vbl++;
                           // System.out.println(vbl+"="+nub);
                        }
                   
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
                           rota.clear();
                            if(((double)alfa*(-calcular_custoNo(aux,matriz_custo)+calcular_custoNo(matriz_solucoes[i],matriz_custo)))-((double)beta*(calcular_custoNo(matriz_solucoes[posic_solucao],matriz_custo)-calcular_custoNo(aux,matriz_custo)))>0){
                                matriz_solucoes[i]=aux;
                            }
                        }else{
                            aux=new int[rota.size()];
                            while(z<rota.size()){
                                    
                                aux[z]=rota.get(z);
                                z++;  
                            }
                    
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
              menor=calcular_custo(matriz_solucoes,matriz_custo,custo_car,false);
              System.out.println("000000000000000000000000000000000000000000");
               
              if(l==0){
                 menorTeste=menor;
              }
              if(menorTeste!=menor){
                  menorTeste=menor;
                  menorCont=0;
              }else{
                  menorCont++;
              }if(menorCont==tolerancia){
                  
                calcular_custo(matriz_solucoes,matriz_custo,custo_car,true);
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n"+listaPiores.size()+"\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                for(int i = 0; i < listaPiores.size(); i++) {
                  matriz_solucoes[listaPiores.get(i)]=gerar_uma_solucao(demanda.length,demanda,capacidade);
                }
                menorCont=0;
              }
              if(l==rounds-1 && cont<numRep){
                  rede_social=criar_rede(numbNo,numbAmigo);
                  l=-1;
                  cont++;
              }
         }
          mostraMatriz(matriz_solucoes);
          calcular_custo(matriz_solucoes,matriz_custo,custo_car,false);
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

        // Essa funÃ§ao apenas imprime a matriz
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
          for (int i = 0; i <no ; i++) {
                    q=0; 
                     Collections.shuffle(numeros);
               for (int j = 0; j < amigo; j++) {
                   if(j==0){
                       if(i<no-1){
                           cont++;
                           socialNet[i][j]=i+1;
                        }
                       
                   }else{
                        aleatorio=numeros.get(q);
                        q++;
                        
                        if(aleatorio!=i && aleatorio!=i+1){
                            socialNet[i][j]=aleatorio;
                        }else{
                            j--;
                        }
                   }
                  
               }
             }
           return socialNet;    
       }
          
       public static int[] gerar_uma_solucao(int NCidade,int[] demanda, int capacidade){
           List<Integer> numeros,rota, adicionados;
           int var;
           int capTotal=0;
           int[] solucao;
           numeros = new ArrayList();
           rota =new ArrayList();
           adicionados=new ArrayList();
            for (int p = 1; p <=NCidade ; p++) {
                numeros.add(p);
            }
            
                rota.add(0);
                Collections.shuffle(numeros);  
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
                solucao= new int[rota.size()];
                int z=0;
                while(z<rota.size()){
                    solucao[z]=rota.get(z);
                    z++;
                }
                rota.clear();
                adicionados.clear();
            return solucao;
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
       
       public static int calcular_custo(int matriz_solucao[][],int matriz_custo[][],int custo_car,boolean cond){
           Scanner e=new Scanner(System.in);
           listaPiores=new ArrayList();
           int custoTotal=0; 
           int maior=0;
           int menor=99999;
           int var=0;
           int media=0;
           for(int i=0;i<matriz_solucao.length;i++){
               for (int j = 0; j < matriz_solucao[i].length-1; j++) {
                 //  System.out.println("Custo da cidade "+matriz_solucao[i][j]+" atÃ© a cidade "+matriz_solucao[i][j+1]+" Ã© "+matriz_custo[matriz_solucao[i][j]][matriz_solucao[i][j+1]]);
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
            //   System.out.println("| Custo da soluÃ§ao "+i+" Ã© "+custoTotal+" |");     
             //  System.out.println("________________________________________________________________");
               custoTotal=0;
           }
           media=media/matriz_solucao.length;
           if(cond){
               /* if((double)menor/media>=0.9){
                    if(var<=-1+matriz_solucao.length/2){
                        for (int i = matriz_solucao.length/2; i < matriz_solucao.length; i++) {
                            listaPiores.add(i);
                        }
                    }else{
                        for (int i =0; i < matriz_solucao.length/2; i++) {
                            listaPiores.add(i);
                        }
                    }
                }else{*/
                     for(int i=0;i<matriz_solucao.length;i++){
                         for (int j = 0; j < matriz_solucao[i].length-1; j++) {
                           //  System.out.println("Custo da cidade "+matriz_solucao[i][j]+" atÃ© a cidade "+matriz_solucao[i][j+1]+" Ã© "+matriz_custo[matriz_solucao[i][j]][matriz_solucao[i][j+1]]);
                              if(matriz_solucao[i][j]==0 ){
                                  custoTotal=custoTotal+matriz_custo[matriz_solucao[i][j]][matriz_solucao[i][j+1]]+custo_car;
                              }else{
                                  custoTotal=custoTotal+matriz_custo[matriz_solucao[i][j]][matriz_solucao[i][j+1]];
                              }

                         }
                         if(custoTotal>=media){
                             listaPiores.add(i);
                         }
                      //   System.out.println("| Custo da soluÃ§ao "+i+" Ã© "+custoTotal+" |");     
                       //  System.out.println("________________________________________________________________");
                         custoTotal=0;
                     }
                //}
           }else{
                System.out.println("Media de custos das soluçoes iniciais é :"+media);
                System.out.println("SoluÃ§ao inicial com o menor curso Ã© a SoluÃ§ao "+var+" com o valor "+ menor);
           }
           
          
           
           
           return menor;
   
       }
        
       public static int[][] dist_by_graph(int [][] graf){
           int distX=0;
           int distY=0;
           double distancia=0;
           int[][] matrix_dist = new int [graf.length][graf.length];
           for (int i = 0; i < graf.length; i++) {
               for (int j = i+1; j < graf.length; j++) {
                    distX=graf[j][0]-graf[i][0];
                    distY=graf[j][1]-graf[i][1];
                    distancia=Math.sqrt((distY*distY)+(distX*distX));
                    matrix_dist[i][j]=(int)Math.floor(distancia);
                    matrix_dist[j][i]=(int)Math.floor(distancia);
               }
           }
           
           return matrix_dist;
       }

    public static void main(String[] args) throws IOException {
        int[][] mat2;
        int[][] mat;
        int [] v;
        int[][]betterMat;
        int linhas;
        
       BufferedReader br = new BufferedReader (new FileReader ("dist3.txt"));
        BufferedReader cap = new BufferedReader (new FileReader ("cap3.txt"));
        linhas = 0;
        while (br.readLine()!= null) 
            linhas++;
        int graf0[][] = new int[linhas][2];
        int demanda[]=new int [linhas-1];
        int capacidade=0;
        int i=0;
        BufferedReader sr = new BufferedReader (new FileReader ("dist3.txt"));
        while(i<linhas){
            String linhaAtual = sr.readLine();
            String palavras[] = linhaAtual.split(" ");
            graf0[i][0]=Integer.parseInt(palavras[2]);
            graf0[i][1]=Integer.parseInt(palavras[3]);
            if(i!=linhas-1){
                String linhaC = cap.readLine();
                String palavrasC[] = linhaC.split(" ");
                demanda[i]=Integer.parseInt(palavrasC[1]);
            }else{
                capacidade=Integer.parseInt(cap.readLine());
            }
            i++;
        }    
        
        sr.close();
        br.close();
        int[][] custos  = dist_by_graph(graf0);
       
        int numeroSolc=200;
        int numeroAmigo=10;
        System.out.println(linhas);
        mat=criar_rede(numeroSolc,numeroAmigo);
        System.out.println("\t \t \tREDE SOCIAL.");
        mostraMatriz(mat);
        System.out.println("\t");
        mat2=gerar_solucoes(numeroSolc,linhas-1,demanda,capacidade);

        System.out.println("\t \t \t SOLUÃ‡OES INICIAIS.");
        mostraMatriz(mat2);
        calcular_custo(mat2,custos,0,false);
        System.out.println("**********************************");
        v=Aprender(mat,mat2,custos,demanda,capacidade,20,0,numeroSolc,numeroAmigo,150,25,1,0);
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
