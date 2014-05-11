package ru.sayn.simple_gui

import scala.swing.BoxPanel
import scala.swing.Button
import scala.swing.Frame
import scala.swing.Label
import scala.swing.MainFrame
import scala.swing.Orientation
import scala.swing.SplitPane
import scala.swing.SwingApplication
import scala.swing.TextArea
import scala.swing.event.ButtonClicked
import ru.sayn.simple_gui.table.Shoot2MenTable
import ru.sayn.simple_gui.table.ShootMenTable
import ru.sayn.simple_gui.table.ShootNRunTable
import ru.sayn.simple_gui.table.ShootWomenTable

object AdminGui extends SwingApplication {
  
  setSystemLookAndFeel()
  
  def startup(args: Array[String]) = {
    try {
      val frame = new MainFrame {
        title = "Администратор"
        contents = ui
        maximize
        menuBar = new MyMenu
        visible = true
      }
    } catch {
      case e: Throwable =>
        println(e)
        println(e.getMessage)
        println(e.getStackTraceString)
        
    }
  }
  
  lazy val ui = new SplitPane(Orientation.Vertical) {
    val right = new BoxPanel(Orientation.Horizontal) {
      contents += new ShootWomenTable()
      contents += new ShootMenTable()
    }
    val left = new BoxPanel(Orientation.Horizontal) {
      contents += new ShootNRunTable()
      contents += new Shoot2MenTable()
    }
  }
  
  def addParticipant() = new Frame() {
    visible = true
    val cancel = new Button("Cancel")
    val save = new Button("Save")
    val label = new Label("Enter name:")
    val textArea = new TextArea() 
        
    contents = new BoxPanel(Orientation.Vertical) {
      contents += new BoxPanel(Orientation.Horizontal) {
        contents += save
        contents += cancel
        listenTo(cancel)
        listenTo(save)
        reactions += {
          case ButtonClicked(component) if component == cancel => close
          case ButtonClicked(component) if component == save && !textArea.text.isEmpty() => AdminGui.createParticipant(textArea.text) 
          case ButtonClicked(component) if component == save && textArea.text.isEmpty() => println("Bad idea")
        }
      }
      contents += new BoxPanel(Orientation.Vertical) {
        contents += label
        contents += textArea
      }
    }
  }
  
  def createParticipant(name: String) = {
    
  }
  
  def setSystemLookAndFeel() {
    import javax.swing.UIManager
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
  }

}