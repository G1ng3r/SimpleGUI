package ru.sayn.simple_gui

import javax.sql.RowSet

class Participants(rowSet: RowSet) extends RowSetTableModel(rowSet) {
  rowSet.setCommand("select name, birthdate from champ.participants")
  rowSet.execute()
}