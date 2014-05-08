package ru.sayn.simple_gui.table_model

import javax.sql.RowSet
import ru.sayn.simple_gui.table_model.RowSetTableModel

class Disciplines(rowSet: RowSet) extends RowSetTableModel(rowSet) {
  rowSet.setCommand("select discipline_name from champ.disciplines")
  rowSet.execute()
}