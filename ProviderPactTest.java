

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

//HTTP test
@RunWith(PactRunner.class)  //Say JUnit to run tests with custom Runner
@Provider("voice_connect_provider")  //Set up name of tested provider
@PactFolder("src/test/resources/pacts")        //Points where to find pacts
public class ProviderPactTest {

    @BeforeClass
    public static void setUpService() {
        //Run DB, create schema
        //Run service
    }

    @Before
    public void before() {
    }

    //Setup to be done to enter state
    @State("test state 1")
    public void toDefaultState() {
        //System.out.println("Now service in default state");
    }

    @TestTarget
    public final Target target = new HttpTarget(9000);

}