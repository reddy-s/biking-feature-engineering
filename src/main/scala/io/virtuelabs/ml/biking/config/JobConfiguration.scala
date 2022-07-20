package io.virtuelabs.ml.biking.config

import org.apache.spark.SparkFiles
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

import java.io.{File, FileInputStream}
import java.util
import scala.beans.BeanProperty
import scala.collection.JavaConverters._


class IOItem {
  @BeanProperty var name: String = ""
  @BeanProperty var location: String = ""
}

class JobConfig {
  @BeanProperty var inputs: util.ArrayList[IOItem] = new util.ArrayList[IOItem]()
  @BeanProperty var outputs: util.ArrayList[IOItem] = new util.ArrayList[IOItem]()
}

object JobConfiguration {

  private def readConfig: JobConfig = {
    val unparsedYaml = new FileInputStream(new File(SparkFiles.get("job.yaml")))
    val yamlParser = new Yaml(new Constructor(classOf[JobConfig]))
    yamlParser.load(unparsedYaml).asInstanceOf[JobConfig]
  }

  def getJobInputs: Map[String, String] = {
    val config: JobConfig = readConfig
    config.inputs.asScala.toList.map(e => e.name -> e.location ).toMap
  }

  def getJobOutputs: Map[String, String] = {
    val config: JobConfig = readConfig
    config.outputs.asScala.toList.map(e => e.name -> e.location ).toMap
  }
}
