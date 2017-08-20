package com.github.girichards

import com.typesafe.scalalogging.LazyLogging
import java.util.UUID

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import com.datastax.spark.connector._
import org.apache.spark.rdd.RDD


import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import com.datastax.spark.connector._

import org.apache.spark.sql.functions._


object App extends LazyLogging {

  val conf = new SparkConf(true)
    .setMaster("local")
    .setAppName("ETL")
    .set("spark.cassandra.connection.host", "localhost")

  val sc = new SparkContext(conf)
  val sqlContext = new SQLContext(sc)


  def main(args: Array[String]) = {


    val SampleKeySpace = "sample"
    val PersonTable = "person"
    val ReportsToTable = "reports_to"
    val ExternalDepartmentMappingsTable = "external_map"
    val Person_Id = "person_id"

    def getDataframe(optionsMap: Map[String, String]) = sqlContext.read.format("org.apache.spark.sql.cassandra").options(optionsMap).load.cache


    val df1 = getDataframe(Map("table" -> ReportsToTable, "keyspace" -> SampleKeySpace))
    val df2 = getDataframe(Map("table" -> PersonTable, "keyspace" -> SampleKeySpace))
    val df3 = getDataframe(Map("table" -> ExternalDepartmentMappingsTable, "keyspace" -> SampleKeySpace))

    val j = df3.join(df2, Seq(Person_Id)).join(df1, Seq(Person_Id)).cache()

    df1.printSchema()
    df2.printSchema()
    df3.printSchema()

    j.printSchema()



    import org.neo4j.spark._

    val neo = Neo4j(sc)



    sc.stop()

  }

  /*



  Person





  Department

   */
}
