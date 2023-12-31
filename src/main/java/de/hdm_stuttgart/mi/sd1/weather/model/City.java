// Generated by http://www.jsonschema2pojo.org

package de.hdm_stuttgart.mi.sd1.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "coord",
        "country",
        "timezone",
        "sunrise",
        "sunset"
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

  @JsonProperty("id")
  private int id;
  @JsonProperty("name")
  private String name;
  @JsonProperty("coord")
  private Coord coord;
  @JsonProperty("country")
  private String country;
  @JsonProperty("timezone")
  private int timezone;
  @JsonProperty("sunrise")
  private int sunrise;
  @JsonProperty("sunset")
  private int sunset;

  /**
   * No args constructor for use in serialization
   *
   */
  public City() {
  }

  /**
   *
   * @param country
   * @param coord
   * @param sunrise
   * @param timezone
   * @param sunset
   * @param name
   * @param id
   */
  public City(int id, String name, Coord coord, String country, int timezone, int sunrise, int sunset) {
    super();
    this.id = id;
    this.name = name;
    this.coord = coord;
    this.country = country;
    this.timezone = timezone;
    this.sunrise = sunrise;
    this.sunset = sunset;
  }

  @JsonProperty("id")
  public int getId() {
    return id;
  }

  @JsonProperty("id")
  public void setId(int id) {
    this.id = id;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("name")
  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("coord")
  public Coord getCoord() {
    return coord;
  }

  @JsonProperty("coord")
  public void setCoord(Coord coord) {
    this.coord = coord;
  }

  @JsonProperty("country")
  public String getCountry() {
    return country;
  }

  @JsonProperty("country")
  public void setCountry(String country) {
    this.country = country;
  }

  @JsonProperty("timezone")
  public int getTimezone() {
    return timezone;
  }

  @JsonProperty("timezone")
  public void setTimezone(int timezone) {
    this.timezone = timezone;
  }

  @JsonProperty("sunrise")
  public int getSunrise() {
    return sunrise;
  }

  @JsonProperty("sunrise")
  public void setSunrise(int sunrise) {
    this.sunrise = sunrise;
  }

  @JsonProperty("sunset")
  public int getSunset() {
    return sunset;
  }

  @JsonProperty("sunset")
  public void setSunset(int sunset) {
    this.sunset = sunset;
  }

}
