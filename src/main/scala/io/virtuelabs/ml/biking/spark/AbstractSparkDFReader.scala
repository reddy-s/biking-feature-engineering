package io.virtuelabs.ml.biking.spark

import io.virtuelabs.ml.biking.config.JobConfiguration
import org.apache.log4j.Logger

abstract class AbstractSparkDFReader extends SparkDFReader {
  @transient
  lazy val logger: Logger = Logger.getLogger(getClass.getName)

  val fileKey: String
  val DELIMITER: String = "delimiter"
  val delimiterValue: String

  def getFileLocation: String = {
    val fileLocation = JobConfiguration.getJobInputs.get(fileKey) match {
      case Some(value) => value
      case _ => throw new RuntimeException // TODO: raise appropriate exception
    }
    logger.info(s"file location => $fileLocation")
    fileLocation
  }
}
