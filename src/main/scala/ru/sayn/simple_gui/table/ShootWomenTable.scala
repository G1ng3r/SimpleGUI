package ru.sayn.simple_gui.table

import javax.sql.RowSet
import ru.sayn.simple_gui.table_model.ShootWomenTableModel
import ru.sayn.simple_gui.DatabaseManager
import scala.swing.Table

class ShootWomenTable extends Table {
  val rowset = DatabaseManager.rowSet(
                    "select * from shooting.results " + 
                    "join shooting.disciplines on results.discipline_id = disciplines.id " +
                    "join shooting.participants on results.participant_id = participants.id " +
                    "where discipline_id = 1")
  model = new ShootWomenTableModel(rowset)
}