/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aliasing;

/**
 *
 * @author manasvi
 */
class A{

    void sayHi(){
        System.out.println("Hi");
    }
    
}
class B extends A{

    void sayHello(){
        System.out.println("Hello");
    }
    
}

public class Aliasing {

    public static void main(String args[]){
        
        
//        B obj = new B();
        B obj = (B) new A();
        obj.sayHi();

        // arr 

//        B []obj_b = new B[5];
//        
//        A []obj_a = obj_b;
//        
//        obj_a[0] = new B();
//        obj_a[1] = new A();
//        obj_a[0].sayHi();
//        obj_a[1].sayHi();
//        
        










//        b[0] = new B();       

        
    }
    
}
