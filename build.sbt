lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.13.1"
    )),
    name := "torus-traveller"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % Test
// Just in case testing gets more involved
//libraryDependencies += "org.mockito" %% "mockito-scala" % "1.11.2" % Test
//libraryDependencies += "org.mockito" %% "mockito-scala-scalatest" % "1.11.2" % Test
