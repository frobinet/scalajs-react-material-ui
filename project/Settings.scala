import sbt._
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
//import org.scalablytyped.sbt.ScalablyTypedPlugin.autoImport.ScalablyTyped

object Settings {
  val version = "0.0.2-SNAPSHOT"

  val organization = "io.kinoplan"

  val description = "scalajs-react facade for material-ui"

  object versions {
    val scala212 = "2.12.12"
    val scala213 = "2.13.3"

    object bundler {
      val webpack = "4.41.5"
      val webpackDev = "3.10.2"
      val webpackCli = "3.3.10"
    }

    object scalajs {
      val scalajsReact = "1.7.4"
      val scalaCss = "0.6.1"
      val scalajsDom = "1.0.0"
      val catsCore = "2.1.1"
      val scalajsReactBridge = "0.8.3-SNAPSHOT"
      val scalablyTypedRuntime = "2.1.0"
    }

    object npm {
      val react = "16.13.1"

      val materialUi = "3.9.0"
      val materialUiIcons = "3.0.2"
      val materialUiLab = "3.0.0-alpha.30"

      val reactSwipeableViews = "0.13.0"
    }
  }

  val scalajsDependenciesLib = Def.setting(Seq(
    "com.github.japgolly.scalajs-react" %%% "core"                 % versions.scalajs.scalajsReact,
    "com.payalabs"                      %%% "scalajs-react-bridge" % versions.scalajs.scalajsReactBridge
  ))

  val scalajsDependenciesDemo = Def.setting(Seq(
    "com.github.japgolly.scalajs-react" %%% "core"                 % versions.scalajs.scalajsReact,
    "com.github.japgolly.scalajs-react" %%% "extra"                % versions.scalajs.scalajsReact,
    "com.github.japgolly.scalacss"      %%% "core"                 % versions.scalajs.scalaCss,
    "com.github.japgolly.scalacss"      %%% "ext-react"            % versions.scalajs.scalaCss,
    "com.github.japgolly.scalacss"      %%% "ext-scalatags"        % versions.scalajs.scalaCss,
    "org.scala-js"                      %%% "scalajs-dom"          % versions.scalajs.scalajsDom,
    "org.typelevel"                     %%% "cats-core"            % versions.scalajs.catsCore,
    "com.payalabs"                      %%% "scalajs-react-bridge" % versions.scalajs.scalajsReactBridge,
    "com.olvind"                        %%% "scalablytyped-runtime" % versions. scalajs.scalablyTypedRuntime,
//    Unmanaged jars in client/lib are used until the LTS version of sbt-scalablytyped appears
//    ScalablyTyped.R.`react-swipeable-views`
  ))

  val reactDependencies = Seq(
    "react"     -> versions.npm.react,
    "react-dom" -> versions.npm.react
  )

  val npmDependenciesCore = Def.setting(reactDependencies ++ Seq(
    "@material-ui/core" -> versions.npm.materialUi
  ))

  val npmDependenciesIcons = Def.setting(reactDependencies ++ Seq(
    "@material-ui/core"  -> versions.npm.materialUi,
    "@material-ui/icons" -> versions.npm.materialUiIcons
  ))

  val npmDependenciesLab = Def.setting(reactDependencies ++ Seq(
    "@material-ui/core" -> versions.npm.materialUi,
    "@material-ui/lab"  -> versions.npm.materialUiLab
  ))

  val npmDependenciesDemo = Def.setting(reactDependencies ++ Seq(
    "react-swipeable-views"       -> versions.npm.reactSwipeableViews,
    "@types/react-swipeable-views"       -> versions.npm.reactSwipeableViews,
    "react-swipeable-views-utils" -> versions.npm.reactSwipeableViews,
    "@types/react-swipeable-views-utils" -> versions.npm.reactSwipeableViews,
    "@material-ui/core"           -> versions.npm.materialUi,
    "@material-ui/icons"          -> versions.npm.materialUiIcons,
    "@material-ui/lab"            -> versions.npm.materialUiLab
  ))

  def generateColors(src: File, npm: File): Seq[File] = {
    val path = npm / "node_modules" / "@material-ui" / "core" / "colors"
    val extensions = "*.js" -- "index.js" -- "index.es.js" -- "common.js"

    val colorSources = path * extensions

    val colorsPackageFile = src / "package.scala"

    val colorsPackage = colorSources.get.sortBy(_.getName).map(file => {
      val name = file.getName.stripSuffix(".js")

      s"""    @JSImport("@material-ui/core/colors/$name", JSImport.Default)
         |    @js.native
         |    object $name extends Color
         |""".stripMargin
    }).mkString(
      start =
        """package io.kinoplan.scalajs.react.material.ui.core
          |
          |import scala.scalajs.js
          |import scala.scalajs.js.annotation.JSImport
          |
          |package object colors {
          |
          |""".stripMargin,
      sep = "\n",
      end =
        """
          |    @JSImport("@material-ui/core/colors/common", JSImport.Default)
          |    @js.native
          |    object common extends CommonColors
          |}
          |""".stripMargin
    )

    IO.write(colorsPackageFile, colorsPackage)

    Seq(colorsPackageFile)
  }

  def generateIcons(src: File, npm: File): Seq[File] = {
    val iconSources = (npm / "node_modules" / "@material-ui" / "icons" ) * ("*.js" -- "index.js" -- "index.es.js")

    val iconNames = iconSources.get.map(_.getName.stripSuffix(".js")).sorted

    val moduleFile: File = src / "MuiIconModule.scala"

    val moduleIcons = iconNames.map(name => s"""|   def $name: js.Any = js.native""".stripMargin).mkString("\n")

    IO.write(
      moduleFile,
      s"""package io.kinoplan.scalajs.react.material.ui.icons
         |
         |import scala.scalajs.js
         |import scala.scalajs.js.annotation.JSImport
         |
         |@js.native
         |@JSImport("@material-ui/icons", JSImport.Namespace)
         |object MuiIconModule extends js.Object {
       """.stripMargin.trim + "\n" + moduleIcons + "\n" + "}"
    )

    val packageFile: File = src / "package.scala"

    val packageIcons = iconNames.map(name =>
        s"""|   object Mui${name}Icon extends Bridge { override lazy val componentValue = $name }""".stripMargin
    ).mkString("\n")

    IO.write(
      packageFile,
      s"""package io.kinoplan.scalajs.react.material.ui
         |
         |import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
         |import io.kinoplan.scalajs.react.material.ui.icons.MuiIconModule._
         |import io.kinoplan.scalajs.react.material.ui.icons.SvgIconExtensions
         |
         |import scala.scalajs.js
         |import scala.scalajs.js.|
         |
         |trait Bridge extends ReactBridgeComponent with MuiIcons
         |
         |trait MuiIcons extends ReactBridgeComponent with SvgIconExtensions {
         |  def apply(
         |    classes: js.UndefOr[Map[ClassKey.Value, String]] = js.undefined,
         |    color: js.UndefOr[Color.Value] = js.undefined,
         |    component: js.UndefOr[String | js.Function] = js.undefined,
         |    fontSize: js.UndefOr[FontSize.Value] = js.undefined,
         |    nativeColor: js.UndefOr[String] = js.undefined,
         |    shapeRendering: js.UndefOr[String] = js.undefined,
         |    titleAccess: js.UndefOr[String] = js.undefined,
         |    viewBox: js.UndefOr[String] = js.undefined
         |  ): WithProps = auto
         |}
         |
         |package object icons {
         |
       """.stripMargin.trim + "\n" + packageIcons + "\n" + "}"
    )

    Seq(moduleFile, packageFile)
  }

  val javacOptions = Seq(
    "-source", "1.8",
    "-target", "1.8",
    "-Xlint"
  )

  // Some options are valid for Scala versions < 2.13 only
  // Scala 2.13 removes them, see https://github.com/scala/scala/pull/6502, https://github.com/scala/scala/pull/5969
  // and https://github.com/scala/scala/releases/tag/v2.13.3
  val scalac212SpecificOptions = Seq(
    "-Ypartial-unification",             // Enable partial unification in type constructor inference
    "-Xlint:by-name-right-associative",  // By-name parameter of right associative operator.
    "-Xlint:unsound-match",              // Pattern match may not be typesafe.
    "-Xlint:nullary-override",           // Warn when non-nullary `def f()' overrides nullary `def f'.
    "-Xfuture",                          // Turn on future language features.
  )

  val scalacOptions = Seq(
    "-target:jvm-1.8",
    "-deprecation",                      // Emit warning and location for usages of deprecated APIs. // TODO Restore
    "-encoding", "utf-8",                // Specify character encoding used by source files.
    "-explaintypes",                     // Explain type errors in more detail.
    "-feature",                          // Emit warning and location for usages of features that should be imported explicitly.
    "-language:existentials",            // Existential types (besides wildcard types) can be written and inferred
    "-language:experimental.macros",     // Allow macro definition (besides implementation and application)
    "-language:higherKinds",             // Allow higher-kinded types
    "-language:implicitConversions",     // Allow definition of implicit functions called views
    "-unchecked",                        // Enable additional warnings where generated code depends on assumptions.
    "-Xcheckinit",                       // Wrap field accessors to throw an exception on uninitialized access.
    //"-Xfatal-warnings",                  // Fail the compilation if there are any warnings.
    "-Xlint:adapted-args",               // Warn if an argument list is modified to match the receiver.
    "-Xlint:constant",                   // Evaluation of a constant arithmetic expression results in an error.
    "-Xlint:delayedinit-select",         // Selecting member of DelayedInit.
    "-Xlint:doc-detached",               // A Scaladoc comment appears to be detached from its element.
    "-Xlint:inaccessible",               // Warn about inaccessible types in method signatures.
    "-Xlint:infer-any",                  // Warn when a type argument is inferred to be `Any`.
    "-Xlint:missing-interpolator",       // A string literal appears to be missing an interpolator id.
    "-Xlint:nullary-unit",               // Warn when nullary methods return Unit.
    "-Xlint:option-implicit",            // Option.apply used implicit view.
    "-Xlint:package-object-classes",     // Class or object defined in package object.
    "-Xlint:poly-implicit-overload",     // Parameterized overloaded implicit methods are not visible as view bounds.
    "-Xlint:private-shadow",             // A private field (or class parameter) shadows a superclass field.
    "-Xlint:stars-align",                // Pattern sequence wildcard must align with sequence component.
    "-Xlint:type-parameter-shadow",      // A local type parameter shadows a type already in scope.
    "-Xlint:adapted-args",               // Warn on argument list adaptation (either by inserting () or creating a tuple) to match the receiver.
    "-Xlint:inaccessible",               // Warn about inaccessible types in method signatures.
    "-Ywarn-extra-implicit",             // Warn when more than one implicit parameter section is defined.
    "-Xlint:infer-any",                  // Warn when a type argument is inferred to be `Any`.
    "-Ywarn-numeric-widen",              // Warn when numerics are widened.
    "-Xlint:unused",                     // Warn if an implicit/import/local/private is unused.
    "-Ywarn-value-discard",              // Warn when non-Unit expression results are unused.
    "-Yrangepos"
  )
}
