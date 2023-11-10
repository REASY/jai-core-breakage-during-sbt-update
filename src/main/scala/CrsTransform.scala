
import org.geotools.geometry.jts.JTS
import org.geotools.referencing.CRS
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.Envelope
import org.locationtech.jts.geom.Geometry
import org.opengis.referencing.crs.CoordinateReferenceSystem
import org.opengis.referencing.operation.MathTransform

class CrsTransform(sourceCrs: CoordinateReferenceSystem, destinationCrs: CoordinateReferenceSystem) {

  val mathTransform: MathTransform = CRS.findMathTransform(sourceCrs, destinationCrs)

  def transformCoordinate(coord: Coordinate): Coordinate = {
    JTS.transform(coord, new Coordinate(), mathTransform)
  }

  def transformGeometry(geom: Geometry): Geometry = {
    JTS.transform(geom, mathTransform)
  }

  def transformEnvelope(envelope: Envelope): Envelope = {
    JTS.transform(envelope, mathTransform)
  }

}

object CrsTransform {

  val wgs84Crs: CoordinateReferenceSystem = CRS.decode("EPSG:4326", true)

  def fromWgs(destSrid: Int): CrsTransform = {
    val destCrs = CRS.decode(s"EPSG:$destSrid", true)
    new CrsTransform(wgs84Crs, destCrs)
  }

  def toWgs(sourceSrid: Int): CrsTransform = {
    val srcCrs = CRS.decode(s"EPSG:$sourceSrid", true)
    new CrsTransform(srcCrs, wgs84Crs)
  }

}