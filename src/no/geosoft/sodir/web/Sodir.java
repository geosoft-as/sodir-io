package no.geosoft.sodir.web;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import no.geosoft.sodir.company.SodirCompany;
import no.geosoft.sodir.company.SodirCompanyReader;
import no.geosoft.sodir.field.SodirField;
import no.geosoft.sodir.field.SodirFieldReader;
import no.geosoft.sodir.discovery.SodirDiscovery;
import no.geosoft.sodir.discovery.SodirDiscoveryReader;
import no.geosoft.sodir.license.SodirLicense;
import no.geosoft.sodir.license.SodirLicenseReader;
import no.geosoft.sodir.well.SodirWellbore;
import no.geosoft.sodir.well.SodirExplorationWellbore;
import no.geosoft.sodir.well.SodirExplorationWellboreReader;
import no.geosoft.sodir.well.SodirDevelopmentWellbore;
import no.geosoft.sodir.well.SodirDevelopmentWellboreReader;
import no.geosoft.sodir.well.SodirOtherWellbore;
import no.geosoft.sodir.well.SodirOtherWellboreReader;

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

  private final List<SodirDiscovery> discoveries_ = new ArrayList<>();

  private final List<SodirField> fields_ = new ArrayList<>();

  private final List<SodirLicense> licenses_ = new ArrayList<>();

  private final List<SodirExplorationWellbore> explorationWellbores_ = new ArrayList<>();

  private final List<SodirDevelopmentWellbore> developmentWellbores_ = new ArrayList<>();

  private final List<SodirOtherWellbore> otherWellbores_ = new ArrayList<>();

  private final List<SodirWellbore> allWellbores_ = new ArrayList<>();

  public Sodir()
  {
    try {
      companies_.addAll(SodirCompanyReader.readAll());
      discoveries_.addAll(SodirDiscoveryReader.readAll());
      fields_.addAll(SodirFieldReader.readAll());
      licenses_.addAll(SodirLicenseReader.readAll());
      explorationWellbores_.addAll(SodirExplorationWellboreReader.readAll());
      developmentWellbores_.addAll(SodirDevelopmentWellboreReader.readAll());
      otherWellbores_.addAll(SodirOtherWellboreReader.readAll());
    }
    catch (IOException exception) {
      logger_.log(Level.WARNING, "Unable to read", exception);
    }

    allWellbores_.addAll(explorationWellbores_);
    allWellbores_.addAll(developmentWellbores_);
    allWellbores_.addAll(otherWellbores_);
  }

    public List<SodirCompany> getCompanies()
    {
        return Collections.unmodifiableList(companies_);
    }

    public SodirCompany getCompany(String id)
    {
        for (SodirCompany company : companies_)
            if (company.getNpdId().equals(id))
                return company;

        // Not found
        return null;
    }

    public List<SodirDiscovery> getDiscoveries()
    {
        return Collections.unmodifiableList(discoveries_);
    }

    public SodirDiscovery getDiscovery(String id)
    {
        for (SodirDiscovery discovery : discoveries_)
            if (discovery.getNpdId().equals(id))
                return discovery;

        // Not found
        return null;
    }

    public List<SodirField> getFields()
    {
        return Collections.unmodifiableList(fields_);
    }

    public SodirField getField(String id)
    {
        for (SodirField field : fields_)
            if (field.getNpdId().equals(id))
                return field;

        // Not found
        return null;
    }

    public List<SodirLicense> getLicenses()
    {
        return Collections.unmodifiableList(licenses_);
    }

    public SodirLicense getLicense(String id)
    {
        for (SodirLicense license : licenses_)
            if (license.getNpdId().equals(id))
                return license;

        // Not found
        return null;
    }

    public List<SodirWellbore> getWellbores()
    {
        return Collections.unmodifiableList(allWellbores_);
    }

    public List<SodirExplorationWellbore> getExplorationWellbores()
    {
        return Collections.unmodifiableList(explorationWellbores_);
    }

    public List<SodirDevelopmentWellbore> getDevelopmentWellbores()
    {
        return Collections.unmodifiableList(developmentWellbores_);
    }

    public List<SodirOtherWellbore> getOtherWellbores()
    {
        return Collections.unmodifiableList(otherWellbores_);
    }

    public SodirWellbore getWellbore(String id)
    {
        for (SodirWellbore wellbore : allWellbores_)
            if (wellbore.getNpdId().equals(id))
                return wellbore;

        // Not found
        return null;
    }
}
