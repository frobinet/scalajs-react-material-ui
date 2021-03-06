package io.kinoplan.scalajs.react.material.ui.core

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import io.kinoplan.scalajs.react.material.ui.core.internal.Origin
import japgolly.scalajs.react.ReactEvent
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.html

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object MuiSnackbar extends ReactBridgeComponent with MuiSnackbarExtensions with JsWriterImplicits {
  override protected lazy val componentValue: js.Function = RawComponent

  @JSImport("@material-ui/core", "Snackbar")
  @js.native
  object RawComponent extends js.Function

  def apply(
    action: js.UndefOr[VdomNode] = js.undefined,
    anchorOrigin: js.UndefOr[Origin] = js.undefined,
    autoHideDuration: js.UndefOr[Int] = js.undefined,
    classes: js.UndefOr[Map[ClassKey.Value, String]] = js.undefined,
    ClickAwayListenerProps: js.UndefOr[js.Object] = js.undefined,
    ContentProps: js.UndefOr[js.Object] = js.undefined,
    disableWindowBlurListener: js.UndefOr[Boolean] = js.undefined,
    message: js.UndefOr[VdomNode] = js.undefined,
    onClose: ReactHandler2[ReactEvent, String] = js.undefined,
    onEnter: Handler1[html.Element] = js.undefined,
    onEntered: Handler1[html.Element] = js.undefined,
    onEntering: Handler1[html.Element] = js.undefined,
    onExit: Handler1[html.Element] = js.undefined,
    onExited: Handler1[html.Element] = js.undefined,
    onExiting: Handler1[html.Element] = js.undefined,
    open: js.UndefOr[Boolean] = js.undefined,
    resumeHideDuration: js.UndefOr[Int] = js.undefined,
    TransitionComponent: OptComponentPropType = js.undefined,
    transitionDuration: js.UndefOr[Int | js.Object] = js.undefined,
    TransitionProps: js.UndefOr[js.Object] = js.undefined
  ): WithProps = auto
}

trait MuiSnackbarExtensions {
  object Direction extends Enumeration {
    type Value = String

    val left = "left"
    val right = "right"
    val up = "up"
    val down = "down"
  }

  object ClassKey extends Enumeration {
    type Value = String

    val root = "root"
    val anchorOriginTopCenter = "anchorOriginTopCenter"
    val anchorOriginBottomCenter = "anchorOriginBottomCenter"
    val anchorOriginTopRight = "anchorOriginTopRight"
    val anchorOriginBottomRight = "anchorOriginBottomRight"
    val anchorOriginTopLeft = "anchorOriginTopLeft"
    val anchorOriginBottomLeft = "anchorOriginBottomLeft"
  }
}
