val ScalaVersion = "2.13.1"
val Organization = "com.commodityvectors"

val sharedSettings = Seq(
  organization := Organization,
  scalaVersion := ScalaVersion,
  scalafmtOnCompile := true,
  parallelExecution in Test := false,
  releaseCrossBuild := true,
  bintrayOrganization := Some("commodityvectors"),
  bintrayRepository := "commodityvectors-releases",
  licenses += ("MIT", url("http://opensource.org/licenses/MIT")),
  pomExtra := (
    <scm>
      <url>git@github.com:commodityvectors/scalatest-snapshot-matcher.git</url>
      <connection>scm:git:git@github.com:commodityvectors/scalatest-snapshot-matcher.git</connection>
    </scm>
  ),
  releaseTagComment := s"[${name.value}] Releasing ${version.value}",
  releaseCommitMessage := s"[${name.value}] Setting version to ${version.value}",
  releaseTagName := s"${name.value}v${version.value}"
)

lazy val root = Project("scalatest-snapshot-matcher", file("."))
  .settings(publish := {})
  .settings(publishArtifact := false)
  .settings(name := "scalatest-snapshot-matcher")
  .settings(sharedSettings: _*)
  .aggregate(core, playJson)

lazy val core =
  Project("scalatest-snapshot-matcher-core", file("scalatest-snapshot-matcher-core"))
    .settings(name := "scalatest-snapshot-matcher-core")
    .settings(sharedSettings: _*)
    .settings(libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.8",
      "com.googlecode.java-diff-utils" % "diffutils" % "1.3.0",
      "com.typesafe" % "config" % "1.3.2",
      "commons-io" % "commons-io" % "2.6" % "test"
    ))

lazy val playJson =
  Project("scalatest-snapshot-matcher-play-json", file("scalatest-snapshot-matcher-play-json"))
    .settings(name := "scalatest-snapshot-matcher-play-json")
    .settings(sharedSettings: _*)
    .settings(
      libraryDependencies ++= Seq(
        "com.typesafe.play" %% "play-json" % "2.7.4",
        "commons-io" % "commons-io" % "2.6" % "test"
      ))
    .dependsOn(core)
