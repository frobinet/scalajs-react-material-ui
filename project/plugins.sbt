//val scalaJSVersion = Option(System.getenv("SCALAJS_VERSION")).getOrElse("1.1.1")
//val scalaJSVersion = Option(System.getenv("SCALAJS_VERSION")).getOrElse("0.6.33")
val scalaJSVersion = Option(System.getenv("SCALAJS_VERSION")).getOrElse("1.1.2")

addSbtPlugin("org.scala-js" % "sbt-scalajs" % scalaJSVersion)

val scalaJSBundlerSuffix = if (scalaJSVersion.startsWith("0.6.")) "-sjs06" else ""
addSbtPlugin("ch.epfl.scala" % s"sbt-scalajs-bundler$scalaJSBundlerSuffix" % "0.18.0")

resolvers += Resolver.bintrayRepo("oyvindberg", "converter")
val scalablyTypedSuffix = if (scalaJSVersion.startsWith("0.6.")) "06" else ""
addSbtPlugin("org.scalablytyped.converter" % s"sbt-converter${scalablyTypedSuffix}" % "1.0.0-beta21")

addSbtPlugin("com.geirsson" % "sbt-ci-release" % "1.5.2")
