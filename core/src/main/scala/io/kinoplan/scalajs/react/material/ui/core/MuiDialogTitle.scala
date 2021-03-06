package io.kinoplan.scalajs.react.material.ui.core

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object MuiDialogTitle extends ReactBridgeComponent with MuiDialogTitleExtensions {
  override protected lazy val componentValue: js.Function = RawComponent

  @JSImport("@material-ui/core", "DialogTitle")
  @js.native
  object RawComponent extends js.Function

  def apply(
    classes: js.UndefOr[Map[ClassKey.Value, String]] = js.undefined,
    disableTypography: js.UndefOr[Boolean] = js.undefined
  ): WithProps = auto
}

trait MuiDialogTitleExtensions {
  object ClassKey extends Enumeration {
    type Value = String

    val root = "root"
  }
}
