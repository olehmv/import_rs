package com.epam.db.core.imprt.param

import scala.xml.NodeSeq

class Parameter(_source: Source, _sink: Sink) {
  def source = _source
  def sink   = _sink

  def source_ = _source
  def sink_   = _sink

  def toXML =
    <parameter>
      {_source.toXML}
      {_sink.toXML}
    </parameter>

}

object Parameter {

  def fromXML(node: NodeSeq): Parameter =
    new Parameter(
      _source = Source.fromXML(node \ "source"),
      _sink = Sink.fromXML(node \ "sink")
    )

}
