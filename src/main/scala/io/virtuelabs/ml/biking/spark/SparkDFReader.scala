package io.virtuelabs.ml.biking.spark

import org.apache.spark.sql.{DataFrame, SparkSession}

trait SparkDFReader {
  def getDataFrame: SparkSession => DataFrame
}
