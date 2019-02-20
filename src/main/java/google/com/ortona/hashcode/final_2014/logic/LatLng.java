package google.com.ortona.hashcode.final_2014.logic;

public class LatLng {

  private static final long serialVersionUID = 1L;

  /** The latitude of this location. */
  public double latitude;

  /** The longitude of this location. */
  public double longitude;

  /**
   * Constructs a location with a latitude/longitude pair.
   *
   * @param lat The latitude of this location.
   * @param lng The longitude of this location.
   */
  public LatLng(double lat, double lng) {
    this.latitude = lat;
    this.longitude = lng;
  }

  /** Serialisation constructor. */
  public LatLng() {}

}