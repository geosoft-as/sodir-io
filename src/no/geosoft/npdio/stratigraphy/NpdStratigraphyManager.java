package no.geosoft.npdio.stratigraphy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class to group convenient stratigraphy functionality
 *
 * @author <a href="mailto:jacob.dreyer@geosoft.no">Jacob Dreyer</a>
 *
 */
public final class NpdStratigraphyManager
{
  private final List<NpdStratigraphy> lithostratigraphy_;
  private final List<NpdWellboreStratigraphy> wellboreStratigraphy_;

  /**
   * Create a NpdStratigraphyManager instance based on all NPD stratigraphy data.
   *
   * @throws IOException
   */
  public NpdStratigraphyManager() throws IOException{
    lithostratigraphy_ = NpdStratigraphyReader.readAll();
    wellboreStratigraphy_ = createWellboreStratigraphy(lithostratigraphy_);
  }

  /**
   * Group stratigraphy instances by wellbore name.
   *
   * @param stratigraphy List of stratigraphy to group.
   * @return List of NpdWellboreStratigraphy instances. Non-null.
   */
  private List<NpdWellboreStratigraphy> createWellboreStratigraphy(List<NpdStratigraphy> stratigraphy){
    assert !stratigraphy.isEmpty() : "List of stratigraphy can not be empty";

    List<NpdWellboreStratigraphy> wellbores = new ArrayList<>();

    List<NpdStratigraphy> temp = new ArrayList<>();
    String wellbore = stratigraphy.get(0).getWellboreName();
    for(NpdStratigraphy entry: stratigraphy) {
      if(entry.getWellboreName().equals(wellbore))
        temp.add(entry);
      else {
        wellbores.add(new NpdWellboreStratigraphy(temp));
        temp = new ArrayList<>();
        wellbore = entry.getWellboreName();
        temp.add(entry);
      }
    }
    return wellbores;
  }

  /**
   * Find all stratigraphy instances of a specific formation.
   *
   * @param formation Name of formation. Non-null.
   * @return List of NpdStratigraphy instances of a specific formation. Null if not found.
   */
  private List<NpdStratigraphy> findFormation(String formation){
    assert formation != null: "Formation can not be null";

    formation = formation.toUpperCase();
    if(formation.split(" ").length == 1) {
      formation += " FM";
    }

    List<NpdStratigraphy> temp = new ArrayList<>();
    for(NpdStratigraphy entry: lithostratigraphy_) {
      if(entry.getLithostratigraphicUnit().equals(formation))
        temp.add(entry);
    }

    if(temp.isEmpty())
      return null;

    return temp;
  }

  /**
   * Find all stratigraphy instances of a specific group.
   *
   * @param group Name of groupo. Non-null.
   * @return List of NpdStratigraphy instances of a specific group. Null if not found.
   */
  private List<NpdStratigraphy> findGroup(String group){
    group = group.toUpperCase();
    if(group.split(" ").length == 1) {
      group += " GP";
    }

    List<NpdStratigraphy> temp = new ArrayList<>();
    for(NpdStratigraphy entry: lithostratigraphy_) {
      if(entry.getLithostratigraphicUnit().equals(group))
        temp.add(entry);
    }

    if(temp.isEmpty())
      return null;

    return temp;
  }

  /**
   * Return a list of all NpdWellboreStratigraphy instances.
   *
   * @return List of all NpdWellboreStratigraphy instances. Non-null.
   */
  public List<NpdWellboreStratigraphy> getWellboreStratigraphy()
  {
    return Collections.unmodifiableList(wellboreStratigraphy_);
  }

  /**
   * Return a NpdWellboreStratigraphy instance with a specific wellbore name.
   *
   * @param name Wellbore name of a NpdStratigraphy instance.
   * @return NpdWellboreStratigraphy instance with a specific wellbore name. Null if not found.
   */
  public NpdWellboreStratigraphy getWellbore(String name) {
    assert name != null: "Name can not be null";

    for(NpdWellboreStratigraphy wellbore: wellboreStratigraphy_) {
      if(wellbore.getWellboreName().equals(name))
        return wellbore;
    }

    return null;
  }

  /**
   * Return a NpdWellboreStratigraphy instance with a specific wellbore id.
   *
   * @param npdId Wellbore NPD id of a NpdStratigraphy instance.
   * @return NpdWellboreStratigraphy instance with a specific wellbore id. Null if not found.
   */
  public NpdWellboreStratigraphy getWellbore(int npdId) {
    for(NpdWellboreStratigraphy wellbore: wellboreStratigraphy_) {
      if(wellbore.getWellboreNpdId() == npdId)
        return wellbore;
    }

    return null;
  }

  /**
   * Return a list of all NpdStratigraphy instances.
   *
   * @return List of NpdStratigraphy instances. Non-null.
   */
  public List<NpdStratigraphy> getLithostratigraphy(){
    return Collections.unmodifiableList(lithostratigraphy_);
  }

  /**
   * Return a list of all NpdStratigraphy instances with a specific formation.
   *
   * @return List of all NpdStratigraphy instances with a specific formation. Null if not found.
   */
  public List<NpdStratigraphy> getFormation(String formation){
    if(formation == null)
      throw new IllegalArgumentException("Formation can not be null");

    List<NpdStratigraphy> stratigraphy = findFormation(formation);

    if(stratigraphy == null)
      return null;

    return Collections.unmodifiableList(stratigraphy);
  }

  /**
   * Return a list of all NpdStratigraphy instances with a specific group.
   *
   * @return List of all NpdStratigraphy instances with a specific group. Null if not found.
   */
  public List<NpdStratigraphy> getGroup(String group){
    if(group == null)
      throw new IllegalArgumentException("Group can not be null");

    List<NpdStratigraphy> stratigraphy = findGroup(group);

    if(stratigraphy == null)
      return null;

    return Collections.unmodifiableList(stratigraphy);
  }
}
