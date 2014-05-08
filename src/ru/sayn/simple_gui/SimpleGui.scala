package ru.sayn.simple_gui

import swing._
import swing.Table._
import event._
import java.awt.{Color}
import java.sql.Connection
import java.sql.DriverManager
import java.util.Properties
import javax.sql.rowset.JdbcRowSet
import com.sun.rowset.JdbcRowSetImpl
import javax.sql.RowSet
import javax.swing.table.AbstractTableModel
import javax.swing.Timer
import java.awt.event.ActionListener
import javax.swing.DefaultListSelectionModel
import ru.sayn.simple_gui.table_model.Participants
import ru.sayn.simple_gui.table_model.Disciplines
import ru.sayn.simple_gui.table_model.RowSetTableModel

object SimpleGui extends SimpleSwingApplication {
  
  setSystemLookAndFeel()
  
  def top = new MainFrame {
    title = "Текущие результаты"
    contents = ui
    maximize
    //peer.getGraphicsConfiguration().getDevice().setFullScreenWindow(peer)
    
  }
  
  lazy val ui = new BoxPanel(Orientation.Vertical) {
    val timer = new Timer(0, new ActionListener() {
      def actionPerformed(e: java.awt.event.ActionEvent): Unit = {
        updateTables()
      }
    })
    
    timer setDelay 5000
    timer start
    
    contents += new ScrollPane(participants)
    contents += new ScrollPane(disciplines)

  }
  
  def updateTables() = {
    participants.model.asInstanceOf[RowSetTableModel].rowSet.execute
  }
  
  def rowSet: RowSet = new JdbcRowSetImpl() {
    setUsername("admin")
    setPassword("123456")
    setUrl("jdbc:postgresql://10.3.4.50/postgres")
  }
  
  def defaultHeaderFont(): Font = {
    new Font("Consolas", java.awt.Font.BOLD, 14)
  }
  
  def defaultCellFont(): Font = {
    new Font("Consolas", java.awt.Font.PLAIN, 14)
  }
  
  lazy val participants = new Table() {
    font = defaultCellFont()
    peer.getTableHeader().setFont(defaultHeaderFont())
    model = new Participants(rowSet)
    rowHeight = 25
    
    val columnModel = this.peer.getColumnModel()
    val col0 = columnModel.getColumn(0)
    col0.setHeaderValue("ФИО участника")
    val col1 = columnModel.getColumn(1)
    col1.setHeaderValue("Дата рождения")
    peer.setAutoCreateColumnsFromModel(false)
    peer.repaint()
  }
  
  lazy val disciplines = new Table() {
    font = new Font("Arial", java.awt.Font.ITALIC, 24)
    peer.getTableHeader.setFont(defaultHeaderFont)
    model = new Disciplines(rowSet)
    val columnModel = this.peer.getColumnModel()
    val column = columnModel.getColumn(0)
    column.setHeaderValue("Вид соревнования")
    peer.setAutoCreateColumnsFromModel(false)
    peer.repaint()
  }

  def setSystemLookAndFeel() {
    import javax.swing.UIManager
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
  }

}