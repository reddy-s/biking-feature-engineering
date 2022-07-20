organization := "io.virtuelabs.ml"

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.16"

lazy val root = (project in file("."))
  .settings(
    name := "biking-feature-engineering"
  )

val sparkVersion = "3.3.0"

val configDependencies = Seq(
  "org.yaml" % "snakeyaml" % "1.30"
)

val sparkDependencies = Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion
)

val s3Dependencies = Seq(
  "org.apache.hadoop" % "hadoop-common" % "3.3.3",
  "org.apache.hadoop" % "hadoop-client" % "3.3.3" % "provided",
  "org.apache.hadoop" % "hadoop-aws" % "3.3.3"
)

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "3.2.12" % Test
)

assembly / mainClass := Some("io.virtuelabs.ml.biking.BikingFeaturesApp")

libraryDependencies ++= configDependencies ++ sparkDependencies ++ s3Dependencies ++ testDependencies

assembly / assemblyJarName := s"${name.value}_${scalaBinaryVersion.value}-${sparkVersion}_${version.value}.jar"

ThisBuild / assemblyMergeStrategy  := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}