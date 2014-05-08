package ru.sayn.simple_gui

import scala.swing.MenuBar
import scala.swing.Menu
import scala.swing.MenuItem
import scala.swing.Action

class MyMenu extends MenuBar {
  contents += new Menu("File") {
    contents += new MenuItem(Action("Exit") {
      sys.exit(0)
    })
    contents += new MenuItem(Action("Add participant") {
      
    })
  }
}