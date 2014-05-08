package ru.sayn.simple_gui.table_model

import javax.swing.table._
import java.sql.ResultSetMetaData
import java.sql.SQLException
import java.sql.Types
import javax.sql.RowSet
import javax.sql.RowSetEvent
import javax.sql.RowSetListener;

class RowSetTableModel(var rowSet: RowSet) extends AbstractTableModel with RowSetListener {
  
  rowSet.addRowSetListener(this)
  
  def cursorMoved(event: RowSetEvent) = {
  }

  override def getColumnClass(column: Int): Class[_] = {
    var cname: String = ""
    var _type: Int = 0

    try {
      val meta: ResultSetMetaData = rowSet.getMetaData();
      if (meta == null) {
        return null;
      }
      _type = meta.getColumnType(column + 1);
    } catch {
      case e: SQLException => 
        e.printStackTrace()
        return super.getColumnClass(column)
    }
    
    _type match {
      case Types.BIT => {
        cname = "java.lang.Boolean"
      }
      case Types.TINYINT => {
        cname = "java.lang.Byte"
      }
      case Types.SMALLINT => {
        cname = "java.lang.Short"
      }
      case Types.INTEGER => {
        cname = "java.lang.Integer"
      }
      case Types.BIGINT => {
        cname = "java.lang.Long"
      }
      case Types.FLOAT =>
      case Types.REAL => {
        cname = "java.lang.Float"
      }
      case Types.DOUBLE => {
        cname = "java.lang.Double"
      }
      case Types.NUMERIC => {
        cname = "java.lang.Number"
      }
      case Types.DECIMAL => {
        cname = "java.math.BigDecimal"
      }
      case Types.CHAR => 
      case Types.VARCHAR => 
      case Types.LONGVARCHAR => {
        cname = "java.lang.String"
      }
      case Types.DATE => {
        cname = "java.sql.Date"
      }
      case Types.TIME => {
        cname = "java.sql.Time"
      }
      case Types.TIMESTAMP => {
        cname = "java.sql.Timestamp"
      }
      case Types.BINARY =>
      case Types.VARBINARY =>
      case Types.LONGVARBINARY => {
        cname = "byte[]";
      }
      case Types.OTHER =>
      case Types.JAVA_OBJECT => {
        cname = "java.lang.Object"
      }
      case Types.CLOB => {
        cname = "java.sql.Clob"
      }
      case Types.BLOB => {
        cname = "java.ssql.Blob"
      }
      case Types.REF => {
        cname = "java.sql.Ref"
      }
      case Types.STRUCT => {
        cname = "java.sql.Struct"
      }
      case _ => {
        return super.getColumnClass(column)
      }
    }
    try {
      return Class.forName(cname)
    } catch {
      case e: Exception =>
        e.printStackTrace()
        return super.getColumnClass(column)
    }
  }

  def getColumnCount(): Int = {
    try {
      val meta: ResultSetMetaData = rowSet.getMetaData()

      if (meta == null) {
        return 0
      }
      return meta.getColumnCount()
    } catch {
      case (e: SQLException) => return 0
    }
  }

  override def getColumnName(col: Int): String = {
    try {
      val meta: ResultSetMetaData = rowSet.getMetaData()

      if (meta == null) {
        return null
      }
      return meta.getColumnName(col + 1)
    } catch {
      case (e: SQLException) => return "Error"
    }
  }

  def getRowCount(): Int = {
    try {
      if (rowSet.last()) {
        return (rowSet.getRow())
      } else {
        return 0
      }
    } catch {
      case (e: SQLException) => return 0
    }
  }

  def getValueAt(row: Int, col: Int): Object = {
    try {
      if (!rowSet.absolute(row + 1)) {
        return null
      }
      return rowSet.getObject(col + 1)
    } catch {
      case (e: SQLException) => return null
    }
  }

  def rowChanged(event: RowSetEvent): Unit = {
    try {
      val row = rowSet.getRow()

      if (rowSet.rowDeleted()) {
        fireTableRowsDeleted(row, row)
      } else if (rowSet.rowInserted()) {
        fireTableRowsInserted(row, row)
      } else if (rowSet.rowUpdated()) {
        fireTableRowsUpdated(row, row)
      }
    } catch {
      case (e: SQLException) =>
    }
  }

  def rowSetChanged(event: RowSetEvent): Unit = {
    fireTableStructureChanged()
  }

  override def setValueAt(value: Object, row: Int, column: Int): Unit = {
    try {
      if (!rowSet.absolute(row + 1)) {
        return
      }
      rowSet.updateObject(column + 1, value)
    } catch {
      case (e: SQLException) =>
    }
  }
}
