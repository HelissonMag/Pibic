
       
     
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
          
       
      
    
}
