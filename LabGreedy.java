/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication9;

/**
 *
 * @author test1
 */
public class JavaApplication9 {
    //array storing denominators
public static Integer Denom[] = {1,5,10,25};
//array storing final values
public static int[] C = new int[100];



//returns minimum number from 4 numbers
static Integer mini(Integer a,Integer b,Integer c,Integer d){
    
if(a<=b&&a<=c&&a<=d)
{
   
    return a;
}
if(b<=a&&b<=c&&b<=d)
{
   
    return b;
}
if(c<=a&&c<=b&&c<=d)
{
    
    return c;
}
else 
{
   
    return d;
}
}


static int DynamicChange(Integer x)
{
    int j =0;
    int a =10000;
    int b =10000;
    int c =10000;
    int d =10000;
    

if(x<0)
    return x+1000000;
if(x==0) 
{
   
return 0;
}
c =1+DynamicChange(x-5);
d =1+DynamicChange(x-1);

a =1+DynamicChange(x-25);
b =(1+DynamicChange(x-10));


if(a<=b&&a<=c&&a<=d)
{
  
    C[x]= 25;
    }
if(b<=a&&b<=c&&b<=d)
{
   
   C[x]= 10;
}
if(c<=a&&c<=b&&c<=d)
{
   
    C[x]= 5;
}
if(d<=a&&d<=b&&d<=c)
{
   
   C[x]= 1;
}





j= mini(a,b,c,d);

    return j;
}



     





static int GreedyChange(Integer a)
{
    int h=0;
    System.out.println("Finding change for "+a+"\n");
    while(a!=0)
    {
for (int i=4; i>0;i--)
{
if(Denom[i-1]<=a)
{
System.out.println(Denom[i-1]+"->");
a= a-Denom[i-1];
h++;
break;
}
}

}
    return h;
}


    public static void main(String[] args) {
      int a= 90;
       int h= GreedyChange(a);
        System.out.println("cost of greedy "+h);
       int s=  DynamicChange(a);
        
        for(int i= a; i>0; i=i)
        {
           
        System.out.println(C[i]);
        i= i-C[i];
        
        }
         System.out.println(s);
    }
    
}
