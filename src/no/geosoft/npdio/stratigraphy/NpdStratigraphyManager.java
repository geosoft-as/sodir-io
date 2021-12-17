package no.geosoft.npdio.stratigraphy;

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
public final class NpdStratigraphyManager
{
  private final List<NpdStratigraphy> lithoStratigraphy_;

  private final List<NpdWellboreStratigraphy> wellboreStratigraphy_ = new ArrayList<>();

  /**
   * Create a NpdStratigraphyManager instance based on
   * all NPD stratigraphy data.
   *
   * @throws IOException  If the read operation fails for some reason.
   */
  public NpdStratigraphyManager()
    throws IOException
  {
    //
    // Load all litho stratigraphies
    //
    lithoStratigraphy_ = NpdStratigraphyReader.readAll();

    //
    // Populate the wellboreStratigraphy_ that organize the above by wellbore
    //
    Map<String, List<NpdStratigraphy>> wellboreStratigraphy = new HashMap<>();
    for (NpdStratigraphy stratigraphy : lithoStratigraphy_) {
      String wellboreName = stratigraphy.getWellboreName();
      List<NpdStratigraphy> stratigraphies = wellboreStratigraphy.containsKey(wellboreName) ? wellboreStratigraphy.get(wellboreName) : new ArrayList<>();
      stratigraphies.add(stratigraphy);
      wellboreStratigraphy.put(wellboreName, stratigraphies);
    }

    for (List<NpdStratigraphy> stratigraphy : wellboreStratigraphy.values())
      wellboreStratigraphy_.add(new NpdWellboreStratigraphy(stratigraphy));
  }

  /**
   * Find all stratigraphy instances of a specific formation.
   *
   * @param formationName  Name of formation to find stratigraph instances for. Non-null.
   * @return  Requested stratigraph instances. Null if not found.
   */
  public List<NpdStratigraphy> getStratigraphyByFormation(String formationName)
  {
    if (formationName == null)
      throw new IllegalArgumentException("formationName cannot be null");

    formationName = formationName.toUpperCase();
    if (formationName.split(" ").length == 1)
      formationName += " FM";

    List<NpdStratigraphy> stratigraphies = new ArrayList<>();
    for (NpdStratigraphy stratigraphy : lithoStratigraphy_) {
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
  public List<NpdStratigraphy> getStratigraphyByGroup(String groupName)
  {
    if (groupName == null)
      throw new IllegalArgumentException("groupName cannot be null");

    groupName = groupName.toUpperCase();
    if (groupName.split(" ").length == 1)
      groupName += " GP";

    List<NpdStratigraphy> stratigraphies = new ArrayList<>();
    for (NpdStratigraphy stratigraphy : lithoStratigraphy_) {
      if (stratigraphy.getLithostratigraphicUnit().equals(groupName))
        stratigraphies.add(stratigraphy);
    }

    return stratigraphies;
  }

  /**
   * Return a list of all NpdWellboreStratigraphy instances.
   *
   * @return List of all NpdWellboreStratigraphy instances. Never null.
   */
  public List<NpdWellboreStratigraphy> getWellboreStratigraphy()
  {
    return Collections.unmodifiableList(wellboreStratigraphy_);
  }

  /**
   * Return a NpdWellboreStratigraphy instance for a specific wellbore.
   *
   * @param wellboreName  Name of wellbore of NpdStratigraphy to find.
   * @return  The requested stratigraphy instance, or null if not found.
   * @throws IllegalArgumentException  If wellboreName is null.
   */
  public NpdWellboreStratigraphy getWellboreStratigraphy(String wellboreName)
  {
    if (wellboreName == null)
      throw new IllegalArgumentException("wellboreName cannot be null");

    for (NpdWellboreStratigraphy wellboreStratigraphy : wellboreStratigraphy_) {
      if (wellboreStratigraphy.getWellboreName().equals(wellboreName))
        return wellboreStratigraphy;
    }

    // Not found
    return null;
  }

  /**
   * Return a NpdWellboreStratigraphy instance with a specific wellbore id.
   *
   * @param npdId Wellbore NPD id of a NpdStratigraphy instance.
   * @return NpdWellboreStratigraphy instance with a specific wellbore id. Null if not found.
   */
  public NpdWellboreStratigraphy getWellboreStratigraphy(int npdId)
  {
    for(NpdWellboreStratigraphy wellbore : wellboreStratigraphy_) {
      if (wellbore.getWellboreNpdId() == npdId)
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
  public List<NpdStratigraphy> getLithostratigraphy()
  {
    return Collections.unmodifiableList(lithoStratigraphy_);
  }
}
