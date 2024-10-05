package no.geosoft.sodir.web;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

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

  /** The Sodir I/O back-end. */
  private final Sodir sodir_;

  /** The actual web server. */
  private final Server webServer_;

  public WebService(int portNo)
  {
    logger_.log(Level.INFO, "Initializing Sodir...");
    sodir_ = new Sodir();
    logger_.log(Level.INFO, "Sodir is ready.");

    logger_.log(Level.INFO, "Starting SODIR web service...");

    // Servlet context
    ServletContextHandler servletContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
    servletContext.setContextPath("/");
    servletContext.addServlet(new ServletHolder(new SodirServlet(this)), "/wellbores");
    servletContext.addServlet(new ServletHolder(new SodirServlet(this)), "/companies");
    servletContext.addServlet(new ServletHolder(new SodirServlet(this)), "/company");

    // Initialize and start
    webServer_ = new Server(portNo);
    webServer_.setHandler(servletContext);

    try {
      webServer_.start();
      logger_.log(Level.INFO, "Sodir I/O web service now running on port " + portNo);

      webServer_.join(); // Endless loop starts here
    }
    catch (Exception exception) { // Yes, start() actually throws Exception!
      logger_.log(Level.WARNING, "Unable to start web service");
    }
  }

  private String getCompanies(Query query)
  {
    List<SodirCompany> companies = sodir_.getCompanies();
    return JsonWriter.toString(JsonWriter.getSodirObjects(companies).build());
  }

  private String getCompany(String id)
  {
    SodirCompany company = sodir_.getCompany(id);
    return company != null ? JsonWriter.toString(JsonWriter.getCompany(company).build()) : null;
  }

  private String getFields(Query query)
  {
    List<SodirField> fields = sodir_.getFields();
    return JsonWriter.toString(JsonWriter.getSodirObjects(fields).build());
  }

  private String getField(String id)
  {
    SodirField field = sodir_.getField(id);
    return field != null ? JsonWriter.toString(JsonWriter.getField(field).build()) : null;
  }

  private String getLicenses(Query query)
  {
    List<SodirLicense> licenses = sodir_.getLicenses();
    return JsonWriter.toString(JsonWriter.getSodirObjects(licenses).build());
  }

  private String getLicense(String id)
  {
    SodirLicense license = sodir_.getLicense(id);
    return license != null ? JsonWriter.toString(JsonWriter.getLicense(license).build()) : null;
  }


  private String getWellbores(Query query)
  {
    List<SodirWellbore> wellbores = sodir_.getWellbores();
    return JsonWriter.toString(JsonWriter.getSodirObjects(wellbores).build());
  }

  private String getWellbore(String id)
  {
    SodirWellbore wellbore = sodir_.getWellbore(id);
    return wellbore != null ? JsonWriter.toString(JsonWriter.getWellbore(wellbore).build()) : null;
  }

  /**
   * The servlet handling request/response for the Sodir I/O web servier module.
   * <p>
   * This class is made as thin as possible, it should only include the
   * request/response mechanics, not any Sodir I/O back-end tasks not JSON
   * serialization.
   *
   * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
   */
  private final class SodirServlet extends HttpServlet
  {
    /** The web service back-end. */
    private final WebService webService_;

    private SodirServlet(WebService webService)
    {
      webService_ = webService;
    }

    /** {@inheritDoc} */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException
    {
      String path = request.getServletPath();
      String queryString = request.getQueryString();
      Query query = new Query(queryString);

      int status = HttpServletResponse.SC_OK;
      String text = "";
      String contentType = "text/json";
      String errorMessage = null;

      //
      // Wellbores
      //
      if (path.equals("/wellbores")) {
        text = webService_.getWellbores(null);
      }

      //
      // Companies
      //
      if (path.equals("/companies")) {
        text = webService_.getCompanies(null);
      }

      //
      // Company
      //
      if (path.equals("/company")) {
        String companyId = query.getString("id");

        if (companyId == null) {
          status = HttpServletResponse.SC_BAD_REQUEST;
          errorMessage = "Invalid query";
        }

        else {
          text = webService_.getCompany(companyId);
          if (text == null) {
            status = HttpServletResponse.SC_BAD_REQUEST;
          }
        }
      }

      response.setCharacterEncoding("UTF-8");
      response.setStatus(status);

      if (errorMessage != null)
        response.sendError(status, errorMessage);

      if (text != null)
        response.getWriter().write(text);
    }
  }

  public static void main(String[] args)
  {
    WebService webService = new WebService(8080);
  }
}

