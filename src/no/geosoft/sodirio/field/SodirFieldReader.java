package no.geosoft.sodirio.field;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import no.geosoft.sodirio.SodirReader;

/**
 * Sodir field reader.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirFieldReader extends SodirReader<SodirField>
{
  /**
   * The moveable facility properties and their order is as follows:
   *
   *   fldName
   *   cmpLongName
   *   fldCurrentActivitySatus
   *   wlbName
   *   wlbCompletionDate
   *   fldMainArea
   *   fldOwnerKind
   *   fldOwnerName
   *   fldMainSupplyBase
   *   fldHcType
   *   fldNpdidOwner
   *   fldNpdidField
   *   wlbNpdidWellbore
   *   cmpNpdidCompany
   *   fldFactPageUrl
   *   fldFactMapUrl
   *   fldDateUpdated
   *   fldDateUpdatedMax
   *   DatesyncNPD
   */
  private static final int FIELD_NAME_INDEX = 0;
  private static final int OPERATOR_NAME_INDEX = 1;
  private static final int ACTIVITY_STATUS_INDEX = 2;
  private static final int DISCOVERY_WELLBORE_NAME_INDEX = 3;
  private static final int DISCOVERY_WELLBORE_COMPLETION_DATE_INDEX = 4;
  private static final int MAIN_AREA_INDEX = 5;
  private static final int OWNER_KIND_INDEX = 6;
  private static final int OWNER_NAME_INDEX = 7;
  private static final int MAIN_SUPPLY_BASE_INDEX = 8;
  private static final int HYDROCARBON_TYPE_INDEX = 9;
  private static final int NPDID_OWNER_INDEX = 10;
  private static final int NPDID_INDEX = 11;
  private static final int NPDID_DISCOVERY_WELLBORE_INDEX = 12;
  private static final int NPDID_OPERATOR_INDEX = 13;
  private static final int FACT_PAGE_URL_INDEX = 14;
  private static final int FACT_MAP_URL_INDEX = 15;
  private static final int DATE_MAIN_LEVEL_UPDATED_INDEX = 16;
  private static final int DATE_ALL_UPDATED_INDEX = 17;
  private static final int DATE_SYNCED_INDEX = 18;

  /**
   * Create a reader for NPD pipelines.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public SodirFieldReader(String url)
  {
    super(url);
  }

  /**
   * Read all Sodir fields.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   SodirFieldReader reader = new SodirFieldReader(url);
   *   List&lt;SodirField&gt; fields = reader.read();
   * </pre>
   *
   * @return  All Sodir fields. Never null.
   * @throws IOException  If the read operation fail for some reason.
   */
  public static List<SodirField> readAll()
    throws IOException
  {
    SodirFieldReader reader = new SodirFieldReader(FIELD_URL);
    return reader.read();
  }

  /** {@inheritDoc} */
  @Override
  protected SodirField newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 19)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String npdId = tokens[NPDID_INDEX];
    String fieldName = tokens[FIELD_NAME_INDEX];
    String operatorName = tokens[OPERATOR_NAME_INDEX];
    String activityStatus = tokens[ACTIVITY_STATUS_INDEX];
    String discoveryWellboreName = tokens[DISCOVERY_WELLBORE_NAME_INDEX];
    Date discoveryWellboreCompletionDate = parseDate(tokens[DISCOVERY_WELLBORE_COMPLETION_DATE_INDEX]);
    String mainArea = tokens[MAIN_AREA_INDEX];
    String ownerKind = tokens[OWNER_KIND_INDEX];
    String ownerName = tokens[OWNER_NAME_INDEX];
    String mainSupplyBase = tokens[MAIN_SUPPLY_BASE_INDEX];
    String hydrocarbonType = tokens[HYDROCARBON_TYPE_INDEX];
    String npdidOwner = tokens[NPDID_OWNER_INDEX];
    String npdidDiscoveryWellbore = tokens[NPDID_DISCOVERY_WELLBORE_INDEX];
    String npdidOperator = tokens[NPDID_OPERATOR_INDEX];
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[FACT_MAP_URL_INDEX];
    Date mainLevelUpdatedDate = parseDate(tokens[DATE_MAIN_LEVEL_UPDATED_INDEX]);
    Date lastChangedDate = parseDate(tokens[DATE_ALL_UPDATED_INDEX]);
    Date syncDate = parseDate(tokens[DATE_SYNCED_INDEX]);

    return new SodirField(npdId,
                          fieldName,
                          operatorName,
                          activityStatus,
                          discoveryWellboreName,
                          discoveryWellboreCompletionDate,
                          mainArea,
                          ownerKind,
                          ownerName,
                          mainSupplyBase,
                          hydrocarbonType,
                          npdidOwner,
                          npdidDiscoveryWellbore,
                          npdidOperator,
                          factPageUrl,
                          factMapUrl,
                          mainLevelUpdatedDate,
                          lastChangedDate,
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
      List<SodirField> fields = SodirFieldReader.readAll();
      for (SodirField field : fields)
        System.out.println(field);
    }
    catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
