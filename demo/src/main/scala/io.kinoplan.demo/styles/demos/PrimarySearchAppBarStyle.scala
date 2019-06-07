package io.kinoplan.demo.styles.demos

import io.kinoplan.demo.CssSettings._
import io.kinoplan.demo.styles.{CommonStyle, DefaultCommonStyle}
import io.kinoplan.scalajs.react.material.ui.core.styles.CreateTransitionsOptions
import io.kinoplan.scalajs.react.material.ui.core.styles.colorManipulator.fade

case class PrimarySearchAppBarStyle(common: CommonStyle = DefaultCommonStyle) extends StyleSheet.Inline  {
  import common.theme
  import dsl._

  private val fadeFirst = fade(theme.palette.common.white, 0.15)
  private val fadeSecond = fade(theme.palette.common.white, 0.25)

  private val inputInputTransition = theme.transitions.create(
    "width",
    CreateTransitionsOptions(
      easing = Some(theme.transitions.easing.easeInOut),
      duration = Some(theme.transitions.duration.standard)
    )
  )

  val root = style(
    width(100.%%)
  )

  val title = style(
    display.none,
    //unsafeRoot(theme.breakpoints.up("sm"))(
    media.minWidth(600.px)(
      display.block
    )
  )

  val search = style(
    position.relative,
    borderRadius :=! theme.shape.borderRadius.px,
    backgroundColor :=! fadeFirst,
    &.hover(
      backgroundColor :=! fadeSecond
    ),
    marginRight((theme.spacing.unit * 2).px),
    marginLeft(0.px),
    width(100.%%),
    //unsafeChild(theme.breakpoints.up("sm"))(
    media.minWidth(600.px)(
      marginLeft((theme.spacing.unit * 3).px),
      width.auto
    )
  )

  val searchIcon = style(
    width((theme.spacing.unit * 9).px),
    height(100.%%),
    position.absolute,
    pointerEvents.none,
    display.flex,
    alignItems.center,
    justifyContent.center
  )

  val inputRoot = style(
    color.inherit,
    width(100.%%)
  )

  val inputInput = style(
    paddingTop(theme.spacing.unit.px),
    paddingRight(theme.spacing.unit.px),
    paddingBottom(theme.spacing.unit.px),
    paddingLeft((theme.spacing.unit * 10).px),
    transition := inputInputTransition,
    width(100.%%),
    //unsafeChild(theme.breakpoints.up("md"))(
    media.minWidth(960.px)(
      width(200.px)
    )
  )

  val sectionDesktop = style(
    display.none,
    //unsafeChild(theme.breakpoints.up("md"))(
    media.minWidth(960.px)(
      display.flex
    )
  )

  val sectionMobile = style(
    display.flex,
    //unsafeChild(theme.breakpoints.up("md"))(
    media.minWidth(960.px)(
      display.none
    )
  )
}

object DefaultPrimarySearchAppBarStyle extends PrimarySearchAppBarStyle