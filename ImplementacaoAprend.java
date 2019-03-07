
       
     
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
