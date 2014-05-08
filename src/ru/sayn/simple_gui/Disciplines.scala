package ru.sayn.simple_gui

import javax.sql.RowSet

class Disciplines(rowSet: RowSet) extends RowSetTableModel(rowSet) {
  rowSet.setCommand("select discipline_name from champ.disciplines")
  rowSet.execute()
}