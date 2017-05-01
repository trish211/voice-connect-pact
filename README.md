# voice-connect-pact
This is a consumer-driven contracts testing using Pact framework written in Java langauge. The test comprises of the consumer, provider and micro service application. The micro service application can be found in voice-connect-ms repository. As these are all dependent on SBT plugin tools, it is essential to install SBT on your system. The build.sbt file is provided in the package.

You may use the "Advanced REST client" application to test the HTTP request and responses received when running the applications. The ConsumerClient.java file is provided as well.

 # How does it work?

# ConsumerPactTest.java
You must first generate your Pact specification file by running the consumer test application (ConsumerPactTest.java). The ConsumerPactTest.java file includes the RegexMatcher.java file. 

# ProviderPactTest.java
You must create a copy of your Pact file in your provider test application (e.g. placing the Pact file under the "Pacts" directory). Run the provider test application (ProviderPactTest.java) with your Pact file against the micro service application.

# Micro service application
The micro service application can be found in https://github.com/trish211/voice-connect-ms.git.




