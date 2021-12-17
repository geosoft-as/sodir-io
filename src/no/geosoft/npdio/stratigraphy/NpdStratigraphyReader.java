package no.geosoft.npdio.stratigraphy;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import no.geosoft.npdio.NpdReader;

/**
 * Reader for NPD Stratigraphy.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public class NpdStratigraphyReader extends NpdReader<NpdStratigraphy>
{
  /** URL to the NPD file containing the stratigraphy data. */
  private static final String URL = "https://factpages.npd.no/ReportServer_npdpublic?/FactPages/TableView/strat_litho_wellbore&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&rs:Format=CSV&Top100=false&IpAddress=not_used&CultureCode=en";

  /**
   * The stratigraphy properties and their order is as follows:
   *
   *   wlbName
   *   lsuTopDepth
   *   lsuBottomDepth
   *   lsuName
   *   lsuLevel
   *   lsuNpdidLithoStrat
   *   wlbCompletionDate
   *   wlbNPDID_wellbore
   *   lsuWellboreUpdatedDate
   *   datesyncNPD
   */
  private static final int WELLBORE_NAME_INDEX = 0;
  private static final int TOP_DEPTH_INDEX = 1;
  private static final int BOTTOM_DEPTH_INDEX = 2;
  private static final int STRATIGRAPHIC_UNIT_INDEX = 3;
  private static final int LEVEL_INDEX = 4;
  private static final int STRATIGRAPHIC_UNIT_ID_INDEX = 5;
  private static final int WELLBORE_COMPLETION_INDEX = 6;
  private static final int WELLBORE_ID_INDEX = 7;
  private static final int DATE_UPDATED_INDEX = 8;
  private static final int DATE_SYNCED_NPD_INDEX = 9;

  /**
   * Create a reader for NPD stratigraphy.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public NpdStratigraphyReader(String url)
  {
    super(url);
  }

  /**
   * Read all NPD stratigraphy.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   NpdStratigraphyReader reader = new NpdStratigraphyReader(url);
   *   List&lt;NpdStratigraphy&gt; stratigraphies = reader.read();
   * </pre>
   *
   * @return  All NPD stratigraphy. Never null.
   * @throws IOException  If the read operation fail for some reason.
   */
  public static List<NpdStratigraphy> readAll()
      throws IOException
    {
      NpdStratigraphyReader reader = new NpdStratigraphyReader(URL);
      return reader.read();
    }

  /** {@inheritDoc} */
  @Override
  protected NpdStratigraphy newInstance(String[] tokens)
      throws ParseException
  {
    String wellName = tokens[WELLBORE_NAME_INDEX];
    int topDepth = parseInt(tokens[TOP_DEPTH_INDEX]);
    int botDepth = parseInt(tokens[BOTTOM_DEPTH_INDEX]);
    String stratigraphicUnit = tokens[STRATIGRAPHIC_UNIT_INDEX];
    String level = tokens[LEVEL_INDEX];
    int stratigraphicUnitId = parseInt(tokens[STRATIGRAPHIC_UNIT_ID_INDEX]);
    Date wellboreCompletion = parseDate(tokens[WELLBORE_COMPLETION_INDEX]);
    int wellboreId = parseInt(tokens[WELLBORE_ID_INDEX]);
    Date lastUpdated = parseDate(tokens[DATE_UPDATED_INDEX]);
    Date lastSynced = parseDate(tokens[DATE_SYNCED_NPD_INDEX]);

    NpdStratigraphy entry = new NpdStratigraphy(wellName,
                                                    topDepth,
                                                    botDepth,
                                                    stratigraphicUnit,
                                                    level,
                                                    stratigraphicUnitId,
                                                    wellboreCompletion,
                                                    wellboreId,
                                                    lastUpdated,
                                                    lastSynced);
    return entry;
  }
}
