package io.virtuelabs.ml.biking.config

import org.apache.spark.SparkConf

import java.util.Properties
import scala.io.Source

object SparkConfiguration {

  def get: SparkConf = {
    val sparkConf = new SparkConf
    val props = new Properties()
    props.load(Source.fromResource("spark.conf").bufferedReader())
    props.forEach((k, v) => sparkConf.set(k.toString , v.toString))
    sparkConf
  }
}
