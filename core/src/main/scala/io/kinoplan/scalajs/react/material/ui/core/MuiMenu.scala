package io.kinoplan.scalajs.react.material.ui.core

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import io.kinoplan.scalajs.react.material.ui.core.internal.Origin
import japgolly.scalajs.react.ReactEvent
import org.scalajs.dom.html

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object MuiMenu extends ReactBridgeComponent with MuiMenuExtensions with JsWriterImplicits {
  override protected lazy val componentValue: js.Function = RawComponent

  @JSImport("@material-ui/core", "Menu")
  @js.native
  object RawComponent extends js.Function

  def apply(
    anchorEl: js.UndefOr[html.Element | js.Function1[html.Element, html.Element]] = js.undefined,
    anchorOrigin: js.UndefOr[Origin] = js.undefined,
    anchorReference: js.UndefOr[AnchorReference.Value] = js.undefined,
    classes: js.UndefOr[Map[ClassKey.Value, String]] = js.undefined,
    disableAutoFocusItem: js.UndefOr[Boolean] = js.undefined,
    MenuListProps: js.UndefOr[js.Object] = js.Object(),
    onClose: ReactHandler2[ReactEvent, String] = js.undefined,
    onEnter: Handler1[html.Element] = js.undefined,
    onEntered: Handler1[html.Element] = js.undefined,
    onEntering: Handler1[html.Element] = js.undefined,
    onExit: Handler1[html.Element] = js.undefined,
    onExited: Handler1[html.Element] = js.undefined,
    onExiting: Handler1[html.Element] = js.undefined,
    open: Boolean,
    PopoverClasses: js.UndefOr[js.Object] = js.undefined,
    transitionDuration: js.UndefOr[js.Object | TransitionDuration.Value] = js.undefined,
    transformOrigin: js.UndefOr[Origin] = js.undefined,
    PaperProps: js.UndefOr[js.Object] = js.undefined,
    TransitionComponent: OptComponentPropType = js.undefined,
    action: js.UndefOr[js.Function] = js.undefined,
    container: OptComponentContainerType = js.undefined,
    elevation: js.UndefOr[Int] = js.undefined,
    getContentAnchorEl: js.UndefOr[html.Element | js.Function1[html.Element, html.Element]] = js.undefined,
    marginThreshold: js.UndefOr[Int] = js.undefined,
    ModalClasses: js.UndefOr[js.Object] = js.undefined,
    TransitionProps: js.UndefOr[js.Object] = js.undefined,
    BackdropComponent: OptComponentPropType = js.undefined,
    BackdropProps: js.UndefOr[js.Object] = js.undefined,
    closeAfterTransition: js.UndefOr[Boolean] = js.undefined,
    disableAutoFocus: js.UndefOr[Boolean] = js.undefined,
    disableBackdropClick: js.UndefOr[Boolean] = js.undefined,
    disableEnforceFocus: js.UndefOr[Boolean] = js.undefined,
    disableEscapeKeyDown: js.UndefOr[Boolean] = js.undefined,
    disablePortal: js.UndefOr[Boolean] = js.undefined,
    disableRestoreFocus: js.UndefOr[Boolean] = js.undefined,
    hideBackdrop: js.UndefOr[Boolean] = js.undefined,
    keepMounted: js.UndefOr[Boolean] = js.undefined,
    onBackdropClick: ReactHandler1[ReactEvent] = js.undefined,
    onEscapeKeyDown: ReactHandler1[ReactEvent] = js.undefined,
    onRendered: OptJsFun0 = js.undefined
  ): WithProps = auto
}

trait MuiMenuExtensions {
  object AnchorReference extends Enumeration {
    type Value = String

    val anchorEl = "anchorEl"
    val anchorPosition = "anchorPosition"
    val none = "none"
  }

  object TransitionDuration extends Enumeration {
    type Value = String

    val auto = "auto"
  }

  object ClassKey extends Enumeration {
    type Value = String

    val paper = "paper"
    val root = "root"
    val hidden = "hidden"
  }
}
