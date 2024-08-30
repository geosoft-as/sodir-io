package no.geosoft.sodir.web;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import no.geosoft.sodir.SodirObject;
import no.geosoft.sodir.discovery.SodirDiscovery;
import no.geosoft.sodir.facility.SodirFacility;
import no.geosoft.sodir.company.SodirCompany;
import no.geosoft.sodir.license.SodirLicense;
import no.geosoft.sodir.field.SodirField;
import no.geosoft.sodir.well.SodirWellbore;
import no.geosoft.sodir.json.JsonWriter;

public final class WebService
{
  /** The logging instance. */
  private static final Logger logger_ = Logger.getLogger(WebService.class.getName());

  private final Sodir sodir_;

  public WebService()
  {
    logger_.log(Level.INFO, "Initializing Sodir...");
    sodir_ = new Sodir();
    logger_.log(Level.INFO, "Sodir is ready.");
  }

  private String getCompanies(Query query)
  {
    List<SodirCompany> companies = sodir_.getCompanies();
    return JsonWriter.toString(JsonWriter.getSodirObjects(companies).build());
  }

    private String getCompany(String id)
    {
        SodirCompany company = sodir_.getCompany(id);
        return company != null ? JsonWriter.toString(JsonWriter.getCompany(company).build()) : "{}";
    }

    private String getFields(Query query)
    {
        List<SodirField> fields = sodir_.getFields();
        return JsonWriter.toString(JsonWriter.getSodirObjects(fields).build());
    }

    private String getField(String id)
    {
        SodirField field = sodir_.getField(id);
        return field != null ? JsonWriter.toString(JsonWriter.getField(field).build()) : "{}";
    }

    private String getLicenses(Query query)
    {
        List<SodirLicense> licenses = sodir_.getLicenses();
        return JsonWriter.toString(JsonWriter.getSodirObjects(licenses).build());
    }

    private String getLicense(String id)
    {
        SodirLicense license = sodir_.getLicense(id);
        return license != null ? JsonWriter.toString(JsonWriter.getLicense(license).build()) : "{}";
    }


    private String getWellbores(Object query)
    {
        List<SodirWellbore> wellbores = sodir_.getWellbores();
        return JsonWriter.toString(JsonWriter.getSodirObjects(wellbores).build());
    }

    private String getWellbore(String id)
    {
        SodirWellbore wellbore = sodir_.getWellbore(id);
        return wellbore != null ? JsonWriter.toString(JsonWriter.getWellbore(wellbore).build()) : "{}";
    }


  public static void main(String[] arguments)
  {
    WebService webService = new WebService();
    System.out.println(webService.getWellbores(null));
    System.out.println("---");
    System.out.println(webService.getWellbore("2235"));
  }
}
