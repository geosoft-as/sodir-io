package no.geosoft.sodir.web;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import no.geosoft.sodir.company.SodirCompany;
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

  private String getCompanyNames()
  {
    return "";
  }

  private String getCompanies(Object query)
  {
    List<SodirCompany> companies = sodir_.getCompanies();
    return JsonWriter.toString(JsonWriter.getCompanies(companies).build());
  }

  public static void main(String[] arguments)
  {
    WebService webService = new WebService();
    System.out.println(webService.getCompanies(null));
  }
}
