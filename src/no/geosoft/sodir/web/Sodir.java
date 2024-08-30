package no.geosoft.sodir.web;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import no.geosoft.sodir.company.SodirCompany;
import no.geosoft.sodir.company.SodirCompanyReader;

/**
 * The Sodir backend of the web service.
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class Sodir
{
  /** The logger instance. */
  private final Logger logger_ = Logger.getLogger(Sodir.class.getName());

  private final List<SodirCompany> companies_ = new ArrayList<>();

  public Sodir()
  {
    try {
      companies_.addAll(SodirCompanyReader.readAll());
    }
    catch (IOException exception) {
      logger_.log(Level.WARNING, "Unable to read", exception);
    }
  }

  public List<SodirCompany> getCompanies()
  {
    return Collections.unmodifiableList(companies_);
  }
}
