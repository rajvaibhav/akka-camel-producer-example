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
    "ch.qos.logback" % "logback-classic" % "1.0.7",
    "org.slf4j" % "slf4j-api" % "1.7.2",            
    "org.apache.camel" % "camel-core" % "2.10.0",
    "org.apache.camel" % "camel-jetty" % "2.10.0",
    "org.specs2"          %%  "specs2"        % "2.2.3" % "test"
  )
}

seq(Revolver.settings: _*)

//seq(webSettings :_*)
