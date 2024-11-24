package no.geosoft.sodirio.survey;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import no.geosoft.sodirio.SodirReader;

/**
 * Sodir survey reader.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirSurveyReader extends SodirReader<SodirSurvey>
{
  /**
   * The survey properties and their order is as follows:
   *
   *   seaName
   *   seaPlanFromDate
   *   seaNpdidSurvey
   *   seaStatus
   *   seaGeographicalArea
   *   seaMidPoint
   *   seaCategory
   *   seaSurveyTypeMain
   *   seaSurveyTypePart
   *   seaCompanyReported
   *   seaVesselAll
   *   seaPlanToDate
   *   seaDateStarting
   *   seaDateFinalized
   *   seaCdpTotalKm
   *   seaBoatTotalKm
   *   sea3DKm2
   *   seaSurveyAcquired
   *   seaMarketAvailable
   *   seaSampling
   *   seaShallowDrilling
   *   seaGeotechnical
   *   seaLawCategory
   *   seaFactPageUrl
   *   seaFactMapHtml5Url
   *   datesyncNPD
   */
  private static final int NAME_INDEX = 0;
  private static final int PLANNED_START_DATE_INDEX = 1;
  private static final int ID_INDEX = 2;
  private static final int STATUS_INDEX = 3;
  private static final int AREA_INDEX = 4;
  private static final int MIDPOINT_INDEX = 5;
  private static final int CATEGORY_INDEX = 6;
  private static final int MAIN_TYPE_INDEX = 7;
  private static final int SUB_TYPE_INDEX = 8;
  private static final int COMPANY_INDEX = 9;
  private static final int VESSEL_INDEX = 10;
  private static final int PLANNED_COMPLETE_DATE_INDEX = 11;
  private static final int START_DATE_INDEX = 12;
  private static final int COMPLETE_DATE_INDEX = 13;
  private static final int PLANNED_TOTAL_LENGTH_CDP_INDEX = 14;
  private static final int PLANNED_TOTAL_LENGTH_BOAT_INDEX = 15;
  private static final int NET_AREA_PLANNED_INDEX = 16;
  private static final int NET_AREA_ACTUAL_INDEX = 17;
  private static final int IS_AVAILABLE_INDEX = 18;
  private static final int IS_SAMPLING_DONE_INDEX = 19;
  private static final int IS_SHALLOW_DRILLING_DONE_INDEX = 20;
  private static final int IS_GEOTECHNICAL_MEASUREMENT_DONE_INDEX = 21;
  private static final int LEGAL_BASIS_INDEX = 22;
  private static final int FACT_PAGE_URL_INDEX = 23;
  private static final int FACT_MAP_URL_INDEX = 24;
  private static final int DATE_SYNCED_INDEX = 25;

  /**
   * Create a reader for Sodir surveys.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public SodirSurveyReader(String url)
  {
    super(url);
  }

  /**
   * Read all Sodir surveys.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   SodirSurveyReader reader = new SodirSurveyReader(url);
   *   List&lt;SodirSurvey&gt; surveys = reader.read();
   * </pre>
   *
   * @return  All Sodir surveys. Never null.
   * @throws IOException  If the read operation fail for some reason.
   */
  public static List<SodirSurvey> readAll()
    throws IOException
  {
    SodirSurveyReader reader = new SodirSurveyReader(SURVEY_URL);
    return reader.read();
  }

  /** {@inheritDoc} */
  @Override
  protected SodirSurvey newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 26)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String name = tokens[NAME_INDEX];
    Date plannedStartDate = parseDate(tokens[PLANNED_START_DATE_INDEX]);
    String id = tokens[ID_INDEX];
    String status = tokens[STATUS_INDEX];
    String area = tokens[AREA_INDEX];
    String midPoint = tokens[MIDPOINT_INDEX];
    String category = tokens[CATEGORY_INDEX];
    String mainType = tokens[MAIN_TYPE_INDEX];
    String subType = tokens[SUB_TYPE_INDEX];
    String company = tokens[COMPANY_INDEX];
    String vessel = tokens[VESSEL_INDEX];
    Date plannedCompleteDate = parseDate(tokens[PLANNED_COMPLETE_DATE_INDEX]);
    Date startDate = parseDate(tokens[START_DATE_INDEX]);
    Date completeDate = parseDate(tokens[COMPLETE_DATE_INDEX]);
    Double plannedTotalLengthCdp = parseDouble(tokens[PLANNED_TOTAL_LENGTH_CDP_INDEX]);
    Double plannedTotalLengthBoat = parseDouble(tokens[PLANNED_TOTAL_LENGTH_BOAT_INDEX]);
    Double netAreaPlanned = parseDouble(tokens[NET_AREA_PLANNED_INDEX]);
    Double netAreaActual = parseDouble(tokens[NET_AREA_ACTUAL_INDEX]);
    Boolean isAvailable = parseBoolean(tokens[IS_AVAILABLE_INDEX]);
    Boolean isSamplingDone = parseBoolean(tokens[IS_SAMPLING_DONE_INDEX]);
    Boolean isShallowDrillingDone = parseBoolean(tokens[IS_SHALLOW_DRILLING_DONE_INDEX]);
    Boolean isGeotechnicalMeasurementDone = parseBoolean(tokens[IS_GEOTECHNICAL_MEASUREMENT_DONE_INDEX]);
    String legalBasis = tokens[LEGAL_BASIS_INDEX];
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[FACT_MAP_URL_INDEX];
    Date syncDate = parseDate(tokens[DATE_SYNCED_INDEX]);

    return new SodirSurvey(id,
                           name,
                           status,
                           area,
                           midPoint,
                           category,
                           mainType,
                           subType,
                           company,
                           vessel,
                           plannedStartDate,
                           plannedCompleteDate,
                           startDate,
                           completeDate,
                           plannedTotalLengthCdp,
                           plannedTotalLengthBoat,
                           netAreaPlanned,
                           netAreaActual,
                           isAvailable,
                           isSamplingDone,
                           isShallowDrillingDone,
                           isGeotechnicalMeasurementDone,
                           legalBasis,
                           factPageUrl,
                           factMapUrl,
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
      List<SodirSurvey> surveys = SodirSurveyReader.readAll();
      for (SodirSurvey survey : surveys)
        System.out.println(survey);
    }
    catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
