package no.geosoft.sodirio.well;

import java.util.Date;

/**
 * Other (that is non-exploration, non-development) wellbore as
 * modeled by Sodir.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirOtherWellbore extends SodirWellbore
{
  /** Wellbore seismic location. Null if N/A or unknown. */
  private final String seismicLocation_;

  private final String alias_;

  /**
   * Create Sodir <em>other</em> wellbore instance.
   */
  SodirOtherWellbore(String npdId,
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
                     String siteSurvey,
                     String npdidSiteSurvey,
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
                     Double latitude, // NsDecDeg
                     Double longitude, // EwDecDeg
                     Double nsUtm,
                     Double ewUtm,
                     Integer utmZone,
                     Integer namePart1, // quadrant
                     Integer namePart2, // block
                     String namePart3, // installation
                     Integer namePart4, // installation
                     String namePart5,
                     String namePart6,
                     String factPageUrl,
                     String factMapUrl,
                     String alias,
                     String diskosWellboreType,
                     String diskosWellboreParent,
                     String npdidDiscovery,
                     String npdidField,
                     Date wdssQcDate,
                     Date releaseDate,
                     String npdidProductionLicense,
                     String npdidFacilityDrilling,
                     String npdidWellboreReclass,
                     Date mainLevelUpdatedDate,
                     Date updatedDate,
                     Date syncDate,

                     // Other wellbore specific
                     String seismicLocation)
  {
    super("wellbore_development", // Yes, development, used to point to the correct url at Sodir
          npdId,
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
          siteSurvey,
          npdidSiteSurvey,
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
          npdidDiscovery,
          npdidField,
          wdssQcDate,
          releaseDate,
          npdidProductionLicense,
          npdidFacilityDrilling,
          npdidWellboreReclass,
          mainLevelUpdatedDate,
          updatedDate,
          syncDate);

    seismicLocation_ = seismicLocation;
    alias_ = alias;
  }

  /**
   * Return seismic location of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * This is the position of wellbore on seismic survey lines. SP: shot point.
   * <p>
   * varchar(200), corresponds to Sodir property <em>wlbSeismicLocation</em>.
   *
   * @return  Seismic location of this wellbore.
   */
  public String getSeismicLocation()
  {
    return seismicLocation_;
  }

  /**
   * Return alias of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * TODO
   *
   * @return  Alias of this wellbore.
   */
  public String getAlias()
  {
    return alias_;
  }
}
