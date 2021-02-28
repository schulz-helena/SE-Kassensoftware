package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	EindeutigkeitEANTest.class, 
	EinkaufTest.class, 
	KategorieLoeschenTest.class, 
	ProduktErstellenTest.class,
	SucheTest.class })
public class TestSuite {

}
