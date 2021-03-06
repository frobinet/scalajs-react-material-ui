package io.kinoplan.scalajs.react.material.ui.core

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object MuiButtonBase extends ReactBridgeComponent with MuiButtonBaseExtensions {
  override protected lazy val componentValue: js.Function = RawComponent

  @JSImport("@material-ui/core", "ButtonBase")
  @js.native
  object RawComponent extends js.Function

  def apply(
    action: js.UndefOr[js.Function] = js.undefined,
    buttonRef: OptComponentRefType = js.undefined,
    centerRipple: js.UndefOr[Boolean] = js.undefined,
    classes: js.UndefOr[Map[ClassKey.Value, String]] = js.undefined,
    component: OptComponentPropType = js.undefined,
    disableRipple: js.UndefOr[Boolean] = js.undefined,
    disableTouchRipple: js.UndefOr[Boolean] = js.undefined,
    focusRipple: js.UndefOr[Boolean] = js.undefined,
    focusVisibleClassName: js.UndefOr[String] = js.undefined,
    onFocusVisible: js.UndefOr[js.Function] = js.undefined,
    TouchRippleProps: js.UndefOr[js.Object] = js.undefined
  ): WithProps = auto
}

trait MuiButtonBaseExtensions {
  object ClassKey extends Enumeration {
    type Value = String

    val root = "root"
    val disabled = "disabled"
    val focusVisible = "focusVisible"
  }
}
