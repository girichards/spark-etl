name := "spark-etl"

version := "0.1"

scalaVersion := "2.11.3"

// https://github.com/neo4j-contrib/neo4j-spark-connector

resolvers += "Spark Packages Repo" at "http://dl.bintray.com/spark-packages/maven"

val sparkV = "1.6.3"
libraryDependencies ++= Seq(
  "com.typesafe.scala-logging" %% "scala-logging"              % "3.7.2",
  "ch.qos.logback"             % "logback-classic"             % "1.2.3",
  "org.apache.spark"           %% "spark-core"                 %  sparkV , // % "provided",
  "org.apache.spark"           %% "spark-sql"                  %  sparkV , // % "provided",
  "org.apache.spark"           %% "spark-streaming"            %  sparkV , // % "provided",
  "com.datastax.spark"         %% "spark-cassandra-connector"  % "1.6.8",
  "neo4j-contrib"              % "neo4j-spark-connector"       % "2.0.0-M2"
)

