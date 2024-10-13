package no.geosoft.sodirio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 * Base class for the Sodir readers.
 *
 * @param <T>  The Sodir instance type to read.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public abstract class SodirReader<T>
{
  /**
   * As per summer 2024 these are the URLs to the Sodir gact pages for each supported data type.
   * Historically, these URLs tends to change, so clients that depends on these strings needs
   * a recompiled version of the Sodir library every time they do. It is therefore adviced to keep
   * this information in the client code instead. All APIs accept the URL as a public input argument.
   */
  public static final String COMPANY_URL              = "https://factpages.sodir.no/public?/Factpages/external/tableview/company&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";
  public static final String DEVELOPMENT_WELLBORE_URL = "https://factpages.sodir.no/public?/Factpages/external/tableview/wellbore_development_all&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";
  public static final String DISCOVERY_URL            = "https://factpages.sodir.no/public?/Factpages/external/tableview/discovery&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";
  public static final String EXPLORATION_WELLBORE_URL = "https://factpages.sodir.no/public?/Factpages/external/tableview/wellbore_exploration_all&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";
  public static final String FIELD_URL                = "https://factpages.sodir.no/public?/Factpages/external/tableview/field&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";
  public static final String FIXED_FACILITY_URL       = "https://factpages.sodir.no/public?/Factpages/external/tableview/facility_fixed&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";
  public static final String LICENSE_URL              = "https://factpages.sodir.no/public?/Factpages/external/tableview/licence&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";
  public static final String MOVEABLE_FACILITY_URL    = "https://factpages.sodir.no/public?/Factpages/external/tableview/facility_moveable&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";
  public static final String OTHER_WELLBORE_URL       = "https://factpages.sodir.no/public?/Factpages/external/tableview/wellbore_other_all&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";
  public static final String PIPELINE_URL             = "https://factpages.sodir.no/public?/Factpages/external/tableview/tuf_pipeline_overview&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";
  public static final String PRODUCTION_URL           = "https://factpages.sodir.no/public?/Factpages/external/tableview/field_production_monthly&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";
  public static final String STRATIGRAPHY_URL         = "https://factpages.sodir.no/public?/Factpages/external/tableview/strat_litho_wellbore&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";
  public static final String SURVEY_URL               = "https://factpages.sodir.no/public?/Factpages/external/tableview/survey&rs:Command=Render&rc:Toolbar=false&rc:Parameters=f&IpAddress=not_used&CultureCode=en&rs:Format=CSV&Top100=false";

  /** Sodir date format description */
  private static final String DATE_FORMAT = "dd.MM.yyyy";

  /** The logger instance */
  private static final Logger logger_ = Logger.getLogger(SodirReader.class.getName());

  /** URL of file to read. Non-null. */
  private final String url_;

  /**
   * Create an Sodir reader instance.
   *
   * @param url  URL of file to read. Non-null.
   */
  protected SodirReader(String url)
  {
    assert url != null : "url cannot be null";
    url_ = url;
  }

  /**
   * Create a new instance from the tokens read from the file.
   *
   * @param tokens  Tokens read from file. Non-null.
   * @return        The requested instance. Never null.
   * @throws ParseException  If the tokens cannot be parsed into a Sodir instance
   *                         of the correct type.
   */
  protected abstract T newInstance(String[] tokens) throws ParseException;

  /**
   * Convert specified text to its corresponding SodirObject class.
   *
   * @param text  Text to convert. May be null.
   * @return      SodirObject class equivalent. Null if not found.
   */
  protected static Class<? extends SodirObject> parseClass(String text)
  {
    if (text == null)
      return null;

    String kind = text.toLowerCase(Locale.US);
    if (kind.startsWith("field"))
     return no.geosoft.sodirio.field.SodirField.class;
    // TODO: More here if needed

    return null;
  }

  /**
   * Convert specified text to a boolean. If text is empty, return false.
   *
   * @param text  Text to convert. May be null.
   * @return      Boolean equivalent. True if text is "Y", "YES" or similar,
   *              false otherwise.
   */
  protected static Boolean parseBoolean(String text)
  {
    if (text == null)
      return null;

    if (text.toLowerCase(Locale.US).startsWith("y"))
      return true;

    if (text.toLowerCase(Locale.US).startsWith("j"))
      return true;

    return false;
  }

  /**
   * Convert specified text to an integer.
   *
   * @param text  Text to convert. May be null.
   * @return      Integer equivalent. Null if text is null or empty.
   * @throws ParseException  If text cannot be converted to int.
   */
  protected static Integer parseInt(String text)
    throws ParseException
  {
    if (text == null || text.isEmpty())
      return null;

    try {
      return Integer.parseInt(text);
    }
    catch (NumberFormatException exception) {
      throw new ParseException("Invalid integer: " + text + "\n" + exception.getMessage(), 0);
    }
  }

  /**
   * Convert specified text to a double.
   *
   * @param text  Text to convert. May be null.
   * @return      Double equivalent. Null if text is null or empty.
   * @throws ParseException  If text cannot be converted to double.
   */
  protected static Double parseDouble(String text)
    throws ParseException
  {
    if (text == null || text.isEmpty())
      return null;

    try {
      return Double.parseDouble(text);
    }
    catch (NumberFormatException exception) {
      throw new ParseException("Invalid double: " + text + "\n" + exception.getMessage(), 0);
    }
  }

  /**
   * Convert specified text to a date.
   *
   * @param text  Text to convert.
   * @return      Date equivalent. null if text is empty.
   * @throws ParseException  If text cannot be converted to a date.
   */
  protected static Date parseDate(String text)
    throws ParseException
  {
    if (text == null || text.isEmpty())
      return null;

    // SimpleDateFromat is not thread-safe so we create it locally
    DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    return dateFormat.parse(text);
  }

  /**
   * Split the specified line on commas. Tokens may optionally be enclosed
   * in quotes if for instance they contain commas themselves.
   *
   * <pre>
   *   This, is,,a,"silly, but",actual,"example"
   * </pre>
   *
   * <p>
   * Splits into:
   *
   * <pre>
   *   "This"
   *   " is"
   *   ""
   *   "a"
   *   "silly, but"
   *   "actual"
   *   "example"
   * </pre>
   *
   * @param line  Line to CSV split. Non-null.
   * @return  Individual tokens. Never null.
   * @throws IllegalArgumentException  If line is null.
   */
  private static String[] csvSplit(String line)
  {
    if (line == null)
      throw new IllegalArgumentException("line cannot be null");

    boolean inQuote = false;

    // Locate the positions of the separating commas
    // include positions just before and after the string too.
    List<Integer> splitPos = new ArrayList<>();
    splitPos.add(-1);
    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      if (c == '\"')  /*"*/
        inQuote = !inQuote;
      else if (c == ',' && !inQuote)
        splitPos.add(i);
    }
    splitPos.add(line.length());

    // Find number of tokens and prepare return structure
    int nTokens = splitPos.size() - 1;
    String[] tokens = new String[nTokens];

    // Split string on split positions
    // Trim and un-quote if necessary
    for (int i = 0; i < splitPos.size() - 1; i++) {
      int from = splitPos.get(i) + 1;
      int to = splitPos.get(i + 1);
      String token = line.substring(from, to).trim();
      if (token.startsWith("\"") && token.endsWith("\""))
        token = token.substring(1, token.length() - 1);

      // Double quote is CSV for a quoute
      token = token.replaceAll("\"\"", "\"");

      tokens[i] = token;
    }

    return tokens;
  }

  /**
   * Read file.
   *
   * @return  The read instances. Never null.
   * @throws IOException  If the read operation fails for some reason.
   */
  public List<T> read()
    throws IOException
  {
    // Prepare return structure
    List<T> instances = new ArrayList<>();

    // Create URL instance from the specified string
    URL url;
    try {
      url = new URL(url_);
    }
    catch (MalformedURLException exception) {
      throw new IOException("Malformed URL: " + url_, exception);
    }

    // Open connection
    logger_.log(Level.INFO, "Connecting to : " + url);
    InputStream stream = url.openStream();
    BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));

    try {
      // Skip past the header line
      reader.readLine();

      // Read line by line. There is data entry per line
      while (true) {
        String line = reader.readLine();
        if (line == null)
          break;

        // Skip empty lines
        if (line.trim().length() == 0)
          continue;

        // Capture the tokens
        String[] tokens = csvSplit(line);

        // Trim and nullify
        for (int i = 0; i < tokens.length; i++) {
          String token = tokens[i];
          String newToken = token.trim();

          if (newToken.length() == 0)
            newToken = null;
          tokens[i] = newToken;
        }

        try {
          T instance = newInstance(tokens);
          instances.add(instance);
        }
        catch (ParseException exception) {
          logger_.log(Level.WARNING, "Skip illegal line: " + line, exception);
        }
      }
    }
    finally {
      reader.close();
    }

    logger_.log(Level.INFO, "Read " + instances.size() + " Sodir instances OK.");

    return instances;
  }
}
