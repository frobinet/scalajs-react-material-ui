package io.kinoplan.scalajs.react.material.ui.core

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithPropsNoChildren}
import japgolly.scalajs.react.raw.React
import japgolly.scalajs.react.ReactEvent
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object MuiFormControlLabel extends ReactBridgeComponent with MuiFormControlLabelExtensions with JsWriterImplicits {
  override protected lazy val componentValue: js.Function = RawComponent

  @JSImport("@material-ui/core", "FormControlLabel")
  @js.native
  object RawComponent extends js.Function

  def apply(
    checked: js.UndefOr[Boolean | String] = js.undefined,
    classes: js.UndefOr[Map[ClassKey.Value, String]] = js.undefined,
    control: js.UndefOr[React.Element] = js.undefined,
    inputRef: OptComponentRefType = js.undefined,
    label: js.UndefOr[VdomNode] = js.undefined,
    labelPlacement: js.UndefOr[LabelPlacement.Value] = js.undefined,
    onChange: ReactHandler2[ReactEvent, Boolean] = js.undefined
  ): WithPropsNoChildren = autoNoChildren
}

trait MuiFormControlLabelExtensions {
  object LabelPlacement extends Enumeration {
    type Value = String

    val end = "end"
    val start = "start"
    val top = "top"
    val bottom = "bottom"
  }

  object ClassKey extends Enumeration {
    type Value = String

    val root = "root"
    val labelPlacementStart = "labelPlacementStart"
    val labelPlacementTop = "labelPlacementTop"
    val labelPlacementBottom = "labelPlacementBottom"
    val disabled = "disabled"
    val label = "label"
  }
}
