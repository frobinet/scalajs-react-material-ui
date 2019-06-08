package io.kinoplan.demo.pages.demos

import io.kinoplan.demo.components.demos.Badges.{BadgeMax, BadgeVisibility, CustomizedBadge, DotBadge, SimpleBadge}
import io.kinoplan.demo.router.AppRouter.Page
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.all._

object BadgesPage {
  case class Props(router: RouterCtl[Page])

  class Backend(t: BackendScope[Props, Unit]) {
    def render(props: Props): VdomElement = {
      div(
        SimpleBadge(),
        BadgeMax(),
        DotBadge(),
        BadgeVisibility(),
        CustomizedBadge()
      )
    }
  }

  private val component = ScalaComponent.builder[Props]("BadgesPage")
    .renderBackend[Backend]
    .build

  def apply(router: RouterCtl[Page]) = component(Props(router))
}
