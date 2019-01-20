import AssemblyKeys._

name := "jdbc_result_set_to_csv_file"

version := "0.1"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq(
  "com.github.tototoshi" %% "scala-csv" % "1.3.5",
  "org.scala-lang" % "scala-xml" % "2.11.0-M4"
)
// This statement includes the assembly plug-in capabilities
assemblySettings
// Configure JAR used with the assembly plug-in
jarName in assembly := "jdbc_result_set_to_csv_file.jar"