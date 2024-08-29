package no.geosoft.sodir.facility;

import java.util.Date;

/**
 * A moveable facility as modeled by Sodir.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirMoveableFacility extends SodirFacility
{
  /** AOC status of the facility. */
  private final String aocStatus_;

  /** Name of responsible company. Null if none or unknown. */
  private final String responsibleCompanyName_;

  /** Unique ID of responsible company. Null if none or unknown. */
  private final String responsibleCompanyId_;

  /**
   * Create a Sodir moveable facility instance.
   */
  SodirMoveableFacility(String npdId,
                        String name,
                        String responsibleCompanyName,
                        String kind,
                        String functions,
                        String aocStatus,
                        String nation,
                        String factPageUrl,
                        String responsibleCompanyId,
                        Date lastChangedDate,
                        Date syncDate)
  {
    super("facility_moveable", npdId, name, kind, functions, nation, factPageUrl, null,  lastChangedDate, syncDate);

    aocStatus_ = aocStatus;
    responsibleCompanyName_ = responsibleCompanyName;
    responsibleCompanyId_ = responsibleCompanyId;
  }

  /**
   * Return the AOC status of this facility.
   * <p>
   * <b>Sodir description:</b><br>
   * AOC status of the facility. Example of legal values:
   * <ul>
   *   <li>AOC ok</li>
   *   <li>In queue for AOC</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to Sodir property <em>fclStatus</em>.
   *
   * @return  AOC status of this facility. Null if N/A or unknown.
   */
  public String getAocStatus()
  {
    return aocStatus_;
  }

  /**
   * Return name responsible company of this facility.
   * <p>
   * <b>Sodir description:</b><br>
   * Official name of currently responsible company for a moveable facility.
   * <p>
   * varchar(100), corresponds to Sodir property <em>fclCurrentRespCompanyName</em>.
   *
   * @return  Name of responsible company. Null if N/A or unknown.
   */
  public String getResponsibleCompanyName()
  {
    return responsibleCompanyName_;
  }

  /**
   * Return Sodir unique ID of current responsible company of this facility.
   * <p>
   * <b>Sodir description:</b><br>
   * Sodir's unique id for companies.
   * <p>
   * int, corresponds to Sodir property <em>fclNpdidCurrentRespCompany</em>.
   *
   * @return  Sodir ID of responsible company. Null if N/A or unknown.
   */
  public String getResponsibleCompanyId()
  {
    return responsibleCompanyId_;
  }
}
