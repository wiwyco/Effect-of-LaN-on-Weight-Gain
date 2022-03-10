import org.apache.commons.math3.distribution.TDistribution;

public class Main {

	public static void main(String [] args)
	{
		double [][] c1 ={			{	10.2,	128.56,		40.848,	3.414,		0,		319.266,	94.495,		1409	},
									{	7.29,	124.43,		47.45,	3.219,		1,		335.772,	279.675,	509		},
									{	7.57,	98.517,		56.429,	3.613,		1,		343.59,		412.821,	2003	},
									{	3.42,	208.26,		55.051,	3.857,		0,		271.717,	148.485,	1084	},
									{	5.82,	80.685,		48.352,	3.587,		1,		402.941,	335.294,	1848	},
									{	10.92,	26.41,		67.635,	4.514,		1,		380.808,	274.747,	1841	},
									{	5.21,	3,			42.969,	4.231,		0,		400,		169.369,	2716	},
									{	13.47,	3,			72.864,	5.324,		1,		328.571,	328.571,	4622	},
									{	8.64,	49.142,		66.746,	4.633,		1,		445.833,	398.958,	1744	},
									{	6.05,	11.994,		56.816,	4.849,		0,		159.048,	144.762,	7253	}};
									
		double [][] c2 ={			{	5.02,	87.838,		31.063,	3.791,		0,		228.448,	134.483,	1437	},
									{	6.67,	191.22,		41.408,	3.923,		0,		231.183,	220.43,		2541	},	
									{	8.17,	67.7,		47.573,	4.489,		0,		226.563,	141.406,	346		},
									{	2.79,	41.017,		34.947,	4.161,		0,		323.077,	199.038,	5837	},
									{	8.13,	21.817,		41.94,	4.416,		0,		500,		190.361,	877		},
									{	6.34,	23.403,		40.5,	4.89,		0,		280,		118.333,	1649	},
									{	6.32,	70.47,		28.95,	4.946,		0,		299.174,	153.719,	728		},
									{	3.97,	56.718,		21.846,	4.004,		0,		461.25,		230,		6048	}};
									
		double [][] c3 ={			{	9.89,	42.132,		71.552,	3.387,		1,		378.704,	328.704,	5752	},
									{	9.58,	48.238,		61.453,	3.451,		0,		379.091,	227.273,	1256	},
									{	11.2,	92.191,		85.978,	3.501,		1,		366.129,	383.871,	244		},
									{	9.05,	51.999,		64.827,	4.24,		0,		392.373,	250,		931		},
									{	12.33,	12.252,		81.6,	3.479,		1,		466.346,	470.192,	3582	},
									{	9.39,	3,			87.257,	5.94,		1,		259.615,	413.462,	2657	},
									{	10.88,	132.4,		70.441,	4.586,		0,		348.78,		126.016,	153		},
									{	9.37,	8.615,		84.415,	4.873,		1,		335.652,	286.957,	4482	},	
									{	17.4,	66.679,		81.636,	7.177,		1,		435.644,	405.941,	6702	}};
		
		
		
		String [] labels = {"DM", "DM", "DM", "DM", "DM", "DM", "DM", "DM", "DM", "DM",
							"LD", "LD", "LD", "LD", "LD", "LD", "LD", "LD", 
							"LL", "LL", "LL", "LL", "LL", "LL", "LL", "LL", "LL"};
		
		double [] sigLevels = {	0.2,	0.1,	0.05,	0.02,	0.01,	0.002,	0.001};
		String [] vars = {"BMGain", "Corticosterone", "DayPct", "Consumption", "GluconeInt", "GTT15", "GTT120", "Activity"};
		/*for(int i = 0; i < 8; i++)
		
		{
			for(int j = 0; j < 7; j++)
			{
				twoSampleDistHypothesisTest(getColumn(c3, i), getColumn(c1, i), sigLevels[j], vars[i]);
			}
		}
		*/
		double calConsump1 [] = new double [10];
		double calConsump2 [] = new double [8];
		double calConsump3 [] = new double [9];
		
		for(int i = 0; i < 10;i++)
		{
			calConsump1[i] = getColumn(c1, 3)[i] * getColumn(c1, 2)[i] * 0.01;
			System.out.println(calConsump1[i]);
			
			if(i < 8)
			{
				calConsump2[i] = getColumn(c2, 3)[i] * getColumn(c2, 2)[i] * 0.01;
			}
			if(i < 9)
			{
				calConsump3[i] = getColumn(c3, 3)[i] * getColumn(c3, 2)[i] * 0.01;
			}
		}
		
		for(int j = 0; j < 7; j++)
		{
			twoSampleDistHypothesisTest(calConsump3, calConsump1, sigLevels[j], "CalConsump");
		}
		
		
	}
	
	public static double[] getColumn(double[][] dataValues, int index){
	    double[] column = new double[dataValues.length]; // Here I assume a rectangular 2D array! 
	    for(int i=0; i<column.length; i++){
	       column[i] = dataValues[i][index];
	    }
	    return column;
	}

	public static void twoSampleDistHypothesisTest(double [] dataSet1, double dataSet2[], double sl, String varName)
	{
		int n1 = dataSet1.length;
		int n2 = dataSet2.length;
		
		double sum1 = 0;
		double sum2 = 0;
		
		for (int i = 0; i < n1; i++)
		{
			sum1 += dataSet1[i];
		}
		
		double avg1 = sum1/n1;
		
		for (int i = 0; i < n2; i++)
		{
			sum2 += dataSet2[i];
		}
		
		double avg2 = sum2/n2;
		
		sum1 = 0;
		sum2 = 0;
		
		for(int i = 0; i < n1; i++)
		{
			sum1 += Math.pow((dataSet1[i] - avg1), 2);
		}
		
		double var1 = sum1/(n1 - 1);
		
		for(int i = 0; i < n2; i++)
		{
			sum2 += Math.pow((dataSet2[i] - avg2), 2);
		}
		
		double var2 = sum2/(n2 - 1);
		
		double DF = Math.floor((Math.pow((var1/n1) + (var2/n2), 2))/
									((Math.pow(var1/n1, 2)/(n1-1)) + (Math.pow(var2/n2, 2)/(n2-1))));
		
		TDistribution tdist = new TDistribution(DF);
		
		tdist.cumulativeProbability(0.01);
		
		double [] sigLevels = {	0.2,	0.1,	0.05,	0.02,	0.01,	0.002,	0.001};
		double [][]tTable ={ {	3.078,	6.314,	12.706,	31.821,	63.656,	318.289,	636.578},
							{	1.886,	2.920,	4.303,	6.965,	9.925,	22.328,	31.600},
							{	1.638,	2.353,	3.182,	4.541,	5.841,	10.214,	12.924},
							{	1.533,	2.132,	2.776,	3.747,	4.604,	7.173,	8.610},
							{	1.476,	2.015,	2.571,	3.365,	4.032,	5.894,	6.869},
							{	1.440,	1.943,	2.447,	3.143,	3.707,	5.208,	5.959},
							{	1.415,	1.895,	2.365,	2.998,	3.499,	4.785,	5.408},
							{	1.397,	1.860,	2.306,	2.896,	3.355,	4.501,	5.041},
							{	1.383,	1.833,	2.262,	2.821,	3.250,	4.297,	4.781},
							{	1.372,	1.812,	2.228,	2.764,	3.169,	4.144,	4.587},
							{	1.363,	1.796,	2.201,	2.718,	3.106,	4.025,	4.437},
							{	1.356,	1.782,	2.179,	2.681,	3.055,	3.930,	4.318},
							{	1.350,	1.771,	2.160,	2.650,	3.012,	3.852,	4.221},
							{	1.345,	1.761,	2.145,	2.624,	2.977,	3.787,	4.140},
							{	1.341,	1.753,	2.131,	2.602,	2.947,	3.733,	4.073},
							{	1.337,	1.746,	2.120,	2.583,	2.921,	3.686,	4.015},
							{	1.333,	1.740,	2.110,	2.567,	2.898,	3.646,	3.965},
							{	1.330,	1.734,	2.101,	2.552,	2.878,	3.610,	3.922},
							{	1.328,	1.729,	2.093,	2.539,	2.861,	3.579,	3.883},
							{	1.325,	1.725,	2.086,	2.528,	2.845,	3.552,	3.850},
							{	1.323,	1.721,	2.080,	2.518,	2.831,	3.527,	3.819},
							{	1.321,	1.717,	2.074,	2.508,	2.819,	3.505,	3.792},
							{	1.319,	1.714,	2.069,	2.500,	2.807,	3.485,	3.768},
							{	1.318,	1.711,	2.064,	2.492,	2.797,	3.467,	3.745},
							{	1.316,	1.708,	2.060,	2.485,	2.787,	3.450,	3.725},
							{	1.315,	1.706,	2.056,	2.479,	2.779,	3.435,	3.707},
							{	1.314,	1.703,	2.052,	2.473,	2.771,	3.421,	3.689},
							{	1.313,	1.701,	2.048,	2.467,	2.763,	3.408,	3.674},
							{	1.311,	1.699,	2.045,	2.462,	2.756,	3.396,	3.660},
							{	1.310,	1.697,	2.042,	2.457,	2.750,	3.385,	3.646}};
		
		double tVal = 0;
		
		for(int i = 0; i < 7; i++)
		{
			if (sl == sigLevels[i])
			{
				tVal = tTable[(int) DF - 1][i];
			}
		}
		
		double CILowerBound = (avg1 - avg2) - ((tVal) * Math.pow((var1/n1) + (var2/n2), 0.5));
		
		double CIUpperBound = (avg1 - avg2) + ((tVal) * Math.pow((var1/n1) + (var2/n2), 0.5));
		
		System.out.println("We can say with " + ((1 - sl) * 100) + "% Confidence that the true mean difference of " + varName + " is between " + CILowerBound + " and " + CIUpperBound + ".");
	}
	
	/*public static void twoSampleDistHypothesisTest1(double [][] data, String [] labelVector, char altHypothesis, double invSignificanceLevel)
	{
		int numFeatures = data[0].length;
		int numInstances = labelVector.length;
		
		//System.out.println(numFeatures + " " + numInstances);
		
		//find the number of classes in the labels
		int numClasses = 1;
		
		for (int i = 0; i < numInstances - 1; i++)
		{
			if (!(labelVector[i].equals(labelVector[i+1])))
			{
				numClasses++;
			}
		}
		
		//find the number of instances in a class and the range
		int [] numClassInstances = new int [numClasses];
		int [] classInstanceLoc = new int [numClasses + 1];
		classInstanceLoc[0] = 0;
		classInstanceLoc[numClasses] = numInstances;
		int temp = 0;
		
		for (int i = 1; i < numInstances; i++)
		{

			numClassInstances[temp]++;
			if (!(labelVector[i].equals(labelVector[i-1])))
			{
				classInstanceLoc[temp + 1] = i;
				temp++;
			}
		}
		
		numClassInstances[numClasses - 1]++;
		
		
		//System.out.println(numClasses);
		
		/*for (int i = 0; i < 3; i++)
		{
			System.out.println(numClassInstances[i]);
		}
		for (int i = 0; i < 4; i++)
		{
			System.out.println(classInstanceLoc[i]);
		}
		
		double [][] sampleMean = new double [numClasses][numFeatures];
		double [][] sampleVariance = new double [numClasses][numFeatures];
		
		for (int i = 0; i < numClasses; i++)
		{	
			for (int k = 0; k < numFeatures; k++)
			{
				double sum = 0;
				for(int j = classInstanceLoc[i]; j < classInstanceLoc[i + 1]; j++)
				{
					//System.out.println(i + " " + j);
					
					sum += data[j][k];
				}
				
				sampleMean[i][k] = sum/numClassInstances[i];
				//System.out.println(sampleMean[i][k]);
				
				double varSum = 0;
				for(int j = classInstanceLoc[i]; j < classInstanceLoc[i + 1]; j++)
				{
					//System.out.println(i + " " + j);
					
					varSum += Math.pow((data[j][k] - sampleMean[i][k]), 2);
				}
				
				sampleVariance[i][k] = varSum/(numClassInstances[i] - 1);
				System.out.println(sampleVariance[i][k]);
			}
		}
		

		int [] degreesOfFreedom = new int[numClasses];
	}*/
		
	
	
	
}
	
	

		


		


