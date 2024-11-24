package no.geosoft.sodirio.well;

import java.util.Date;

/**
 * Exploration wellbore as modeled by Sodir.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirExplorationWellbore extends SodirWellbore
{
  private final Integer bottomHoleTemperature_;
  private final String seismicLocation_;
  private final Double maxInclination_;
  private final String ageAtTd_;
  private final String formationAtTd_;
  private final String reentryExplorationActivity_;
  private final String formationWithHc1_;
  private final String ageWithHc1_;
  private final String formationWithHc2_;
  private final String ageWithHc2_;
  private final String formationWithHc3_;
  private final String ageWithHc3_;
  private final Integer drillingDays_;
  private final Boolean isReentry_;
  private final String pressReleaseUrl_;

  /**
   * Create an NPS exploration wellbore instance.
   */
  SodirExplorationWellbore(String id,
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
                           Date reclassificationDate,
                           String siteSurvey,
                           String idSiteSurvey,
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

                           // Explorarion wellbore specific
                           Integer bottomHoleTemperature,
                           String seismicLocation,
                           Double maxInclination,
                           String ageAtTd,
                           String formationAtTd,
                           String reentryExplorationActivity,
                           String formationWithHc1,
                           String ageWithHc1,
                           String formationWithHc2,
                           String ageWithHc2,
                           String formationWithHc3,
                           String ageWithHc3,
                           Integer drillingDays,
                           Boolean isReentry,
                           String pressReleaseUrl)
  {
    super("wellbore_exploration",
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
          reclassificationDate,
          siteSurvey,
          idSiteSurvey,
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

    bottomHoleTemperature_ = bottomHoleTemperature;
    seismicLocation_ = seismicLocation;
    maxInclination_ = maxInclination;
    ageAtTd_ = ageAtTd;
    formationAtTd_ = formationAtTd;
    reentryExplorationActivity_ = reentryExplorationActivity;
    formationWithHc1_ = formationWithHc1;
    ageWithHc1_ = ageWithHc1;
    formationWithHc2_ = formationWithHc2;
    ageWithHc2_ = ageWithHc2;
    formationWithHc3_ = formationWithHc3;
    ageWithHc3_ = ageWithHc3;
    drillingDays_ = drillingDays;
    isReentry_ = isReentry;
    pressReleaseUrl_ = pressReleaseUrl;
  }

  /**
   * Return bottom hole temperature for the wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * This is the estimated temperature at final total depth of the wellbore.
   * <p>
   * int, corresponds to the Sodir property <em>wlbBottomHoleTemperature</em>.
   *
   * @return Bottom hole temperature in degrees Celsius.
   *         Null if N/A or unknown.
   */
  public Integer getBottomHoleTemperature()
  {
    return bottomHoleTemperature_;
  }

  /**
   * Return seismic location.
   * <p>
   * <b>Sodir description:</b><br>
   * Position of wellbore on seismic survey lines. SP: shot point.
   * <p>
   * varchar(200), corresponds to the Sodir property <em>wlbSeismicLocation</em>.
   *
   * @return  Seismic location. Null if N/A or unknown.
   */
  public String getSeismicLocation()
  {
    return seismicLocation_;
  }

  /**
   * Return maximum inclination.
   * <p>
   * <b>Sodir description:</b><br>
   * Maximum deviation, in degrees, from a vertical well path.
   * <p>
   * real, corresponds to the Sodir property <em>wlbMaxInclation</em>.
   *
   * @return  Max inclination in degrees. Null if N/A or unknown.
   */
  public Double getMaxInclination()
  {
    return maxInclination_;
  }

  /**
   * Return age of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Age (according to Geologic Time Scale 2004 by F. M. Gradstein, et al. (2004))
   * of the oldest penetrated formation. May differ from age at TD for example in
   * deviated wellbores.
   * <p>
   * Examples of legal values:
   * <ul>
   *   <li>CRETACEOUS</li>
   *   <li>EARLY CRETACEOUS</li>
   *   <li>LATE JURASSIC</li>
   *   <li>EARLY PERMIAN</li>
   *   <li>EARLY TRIASSIC</li>
   *   <li>EOCENE</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to the Sodir property <em>wlbAgeAtTd</em>.
   *
   * @return  Age of this wellbore. Null if N/A or unknown.
   */
  public String getAgeAtTd()
  {
    return ageAtTd_;
  }

  /**
   * Return formation of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Name of the oldest lithostratigraphic unit penetrated by the wellbore.
   * In most wellbores this is formation or group at total depth.
   * May differ from formation or group at TD for example in wellbores
   * drilled with high deviation or through faults.
   * <p>
   * Examples of legal values:
   * <ul>
   *   <li>AMUNDSEN FM</li>
   *   <li>BALDER FM</li>
   *   <li>BASEMENT</li>
   *   <li>BLODØKS FM</li>
   *   <li>BRYNE FM</li>
   *   <li>BURTON FM</li>
   *   <li>COOK FM</li>
   *   <li>DRAKE FM</li>
   *   <li>DRAUPNE FM</li>
   *   <li>EKOFISK FM</li>
   *   <li>DUNLIN GP</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to the Sodir property <em>wlbFormationAtTd</em>.
   *
   * @return  Formation of this wellbore. Null if N/A or unknown.
   */
  public String getFormationAtTd()
  {
    return formationAtTd_;
  }

  /**
   * Return reason for reentry of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Reason for reentry of the wellbore. Only relevant for exploration wellbores.
   * <p>
   * Example of legal values:
   * <ul>
   *   <li>DRILLING</li>
   *   <li>DRILLING/PLUGGING</li>
   *   <li>LOGGING</li>
   *   <li>PLUGGING</li>
   *   <li>TESTING</li>
   *   <li>TESTING/PLUGGING</li>
   * </ul>
   * <p>
   * varchar(40), corresponds to the Sodir property <em>wlbReentryExplorationActivity</em>.
   *
   * @return  Reason for reentry of this wellbore. Null if N/A or unknown.
   */
  public String getReentryExplorationActivity()
  {
    return reentryExplorationActivity_;
  }

  /**
   * Return 1st level of lithostratigraphic unit of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Name of lithostratigraphic unit, 1st level, where hydrocarbons were
   * encountered.
   * <p>
   * Examples of legal values:
   * <ul>
   *   <li>BASEMENT</li>
   *   <li>COOK FM</li>
   *   <li>EKOFISK FM</li>
   *   <li>HEIMDAL FM</li>
   *   <li>SANDNES FM</li>
   *   <li>SOGNEFJORD FM</li>
   *   <li>TARBERT FM</li>
   *   <li>BRENT GP</li>
   * </ul>
   * <p>
   * varchar(20), corresponds to the Sodir property <em>wlbFormationWithHc1</em>.
   *
   * @return  Formation with hydrocarbons of this wellbore.
   *          Null if N/A or unknown.
   */
  public String getFormationWithHc1()
  {
    return formationWithHc1_;
  }

  /**
   * Return age of lithostratigraphic unit of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Age of lithostratigraphic unit, 1st level, where hydrocarbons were
   * encountered.
   * <p>
   * Examples of legal values:
   * <ul>
   *   <li>CRETACEOUS</li>
   *   <li>EARLY CRETACEOUS</li>
   *   <li>LATE JURASSIC</li>
   *   <li>EOCENE</li>
   * </ul>
   * <p>
   * varchar(20), corresponds to the Sodir property <em>wlbAgeWithHc1</em>.
   *
   * @return  Age of lithostratigraphic unit of this wellbore.
   *          Null if N/A or unknown.
   */
  public String getAgeWithHc1()
  {
    return ageWithHc1_;
  }

  /**
   * Return 2nd level of lithostratigraphic unit of this wellbore.
   * <p>
   * Name of lithostratigraphic unit, 2nd level, where hydrocarbons were encountered.
   * <p>
   * Examples of legal values:
   * <ul>
   *   <li>BASEMENT</li>
   *   <li>COOK FM</li>
   *   <li>EKOFISK FM</li>
   *   <li>HEIMDAL FM</li>
   *   <li>SANDNES FM</li>
   *   <li>SOGNEFJORD FM</li>
   *   <li>TARBERT FM</li>
   *   <li>BRENT GP</li>
   * </ul>
   * <p>
   * varchar(20), corresponds to the Sodir property <em>wlbFormationWithHc2</em>.
   *
   * @return  Formation with hydrocarbons of this wellbore.
   *          Null if N/A or unknown.
   */
  public String getFormationWithHc2()
  {
    return formationWithHc2_;
  }

  /**
   * Return age of lithostratigraphic unit of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Age of lithostratigraphic unit, 2nd level, where hydrocarbons were
   * encountered.
   * <p>
   * Examples of legal values:
   * <ul>
   *   <li>CRETACEOUS</li>
   *   <li>EARLY CRETACEOUS</li>
   *   <li>LATE JURASSIC</li>
   *   <li>EOCENE</li>
   * </ul>
   * <p>
   * varchar(20), corresponds to the Sodir property <em>wlbAgeWithHc2</em>.
   *
   * @return  Age of lithostratigraphic unit of this wellbore.
   *          Null if N/A or unknown.
   */
  public String getAgeWithHc2()
  {
    return ageWithHc2_;
  }

  /**
   * Return 3rd level of lithostratigraphic unit of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Name of lithostratigraphic unit, 3rd level, where hydrocarbons were
   * encountered.
   * <p>
   * Examples of legal values:
   * <ul>
   *   <li>BASEMENT</li>
   *   <li>COOK FM</li>
   *   <li>EKOFISK FM</li>
   *   <li>HEIMDAL FM</li>
   *   <li>SANDNES FM</li>
   *   <li>SOGNEFJORD FM</li>
   *   <li>TARBERT FM</li>
   *   <li>BRENT GP</li>
   * </ul>
   * <p>
   * varchar(20), corresponds to the Sodir property <em>wlbFormationWithHc3</em>.
   *
   * @return  Formation with hydrocarbons of this wellbore.
   *          Null if N/A or unknown.
   */
  public String getFormationWithHc3()
  {
    return formationWithHc3_;
  }

  /**
   * Return age of lithostratigraphic unit of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Age of lithostratigraphic unit, 3rd level, where hydrocarbons were
   * encountered.
   * <p>
   * Examples of legal values:
   * <ul>
   *   <li>CRETACEOUS</li>
   *   <li>EARLY CRETACEOUS</li>
   *   <li>LATE JURASSIC</li>
   *   <li>EOCENE</li>
   * </ul>
   * <p>
   * varchar(20), corresponds to the Sodir property <em>wlbAgeWithHc3</em>.
   *
   * @return  Age of lithostratigraphic unit of this wellbore.
   *          Null if N/A or unknown.
   */
  public String getAgeWithHc3()
  {
    return ageWithHc3_;
  }

  /**
   * Return drilling days of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * Number of days from wellbore entry to wellbore completion.
   * <p>
   * int, corresponds to the Sodir property <em>wlbDrillingDays</em>.
   *
   * @return  Drilling days of this wellbore.
   */
  public int getDrillingDays()
  {
    return drillingDays_;
  }

  /**
   * Check if this wellbore has been re-entered.
   * <p>
   * <b>Sodir description:</b><br>
   * Status whether the wellbore has been re-entered. When counting number of
   * wellbores on the Norwegian continental shelf, only those not reentered
   * are counted.
   * <p>
   * varchar(3), corresponds to the Sodir property <em>wlbReentry</em>.
   *
   * @return  True if this wellbore has been re-entered, false otherwise.
   *          Null if N/A or unknown.
   */
  public Boolean isReentry()
  {
    return isReentry_;
  }

  /**
   * Return URL to the press release of this wellbore.
   * <p>
   * <b>Sodir description:</b><br>
   * URL to Sodir press release for the wellbore. Only relevant for exploration
   * wells drilled after 1.1.1999.
   * <p>
   * varchar(200), corresponds to the Sodir property <em>wlbPressReleaseUrl</em>.
   *
   * @return  URL of the press release of this wellbore. Null if N/A or unknown.
   */
  public String getPressReleaseUrl()
  {
    return pressReleaseUrl_;
  }
}
