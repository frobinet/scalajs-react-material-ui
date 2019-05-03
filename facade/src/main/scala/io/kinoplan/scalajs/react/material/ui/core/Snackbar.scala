package io.kinoplan.scalajs.react.material.ui.core

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object Snackbar extends ReactBridgeComponent with SnackbarExtensions with JsWriterImplicits {
  override protected lazy val componentValue: js.Function = RawComponent

  @JSImport("@material-ui/core", "Snackbar")
  @js.native
  object RawComponent extends js.Function

  def apply(
    action: Option[VdomNode] = None,
    anchorOrigin: js.Object = js.Object(),
    autoHideDuration: Option[Int] = None,
    classes: js.Object = js.Object(),
    ClickAwayListenerProps: js.Object = js.Object(),
    ContentProps: js.Object = js.Object(),
    disableWindowBlurListener: Boolean = false,
    key: Option[js.Any] = None,
    message: Option[VdomNode] = None,
    onClose: Option[js.Function] = None,
    onEnter: Option[js.Function] = None,
    onEntered: Option[js.Function] = None,
    onEntering: Option[js.Function] = None,
    onExit: Option[js.Function] = None,
    onExited: Option[js.Function] = None,
    onExiting: Option[js.Function] = None,
    open: Option[Boolean] = None,
    resumeHideDuration: Option[Int] = None,
    TransitionComponent: Option[String | js.Function] = Some("Slide"),
    transitionDuration: Option[Int | js.Object] = None,
    TransitionProps: js.Object = js.Object
  ): WithProps = auto
}

trait SnackbarExtensions {
  object Direction extends Enumeration {
    val left = Value("left")
    val right = Value("right")
    val up = Value("up")
    val down = Value("down")
  }
}