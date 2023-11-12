
name := "jai-core-breakage-during-sbt-update"

version := "1.0.1"

scalaVersion := "2.13.12"

val gt: String = "29.3"

resolvers ++= Seq(
  "Geomatys".at("https://nexus.geomatys.com/repository/geotoolkit/"),
  "GeoToolkit".at("https://maven.geotoolkit.org/"),
  Resolver.typesafeRepo("releases"),
  "OSGeo Repository: Release".at("https://repo.osgeo.org/repository/geotools-releases/"),
  "OSGeo Release".at("https://repo.osgeo.org/repository/release/"),
)

libraryDependencies ++= Seq(
  ("org.geotools" % "gt-main" % gt).excludeAll(ExclusionRule("javax.media", "jai_core")),
  ("org.geotools" % "gt-epsg-wkt" % gt).excludeAll(ExclusionRule("javax.media", "jai_core")),
  ("org.geotools" % "gt-referencing" % gt).excludeAll(ExclusionRule("javax.media", "jai_core")),
)


