package no.geosoft.sodirio.company;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import no.geosoft.sodirio.SodirReader;

/**
 * Sodir company reader.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirCompanyReader extends SodirReader<SodirCompany>
{
  /**
   * The company properties and their order is as follows:
   *
   * cmpLongName,
   * cmpOrgNumberBrReg,
   * cmpShortName,
   * cmpNationCode,
   * cmpSurveyPrefix,
   * cmpNpdidCompany,
   * cmpLicenceOperCurrent,
   * cmpLicenceOperFormer,
   * cmpLicenceLicenseeCurrent,
   * cmpLicenceLicenseeFormer,
   * cmpTufOperCurrent,
   * cmpTufOperFormer,
   * cmpTufPartnerCurrent,
   * cmpTufPartnerFormer,
   * cmpBsnsArrAreaOperCurrent,
   * cmpBsnsArrAreaOperFormer,
   * cmpBsnsArrAreaPartnerCurrent,
   * cmpBsnsArrAreaPartnerFormer,
   * dateSyncNPD
   */
  private static final int NAME_INDEX = 0;
  private static final int ORGANIZATION_NUMBER_INDEX = 1;
  private static final int SHORT_NAME_INDEX = 2;
  private static final int NATION_CODE_INDEX = 3;
  private static final int SURVEY_PREFIX_INDEX = 4;
  private static final int NPDID_INDEX = 5;
  private static final int IS_CURRENT_LICENSE_OPERATOR_INDEX = 6;
  private static final int IS_FORMER_LICENSE_OPERATOR_INDEX = 7;
  private static final int IS_CURRENT_LICENSE_LICENSEE_INDEX = 8;
  private static final int IS_FORMER_LICENSE_LICENSEE_INDEX = 9;
  private static final int IS_CURRENT_TUF_OPERATOR_INDEX = 10;
  private static final int IS_FORMER_TUF_OPERATOR_INDEX = 11;
  private static final int IS_CURRENT_TUF_PARTNER_INDEX = 12;
  private static final int IS_FORMER_TUF_PARTNER_INDEX = 13;
  private static final int IS_CURRENT_BUSINESS_ARRANGEMENT_OPERATOR_INDEX = 14;
  private static final int IS_FORMER_BUSINESS_ARRANGEMENT_OPERATOR_INDEX = 15;
  private static final int IS_CURRENT_BUSINESS_ARRANGEMENT_PARTNER_INDEX = 16;
  private static final int IS_FORMER_BUSINESS_ARRANGEMENT_PARTNER_INDEX = 17;
  private static final int DATE_SYNCED_INDEX = 18;

  /**
   * Create a reader for Sodir companies.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public SodirCompanyReader(String url)
  {
    super(url);
  }

  /**
   * Read all Sodir companies.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   SodirCompanyReader reader = new SodirCompanyReader(url);
   *   List&lt;SodirCompany&gt; companies = reader.read();
   * </pre>
   *
   * @return  All Sodir companies. Never null.
   * @throws IOException  If the read operation fail for some reason.
   */
  public static List<SodirCompany> readAll()
    throws IOException
  {
    SodirCompanyReader reader = new SodirCompanyReader(COMPANY_URL);
    return reader.read();
  }

  /** {@inheritDoc} */
  @Override
  protected SodirCompany newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 19)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String npdId = tokens[NPDID_INDEX];
    String name = tokens[NAME_INDEX];
    String organizationNumber = tokens[ORGANIZATION_NUMBER_INDEX];
    String shortName = tokens[SHORT_NAME_INDEX];
    String nationCode = tokens[NATION_CODE_INDEX];
    String surveyPrefix = tokens[SURVEY_PREFIX_INDEX];
    boolean isCurrentLicenseOperator = parseBoolean(tokens[IS_CURRENT_LICENSE_OPERATOR_INDEX]);
    boolean isFormerLicenseOperator = parseBoolean(tokens[IS_FORMER_LICENSE_OPERATOR_INDEX]);
    boolean isCurrentLicenseLicensee = parseBoolean(tokens[IS_CURRENT_LICENSE_LICENSEE_INDEX]);
    boolean isFormerLicenseLicensee = parseBoolean(tokens[IS_FORMER_LICENSE_LICENSEE_INDEX]);
    boolean isCurrentTufOperator = parseBoolean(tokens[IS_CURRENT_TUF_OPERATOR_INDEX]);
    boolean isFormerTufOperator = parseBoolean(tokens[IS_FORMER_TUF_OPERATOR_INDEX]);
    boolean isCurrentTufPartner = parseBoolean(tokens[IS_CURRENT_TUF_PARTNER_INDEX]);
    boolean isFormerTufPartner = parseBoolean(tokens[IS_FORMER_TUF_PARTNER_INDEX]);
    boolean isCurrentBunsinessArrangementOperator = parseBoolean(tokens[IS_CURRENT_BUSINESS_ARRANGEMENT_OPERATOR_INDEX]);
    boolean isFormerBunsinessArrangementOperator = parseBoolean(tokens[IS_FORMER_BUSINESS_ARRANGEMENT_OPERATOR_INDEX]);
    boolean isCurrentBunsinessArrangementPartner = parseBoolean(tokens[IS_CURRENT_BUSINESS_ARRANGEMENT_PARTNER_INDEX]);
    boolean isFormerBunsinessArrangementPartner = parseBoolean(tokens[IS_FORMER_BUSINESS_ARRANGEMENT_PARTNER_INDEX]);
    Date syncDate = parseDate(tokens[DATE_SYNCED_INDEX]);

    return new SodirCompany(npdId,
                            name,
                            organizationNumber,
                            shortName,
                            nationCode,
                            surveyPrefix,
                            isCurrentLicenseOperator,
                            isFormerLicenseOperator,
                            isCurrentLicenseLicensee,
                            isFormerLicenseLicensee,
                            isCurrentTufOperator,
                            isFormerTufOperator,
                            isCurrentTufPartner,
                            isFormerTufPartner,
                            isCurrentBunsinessArrangementOperator,
                            isFormerBunsinessArrangementOperator,
                            isCurrentBunsinessArrangementPartner,
                            isFormerBunsinessArrangementPartner,
                            syncDate);
  }

  /**
   * Testing this class.
   *
   * @param arguments  Application arguments. Not used.
   */
  public static void main(String[] arguments)
  {
    try {
      List<SodirCompany> companies = SodirCompanyReader.readAll();
      for (SodirCompany company : companies)
        System.out.println(company);
    }
    catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
