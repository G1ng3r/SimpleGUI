package ru.sayn.simple_gui.table

import javax.sql.RowSet
import scala.swing.Table
import ru.sayn.simple_gui.table_model.Shoot2MenTableModel
import ru.sayn.simple_gui.DatabaseManager

class Shoot2MenTable extends Table {
  val rowset = DatabaseManager.rowSet(
                    "select * from shooting.results " + 
                    "join shooting.disciplines on results.discipline_id = disciplines.id " +
                    "join shooting.participants on results.participant_id = participants.id " +
                    "where discipline_id = 3")
  model = new Shoot2MenTableModel(rowset)
}