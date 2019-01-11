package com.epam.db.core.imprt

import java.util.Properties

import com.epam.db.core.imprt.param.Parameter
import com.epam.db.core.imprt.utils._
import org.apache.spark.sql.SparkSession

object ImportMSSQL {

  def main(args: Array[String]): Unit = {
    if (args.size < 1) {
      println(
        "Usage: \n" +
        "Provide parameter.xml file"
      )
      sys.exit(1)
    }
    // Get  parameters
    val param: Parameter = Utils.parseJDBCConf(args(0))

    // Create spark session
    val spark = SparkSession
      .builder()
      .master("local[*]")
      .appName("")
      .getOrCreate()

    spark.read
      .format("jdbc")
      .option("url", param.source.jdbcURL)
      .option("dbtable",param.sink.query)
      .option("user", param.source.user)
      .option("password", param.source.password)
      .load()
      .write.format(param.sink.format).save(s"${param.sink.destination}/${param.source.tableName}")

  }

}
