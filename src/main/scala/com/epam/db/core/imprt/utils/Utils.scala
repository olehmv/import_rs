package com.epam.db.core.imprt.utils

import com.epam.db.core.imprt.param.Parameter

import scala.xml.{Elem, XML}

object Utils {

  def parseConf(file: String): Parameter = {
    val node: Elem = XML.loadFile(file)
    Parameter.fromXML(node)
  }

}
