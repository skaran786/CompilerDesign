/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsetree2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author manasvi
 */
class PTree{
    PTree parent = null;
    List<PTree> children = new LinkedList<>();
    String node_name=null;
    String node_val=null;
    PTree(String name){
        node_name = name;
    }
}



public class ParseTree_2 {
    
   void printNAryTree(PTree root){
        if(root == null) return;
        Queue<PTree> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int len = queue.size();
            for(int i=0;i<len;i++) { // so that we can reach each level
                PTree node = queue.poll();
                System.out.print(node.node_name + " ");
                node.children.forEach((item) -> {
                    // for-Each loop to iterate over all childrens
                    queue.offer(item);
                });
            }
            System.out.println();
        }
    }
    

    
    
    
    
    
    void generate(){
       /*
                   E
               E   +    T
             T       T  *  F
           F       F         id
         id     id  
       
       */
       
       PTree root;
       root = new PTree("E"); // root E
       root.parent = null;
       //E + T
       root.children.add(new PTree("E")); 
       root.children.get(0).parent = root;
       root.children.add(new PTree("+"));
       root.children.get(1).parent = root;
       root.children.add(new PTree("T"));
       root.children.get(2).parent = root;
       
       
       
       PTree child1 = root.children.get(0);
       
       child1.children.add(new PTree("T"));
       child1.children.get(0).parent = child1;
       
       
       PTree child1_1 = child1.children.get(0);
       child1_1.children.add(new PTree("F"));
       child1_1.children.get(0).parent = child1_1;
       
       
       PTree child1_1_1 = child1_1.children.get(0);
       child1_1_1.children.add(new PTree("id"));
       child1_1_1.children.get(0).parent = child1_1_1;
       
       
       
       
       
       
       
       
       PTree child2 = root.children.get(2);
       child2.children.add(new PTree("T"));
       PTree child2_1 = child2.children.get(0);
       child2_1.children.add(new PTree("F"));
       PTree child2_1_1 = child2_1.children.get(0);
       child2_1_1.children.add(new PTree("id"));


       child2.children.add(new PTree("*"));
       
       
       child2.children.add(new PTree("F"));       
       PTree child2_2 = child2.children.get(2);
       child2_2.children.add(new PTree("id"));       
             
       printNAryTree(root);
   
   }
   
   
   public static void main(String args[]){
       
       ParseTree_2 obj = new ParseTree_2();
       obj.generate();
      
       
//       System.out.println(obj.root.node_name);
//       System.out.println(obj.root.children.get(0).node_name);
//       System.out.println(obj.root.children.get(0).children.get(0).node_name);
//       System.out.println(obj.root.children.get(0).children.get(0).children.get(0).node_name);
//       System.out.println(obj.root.children.get(0).children.get(0).children.get(0).children.get(0).node_name);
//       System.out.println(obj.root.children.get(0).children.get(0).children.get(0).children.get(0).children);
       
   
   
   }
        
    
}
