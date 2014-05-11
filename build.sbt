EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource

EclipseKeys.eclipseOutput := Some(".target")

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-actors" % _)

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.11.3" % "test"

libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

libraryDependencies += "com.typesafe" % "config" % "1.2.1"

libraryDependencies += "org.scala-lang" % "scala-swing" % "2.10.3"