package com.epam.db.core.imprt.utils
import com.epam.db.core.imprt.param.Parameter
import org.apache.spark.sql.DataFrame

import scala.xml.{ Elem, XML }

object Utils {

  def parseJDBCConf(file: String): Parameter = {
    val node: Elem = XML.loadFile(file)
    Parameter.fromXML(node)
  }

  def truncateSpaceInStringColumns(dataFrame: DataFrame): DataFrame = {
    import org.apache.spark.sql.functions._
    var dataFrameTrancColumns = dataFrame
    val space                 = " "
    val columnsWithSpace      = dataFrameTrancColumns.columns.filter(column => column.contains(space))

    columnsWithSpace
      .map { column =>
        var columnNoSpace = column.filterNot((x: Char) => x.isWhitespace)
        dataFrameTrancColumns = dataFrameTrancColumns
          .withColumn(columnNoSpace, col(column))
          .drop(column)
      }
    dataFrameTrancColumns

  }

}
