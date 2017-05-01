//Consumer Test
//To set up expectations on a mock service provider
//The mock service provider returns the expected responses
//A Pact file is written containing the requests ans responses
package consumer;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRule;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.matchers.RegexpMatcher;
import au.com.dius.pact.model.PactFragment;
import client.ConsumerClient;
import org.apache.http.entity.ContentType;
import org.apache.xerces.impl.xpath.regex.RegularExpression;
import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static consumer.RegexMatcher.matchesRegex;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ConsumerPactTest {

    @Rule
    public PactProviderRule mockProvider = new PactProviderRule("voice_connect_provider", "localhost", 9000, this);

    //Pact method that returns a pact fragment for the provider and consumer
    @Pact(provider = "voice_connect_provider", consumer = "voice_connect_consumer")

    public PactFragment createFragment(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("content-type", ContentType.APPLICATION_JSON.toString());

        //type matching using PactDslJsonBody, then verify the pact file on the provider side
        PactDslJsonBody body = new PactDslJsonBody()

                .numberValue("waitTimeInMills", 300000)

                .stringMatcher("voiceConnectId", "[A-Z0-9]{11}");

                //.stringMatcher("voiceConnectId", "([^\\s]+)");


        return builder
                .given("test state")
                .uponReceiving("a request for wait time & virtual queue ID")
                    .path("/")
                    .method("POST")
                    .headers(headers)
                    .body("{\"segment\":\"Consumer\",\n" +
                            "\"reason\":\"EnquireBill\",\n" +
                        "\"service\":\"WhitePages\"}")

                .willRespondWith()
                    .status(200)
                    .body(body)
                .toFragment();
    }

    @Test
    @PactVerification()
    public void runTest() throws IOException {
        Map response = new ConsumerClient("http://localhost:9000").post("/", "{\"segment\":\"Consumer\",\n" +
                "\"reason\":\"EnquireBill\",\n" +
                "\"service\":\"WhitePages\"}", ContentType.APPLICATION_JSON);
        assertEquals(response.get("waitTimeInMills"), 300000);

        assertThat((String)response.get("voiceConnectId"), RegexMatcher.matchesRegex("[A-Z0-9]{11}"));
        //assertThat((String)response.get("voiceConnectId"), RegexMatcher.matchesRegex("([^\\s]+)"));
        //assertThat((String)response.get("voiceConnectId"), containsString("1"));
    }
}
