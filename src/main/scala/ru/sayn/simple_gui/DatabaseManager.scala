package ru.sayn.simple_gui

import com.sun.rowset.JdbcRowSetImpl
import javax.sql.RowSet
import com.typesafe.config.ConfigFactory

object DatabaseManager {
  
  var pool = Seq[RowSet]()
  
  def rowSet(sql: String) = {
    val config = ConfigFactory.load("config")
    val rs = new JdbcRowSetImpl() {
      setUsername(config.getString("db.user"))
      setPassword(config.getString("db.password"))
      setUrl(config.getString("db.url"))
      setCommand(sql)
      execute
    }
    pool ++= Seq(rs)
    rs
  }
  
  def refresh() = {
    pool.foreach(rowSet => rowSet.execute)
  }
  
}