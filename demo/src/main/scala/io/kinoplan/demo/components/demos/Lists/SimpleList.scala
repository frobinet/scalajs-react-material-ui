package io.kinoplan.demo.components.demos.Lists

import io.kinoplan.demo.components.{ComponentContainer, Layout}
import io.kinoplan.demo.styles.demos.Lists.{DefaultListStyle, ListStyle}
import io.kinoplan.scalajs.react.material.ui.core.{MuiDivider, MuiList, MuiListItem, MuiListItemIcon, MuiListItemText}
import io.kinoplan.scalajs.react.material.ui.icons.{MuiDraftsIcon, MuiInboxIcon}
import japgolly.scalajs.react.vdom.all._
import japgolly.scalajs.react.{BackendScope, ScalaComponent}
import scalacss.ScalaCssReactImplicits

object SimpleList extends ScalaCssReactImplicits {
  case class Props(style: ListStyle)

  class Backend(t: BackendScope[Props, Unit]) {
    def render(props: Props): VdomElement = {
      val css = props.style

      div(
        ComponentContainer("Simple List")(
          div(css.root, css.rootPaper(Layout.isPaletteLight),
            MuiList(component = "nav")(
              MuiListItem(button = true)(
                MuiListItemIcon()(
                  MuiInboxIcon()
                ),
                MuiListItemText(primary = VdomNode("Inbox"))
              ),
              MuiListItem(button = true)(
                MuiListItemIcon()(
                  MuiDraftsIcon()
                ),
                MuiListItemText(primary = VdomNode("Drafts"))
              ),
              MuiDivider(),
              MuiList(component = "nav")(
                MuiListItem(button = true)(
                  MuiListItemText(primary = VdomNode("Trash"))
                ),
                MuiListItem(component = "a", button = true)(href := "#simple-list",
                  MuiListItemText(primary = VdomNode("Spam"))
                )
              )
            )
          )
        )
      )
    }
  }

  private val component = ScalaComponent.builder[Props]("SimpleList")
    .renderBackend[Backend]
    .build

  def apply(style: ListStyle = DefaultListStyle) = component(Props(style))
}
