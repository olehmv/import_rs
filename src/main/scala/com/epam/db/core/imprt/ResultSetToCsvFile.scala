package com.epam.db.core.imprt

import java.io.File
import java.sql.DriverManager

import com.epam.db.core.imprt.param.Parameter
import com.epam.db.core.imprt.utils.Utils
import com.github.tototoshi.csv._

object ResultSetToCsvFile {


  def main(args: Array[String]): Unit = {
    val param: Parameter = Utils.parseConf(args(0))
    val jdbcConnectionString = param.source.jdbcConnectionString
    val destination = param.sink.destination
    val sqlStatement = param.sink.query
    val conn = DriverManager.getConnection(param.source.jdbcConnectionString)
    println(s"Connected to ${jdbcConnectionString}")
    val statement = conn.prepareStatement(sqlStatement)
    val resultSet = statement.executeQuery()
    val columnIndexes = 1 until resultSet.getMetaData.getColumnCount + 1
    val columnNames = columnIndexes.map(index => resultSet.getMetaData.getColumnName(index))
    println(s"Column's  selected: ${columnNames.mkString(",")}")
    var rowCount = 0
    val outputFile = new File(destination)
    val writer = CSVWriter.open(outputFile)
    writer.writeRow(columnNames)
    while (resultSet.next()) {
      val in = for (columnName <- columnNames) yield {
        val value = resultSet.getString(columnName); if (value == null) "null" else value
        value
      }
      writer.writeRow(in)
      rowCount = rowCount + 1
    }
    println(s"Writen ${rowCount} rows")
    conn.close()
  }

}
