package io.virtuelabs.ml.biking.spark.input

import io.virtuelabs.ml.biking.spark.AbstractSparkDFReader
import org.apache.spark.sql.{DataFrame, SparkSession}

object Trips extends AbstractSparkDFReader {

  override val fileKey = "trips"

  override val delimiterValue: String = "|"

  def getDataFrame: SparkSession => DataFrame = (spark: SparkSession) => {
    logger.info(s"Delimiter being used => $delimiterValue")
    val df = spark.read.option("header", "true").csv(getFileLocation)
    logger.info(s"Trips Schema: \n ${df.schema.toString()}")
    df
  }
}
