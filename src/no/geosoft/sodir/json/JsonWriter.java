package no.geosoft.sodir.json;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.io.StringWriter;
import java.io.OutputStreamWriter;
import java.io.OutputStream;
import java.io.Writer;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.nio.charset.StandardCharsets;

import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.Json;
import javax.json.JsonStructure;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

import no.geosoft.sodir.SodirObject;
import no.geosoft.sodir.company.SodirCompany;

/**
 * Class for writing Sodir instances as JSON.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class JsonWriter
{
  private JsonWriter()
  {
    assert false : "This constructor should never be called";
  }

  /**
   * Save the specified JSON structure to the given stream.
   *
   * @param stream  Stream to save to. Non-null.
   * @param json  JSON structure to save. Non-null.
   * @throws IllegalArgumentException  If stream of json is null.
   * @throws IOException  If the save operation fails for some reason.
   */
  public static void save(OutputStream stream, JsonStructure json)
    throws IOException
  {
    if (stream == null)
      throw new IllegalArgumentException("stream cannot be null");

    if (json == null)
      throw new IllegalArgumentException("json cannot be null");

    Map<String, Object> config = new HashMap<>();
    config.put(JsonGenerator.PRETTY_PRINTING, true);

    Writer writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8);

    JsonWriterFactory jsonWriterFactory = Json.createWriterFactory(config);
    javax.json.JsonWriter jsonWriter = jsonWriterFactory.createWriter(writer);

    jsonWriter.write(json);
    jsonWriter.close();
  }

  /**
   * Save the specified JSON structure to the given file.
   *
   * @param file  File to save to. Non-null.
   * @param json  JSON structure to save. Non-null.
   * @throws IllegalArgumentException  If file of json is null.
   * @throws IOException  If the save operation fails for some reason.
   */
  public static void save(File file, JsonStructure json)
    throws IOException
  {
    if (file == null)
      throw new IllegalArgumentException("file cannot be null");

    if (json == null)
      throw new IllegalArgumentException("json cannot be null");

    FileOutputStream fileStream = new FileOutputStream(file);
    try {
      JsonWriter.save(fileStream, json);
      fileStream.close();
    }
    catch (Exception exception) {
      throw exception;
    }
    finally {
      fileStream.close();
    }
  }

  /**
   * Return the specified JSON structure as a pretty-printed JSON string.
   *
   * @param json  JSON structure to consider. Non-null.
   * @return      The equivalent pretty-printed JSON string. Never null.
   */
  public static String toString(JsonStructure json)
  {
    if (json == null)
      throw new IllegalArgumentException("json cannot be null");

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(byteArrayOutputStream);

    try {
      save(printStream, json);
      return new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8);
    }
    catch (IOException exception) {
      assert false;
      return null;
    }
  }

  /**
   * Add entry of the specified key/value to the given object builder.
   *
   * @param objectBuilder  Object builder to add to. Non-null.
   * @param key            Key of entry to add. Non-null.
   * @param value          Value of key. May be null, in case "null" is added.
   */
  private static void add(JsonObjectBuilder objectBuilder, String key, String value)
  {
    assert objectBuilder != null : "objectBuilder cannot be null";
    assert key != null : "key cannot be null";

    if (value != null)
      objectBuilder.add(key, value);
    else
      objectBuilder.addNull(key);
  }

  /**
   * Add entry of the specified key/value to the given object builder.
   *
   * @param objectBuilder  Object builder to add to. Non-null.
   * @param key            Key of entry to add. Non-null.
   * @param value          Value of key. May be null, in case "null" is added.
   */
  private static void add(JsonObjectBuilder objectBuilder, String key, Integer value)
  {
    assert objectBuilder != null : "objectBuilder cannot be null";
    assert key != null : "key cannot be null";

    if (value != null)
      objectBuilder.add(key, value);
    else
      objectBuilder.addNull(key);
  }

  /**
   * Add entry of the specified key/value to the given object builder.
   *
   * @param objectBuilder  Object builder to add to. Non-null.
   * @param key            Key of entry to add. Non-null.
   * @param value          Value of key. May be null, in case "null" is added.
   */
  private static void add(JsonObjectBuilder objectBuilder, String key, Boolean value)
  {
    assert objectBuilder != null : "objectBuilder cannot be null";
    assert key != null : "key cannot be null";

    if (value != null)
      objectBuilder.add(key, value);
    else
      objectBuilder.addNull(key);
  }

  private static void add(JsonObjectBuilder objectBuilder, SodirObject sodirObject)
  {
    add(objectBuilder, "type", sodirObject.getType());
    add(objectBuilder, "name", sodirObject.getName());
    add(objectBuilder, "id", sodirObject.getNpdId());
    add(objectBuilder, "factPageUrl", sodirObject.getFactPageUrl());
    add(objectBuilder, "factMapUrl", sodirObject.getFactMapUrl());
  }

  /**
   * Return the specified Sodir company as a JSON object builder.
   *
   * @param company  Company to consider. Non-null.
   * @return         The equivalent JSON object builder. Never null.
   * @throws IllegalArgumentException  If company is null.
   */
  public static JsonObjectBuilder getCompany(SodirCompany company)
  {
    if (company == null)
      throw new IllegalArgumentException("company cannot be null");

    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
    add(objectBuilder, "name", company.getName());
    add(objectBuilder, "id", company.getNpdId());
    add(objectBuilder, "organizationNumber", company.getOrganizationNumber());
    add(objectBuilder, "shortName", company.getShortName());
    add(objectBuilder, "nationCode", company.getNationCode());
    add(objectBuilder, "surveyPrefix", company.getSurveyPrefix());
    add(objectBuilder, "isCurrentLicenseOperator", company.isCurrentLicenseOperator());
    add(objectBuilder, "isFormerLicenseOperator", company.isFormerLicenseOperator());
    add(objectBuilder, "isCurrentLicenseLicensee", company.isCurrentLicenseLicensee());
    add(objectBuilder, "isFormerLicenseLicensee", company.isFormerLicenseLicensee());

    return objectBuilder;
  }

  public static JsonArrayBuilder getCompanies(Collection<SodirCompany> companies)
  {
    if (companies == null)
      throw new IllegalArgumentException("companies cannot be null");

    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    for (SodirCompany company : companies)
      arrayBuilder.add(getCompany(company));

    return arrayBuilder;
  }
}
