name := "project-provider"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "junit" % "junit" % "4.11",
  "au.com.dius" % "pact-jvm-provider-junit_2.11" % "3.2.9",
  "org.apache.httpcomponents" % "httpclient" % "4.5.1",
  "com.google.guava" % "guava-gwt" % "18.0",
  "org.slf4j" % "slf4j-simple" % "1.7.12"
)

projectDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.1" % Test,
  "com.novocode" % "junit-interface" % "0.11" % Test
)
