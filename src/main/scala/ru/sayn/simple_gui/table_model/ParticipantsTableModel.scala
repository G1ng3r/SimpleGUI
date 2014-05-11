package ru.sayn.simple_gui.table_model

import javax.sql.RowSet

class ParticipantsTableModel(rowSet: RowSet) extends RowSetTableModel(rowSet) {
  rowSet.setCommand("select name, birthdate from champ.participants")
  rowSet.execute()
}