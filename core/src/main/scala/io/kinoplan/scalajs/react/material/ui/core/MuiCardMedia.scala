package io.kinoplan.scalajs.react.material.ui.core

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithPropsNoChildren}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object MuiCardMedia extends ReactBridgeComponent with MuiCardMediaExtensions {
  override protected lazy val componentValue: js.Function = RawComponent

  @JSImport("@material-ui/core", "CardMedia")
  @js.native
  object RawComponent extends js.Function

  def apply(
    classes: js.UndefOr[Map[ClassKey.Value, String]] = js.undefined,
    component: OptComponentPropType = js.undefined,
    image: js.UndefOr[String] = js.undefined
  ): WithPropsNoChildren = autoNoChildren
}

trait MuiCardMediaExtensions {
  object ClassKey extends Enumeration {
    type Value = String

    val root = "root"
    val media = "media"
  }
}
