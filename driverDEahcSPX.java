/*
 * driverDEahcSPX.java
 *
 * Created on November 10, 2007, 10:39 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package Rumana;

import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.Random;

public class driverDEahcSPX implements Constants{
  public driverDEahcSPX() {
  }

 

       
  public static void main(String args[]) throws ClassNotFoundException, IOException {
    DEahcSPX exp;
    int genMax;
    int dataNo;
    int totalData, functNo;
    
        totalData = 25;
        genMax = (gMaxNoEvaluation / gPopSize)+1;
       // System.out.println(genMax);

 double  results1[][] = new double[20][];
        
System.out.println("\n\nOptimization by DE with AHC using SPX\n");
System.out.println("Parameter No: " + gParamNo + " PopSize: " +gPopSize + "\n\n");

     for ( dataNo=0;dataNo < totalData;++dataNo){
     
     
        //  System.out.print("Data No " + dataNo);
         // System.out.println("\n\nOperating Data No: " + gInitGenFileName[dataNo]);
          DEahcSPX ob = new DEahcSPX(genMax, gParamNo, gPopSize);
          ob.diffEvolAHCspx();
     }
    
        System.out.println("Processing data....");        
        System.out.println("\n\nOptimization by DE with AHC using SPX\n");        
        //String outputFileName = "DEahcSPX_N" + gParamNo + "_P" + gPopSize +"_" ;

        System.out.println("Complete");
                    
  }
	




  }





