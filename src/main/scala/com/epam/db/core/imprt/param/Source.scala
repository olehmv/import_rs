package com.epam.db.core.imprt.param

import scala.xml.NodeSeq

class Source(_jdbcConnectionString: String) {

  def jdbcConnectionString = _jdbcConnectionString

  def jdbcConnectionString_ = _jdbcConnectionString


  def toXML =
      <source jdbcConnectionString={_jdbcConnectionString}/>

}

object Source {

  def fromXML(node: NodeSeq): Source =
    new Source(
      _jdbcConnectionString = (node \ "@jdbcConnectionString") text
    )
}
