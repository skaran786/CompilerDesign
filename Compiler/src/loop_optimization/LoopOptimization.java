/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loop_optimization;

/**
 *
 * @author manasvi
 */
public class LoopOptimization {
    void print(String str){
        System.out.println(str);
    }
    
    void loopInvariant(int c){
        //Code Motion
        
        int i, max=c;
        
        for(i=100; i<=max-1;i--){
            
            print(i+"");
            
        }
        
        int r = max - 1;
        for(i=100; i<=r;i--){
            
            print(i+"");
            
        }
        
        
//        int k;
        int k =25; // pre headers
        for(i=1000; i<=10;i--){
//            k=25;
            print(k+"");
            
        }
        
    
    }
    
    void loopFusion(){
        //loop jamming
        
        int i;
        int a[] = new int[100];
        int b[] = new int[100];
        
//        for(i=0;i<100;i++){
//            a[i]=1;
//        }
//        for(i=0;i<100;i++){
//            b[i]=1;
//        }
    
        
        for(i=0;i<100;i++){
            a[i]=1;
            b[i]=1;
        }
        
    }
    
    void loopUnrolling(){
        int a[] = new int[100];
        int b[] = new int[100];
        int i =1;
        // b = 0
        
//        while(i<100){
//            
//            a[i] = b[i]+1; //a[1] = b[1] + 1 = 0 + 1
//            i++;    
//        }
        
        while(i<100){
            
            a[i] = b[i]+1;//a[1] = b[1] + 1 = 0 + 1
            i++;    //  i = 2
            a[i] = b[i]+1;//a[2] = b[2] + 1 = 0 + 1
            i++;    // i=3
            a[i] = b[i]+1;//a[3] = b[3] + 1 = 0 + 1
            i++;  // i = 4
            a[i] = b[i]+1; //a[4] = b[4] + 1 = 0 + 1
            i++; // i = 5
        }
        
    
    }
    
    void loopPeeling(){
        
        int n =100;
        int a[] = new int[100];
        int b[] = new int[100];
        
//        for(int i =0; i< n; i++){
//            
//            if(i == 0){
//                b[i] = a[i];
//            }
//            else{
//                b[i] = a[i] +b[i];
//            }
//        
//        }
        
        b[0] = a[0];
        for(int i =1; i< n; i++){
            b[i] = a[i] +b[i];

        }
           
        
    }
    
    int loopInduction(){
        
        int max =10;
        int result =0;
        for(int i = 0; i<max; i++){
            
            result = result + 2; 
        
        }
        
       // result =20
        
        int r = max *2; //20
        for( result = 0; result<r; result+=2){
        
        }
        // result =20
        
        return result;
        
        
    
    }
    
    int strengthReduction(){
        
        int j = 1;
        for(int i =0; i<10;i++){
            j =2 * i;
        }
        
        int s =0;
        for(int i =0; i<10;i++){          
            j=s;
            s=s+2;        
        }
        
        for(; s<20;s+=2){          
                   
        }
        j=s;
        
        return j;
    
    }
    
    void frequencyReduction(int y, int z){
        int a=1000;
        // low frequency region
        while (a>0){
            int x = y+z; // high frequency region
            if(a%x==0){
                print(a+"");
            }
            a--;
            
        }
        
        
        int x = y+z;
        while (a>0){
            
            if(a%x==0){
                print(a+"");
            }
            a--;
            
        }        
        
        
    }
    
    public static void main(String args[]){
        
        LoopOptimization obj = new LoopOptimization();
    
        // calling all those won't really make sense and so is the creation of obj
        
    }
    
    
}
