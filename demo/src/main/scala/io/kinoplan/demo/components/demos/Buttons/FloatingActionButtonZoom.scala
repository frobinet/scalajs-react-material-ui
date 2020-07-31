package io.kinoplan.demo.components.demos.Buttons

import io.kinoplan.demo.components.wrappers.SwipeableViews
import io.kinoplan.demo.components.{ComponentContainer, Layout, TabContainer}
import io.kinoplan.demo.models.Buttons.Fab
import io.kinoplan.demo.styles.demos.Buttons.{DefaultFloatingActionButtonsZoomStyle, FloatingActionButtonsZoomStyle}
import io.kinoplan.demo.utils.Helpers.StringExtended
import io.kinoplan.scalajs.react.material.ui.core.styles.Direction
import io.kinoplan.scalajs.react.material.ui.core._
import io.kinoplan.scalajs.react.material.ui.icons.{MuiAddIcon, MuiEditIcon, MuiKeyboardArrowUpIcon}
import japgolly.scalajs.react.vdom.Attr
import japgolly.scalajs.react.vdom.all._
import japgolly.scalajs.react.{BackendScope, Callback, ReactEvent, ScalaComponent}
import scalacss.ScalaCssReactImplicits
import typings.reactSwipeableViews.{reactSwipeableViewsStrings => SwipeableViewsStrings}
import scala.scalajs.js

object FloatingActionButtonZoom extends ScalaCssReactImplicits {
  case class Props(style: FloatingActionButtonsZoomStyle)

  case class State(value: js.Any = 0) {
    def handleChange(value: js.Any) = copy(value = value)
  }

  class Backend(t: BackendScope[Props, State]) {
    def handleChange: (ReactEvent, js.Any) => Callback = (_, value) => {
      t.modState(_.handleChange(value))
    }

    def handleChangeIndex: (Double, Double) => Unit = (index, _) => {
      t.modState(_.handleChange(index)).runNow()
    }

    def render(props: Props, state: State): VdomElement = {
      val css = props.style

      val fabs = List(
        Fab(MuiFab.Color.inherit, css.fab, MuiAddIcon()),
        Fab(MuiFab.Color.secondary, css.fab, MuiEditIcon()),
        Fab(MuiFab.Color.inherit, css.fabAndFabGreen, MuiKeyboardArrowUpIcon())
      )

      val enter = css.theme.transitions.duration.enteringScreen
      val exit = css.theme.transitions.duration.leavingScreen

      val transitionDuration = js.Dynamic.literal(enter = enter, exit = exit)

      val swipeableViewsAxis = if (css.theme.direction == Direction.rtl.toString) {
        SwipeableViewsStrings.`x-reverse`
      } else {
        SwipeableViewsStrings.x
      }

      div(
        ComponentContainer("Floating Action Button Zoom")(
          div(css.root, css.rootPaper(Layout.isPaletteLight),
            MuiAppBar(position = MuiAppBar.Position.static, color = MuiAppBar.Color.default)(
              MuiTabs(
                onChange = handleChange,
                indicatorColor = MuiTabs.IndicatorColor.primary,
                textColor = MuiTabs.TextColor.primary,
                variant = MuiTabs.Variant.fullWidth
              )(
                value := state.value,
                MuiTab(label = "Item One".toVdom),
                MuiTab(label = "Item Two".toVdom),
                MuiTab(label = "Item Three".toVdom)
              )
            ),
            SwipeableViews(
              axis = swipeableViewsAxis,
              index = state.value.asInstanceOf[Int],
              onChangeIndex = handleChangeIndex
            )(
              TabContainer()("Item One"),
              TabContainer()("Item Two"),
              TabContainer()("Item Three")
            ),
            fabs.zipWithIndex.toVdomArray { case (fab, index) =>
              MuiZoom(in = state.value == index.asInstanceOf[js.Any], timeout = transitionDuration)(
                style := js.Dictionary(
                  "transitionDelay" -> s"${if (state.value == index.asInstanceOf[js.Any]) exit else 0}ms"
                ),
                Attr("key") := index,
                MuiFab(color = fab.color)(fab.className, fab.icon)
              )
            }
          )
        )
      )
    }
  }

  private val component = ScalaComponent.builder[Props]("FloatingActionButtonZoom")
    .initialState(State())
    .renderBackend[Backend]
    .build

  def apply(style: FloatingActionButtonsZoomStyle = DefaultFloatingActionButtonsZoomStyle) = component(Props(style))
}
