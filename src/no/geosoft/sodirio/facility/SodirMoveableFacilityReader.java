package no.geosoft.sodirio.facility;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import no.geosoft.sodirio.SodirReader;

/**
 * Reader for Sodir moveable facilities.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirMoveableFacilityReader extends SodirReader<SodirMoveableFacility>
{
  /**
   * The moveable facility properties and their order is as follows:
   *
   *   fclName
   *   fclCurrentRespCompanyName
   *   fclKind
   *   fclFunctions
   *   fclStatus
   *   fclNationName
   *   fclFactPageUrl
   *   fclNpdidFacility
   *   fclNpdidCurrentRespCompany
   *   fclDateUpdated
   *   datesyncNPD
   */
  private static final int NAME_INDEX = 0;
  private static final int RESPONSIBLE_COMPANY_NAME_INDEX = 1;
  private static final int KIND_INDEX = 2;
  private static final int FUNCTIONS_INDEX = 3;
  private static final int AOC_STATUS_INDEX = 4;
  private static final int NATION_INDEX = 5;
  private static final int FACT_PAGE_URL_INDEX = 6;
  private static final int ID_INDEX = 7;
  private static final int RESPONSIBLE_COMPANY_ID_INDEX = 8;
  private static final int DATE_UPDATED_INDEX = 9;
  private static final int SYNC_DATE_INDEX = 10;

  /**
   * Create a reader for Sodir moveable facilities.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public SodirMoveableFacilityReader(String url)
  {
    super(url);
  }

  /**
   * Read all Sodir moveable facilities.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   SodirMoveableFacilityReader reader = new SodirMoveableFacilityReader(url);
   *   List&lt;SodirMoveableFacility&gt; moveableFacilities = reader.read();
   * </pre>
   *
   * @return  All Sodir moveable facilities. Never null.
   * @throws IOException  If the read operation fail for some reason.
   */
  public static List<SodirMoveableFacility> readAll()
    throws IOException
  {
    SodirMoveableFacilityReader reader = new SodirMoveableFacilityReader(MOVEABLE_FACILITY_URL);
    return reader.read();
  }

  /** {@inheritDoc} */
  @Override
  protected SodirMoveableFacility newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 11)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String id = tokens[ID_INDEX];
    String name = tokens[NAME_INDEX];
    String responsibleCompanyName = tokens[RESPONSIBLE_COMPANY_NAME_INDEX];
    String responsibleCompanyId = tokens[RESPONSIBLE_COMPANY_ID_INDEX];
    String kind = tokens[KIND_INDEX];
    String functions = tokens[FUNCTIONS_INDEX];
    String aocStatus = tokens[AOC_STATUS_INDEX];
    String nation = tokens[NATION_INDEX];
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    Date lastChangedDate = parseDate(tokens[DATE_UPDATED_INDEX]);
    Date syncDate = parseDate(tokens[SYNC_DATE_INDEX]);

    return new SodirMoveableFacility(id,
                                     name,
                                     responsibleCompanyName,
                                     kind,
                                     functions,
                                     aocStatus,
                                     nation,
                                     factPageUrl,
                                     responsibleCompanyId,
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
      List<SodirMoveableFacility> facilities = SodirMoveableFacilityReader.readAll();
      for (SodirMoveableFacility facility : facilities)
        System.out.println(facility);
    }
    catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
