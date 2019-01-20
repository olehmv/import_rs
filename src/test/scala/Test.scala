import com.epam.db.core.imprt.param._

import scala.xml.XML

object Test {

  def main(args: Array[String]): Unit = {

    val source = new Source(
      _jdbcConnectionString = "jdbc:sqlserver://localhost:1433;DatabaseName=AdventureWorks2012;user=sa;password=asus;sendStringParametersAsUnicode=false"
    )

    val sink = new Sink(
      _destination = "src/test/resources/result.csv",
      _query = "select  * from HumanResources.EmployeeDepartmentHistory left join HumanResources.Department on HumanResources.EmployeeDepartmentHistory.DepartmentID = HumanResources.Department.DepartmentID")

    val parameter = new Parameter(_source = source,_sink = sink)


    val elem = XML
      .loadString(
        new scala.xml.PrettyPrinter(80, 1)
          .formatNodes(parameter.toXML)
      )
    XML.save("src/test/resources/parameter.xml", elem, "UTF-8", xmlDecl = true)

  }



}
