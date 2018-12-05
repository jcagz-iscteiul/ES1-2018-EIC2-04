package jUnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({BaseDadosTest.class, DestaquesTest.class, EmailPostTest.class, FacebookPostTest.class ,FacebookTest.class, GmailTest.class, testeXML.class, 
	TwitterPostTest.class, TwitterTest.class })
public class AllTests {

}
