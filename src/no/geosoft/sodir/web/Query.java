package no.geosoft.sodir.web;

import java.util.Map;
import java.util.HashMap;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;

/**
 * Generic class for handling REST queries, i.e. the query string
 * that follows a url in the form:
 * <pre>
 *   &lt;url&gt;?&lt;key1&gt;=&lt;value1&gt;&amp;&lt;key2&gt;=&lt;value3&gt;amp;...
 * </pre>
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class Query
{
  /** The individual key/value pars of the query. */
  private final Map<String,String> entries_ = new HashMap<>();

  /**
   * Create a REST query instance based on the specified query string.
   *
   * @param queryString  Query string to consider. Null means none.
   */
  public Query(String queryString)
  {
    if (queryString == null)
      return;

    entries_.putAll(split(queryString));
  }

  /**
   * Split the speicfied REST query string into its key/value entries.
   *
   * @param queryString  Query string to consider. Non-null.
   * @return             The query string key/value pairs. Never null.
   */
  private static Map<String,String> split(String queryString)
  {
    assert queryString != null : "queryString cannot be null";

    Map<String,String> entries = new HashMap<>();

    String[] pairs = queryString.split("&");
    for (String pair : pairs) {
      int index = pair.indexOf('=');
      try {
        String key = URLDecoder.decode(pair.substring(0, index), "UTF-8");
        String value = URLDecoder.decode(pair.substring(index + 1), "UTF-8");

        entries.put(key, value);
      }
      catch (UnsupportedEncodingException exception) {
        assert false : "This will never happen";
      }
    }

    return entries;
  }

  /**
   * Return value for the specified key as a string.
   *
   * @param key           Key to consider. Non-null.
   * @param defaultValue  Value to return if key is not present.
   * @return              Value of key, or the default value if key is not present.
   * @throws IllegalArgumentException  If key is null.
   */
  public String getString(String key, String defaultValue)
  {
    if (key == null)
      throw new IllegalArgumentException("key cannot be null");

    String value = entries_.get(key);
    return value != null ? value : defaultValue;
  }

  /**
   * Return value for the specified key as a string.
   *
   * @param key  Key to consider. Non-null.
   * @return     Value of key, or null if key is not present.
   * @throws IllegalArgumentException  If key is null.
   */
  public String getString(String key)
  {
    if (key == null)
      throw new IllegalArgumentException("key cannot be null");

    return getString(key, null);
  }

  /**
   * Return value for the specified key as an integer.
   *
   * @param key           Key to consider. Non-null.
   * @param defaultValue  Value to return if key is not present.
   * @return              Value of key, or the default value if key is not present.
   * @throws IllegalArgumentException  If key is null.
   */
  public Integer getInteger(String key, Integer defaultValue)
  {
    if (key == null)
      throw new IllegalArgumentException("key cannot be null");

    String value = entries_.get(key);

    if (value == null)
      return defaultValue;

    try {
      return Integer.parseInt(value);
    }
    catch (NumberFormatException exception) {
      return defaultValue;
    }
  }

  /**
   * Return value for the specified key as an integer.
   *
   * @param key           Key to consider. Non-null.
   * @return              Value of key, or null if key is not present.
   * @throws IllegalArgumentException  If key is null.
   */
  public Integer getInteger(String key)
  {
    if (key == null)
      throw new IllegalArgumentException("key cannot be null");

    return getInteger(key, null);
  }

  /**
   * Return value for the specified key as a boolean.
   *
   * @param key           Key to consider. Non-null.
   * @param defaultValue  Value to return if key is not present.
   * @return              Value of key, or the default value if key is not present.
   * @throws IllegalArgumentException  If key is null.
   */
  public boolean getBoolean(String key, boolean defaultValue)
  {
    if (key == null)
      throw new IllegalArgumentException("key cannot be null");

    String value = entries_.get(key);
    if (value == null)
      return defaultValue;

    return value.equalsIgnoreCase("true");
  }

  /**
   * Return value for the specified key as a boolean.
   *
   * @param key           Key to consider. Non-null.
   * @return              Value of key, or false if key is not present.
   * @throws IllegalArgumentException  If key is null.
   */
  public boolean getBoolean(String key)
  {
    if (key == null)
      throw new IllegalArgumentException("key cannot be null");

    return getBoolean(key, false);
  }
}
