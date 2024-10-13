package no.geosoft.sodirio.field;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import no.geosoft.sodirio.SodirReader;

/**
 * Production reader.
 * <p>
 * This class is thread-safe.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class ProductionReader extends SodirReader<Production.Entry>
{
  /**
   * The production properties and their order is as follows:
   *
   *   prfInformationCarrier
   *   prfYear
   *   prfMonth
   *   prfPrdOilNetMillSm3
   *   prfPrdGasNetBillSm3
   *   prfPrdNGLNetMillSm3
   *   prfPrdCondensateNetMillSm3
   *   prfPrdOeNetMillSm3
   *   prfPrdProducedWaterInFieldMillSm3
   *   prfNpdidInformationCarrier
   */
  private static final int FIELD_NAME_INDEX = 0;
  private static final int YEAR_INDEX = 1;
  private static final int MONTH_INDEX = 2;
  private static final int OIL_INDEX = 3;
  private static final int GAS_INDEX = 4;
  private static final int NGL_INDEX = 5;
  private static final int CONDENSATE_INDEX = 6;
  private static final int OIL_EQUIVALENTS_INDEX = 7;
  private static final int WATER_INDEX = 8;
  private static final int NPDID_INDEX = 9;

  /**
   * Create a reader for Sodir field production.
   *
   * @param url  Location of file to read. Non-null.
   * @throws IllegalArgumentException  If url is null.
   */
  public ProductionReader(String url)
  {
    super(url);
  }

  /** {@inheritDoc} */
  @Override
  protected Production.Entry newInstance(String[] tokens)
    throws ParseException
  {
    assert tokens != null : "tokens cannot be null";

    if (tokens.length != 10)
      throw new ParseException("Invalid number of tokens: " + tokens.length, 0);

    int year = parseInt(tokens[YEAR_INDEX]);
    int month = parseInt(tokens[MONTH_INDEX]);

    double oil = parseDouble(tokens[OIL_INDEX]);
    double gas = parseDouble(tokens[GAS_INDEX]);
    double ngl = parseDouble(tokens[NGL_INDEX]);
    double condensate = parseDouble(tokens[CONDENSATE_INDEX]);
    double oilEquivalents = parseDouble(tokens[OIL_EQUIVALENTS_INDEX]);
    double water = parseDouble(tokens[WATER_INDEX]);
    String npdidField = tokens[NPDID_INDEX];

    return new Production.Entry(year,
                                month,
                                oil,
                                gas,
                                ngl,
                                condensate,
                                oilEquivalents,
                                water,
                                npdidField);
  }

  /**
   * Read production entries for the specified field and populate
   * its <em>production</em> member.
   *
   * @param field  Field to read production of. Non-null.
   * @throws IllegalArgumentException  If field is null.
   * @throws IOException               If the read operation fails for some reason.
   */
  public void read(SodirField field)
    throws IOException
  {
    if (field == null)
      throw new IllegalArgumentException("field cannot be null");

    String npdidField = field.getNpdId();

    // Read all production entries
    List<Production.Entry> allProductionEntries = read();

    //
    // Find those from the requested field
    //
    List<Production.Entry> fieldProductionEntries = new ArrayList<>();
    for (Production.Entry productionEntry : allProductionEntries) {
      if (npdidField.equals(productionEntry.getNpdidField()))
        fieldProductionEntries.add(productionEntry);
    }

    field.setProduction(new Production(fieldProductionEntries));
  }

  /**
   * Read all production entries for the specified field and populate
   * its <em>production</em> member.
   * <p>
   * This is a convenient alternative to the more flexible and generic
   * approach where the URL location of the data is provided by the client:
   * <pre>
   *   ProductionReader reader = new ProductionReader(url);
   *   reader.read(field);
   * </pre>
   *
   * @param field  Field to read production of. Non-null.
   * @throws IllegalArgumentException  If field is null.
   * @throws IOException               If the read operation fails for some reason.
   */
  public static void readAll(SodirField field)
    throws IOException
  {
    ProductionReader reader = new ProductionReader(PRODUCTION_URL);
    reader.read(field);
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
      for (SodirField field : fields) {

        System.out.println(field);

        ProductionReader.readAll(field);
        System.out.println(field.getProduction());
      }
    }
    catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
