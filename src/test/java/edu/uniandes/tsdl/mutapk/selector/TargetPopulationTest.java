package edu.uniandes.tsdl.mutapk.selector;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.uniandes.tsdl.mutapk.selector.TargetPopulation;

public class TargetPopulationTest {
	
	/**
	 * PS = Population Size;
	 * CLP = Confidence Level Percentage; 
	 * MEP = Margin Error Percentage; 
	 */

	@Test
	public void test100PS99CLP1MEP() {
		int size = TargetPopulation.calculateSampleSize(100, 99, 1);
		assertEquals(100, size);
	}
	
	@Test
	public void test1000PS99CLP10MEP() {
		int size = TargetPopulation.calculateSampleSize(1000, 99, 10);
		assertEquals(143, size);
	}
	
	@Test
	public void test10000PS99CLP10MEP() {
		int size = TargetPopulation.calculateSampleSize(10000, 99, 10);
		assertEquals(164, size);
	}
	
	@Test
	public void test100PS95CLP1MEP() {
		int size = TargetPopulation.calculateSampleSize(100, 95, 1);
		assertEquals(99, size);
	}
	
	@Test
	public void test1000PS95CLP10MEP() {
		int size = TargetPopulation.calculateSampleSize(1000, 95, 10);
		assertEquals(88, size);
	}
	
	@Test
	public void test10000PS95CLP10MEP() {
		int size = TargetPopulation.calculateSampleSize(10000, 95, 10);
		assertEquals(96, size);
	}
	
	@Test
	public void test100PS90CLP1MEP() {
		int size = TargetPopulation.calculateSampleSize(100, 90, 1);
		assertEquals(99, size);
	}
	
	@Test
	public void test1000PS90CLP10MEP() {
		int size = TargetPopulation.calculateSampleSize(1000, 90, 10);
		assertEquals(64, size);
	}
	
	@Test
	public void test10000PS90CLP10MEP() {
		int size = TargetPopulation.calculateSampleSize(10000, 90, 10);
		assertEquals(68, size);
	}
	
	@Test
	public void test100PS85CLP1MEP() {
		int size = TargetPopulation.calculateSampleSize(100, 85, 1);
		assertEquals(99, size);
	}
	
	@Test
	public void test1000PS85CLP10MEP() {
		int size = TargetPopulation.calculateSampleSize(1000, 85, 10);
		assertEquals(50, size);
	}
	
	@Test
	public void test10000PS85CLP10MEP() {
		int size = TargetPopulation.calculateSampleSize(10000, 85, 10);
		assertEquals(52, size);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test0PS85CLP10MEP() {
		TargetPopulation.calculateSampleSize(0, 85, 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void test10000PSLess1CLP10MEP() {
		TargetPopulation.calculateSampleSize(10000, -1, 10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test10000PS101CLP10MEP() {
		TargetPopulation.calculateSampleSize(10000, 101, 10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test10000PS85CLPLess1MEP() {
		TargetPopulation.calculateSampleSize(10000, 85, -1);
	}
}

