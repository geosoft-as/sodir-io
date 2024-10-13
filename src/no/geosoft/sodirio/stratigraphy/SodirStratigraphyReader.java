package no.geosoft.sodirio.stratigraphy;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import no.geosoft.sodirio.SodirReader;

/**
 * Reader for Sodir stratigraphy.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public class SodirStratigraphyReader extends SodirReader<SodirStratigraphy>
{
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
   * Create a reader for Sodir stratigraphy.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public SodirStratigraphyReader(String url)
  {
    super(url);
  }

  /**
   * Read all Sodir stratigraphies.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   SodirStratigraphyReader reader = new SodirStratigraphyReader(url);
   *   List&lt;SodirStratigraphy&gt; stratigraphies = reader.read();
   * </pre>
   *
   * @return  All Sodir stratigraphies. Never null.
   * @throws IOException  If the read operation fail for some reason.
   */
  public static List<SodirStratigraphy> readAll()
      throws IOException
  {
    SodirStratigraphyReader reader = new SodirStratigraphyReader(STRATIGRAPHY_URL);
    return reader.read();
  }

  /** {@inheritDoc} */
  @Override
  protected SodirStratigraphy newInstance(String[] tokens)
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

    SodirStratigraphy entry = new SodirStratigraphy(wellName,
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
