name := "akka-camel-proxy"

organization := "com.example"

version := "0.1"

scalaVersion := "2.10.3"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

resolvers ++= Seq(
  "spray repo" at "http://repo.spray.io/"
)

libraryDependencies ++= {
  val akkaV = "2.2.3"
  val sprayV = "1.2.0"
  Seq(
    "io.spray"            %   "spray-can" % sprayV,
    "io.spray"            %   "spray-routing" % sprayV,
    "io.spray"            %   "spray-testkit" % sprayV,
    "io.spray"		  %%   "spray-json"    % "1.2.5",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV,
    "com.typesafe.akka"   %%  "akka-testkit"  % akkaV,
    "com.typesafe.akka"   %%  "akka-slf4j"    % akkaV,
    "com.typesafe.akka"   %%  "akka-camel"    % akkaV,
    //"junit" % "junit" % "4.10",
    "org.apache.camel" % "camel-core" % "2.10.0",
    "org.apache.camel" % "camel-jetty" % "2.10.0",
    //"org.mockito" % "mockito-all" % "1.8.0",
    //"org.scalatest" % "scalatest_2.10.0-M7" % "1.9-2.10.0-M7-B1",
    "org.specs2"          %%  "specs2"        % "2.2.3" % "test"
    //"org.eclipse.jetty" % "jetty-webapp" % "9.1.0.v20131115" % "container",
    //"org.eclipse.jetty" % "jetty-plus"   % "9.1.0.v20131115" % "container"
    //"org.apache.tomcat.embed" % "tomcat-embed-core" % "7.0.29" % "container",
    //"org.apache.tomcat.embed" % "tomcat-embed-logging-juli" % "7.0.29" % "container",
    //"org.apache.tomcat.embed" % "tomcat-embed-jasper" % "7.0.29" % "container",
    //"org.eclipse.jdt.core.compiler" % "ecj" % "4.2.2" % "container"
  )
}

seq(Revolver.settings: _*)

//seq(webSettings :_*)
