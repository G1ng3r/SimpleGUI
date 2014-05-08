package ru.sayn.simple_gui.table

import scala.swing.Table
import javax.sql.RowSet
import ru.sayn.simple_gui.table_model.RowSetTableModel

class RowSetTable(val rowset: RowSet) extends Table {
  model = new RowSetTableModel(rowset)
}