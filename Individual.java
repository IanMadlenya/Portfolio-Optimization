package Rumana;

/**
 *
 * @author Quazi
 */

import java.util.Random;

public class Individual implements Constants, Comparable {
	private int Dimension;
	private double Parameters[];
	private double Score;

	double sc;

	//float cR[];
	Random Rnd;

	//double Violation; // for constrained optimization
	//  private double error;

	public Individual(double param[], Random rnd) {
		Parameters = (double[]) param.clone();
		Dimension = Parameters.length; // dimension of each individual
		Rnd = rnd;
		//Violation = 0;
	}
        
        
public double fitness(float cReturn[],float cRisk[],float covM[][])


{
int i,j;
//double f_score=0;


float TotalReturn,TotalRisk;

TotalReturn=0;
TotalRisk=0;
double Std;
double term1=0;
double term2=0;


for(i=0;i<Dimension;i++)
{
    if(Parameters[i]!=0){
        TotalReturn+=Parameters[i]*cReturn[i];
    }
}

for(i=0;i<Dimension;i++){
           
           for(j=0;j<Dimension;j++){
               
               
               term1+=Parameters[i]*Parameters[j]*covM[i][j];
           }
       }

for(i=0;i<Dimension;i++){
    
    term2+=((Parameters[i]*Parameters[i])*(cRisk[i]*cRisk[i]));
}

double sum=(term1+term2);
Std=Math.sqrt(sum);

Score=(TotalReturn-1*Std);

return Score;

}


	/*
	 public void setFitness (double score){
	 this.Score = score;//Math.abs(score);
	 }
	 */
	public int getDimension() {
		return (Dimension);
	}

	public double getScore() {
		return (Score);
	}

	public double getParamAt(int index) {
		return (Parameters[index]);
	}

	public double[] getParameters() {
		return (Parameters);
	}

	///   DE/RAND/1/EXP
	public Individual reproduction(Individual A, Individual B, Individual C) {
		double a[], b[], c[], offSpring[];
		//Random rnd = new Random((long)System.currentTimeMillis());
		//Random rnd = new Random((new Random()).nextLong());
		int i, j;
		offSpring = new double[Dimension];
		// get the parameters of A, B, C for calculation efficiency
		a = A.getParameters();
		b = B.getParameters();
		c = C.getParameters();

		for (i = 0; i < Dimension; ++i) {
			offSpring[i] = this.Parameters[i];
		}

		j = (int) (this.Rnd.nextDouble() * Dimension); // randomly pick the first parameter
		// Exponential crossover
		i = 0;
		do {
			offSpring[j] = c[j] + gAmpFact * (a[j] - b[j]);
			j = (j + 1) % Dimension;
		} while ((this.Rnd.nextDouble() < gCrossRate) && (++i < Dimension));

		/*
		 // binomial crossover
		 for(i=0; i<Dimension; ++i){
		 if( ( this.Rnd.nextDouble() < gCrossRate) || (i == (Dimension -1))){
		 offSpring[j] = c[j] + gAmpFact*(a[j] - b[j] );
		 }
		 else{
		 offSpring[j] = Parameters[j];
		 }
		 j = (j+1) %Dimension;
		 }
		 */
		// Keep solutions feasible: with in the search range

		for (i = 0; i < Dimension; i++) {

			if (offSpring[i] < 0)
				offSpring[i] = 0;
		}
		double offSum = 0;

		for (i = 0; i < Dimension; i++) {
			offSum += offSpring[i];
                        
                        //System.out.println("The population is now");
		}
                
               // System.out.println("The efficient portfolios are");
		for(i=0;i<Dimension;i++){
			if (offSum >= .999) {
				offSpring[i] = offSpring[i] / offSum;
                                //System.out.println("The sum is"+offSum);
                                
                                //System.out.print(offSpring[i]+"   ");
                                
			}
                        
                        //System.out.println(" ");
                        
                }

		return (new Individual(offSpring, this.Rnd));

	}

	public int compareTo(Object indiv) throws ClassCastException {
		if (!(indiv instanceof Individual))
			throw new ClassCastException("An Individual object expected.");
		if (this.getScore() < ((Individual) indiv).getScore()) {
			return (-1);
		} else if (this.getScore() > ((Individual) indiv).getScore()) {
			return (1);
		} else {
			return (0);
		}
	}

}
