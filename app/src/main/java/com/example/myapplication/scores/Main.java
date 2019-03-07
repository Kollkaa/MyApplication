package com.example.myapplication.scores;

public class Main {
    public static void main(String...args)
    {
         double startTime = System.currentTimeMillis();
Math.pow(100,2);
double lastTime =System.currentTimeMillis();
System.out.println(String.valueOf(startTime-lastTime)+" "+Math.pow(100,2));
        double startTime1 = System.currentTimeMillis();
        Main.Power(100,2);
        double lastTime1 =System.currentTimeMillis();
        System.out.println(String.valueOf(startTime1-lastTime1)+" "+ Main.Power(100,2));

    }
   public static double Power(double a, int n)
    {
        double result=1 ;
        double aInDegreeOf2=a;
        while (n>0){
            if(n%1==1)
                result*=aInDegreeOf2;
            aInDegreeOf2*=aInDegreeOf2;
            n=n>>1;

        }
        return result;

    }
}
