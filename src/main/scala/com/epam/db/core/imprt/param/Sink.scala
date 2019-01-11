package com.epam.db.core.imprt.param

import scala.xml.NodeSeq

class Sink(_format: String, _query: String, _destination: String) {

  def format       = _format
  def query = _query
  def destination  = _destination

  def format_       = _format
  def query_ = _query
  def destination_  = _destination

  def toXML =
    <sink format={_format} destination={_destination} query={query}/>

}

object Sink {

  def fromXML(node: NodeSeq): Sink =
    new Sink(_format = (node \ "@format") text,
             _query = (node \ "@query") text,
             _destination = (node \ "@destination") text)

}
