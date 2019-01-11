import com.epam.db.core.imprt.param._
import org.apache.spark.sql.ColumnName

import scala.xml.XML

object Test {

  def main(args: Array[String]): Unit = {

    val source = new Source(_jdbcURL = "jdbc:sqlserver://192.168.99.100:1433;DatabaseName=AdventureWorks2012",
               _tableSchema="HumanResources",_tableName="Department",_user = "sa",_password = "SA1_sa_sa")

    val sink = new Sink(_destination = "src/test/resources",_format = "csv",_query = "(SELECT TOP 10 * FROM HumanResources.Department ) AS d")

    val parameter = new Parameter(_source = source,_sink = sink)


    val elem = XML
      .loadString(
        new scala.xml.PrettyPrinter(80, 1)
          .formatNodes(parameter.toXML)
      )
    XML.save("src/test/resources/parameter.xml", elem, "UTF-8", xmlDecl = true)

  }



}
