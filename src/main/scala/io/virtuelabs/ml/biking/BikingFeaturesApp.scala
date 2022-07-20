package io.virtuelabs.ml.biking

import io.virtuelabs.ml.biking.config.SparkConfiguration
import io.virtuelabs.ml.biking.spark.input.{Stations, Trips}
import org.apache.log4j.Logger
import org.apache.spark.sql.SparkSession

case object BikingFeaturesApp extends App {

  @transient
  lazy val logger: Logger = Logger.getLogger(getClass.getName)

  logger.info("Creating SparkSession")
  val spark = SparkSession.builder()
    .config(SparkConfiguration.get)
    .getOrCreate()
  logger.info(s"Spark Config => ${spark.conf.getAll.toString()}")

  val stationsDf = Stations.getDataFrame(spark).repartition(3)
  stationsDf.printSchema()
  stationsDf.show(5, truncate = true)

  val tripsDf = Trips.getDataFrame(spark).repartition(3)
  tripsDf.printSchema()
  tripsDf.show(5, truncate = true)

  logger.info("Ending spark session")
  spark.stop()
}
