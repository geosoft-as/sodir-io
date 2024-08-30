package no.geosoft.sodir.company;

import java.util.Date;

import no.geosoft.sodir.SodirObject;

/**
 * A company as modeled by the Sodir.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirCompany extends SodirObject
{
  /** Norwegian organization number. Null if N/A. */
  private final String organizationNumber_;

  /** Company short name. Null if N/A. */
  private final String shortName_;

  /** Nation ISO code. Null if unknown. */
  private final String nationCode_;

  /** Survey prefix. Null if N/A or unknown. */
  private final String surveyPrefix_;

  /** Flag indicating if company is an operator. */
  private final boolean isCurrentLicenseOperator_;

  /** Flag indicating if company was an operator. */
  private final boolean isFormerLicenseOperator_;

  /** Flag indicating if company is a licensee. */
  private final boolean isCurrentLicenseLicensee_;

  /** Flag indicating if company was a licensee. */
  private final boolean isFormerLicenseLicensee_;

  /**
   * Create an Sodir company instance.
   *
   * @param npdId                     Sodir ID. Non-null.
   * @param name                      Company name. Non-null.
   * @param organizationNumber        Organization number. Null if N/A.
   * @param shortName                 Company short name. Null if N/A.
   * @param nationCode                Nation code. Null if not known.
   * @param surveyPrefix              Survey prefix. Null if N/A.
   * @param isCurrentLicenseOperator  Is company currently license operator?
   * @param isFormerLicenseOperator   Is company former license operator?
   * @param isCurrentLicenseLicensee  Is company currently license licensee?
   * @param isFormerLicenseLicensee   Is company former license licensee?
   */
  SodirCompany(String npdId,
               String name,
               String organizationNumber,
               String shortName,
               String nationCode,
               String surveyPrefix,
               boolean isCurrentLicenseOperator,
               boolean isFormerLicenseOperator,
               boolean isCurrentLicenseLicensee,
               boolean isFormerLicenseLicensee,
               Date syncDate)
  {
    super("company", npdId, name, null, null, null, syncDate);

    organizationNumber_ = organizationNumber;
    shortName_ = shortName;
    nationCode_ = nationCode;
    surveyPrefix_ = surveyPrefix;
    isCurrentLicenseOperator_ = isCurrentLicenseOperator;
    isFormerLicenseOperator_ = isFormerLicenseOperator;
    isCurrentLicenseLicensee_ = isCurrentLicenseLicensee;
    isFormerLicenseLicensee_ = isFormerLicenseLicensee;
  }

  /**
   * Return the official Norwegian organization number of this company.
   * <p>
   * <b>Sodir description:</b><br>
   * Official Norwegian organisation number.
   * <p>
   * varchar(100), corresponds to Sodir property <em>cmpOrgNumberBrReg</em>.
   *
   * @return Official Norwegian organization number. Null if N/A.
   */
  public String getOrganizationNumber()
  {
    return organizationNumber_;
  }

  /**
   * Return short name of this company.
   * <p>
   * <b>Sodir description:</b><br>
   * Missing.
   * <p>
   * Corresponds to Sodir property <em>cmpShortName</em>.
   *
   * @return  Company short name. Null if N/A.
   */
  public String getShortName()
  {
    return shortName_;
  }

  /**
   * Return two letter nation code (ISO) of this company.
   * registered in.
   * <p>
   * <b>Sodir description:</b><br>
   * 2 letter nation code (ISO) for the nation the company is registered in.
   * <p>
   * varchar(2), corresponds to Sodir property <em>cmpNationCode</em>.
   *
   * @return  Nation code of this company. Null if N/A or unknown.
   */
  public String getNationCode()
  {
    return nationCode_;
  }

  /**
   * Return prefix for survey names of this company.
   * <p>
   * <b>Sodir description:</b><br>
   * Prefix for survey names. In "ST14001" is ST prefx for Statoil.
   * <p>
   * varchar(4), corresponds to Sodir property <em>cmpSurveyPrefix</em>.
   *
   * @return  Prefix for survey names. Null if N/A.
   */
  public String getSurveyPrefix()
  {
    return surveyPrefix_;
  }

  /**
   * Check if this company is currently a license operator.
   * <p>
   * <b>Sodir description:</b><br>
   * Indicator which tells if the company is currently a production licence
   * operator.
   * <p>
   * varchar(1), corresponds to Sodir property <em>cmpLicenceOperCurrent</em>.
   *
   * @return True if the company is currently a license operator, false otherwise.
   */
  public boolean isCurrentLicenseOperator()
  {
    return isCurrentLicenseOperator_;
  }

  /**
   * Check if this company was a former license operator.
   * <p>
   * <b>Sodir description:</b><br>
   * Indicator which tells if the company has previously been a
   * production license operator.
   * <p>
   * varchar(1), corresponds to Sodir property <em>cmpLicenceOperFormer</em>.
   *
   * @return True if the company was a former license operator, false otherwise.
   */
  public boolean isFormerLicenseOperator()
  {
    return isFormerLicenseOperator_;
  }

  /**
   * Check if this company is currently a licensee.
   * <p>
   * <b>Sodir description:</b><br>
   * Indicator which tells if the company is currently a licensee in
   * production licenses.
   * <p>
   * varchar(1), corresponds to Sodir property <em>cmpLicenceLicenseeCurrent</em>.
   *
   * @return True if this company is currently a licensee, false otherwise.
   */
  public boolean isCurrentLicenseLicensee()
  {
    return isCurrentLicenseLicensee_;
  }

  /**
   * Check if this company was a former licensee.
   * <p>
   * <b>Sodir description:</b><br>
   * Indicator which tells if the company has been a licensee in production
   * licenses.
   * <p>
   * varchar(1), corresponds to Sodir property <em>cmpLicenceLicenseeFormer</em>.
   *
   * @return True if this company was a former licensee, false otherwise.
   */
  public boolean isFormerLicenseLicensee()
  {
    return isFormerLicenseLicensee_;
  }
}
