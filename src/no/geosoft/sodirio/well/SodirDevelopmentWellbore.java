package no.geosoft.sodirio.well;

import java.util.Date;

/**
 * Development wellbore as modeled by Sodir.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirDevelopmentWellbore extends SodirWellbore
{
  private final Date preDrillEntryDate_;

  private final Date preDrillCompletionDate_;

  private final String productionFacility_;

  private final String contentPlanned_;

  private final String idFacilityProducing_;

  private final String idTargetProductionLicense_;

  /**
   * Create a Sodir development wellbore instance.
   */
  SodirDevelopmentWellbore(String id,
                           String name,
                           String wellName,
                           String drillingOperator,
                           String productionLicense,
                           String status,
                           String purpose,
                           String purposePlanned,
                           String content,
                           String wellType,
                           Boolean isSubsea,
                           Date entryDate,
                           Date completionDate,
                           Date preDrillEntryDate,
                           Date preDrillCompletionDate,
                           String field,
                           String drillPermit,
                           String discovery,
                           Boolean isDiscoveryWellbore,
                           Double kellyBushElevation,
                           Double finalVerticalDepth,
                           Double totalDepth,
                           Double waterDepth,
                           Double kickOffPoint,
                           String mainArea,
                           String drillingFacility,
                           String drillingFacilityType,
                           String drillingFacilityCategory,
                           String licensingActivity,
                           Boolean isMultilateral,
                           Integer entryYear,
                           Integer completionYear,
                           String reclassFromWellbore,
                           Date pluggedAndAbandonDate,
                           Date pluggedDate,
                           String licenseTargetName,
                           Integer plotSymbol,
                           String geodeticDatum,
                           Integer nsDeg,
                           Integer nsMin,
                           Double nsSec,
                           String nsCode,
                           Integer ewDeg,
                           Integer ewMin,
                           Double ewSec,
                           String ewCode,
                           Double latitude,
                           Double longitude,
                           Double nsUtm,
                           Double ewUtm,
                           Integer utmZone,
                           Integer namePart1,
                           Integer namePart2,
                           String namePart3,
                           Integer namePart4,
                           String namePart5,
                           String namePart6,
                           String factPageUrl,
                           String factMapUrl,
                           String diskosWellboreType,
                           String diskosWellboreParent,
                           String idDiscovery,
                           String idField,
                           Date wdssQcDate,
                           Date releaseDate,
                           String idProductionLicense,
                           String idFacilityDrilling,
                           String idWellboreReclass,
                           Date mainLevelUpdatedDate,
                           Date updatedDate,
                           Date syncDate,
                           String productionFacility,
                           String contentPlanned,
                           String idFacilityProducing,
                           String idTargetProductionLicense)
  {
    super("wellbore_development",
          id,
          name,
          wellName,
          drillingOperator,
          productionLicense,
          status,
          purpose,
          purposePlanned,
          content,
          wellType,
          isSubsea,
          entryDate,
          completionDate,
          field,
          drillPermit,
          discovery,
          isDiscoveryWellbore,
          kellyBushElevation,
          finalVerticalDepth,
          totalDepth,
          waterDepth,
          kickOffPoint,
          mainArea,
          drillingFacility,
          drillingFacilityType,
          drillingFacilityCategory,
          licensingActivity,
          isMultilateral,
          entryYear,
          completionYear,
          reclassFromWellbore,
          null, // reclassificationDate
          null, // siteSurvey
          null, // idSiteSurvey
          pluggedAndAbandonDate,
          pluggedDate,
          licenseTargetName,
          plotSymbol,
          geodeticDatum,
          nsDeg,
          nsMin,
          nsSec,
          nsCode,
          ewDeg,
          ewMin,
          ewSec,
          ewCode,
          latitude,
          longitude,
          nsUtm,
          ewUtm,
          utmZone,
          namePart1,
          namePart2,
          namePart3,
          namePart4,
          namePart5,
          namePart6,
          factPageUrl,
          factMapUrl,
          diskosWellboreType,
          diskosWellboreParent,
          idDiscovery,
          idField,
          wdssQcDate,
          releaseDate,
          idProductionLicense,
          idFacilityDrilling,
          idWellboreReclass,
          mainLevelUpdatedDate,
          updatedDate,
          syncDate);

    productionFacility_ = productionFacility;
    contentPlanned_ = contentPlanned;
    idFacilityProducing_ = idFacilityProducing;
    preDrillEntryDate_ = preDrillEntryDate;
    preDrillCompletionDate_ = preDrillCompletionDate;
    idTargetProductionLicense_ = idTargetProductionLicense;
  }

  /**
   * Return the pre drill entry date of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Date when the predrilling activity of the wellbore started (see also
   * Entry date).
   * <p>
   * datetime, corresponds to the Sodir property <em>wlbEntryPreDrillDate</em>.
   *
   * @return  Pre drill entry date of this wellbore. Null if not specified.
   */
  public Date getPreDrillEntryDate()
  {
    return preDrillEntryDate_ != null ? new Date(preDrillEntryDate_.getTime()) : null;
  }

  /**
   * Return the pre drill completion date of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Date when the predrilling activity of the wellbore was completed
   * (see also completion date).
   * <p>
   * datetime, corresponds to the Sodir property <em>wlbCompPreDrillDate</em>.
   *
   * @return  Pre drill completion date of this wellbore. Null if not specified.
   */
  public Date getPreDrillCompletionDate()
  {
    return preDrillCompletionDate_ != null ? new Date(preDrillCompletionDate_.getTime()) : null;
  }

  /**
   * Return production facility of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * The Sodir's name for the production facility, for development wells.
   * <p>
   * varchar(50), corresponds to the Sodir property <em>wlbProductionFacility</em>.
   *
   * @return  Production facility of this wellbore.
   */
  public String getProductionFacility()
  {
    return productionFacility_;
  }

  /**
   * Return planned content of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Only relevant for development wellbores, planned type of produced or injected fluid.<br>
   * Examples of legal values:
   * <ul>
   *   <li>CO2</li>
   *   <li>CUTTINGS</li>
   *   <li>GAS</li>
   *   <li>NOT APPLICABLE</li>
   *   <li>NOT AVAILABLE</li>
   *   <li>WATER</li>
   *   <li>WATER/GAS</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to the Sodir property <em>wlbContentPlanned</em>.
   *
   * @return  Planned content of this wellbore.
   */
  public String getContentPlanned()
  {
    return contentPlanned_;
  }

  /**
   * Return ID of the producing facility of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Sodir's unique id for the production facility, for development wellbores.
   * <p>
   * bigint, corresponds to the Sodir property <em>fclNpdidFacilityProducing</em>.
   *
   * @return  ID of the producing facility of this wellbore.
   */
  public String getIdFacilityProducing()
  {
    return idFacilityProducing_;
  }

  /**
   * Return ID of the target production license.
   * <p>
   * <b>Sodir description:</b><br>
   * NPDID production license target.
   * Sodir unique key for the production license of the wellbore target.
   * This can be another production license then where the wellhead
   * position.
   * <p>
   * int, corresponds to the Sodir property <em>prlNpdidProdLicenceTarget</em>.
   *
   * @return  ID of the target production license. Null if N/A.
   */
  public String getIdTargetProductionLicense()
  {
    return idTargetProductionLicense_;
  }
}
