package no.geosoft.sodirio.stratigraphy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to group convenient stratigraphy functionality
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 */
public final class SodirStratigraphyManager
{
  private final List<SodirStratigraphy> lithoStratigraphy_;

  private final List<SodirWellboreStratigraphy> wellboreStratigraphy_ = new ArrayList<>();

  /**
   * Create a SodirStratigraphyManager instance based on
   * all Sodir stratigraphy data.
   *
   * @throws IOException  If the read operation fails for some reason.
   */
  public SodirStratigraphyManager()
    throws IOException
  {
    //
    // Load all litho stratigraphies
    //
    lithoStratigraphy_ = SodirStratigraphyReader.readAll();

    //
    // Populate the wellboreStratigraphy_ that organize the above by wellbore
    //
    Map<String, List<SodirStratigraphy>> wellboreStratigraphy = new HashMap<>();
    for (SodirStratigraphy stratigraphy : lithoStratigraphy_) {
      String wellboreName = stratigraphy.getWellboreName();
      List<SodirStratigraphy> stratigraphies = wellboreStratigraphy.containsKey(wellboreName) ? wellboreStratigraphy.get(wellboreName) : new ArrayList<>();
      stratigraphies.add(stratigraphy);
      wellboreStratigraphy.put(wellboreName, stratigraphies);
    }

    for (List<SodirStratigraphy> stratigraphy : wellboreStratigraphy.values())
      wellboreStratigraphy_.add(new SodirWellboreStratigraphy(stratigraphy));
  }

  /**
   * Find all stratigraphy instances of a specific formation.
   *
   * @param formationName  Name of formation to find stratigraph instances for. Non-null.
   * @return  Requested stratigraph instances. Null if not found.
   */
  public List<SodirStratigraphy> getStratigraphyByFormation(String formationName)
  {
    if (formationName == null)
      throw new IllegalArgumentException("formationName cannot be null");

    formationName = formationName.toUpperCase();
    if (formationName.split(" ").length == 1)
      formationName += " FM";

    List<SodirStratigraphy> stratigraphies = new ArrayList<>();
    for (SodirStratigraphy stratigraphy : lithoStratigraphy_) {
      if (stratigraphy.getLithostratigraphicUnit().equals(formationName))
        stratigraphies.add(stratigraphy);
    }

    return stratigraphies;
  }

  /**
   * Find all stratigraphy instances of a specific group.
   *
   * @param groupName  Name of group to find stratigraphy for. Non-null.
   * @return  Requested stratigraphy. Never null.
   * @throws IllegalArgumentException  If groupName is null.
   */
  public List<SodirStratigraphy> getStratigraphyByGroup(String groupName)
  {
    if (groupName == null)
      throw new IllegalArgumentException("groupName cannot be null");

    groupName = groupName.toUpperCase();
    if (groupName.split(" ").length == 1)
      groupName += " GP";

    List<SodirStratigraphy> stratigraphies = new ArrayList<>();
    for (SodirStratigraphy stratigraphy : lithoStratigraphy_) {
      if (stratigraphy.getLithostratigraphicUnit().equals(groupName))
        stratigraphies.add(stratigraphy);
    }

    return stratigraphies;
  }

  /**
   * Return a list of all SodirWellboreStratigraphy instances.
   *
   * @return List of all SodirWellboreStratigraphy instances. Never null.
   */
  public List<SodirWellboreStratigraphy> getWellboreStratigraphy()
  {
    return Collections.unmodifiableList(wellboreStratigraphy_);
  }

  /**
   * Return a SodirWellboreStratigraphy instance for a specific wellbore.
   *
   * @param wellboreName  Name of wellbore of SodirStratigraphy to find.
   * @return  The requested stratigraphy instance, or null if not found.
   * @throws IllegalArgumentException  If wellboreName is null.
   */
  public SodirWellboreStratigraphy getWellboreStratigraphy(String wellboreName)
  {
    if (wellboreName == null)
      throw new IllegalArgumentException("wellboreName cannot be null");

    for (SodirWellboreStratigraphy wellboreStratigraphy : wellboreStratigraphy_) {
      if (wellboreStratigraphy.getWellboreName().equals(wellboreName))
        return wellboreStratigraphy;
    }

    // Not found
    return null;
  }

  /**
   * Return a SodirWellboreStratigraphy instance with a specific wellbore id.
   *
   * @param id  Wellbore Sodir ID of a SodirStratigraphy instance.
   * @return SodirWellboreStratigraphy instance with a specific wellbore id. Null if not found.
   */
  public SodirWellboreStratigraphy getWellboreStratigraphy(int id)
  {
    for(SodirWellboreStratigraphy wellbore : wellboreStratigraphy_) {
      if (wellbore.getWellboreId() == id)
        return wellbore;
    }

    // Not found
    return null;
  }

  /**
   * Return all stratigraphy instances.
   *
   * @return All stratigraphy instances. Never null.
   */
  public List<SodirStratigraphy> getLithostratigraphy()
  {
    return Collections.unmodifiableList(lithoStratigraphy_);
  }
}
