package no.geosoft.sodirio.field;

import java.util.Date;

import no.geosoft.sodirio.SodirObject;

/**
 * A field as modeled by the Sodir.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirField extends SodirObject
{
  /** Operator name. Null if N/A or unknown. */
  private final String operatorName_;

  /** Current activity status. Null if N/A or unknown. */
  private final String activityStatus_;

  /** Discovery wellbore name. Null if N/A or unknown. */
  private final String discoveryWellboreName_;

  /** Discovery wellbore completion date. Null if N/A or unknown. */
  private final Date discoveryWellboreCompletionDate_;

  /** Main area. Null if N/A or unknown. */
  private final String mainArea_;

  /** Owner kind. Null if N/A or unknown. */
  private final String ownerKind_;

  /** Owner name. Null if N/A or unknown. */
  private final String ownerName_;

  /** Main supply base. Null if N/A or unknown. */
  private final String mainSupplyBase_;

  private final String hydrocarbonType_;

  /** NPDID of owner. Null if N/A or unknown. */
  private final String idOwner_;

  /** NPDID of discovery wellbore. Null if N/A or unknown. */
  private final String idDiscoveryWellbore_;

  /** NPDID of operator. Null if N/A or unknown. */
  private final String idOperator_;

  /** Lock used when setting production. */
  private final Object lock_ = new Object();

  /** Field production. Null if not specified. */
  private Production production_ = null;

  /**
   * Create an Sodir field instance.
   */
  SodirField(String id,
             String name,
             String operatorName,
             String activityStatus,
             String discoveryWellboreName,
             Date discoveryWellboreCompletionDate,
             String mainArea,
             String ownerKind,
             String ownerName,
             String mainSupplyBase,
             String hydrocarbonType,
             String idOwner,
             String idDiscoveryWellbore,
             String idOperator,
             String factPageUrl,
             String factMapUrl,
             Date mainLevelUpdatedDate,
             Date lastChangedDate,
             Date syncDate)
  {
    super("field", id, name, factPageUrl, factMapUrl, lastChangedDate, syncDate);

    operatorName_ = operatorName;
    activityStatus_ = activityStatus;
    discoveryWellboreName_ = discoveryWellboreName;
    discoveryWellboreCompletionDate_ = discoveryWellboreCompletionDate != null ? new Date(discoveryWellboreCompletionDate.getTime()) : null;
    mainArea_ = mainArea;
    ownerKind_ = ownerKind;
    ownerName_ = ownerName;
    mainSupplyBase_ = mainSupplyBase;
    hydrocarbonType_ = hydrocarbonType;
    idOwner_ = idOwner;
    idDiscoveryWellbore_ = idDiscoveryWellbore;
    idOperator_ = idOperator;
  }

  /**
   * Return operator name of this field.
   * <p>
   * <b>Sodir description:</b><br>
   * The operating company's official name.
   * <p>
   * varchar(100), corresponds to Sodir property <em>cmpLongName</em>.
   *
   * @return  Operator name of this field.
   */
  public String getOperatorName()
  {
    return operatorName_;
  }

  /**
   * Return current activity status of this field.
   * <p>
   * <b>Sodir description:</b><br>
   * Current activity status of the field. Example of legal values:
   * <ul>
   *   <li>SHUT DOWN</li>
   *   <li>PRODUCING</li>
   *   <li>PDO APPROVED<li>
   * </ul>
   * <p>
   * varchar(40), corresponds to Sodir property <em>fldCurrentActivitySatus</em>.
   *
   * @return  Current activity status of the field. Null if unknown.
   */
  public String getActivityStatus()
  {
    return activityStatus_;
  }

  /**
   * Return discovery wellbore name of this field.
   * <p>
   * <b>Sodir description:</b><br>
   * Name of the wellbore which proved the field.
   * <p>
   * varchar(60), corresponds to Sodir property <em>wlbName</em>.
   *
   * @return  Discovery wellbore name of the field.
   */
  public String getDiscoveryWellboreName()
  {
    return discoveryWellboreName_;
  }

  /**
   * Return the completion date of the discovery wellbore of this field.
   * <p>
   * <b>Sodir description:</b><br>
   * Missing.
   * <p>
   * Corresponds to Sodir property <em>wlbCompletionDate</em>.
   *
   * @return  The completion date of the discovery wellbore of this field. Null if N/A or unknown.
   */
  public Date getDiscoveryWellboreCompletionDate()
  {
    return discoveryWellboreCompletionDate_ != null ? new Date(discoveryWellboreCompletionDate_.getTime()) : null;
  }

  /**
   * Return main area of this field.
   * <p>
   * <b>Sodir description:</b><br>
   * Main NCS area. Example of legal values:
   * <ul>
   *   <li>NORTH SEA</li>
   *   <li>NORWEGIAN SEA</li>
   *   <li>BARENTS SEA</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to Sodir property <em>fldMainArea</em>.
   *
   * @return  Main area of this field. Null if N/A or unknown.
   */
  public String getMainArea()
  {
    return mainArea_;
  }

  /**
   * Return kind of owner of this field.
   * <p>
   * <b>Sodir description:</b><br>
   * Kind of owner:
   * <ul>
   *   <li>PRODUCTION LICENCE</li>
   *   <li>BUSINESS ARRANGEMENT AREA)</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to Sodir property <em>fldOwnerKind</em>.
   *
   * @return  Kind of owner of this field. Null if N/A or unknown.
   */
  public String getOwnerKind()
  {
    return ownerKind_;
  }

  /**
   * Return name of owner of this field.
   * <p>
   * <b>Sodir description:</b><br>
   * Owner name.
   * <p>
   * varchar(40), corresponds to Sodir property <em>fldOwnerName</em>.
   *
   * @return  Name of owner of this field. Null if N/A or unknown.
   */
  public String getOwnerName()
  {
    return ownerName_;
  }

  /**
   * Return the main supply base of this field.
   * <p>
   * <b>Sodir description:</b><br>
   * Main supply base.
   * <p>
   * varchar(50), corresponds to Sodir property <em>fldMainSupplyBase</em>.
   *
   * @return  Main supply base of this field. Null if N/A or unknown.
   */
  public String getMainSupplyBase()
  {
    return mainSupplyBase_;
  }

  /**
   * Return the hydrocarbon type of this field.
   * <p>
   * <b>Sodir description:</b><br>
   * TODO
   *
   * @return  The hydrocarbon type of this field.
   */
  public String getHydrocarbonType()
  {
    return hydrocarbonType_;
  }

  /**
   * Unique ID of the license of this field.
   *
   * @return  Unique ID of the license of this field.
   */
  /**
   * Return the Sodir ID of the owner of this field.
   * <p>
   * <b>Sodir description:</b><br>
   * <p>
   * int, corresponds to Sodir property <em>fldNpdidOwner</em>.
   *
   * @return  Sodir ID of the owner of this field. Null if N/A or unknown.
   */
  public String getIdOwner()
  {
    return idOwner_;
  }

  /**
   * Return Sodir ID of the dicovery wellbore of this field.
   * <p>
   * <b>Sodir description:</b><br>
   * Sodir's unique id for wellbores.
   * <p>
   * int, corresponds to Sodir property <em>wlbNpdidWellbore</em>.
   *
   * @return  Sodir ID of the discovery wellbore of this field. Null if N/A or unknown.
   */
  public String getIdDiscoveryWellbore()
  {
    return idDiscoveryWellbore_;
  }

  /**
   * Return the Sodir ID of the opertaor of this field.
   * <p>
   * <b>Sodir description:</b><br>
   * Sodir's unique id for companies.
   * <p>
   * varchar(), corresponds to Sodir property <em>cmpNpdidCompany</em>.
   *
   * @return  Sodir ID of the operator of this field. Null if N/A or unknown.
   */
  public String getIdOperator()
  {
    return idOperator_;
  }

  /**
   * Set the production of this field.
   *
   * @param production  Production to set. Null if none.
   */
  void setProduction(Production production)
  {
    synchronized (lock_) {
      production_ = production;
    }
  }

  /**
   * Return production of this field.
   * <p>
   * Production is null until it is instantiated by the production reader.
   *
   * @return  Production of this field. Null if none or not loaded.
   */
  public Production getProduction()
  {
    synchronized (lock_) {
      return production_;
    }
  }
}
