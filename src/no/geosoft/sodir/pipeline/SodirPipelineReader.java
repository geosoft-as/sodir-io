package no.geosoft.sodir.pipeline;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import no.geosoft.sodir.SodirReader;

/**
 * Reader for Sodir pipelines.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirPipelineReader extends SodirReader<SodirPipeline>
{
  /**
   * The pipeline properties and their order is as follows:
   *
   *   pplName
   *   pplMapLabel
   *   fclNameFrom
   *   fclNameTo
   *   pplBelongsToName
   *   cmpLongName
   *   pplCurrentPhase
   *   pplCurrentPhaseFromDate
   *   pplMedium
   *   pplMainGroupingName
   *   pplDimension
   *   pplWaterDepth
   *   cmpNpdidCompany
   *   fclNpdidFacilityFrom
   *   fclNpdidFacilityTo
   *   pplFactPageUrl
   *   pplFactMapUrl
   *   pplNpdidPipeline
   *   pplDateUpdated
   *   DatesyncNPD
   */
  private static final int NAME_INDEX = 0;
  private static final int MAP_LABEL_INDEX = 1;
  private static final int FROM_FACILITY_INDEX = 2;
  private static final int TO_FACILITY_INDEX = 3;
  private static final int BELONGS_TO_INDEX = 4;
  private static final int OPERATOR_INDEX = 5;
  private static final int CURRENT_PHASE_INDEX = 6;
  private static final int CURRENT_PHASE_FROM_DATE_INDEX = 7;
  private static final int MEDIUM_INDEX = 8;
  private static final int MAIN_GROUPING_INDEX = 9;
  private static final int DIMENSION_INDEX = 10;
  private static final int WATER_DEPTH_INDEX = 11;
  private static final int NPDID_OPERATOR_INDEX = 12;
  private static final int NPDID_FROM_FACILITY_INDEX = 13;
  private static final int NPDID_TO_FACILITY_INDEX = 14;
  private static final int FACT_PAGE_URL_INDEX = 15;
  private static final int FACT_MAP_URL_INDEX = 16;
  private static final int NPDID_INDEX = 17;
  private static final int LAST_CHANGED_DATE_INDEX = 18;
  private static final int SYNCED_DATE_INDEX = 19;

  /**
   * Create a reader for Sodir pipelines.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public SodirPipelineReader(String url)
  {
    super(url);
  }

  /**
   * Read all Sodir pipelines.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   SodirPipelineReader reader = new SodirPipelineReader(url);
   *   List&lt;SodirPipeline&gt; pipelines = reader.read();
   * </pre>
   *
   * @return  All Sodir pipelines. Never null.
   * @throws IOException  If the read operation fail for some reason.
   */
  public static List<SodirPipeline> readAll()
    throws IOException
  {
    SodirPipelineReader reader = new SodirPipelineReader(PIPELINE_URL);
    return reader.read();
  }

  /** {@inheritDoc} */
  @Override
  protected SodirPipeline newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokesn cannot be null";

    if (tokens.length != 20)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    String name = tokens[NAME_INDEX];
    String mapLabel = tokens[MAP_LABEL_INDEX];
    String fromFacility = tokens[FROM_FACILITY_INDEX];
    String toFacility = tokens[TO_FACILITY_INDEX];
    String belongsTo = tokens[BELONGS_TO_INDEX];
    String operator = tokens[OPERATOR_INDEX];
    String currentPhase = tokens[CURRENT_PHASE_INDEX];
    Date currentPhaseFromDate = parseDate(tokens[CURRENT_PHASE_FROM_DATE_INDEX]);
    String medium = tokens[MEDIUM_INDEX];
    String mainGrouping = tokens[MAIN_GROUPING_INDEX];
    Double dimension = parseDouble(tokens[DIMENSION_INDEX]);
    Double waterDepth = parseDouble(tokens[WATER_DEPTH_INDEX]);
    String npdidOperator  = tokens[NPDID_OPERATOR_INDEX];
    String npdidFromFacility = tokens[NPDID_FROM_FACILITY_INDEX];
    String npdidToFacility = tokens[NPDID_TO_FACILITY_INDEX];
    String factPageUrl = tokens[FACT_PAGE_URL_INDEX];
    String factMapUrl = tokens[FACT_MAP_URL_INDEX];
    String npdId = tokens[NPDID_INDEX];
    Date lastChangedDate = parseDate(tokens[LAST_CHANGED_DATE_INDEX]);
    Date syncDate = parseDate(tokens[SYNCED_DATE_INDEX]);

    return new SodirPipeline(npdId,
                             name,
                             mapLabel,
                             fromFacility,
                             toFacility,
                             belongsTo,
                             operator,
                             currentPhase,
                             currentPhaseFromDate,
                             medium,
                             mainGrouping,
                             dimension,
                             waterDepth,
                             npdidOperator,
                             npdidFromFacility,
                             npdidToFacility,
                             factPageUrl,
                             factMapUrl,
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
      List<SodirPipeline> pipelines = SodirPipelineReader.readAll();
      for (SodirPipeline pipeline : pipelines)
        System.out.println(pipeline);
    }
    catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
