package com.epam.db.core.imprt.param

import scala.xml.NodeSeq
class Source(_jdbcURL: String, _tableCatalog: String,_tableSchema:String,_tableName:String, _user: String, _password: String) {

  def jdbcURL  = _jdbcURL
  def tableCatalog       = _tableCatalog
  def tableSchema:String=_tableSchema
  def tableName=_tableName
  def user     = _user
  def password = _password

  def jdbcURL_  = _jdbcURL
  def tableCatalog_       = _tableCatalog
  def tableSchema_ = _tableSchema
  def tableName_ = _tableName
  def user_     = _user
  def password_ = _password

  def toXML =
    <source jdbcurl={_jdbcURL} tablecatalog={_tableCatalog} tableschema={_tableSchema} tablename={_tableName} user={_user} password={_password}/>

}

object Source {

  def fromXML(node: NodeSeq): Source =
    new Source(
      _jdbcURL = (node \ "@jdbcurl") text,
      _tableCatalog = (node \ "@tablecatalog") text,
      _tableSchema = (node \ "@tableschema") text,
      _tableName= (node \ "@tablename") text,
      _user = (node \ "@user") text,
      _password = (node \ "@password") text
    )
}
