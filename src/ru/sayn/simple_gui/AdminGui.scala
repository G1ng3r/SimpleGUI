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
import ru.sayn.simple_gui.tables._
import ru.sayn.simple_gui.table.ShootMenTable
import ru.sayn.simple_gui.table.Shoot2MenTable
import ru.sayn.simple_gui.table.ShootNRunTable

object AdminGui extends SwingApplication {
  
  setSystemLookAndFeel()
  
  def startup(args: Array[String]) = {
    val frame = new Frame {
      title = "Админстратор"
      contents = ui
      maximize
      menuBar = new MyMenu
      visible = true
      override def closeOperation() { sys.exit(0) }
    }
  }
  
  def rowset = new JdbcRowSetImpl() {
    setUsername("admin")
    setPassword("123456")
    setUrl("jdbc:postgresql://10.3.4.50/postgres")
  }
  
  lazy val ui = new SplitPane(Orientation.Vertical) {
    val right = new BoxPanel(Orientation.Horizontal) {
      contents += new ShootWomenTable()
      contents += new ShootMenTable()
      contents += new ShootNRunTable()
      contents += new Shoot2MenTable()
    }
    val left = new ScrollPane()
  }
  
  def setSystemLookAndFeel() {
    import javax.swing.UIManager
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
  }

}