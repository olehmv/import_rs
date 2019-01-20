package com.epam.db.core.imprt.param

import scala.xml.NodeSeq

class Sink(_query: String, _destination: String) {

  def query = _query

  def destination = _destination

  def query_ = _query

  def destination_ = _destination

  def toXML =
      <sink destination={_destination} query={query}/>

}

object Sink {

  def fromXML(node: NodeSeq): Sink =
    new Sink(
      _query = (node \ "@query") text,
      _destination = (node \ "@destination") text)

}
