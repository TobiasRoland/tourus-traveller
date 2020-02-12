lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "codes.mostly",
      scalaVersion := "2.13.1"
    )),
    name := "torus-traveller"
  )

libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % Test
//libraryDependencies += "org.mockito" %% "mockito-scala" % "1.11.2" % Test
//libraryDependencies += "org.mockito" %% "mockito-scala-scalatest" % "1.11.2" % Test
