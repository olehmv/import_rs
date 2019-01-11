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

    // Create a Properties() object to hold the parameters.
    val connectionProperties = new Properties()
    connectionProperties.put("user", param.source.user)
    connectionProperties.put("password", param.source.password)

    // Build full table name for read
    val table =
      s"${param.source.tableCatalog}.${param.source.tableSchema}.${param.source.tableName}"

    val dbDF = spark.read
      .jdbc(param.source.jdbcURL, table, connectionProperties)

    dbDF.createOrReplaceTempView(param.source.tableName)

    val result = spark.sql(param.sink.query)

    result.write.format(param.sink.format).save(s"${param.sink.destination}/${param.source.tableName}")

  }

}
