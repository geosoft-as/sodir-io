package no.geosoft.npdio.stratigraphy;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Class with stratigraphy information grouped by wellbore.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class NpdWellboreStratigraphy
{
  private final String wellboreName_;
  private final int wellboreNpdId_;
  private final int topDepth_;
  private final int bottomDepth_;
  private final Date wellboreCompletion_;
  private final Date lastUpdated_;
  private final Date lastSynced_;

  // Raw data
  private final List<NpdStratigraphy> lithostratigraphy_;

  /**
   * Create an NPD wellbore stratigraphy instance.
   *
   * @param lithostratigraphy List of all stratigraphy instances
   */
  NpdWellboreStratigraphy(List<NpdStratigraphy> lithostratigraphy)
  {
    wellboreName_ = lithostratigraphy.get(0).getWellboreName();
    wellboreNpdId_ = lithostratigraphy.get(0).getWellboreNpdId();
    wellboreCompletion_ = lithostratigraphy.get(0).getWellboreCompletion();
    lastUpdated_ = lithostratigraphy.get(0).getLastUpdated();
    lastSynced_ = lithostratigraphy.get(0).getLastSynced();
    topDepth_ = lithostratigraphy.get(0).getTopDepth();
    bottomDepth_ = lithostratigraphy.get(lithostratigraphy.size()-1).getBottomDepth();

    lithostratigraphy_ = lithostratigraphy;
  }

  /**
   * Return the wellbore name of this wellbore stratigraphy.
   * <p>
   * <b>NPD description:</b><br>
   * Official name of the wellbore based on NPD guidelines for designation of wells and wellbores.
   * <p>
   * varchar(40), corresponds to NPD property <em>wlbName</em>.
   *
   * @return  Wellbore name of this wellbore stratigraphy. Null if N/A or unknown.
   */
  public String getWellboreName()
  {
    return wellboreName_;
  }

  /**
   * Return the wellbore NPD ID of this wellbore stratigraphy.
   * <p>
   * <b>NPD description</b><br>
   * NPD's unique id for the wellbore.
   * <p>
   * int, corresponds to NPD property <em>wlbNPDID_wellbore<em>
   *
   * @return Wellbore NPD ID of this wellbore stratigraphy.
   */
  public int getWellboreNpdId()
  {
    return wellboreNpdId_;
  }

  /**
   * Return the start depth of this wellbore stratigraphy.
   * <p>
   * <b>NPD description</b><br>
   * Measured length along the wellbore from kelly bushing to start of lithostratigraphic unit.
   * <p>
   * numeric, corresponds to NPD property <em>lsuTopDepth</em>
   *
   * @return Start depth of this wellbore stratigraphy. Null if N/A or unknown.
   */
  public int getTopDepth()
  {
    return topDepth_;
  }

  /**
   * Return end depth of this wellbore stratigraphy.
   * <p>
   * <b>NPD description</b><br>
   * Depth in meters to the bottom of the lithostratigraphic unit.
   * <p>
   * numeric, corresponds to NPD property <em>lsuBottomDepth</em>
   *
   * @return End depth of this wellbore stratigraphy. Null if N/A or unknown.
   */
  public int getBottomDepth()
  {
    return bottomDepth_;
  }

  /**
   * Return the wellbore completion date for this wellbore stratigraphy.
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
   * @return  Wellbore completion date for this wellbore stratigraphy. Null if N/A or unknown.
   */
  public Date getWellboreCompletion()
  {
    return wellboreCompletion_;
  }

  /**
   * Return the date the wellbore of this stratigraphy was last updated.
   * <p>
   * <b>NPD description</b><br>
   * Date when this record was updated last or inserted the first time.
   * <p>
   * datetime, corresponds to NPD property <em>lsuWellboreUpdatedDate<em>
   *
   * @return Date the wellbore of this wellbore stratigraphy was last updated. Null if N/A or unknown.
   */
  public Date getLastUpdated()
  {
    return lastUpdated_;
  }

  /**
   * Return the date this wellbore stratigraphy was synced from the NPD back-end database.
   *
   * @return Sync date of this wellbore stratigraphy. Null if N/A or unknown.
   */
  public Date getLastSynced()
  {
    return lastSynced_;
  }

  /**
   * Return a list of all NpdStratigraphy instances for this wellbore stratigraphy.
   *
   * @return List of NpdStratigraphy instances. Non-null.
   */
  public List<NpdStratigraphy> getLithostratigraphy()
  {
    return Collections.unmodifiableList(lithostratigraphy_);
  }
}
