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
import javax.swing.JDesktopPane

object AdminGui extends SwingApplication {
  
  setSystemLookAndFeel()
  
  def startup(args: Array[String]) = {
    val frame = new Frame {
      title = "Админстратор"
      contents = ui
      maximize
      menuBar = menu
      visible = true
    }
  }
  
  lazy val menu = new MenuBar {
    contents += new Menu("File") {
      contents += new MenuItem(Action("Exit") {
        sys.exit(0)
      })
      contents += new MenuItem(Action("Add participant") {
        
      })
    }
  }
  
  lazy val ui = new BoxPanel(Orientation.Vertical) {
    
  }
  
  def setSystemLookAndFeel() {
    import javax.swing.UIManager
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
  }

}