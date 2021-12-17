package no.geosoft.npdio.stratigraphy;

import java.util.Date;

/**
 * Stratigraphy as modeled by the NPD.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public class NpdStratigraphy
{
  private final String wellboreName_;
  private final int topDepth_;
  private final int bottomDepth_;
  private final String lithostratigraphicUnit_;
  private final String level_;
  private final int lithostratigraphicUnitId_;
  private final Date wellboreCompletion_;
  private final int wellboreNpdId_;
  private final Date lastUpdated_;
  private final Date lastSynced_;

  /**
   * Create an NPD stratigraphy instance
   */
  NpdStratigraphy(String wellboreName,
                 int topDepth,
                 int botDepth,
                 String stratigraphicUnit,
                 String level,
                 int stratigrahpicUnitId,
                 Date wellboreCompletion,
                 int wellboreId,
                 Date lastUpdated,
                 Date lastSynced){

    wellboreName_ = wellboreName;
    topDepth_ = topDepth;
    bottomDepth_ = botDepth;
    lithostratigraphicUnit_ = stratigraphicUnit;
    level_ = level;
    lithostratigraphicUnitId_ = stratigrahpicUnitId;
    wellboreCompletion_ = wellboreCompletion;
    wellboreNpdId_ = wellboreId;
    lastUpdated_ = lastUpdated;
    lastSynced_ = lastSynced;
  }

  /**
   * Return the wellbore name of this stratigraphy.
   * <p>
   * <b>NPD description:</b><br>
   * Official name of the wellbore based on NPD guidelines for designation of wells and wellbores.
   * <p>
   * varchar(40), corresponds to NPD property <em>wlbName</em>.
   *
   * @return  Wellbore name of this stratigraphy. Null if N/A or unknown.
   */
  public String getWellboreName()
  {
    return wellboreName_;
  }

  /**
   * Return the start depth of this stratigraphy.
   * <p>
   * <b>NPD description</b><br>
   * Measured length along the wellbore from kelly bushing to start of lithostratigraphic unit.
   * <p>
   * numeric, corresponds to NPD property <em>lsuTopDepth</em>
   *
   * @return Start depth of this stratigraphy. Null if N/A or unknown.
   */
  public int getTopDepth()
  {
    return topDepth_;
  }

  /**
   * Return end depth of this stratigraphy.
   * <p>
   * <b>NPD description</b><br>
   * Depth in meters to the bottom of the lithostratigraphic unit.
   * <p>
   * numeric, corresponds to NPD property <em>lsuBottomDepth</em>
   *
   * @return End depth of this stratigraphy. Null if N/A or unknown.
   */
  public int getBottomDepth()
  {
    return bottomDepth_;
  }

  /**
   * Return the lithostratigraphic unit of this stratigraphy.
   * <p>
   * <b>NPD description</b><br>
   * The lithostratigraphic unit's official name.
   * <p>
   * varchar(20), corresponds to NPD property <em>lsuName</em>.
   *
   * @return Lithostratigraphic unit of this stratigraphy. Null if N/A or unknown.
   */
  public String getLithostratigraphicUnit()
  {
    return lithostratigraphicUnit_;
  }

  /**
   * Return the level of the lithostratigraphic unit for this stratigraphy.
   * <p>
   * <b>NPD description</b><br>
   * Indicates the level of the unit.
   * <p>
   * varchar(9), corresponds to NPD property <em>lsuLevel</em>
   *
   * @return Level of lithostratigraphic unit for this stratigraphy. Null if N/A or unknown.
   */
  public String getLevel()
  {
    return level_;
  }

  /**
   * Return the id of the lithostratigraphic unit for this stratigraphy.
   * <p>
   * <b>NPD description</b><br>
   * NPD's unique id for lithostratigraphic units.
   * <p>
   * int, corresponds to NPD property <em>lsuNpdidLithoStrat</em>.
   *
   * @return Id of the lithostratigraphic unit for this stratigraphy. Null if N/A or unknown.
   */
  public int getLithostratigraphicUnitId()
  {
    return lithostratigraphicUnitId_;
  }

  /**
   * Return the wellbore completion date for this stratigraphy.
   * <p>
   * <b>NPD description:</b><br>
   * Exploration wellbores from moveable facilities:
   * <p>
   *   For floating facilities - date when anchor handling is started.<br>
   *   For jackups - date the jacking-down started.
   *
   * <p>
   * Exploration wellbores from fixed facilities and all development wellbores:
   * <p>
   * Date when the wellbore is at total depth, and last casing or liner is set.
   * <p>
   * datetime, corresponds to the NPD property <em>wlbCompletionDate</em>.
   *
   * @return  Wellbore completion date for this stratigraphy. Null if N/A or unknown.
   */
  public Date getWellboreCompletion()
  {
    return wellboreCompletion_;
  }

  /**
   * Return the wellbore NPD ID of this stratigraphy.
   * <p>
   * <b>NPD description</b><br>
   * NPD's unique id for the wellbore.
   * <p>
   * int, corresponds to NPD property <em>wlbNPDID_wellbore</em>
   *
   * @return Wellbore NPD ID of this stratigraphy.
   */
  public int getWellboreNpdId()
  {
    return wellboreNpdId_;
  }

  /**
   * Return the date the wellbore of this stratigraphy was last updated.
   * <p>
   * <b>NPD description</b><br>
   * Date when this record was updated last or inserted the first time.
   * <p>
   * datetime, corresponds to NPD property <em>lsuWellboreUpdatedDate</em>
   *
   * @return Date the wellbore of this stratigraphy was last updated.
   */
  public Date getLastUpdated()
  {
    return lastUpdated_;
  }

  /**
   * Return the date this stratigraphy was synced from the NPD back-end database.
   *
   * @return Sync date of this stratigraphy. Null if unknown.
   */
  public Date getLastSynced()
  {
    return lastSynced_;
  }

  @Override
  public String toString()
  {
    return "NpdStratigraphy [wellboreName=" + wellboreName_ + ", topDepth=" + topDepth_ + ", bottomDepth="
        + bottomDepth_ + ", lithostratigraphicUnit=" + lithostratigraphicUnit_ + ", level=" + level_
        + ", lithostratigraphicUnitId=" + lithostratigraphicUnitId_ + ", wellboreCompletion="
        + wellboreCompletion_ + ", wellboreNpdId=" + wellboreNpdId_ + ", lastUpdated=" + lastUpdated_
        + ", lastSynced=" + lastSynced_ + "]";
  }
}
