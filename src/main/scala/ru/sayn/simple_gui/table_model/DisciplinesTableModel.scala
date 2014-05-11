package ru.sayn.simple_gui
package table_model

import javax.sql.RowSet

class DisciplinesTableModel(rowSet: RowSet) extends RowSetTableModel(rowSet) {
  rowSet.setCommand("select discipline_name from champ.disciplines")
  rowSet.execute()
}