package edu.uniandes.tsdl.mutapk.operators.selector;

import org.apache.commons.math3.distribution.NormalDistribution;

public class TargetPopulation {

	private static final NormalDistribution normalDistribution = new NormalDistribution();

	/**
	 * Static Factory Method
	 */
	private TargetPopulation() {
	}

	public static int calculateSampleSize(int populationSize, int confidenceLevel, int marginError) {
		if (populationSize <= 0) {
			throw new IllegalArgumentException("The population size need to be a value bigger than 0");
		}

		if (confidenceLevel > 100 || confidenceLevel < 0) {
			throw new IllegalArgumentException("The condifence level need to be a value between 0 to 100");
		}

		if (marginError < 0) {
			throw new IllegalArgumentException("The margin error need to be a value equal or bigger than 0");
		}

		double punctuationZ = getPuntuationZ(confidenceLevel);
		double expectedProportion = 0.5;
		double marginErrorP = (double) marginError / 100;

		double numeratorOne = punctuationZ * punctuationZ * expectedProportion * (1 - expectedProportion);
		double denominatorOne = marginErrorP * marginErrorP;
		double firstValue = numeratorOne / denominatorOne;

		double secondValue = firstValue / populationSize;

		return (int) Math.ceil(firstValue / (1 + secondValue));
	}

	private static double getPuntuationZ(int confidenceLevel) {
		double centralArea = (double) confidenceLevel / 100;
		double tailArea = (1 - centralArea) / 2;
		double punctuationZ = normalDistribution.inverseCumulativeProbability(centralArea + tailArea);
		return punctuationZ;
	}
}
