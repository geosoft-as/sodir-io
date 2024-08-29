package no.geosoft.sodir.stratigraphy;

import java.io.IOException;
import java.util.List;

public class Test
{
  public static void main(String[] args)
      throws IOException
  {
    // Read all entries from www.factpages.npd.no
//    List<SodirStratigraphy> entries = SodirStratigraphyReader.readAll();
//    System.out.println(entries.size());

    // Create stratigraphy object
    SodirStratigraphyManager stratigraphyManager = new SodirStratigraphyManager();

    // Print all entries for wellbore 1/5-5
    System.out.println("------------");
    System.out.println("All stratigraphy entries for well: 1/5-5");
    SodirWellboreStratigraphy wellbore = stratigraphyManager.getWellboreStratigraphy("1/5-5");
    List<SodirStratigraphy> stratigraphy = wellbore.getLithostratigraphy();
    for (SodirStratigraphy strat : stratigraphy)
      System.out.println(strat);

    // Print all entries for with BALDER formation
    System.out.println("------------");
    System.out.println("All stratigraphy entries with formation: BALDER");
    List<SodirStratigraphy> stratigraphy2 = stratigraphyManager.getStratigraphyByFormation("balder");
    for (SodirStratigraphy strat : stratigraphy2)
      System.out.println(strat);

    // Print all entries with NORDLAND group
    System.out.println("------------");
    System.out.println("All stratigraphy entries with group: NORDLAND");
    List<SodirStratigraphy> stratigraphy3 = stratigraphyManager.getStratigraphyByGroup("nordland");
    for (SodirStratigraphy strat : stratigraphy3)
      System.out.println(strat);

    try {
      // Null values
//      List<SodirStratigraphy> test1 = stratigraphyManager.getFormation(null);
//      List<SodirStratigraphy> test2 = stratigraphyManager.getGroup(null);
//      SodirWellboreStratigraphy test3 = stratigraphyManager.getWellbore(0);
//      SodirWellboreStratigraphy test4 = stratigraphyManager.getWellbore((String)null);

      // Wrong values
//      List<SodirStratigraphy> test5 = stratigraphyManager.getFormation("ABCD");
//      List<SodirStratigraphy> test6 = stratigraphyManager.getGroup("ABCD");
//      SodirWellboreStratigraphy test7 = stratigraphyManager.getWellbore(1111);
//      SodirWellboreStratigraphy test8 = stratigraphyManager.getWellbore("ABCD");

    }
    catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Finished");

  }
}
