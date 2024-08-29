package no.geosoft.sodir.pipeline;

import java.util.Date;

import no.geosoft.sodir.SodirObject;

/**
 * A pipeline as modeled by Sodir.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirPipeline extends SodirObject
{
  /** Map label of this pipeline. Null if N/A. */
  private final String mapLabel_;

  /** Name of the facility where the pipeline starts. Null if N/A. */
  private final String fromFacility_;

  /** Name of the facility where the pipeline ends. Null if N/A. */
  private final String toFacility_;

  /** Name of the pipeline or field this pipeline belongs to. Null if N/A. */
  private final String belongsTo_;

  /** Operator company of this pipeline. Null id N/A. */
  private final String operator_;

  /** Current phase for the pipeline. Null if N/A. */
  private final String currentPhase_;

  /** Date the current phase started. Null if N/A. */
  private final Date currentPhaseFromDate_;

  /** Medium transported in the pipeline. Null if N/A. */
  private final String medium_;

  /** Main grouping of the pipeline. Null if N/A. */
  private final String mainGrouping_;

  /** Pipeline diameter dimension in inches. Null if not specified. */
  private final Double dimension_;

  /** Maximum water depth of the pipeline. Null if not specified. */
  private final Double waterDepth_;

  /** NPDID of the operator company. Null if N/A. */
  private final String npdidOperator_;

  /** NPDID of the from facility. Null if N/A. */
  private final String npdidFromFacility_;

  /** NPDID of the to facility. Null if N/A. */
  private final String npdidToFacility_;

  /**
   * Create an NPD pipeline instance.
   */
  SodirPipeline(String npdId,
                String name,
                String mapLabel,
                String fromFacility,
                String toFacility,
                String belongsTo,
                String operator,
                String currentPhase,
                Date currentPhaseFromDate,
                String medium,
                String mainGrouping,
                Double dimension,
                Double waterDepth,
                String npdidOperator,
                String npdidFromFacility,
                String npdidToFacility,
                String factPageUrl,
                String factMapUrl,
                Date lastChangedDate,
                Date syncDate)
  {
    super("tuf_pipeline", npdId, name, factPageUrl, factMapUrl, lastChangedDate, syncDate);

    mapLabel_ = mapLabel;
    fromFacility_ = fromFacility;
    toFacility_ = toFacility;
    belongsTo_ = belongsTo;
    operator_ = operator;
    currentPhase_ = currentPhase;
    currentPhaseFromDate_ = currentPhaseFromDate != null ? new Date(currentPhaseFromDate.getTime()) : null;
    medium_ = medium;
    mainGrouping_ = mainGrouping;
    dimension_ = dimension;
    waterDepth_ = waterDepth;
    npdidOperator_ = npdidOperator;
    npdidFromFacility_ = npdidFromFacility;
    npdidToFacility_ = npdidToFacility;
  }

  /**
   * Return the map label of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Name used in the map.
   * <p>
   * varchar(100), corresponds to Sodir property <em>pplMapLabel</em>.
   *
   * @return  The map label of this pipeline. Null if N/A.
   */
  public String getMapLabel()
  {
    return mapLabel_;
  }

  /**
   * Return the from facility of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Name of facility where the pipeline starts.
   * <p>
   * varchar(50), corresponds to Sodir property <em>fclNameFrom</em>.
   *
   * @return  The from facility of this pipeline. Null if N/A.
   */
  public String getFromFacility()
  {
    return fromFacility_;
  }

  /**
   * Return the to facility of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Name of facility where the pipeline ends.
   * <p>
   * varchar(50), corresponds to Sodir property <em></em>.
   *
   * @return  The to facility of this pipeline. Null if N/A.
   */
  public String getToFacility()
  {
    return toFacility_;
  }

  /**
   * Return the pipeline or field this pipeline belongs to.
   * <p>
   * <b>Sodir description:</b><br>
   * Name of the TUF or field the pipeline belongs to.
   * <p>
   * varchar(50), corresponds to Sodir property <em>pplBelongsToName</em>.
   *
   * @return  The pipeline or field this pipeline belongs to. Null if N/A.
   */
  public String getBelongsTo()
  {
    return belongsTo_;
  }

  /**
   * Return the operator of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Operator company for this pipeline.
   * <p>
   * varchar(100), corresponds to Sodir property <em>cmpLongName</em>.
   *
   * @return  The operator of this pipeline. Null if N/A.
   */
  public String getOperator()
  {
    return operator_;
  }

  /**
   * Return the current phase of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Current phase for the pipeline.
   * Examples of legal values:
   * <ul>
   *   <li>FUTURE</li>
   *   <li>REMOVED</li>
   *   <li>IN SERVICE</li>
   *   <li>INSTALLATION</li>
   *   <li>DECOMMISSIONED</li>
   *   <li>ABANDONED IN PLACE</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to Sodir property <em>pplCurrentPhase</em>.
   *
   * @return The current phase of this pipeline. Null if N/A.
   */
  public String getCurrentPhase()
  {
    return currentPhase_;
  }

  /**
   * Return the from date of the current phase of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Date from, current phase.
   * <p>
   * datetime, corresponds to Sodir property <em>pplCurrentPhaseFromDate</em>.
   *
   * @return  The from date of the current phase of this pipeline. Null if N/A.
   */
  public Date getCurrentPhaseFromDate()
  {
    return currentPhaseFromDate_ != null ? new Date(currentPhaseFromDate_.getTime()) : null;
  }

  /**
   * Return the medium of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Medium transported in the pipeline.
   * <p>
   * varchar(40), corresponds to Sodir property <em>pplMedium</em>.
   *
   * @return  The medium of this pipeline. Null if N/A.
   */
  public String getMedium()
  {
    return medium_;
  }

  /**
   * Return the main grouping of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Name of main grouping of pipeline.
   * Example of legal values:
   * <ul>
   *   <li>Transportation</li>
   *   <li>Feeder</li>
   * </ul>
   * <p>
   * varchar(100), corresponds to Sodir property <em>pplMainGroupingName</em>.
   *
   * @return  The main grouping of this pipeline. Null if N/A.
   */
  public String getMainGrouping()
  {
    return mainGrouping_;
  }

  /**
   * Return the diameter dimension of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Pipeline dimension in inches.
   * <p>
   * real, corresponds to Sodir property <em>pplDimension</em>.
   *
   * @return  The diameter dimension of this pipeline in inches. Null if not specified.
   */
  public Double getDimension()
  {
    return dimension_;
  }

  /**
   * Return the maximum water depth of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Maximum water depth [m].
   * <p>
   * real, corresponds to Sodir property <em>pplWaterDepth</em>.
   *
   * @return  The maximum water depth of this pipeline in meters. Null if N/A.
   */
  public Double getWaterDepth()
  {
    return waterDepth_;
  }

  /**
   * Return the NPDID of the operator of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Sodir's unique id for the company operating the pipeline.
   * <p>
   * int, corresponds to Sodir property <em>cmpNpdidCompany</em>.
   *
   * @return  The NPDID of the operator of this pipeline. Null if N/A.
   */
  public String getNpdidOperator()
  {
    return npdidOperator_;
  }

  /**
   * Return the NPDID of the from facility of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Sodir's unique id for the facility where the pipeline starts.
   * <p>
   * int, corresponds to Sodir property <em>fclNpdidFacilityFrom</em>.
   *
   * @return  The NPDID of the from facility of this pipeline. Null if N/A.
   */
  public String getNpdidFromFacility()
  {
    return npdidFromFacility_;
  }

  /**
   * Return the NPDID of the to facility of this pipeline.
   * <p>
   * <b>Sodir description:</b><br>
   * Sodir's unique id for the facility where the pipeline ends.
   * <p>
   * int, corresponds to Sodir property <em>fclNpdidFacilityTo</em>.
   *
   * @return  The NPDID of the to facility of this pipeline. Null if N/A.
   */
  public String getNpdidToFacility()
  {
    return npdidToFacility_;
  }
}
