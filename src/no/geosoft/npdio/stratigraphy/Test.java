package no.geosoft.npdio.stratigraphy;

import java.io.IOException;
import java.util.List;

public class Test
{

  public static void main(String[] args)
      throws IOException
  {
    // Read all entries from www.factpages.npd.no
//    List<NpdStratigraphy> entries = NpdStratigraphyReader.readAll();
//    System.out.println(entries.size());

    // Create stratigraphy object
    NpdStratigraphyManager stratigraphyManager = new NpdStratigraphyManager();

    // Print all entries for wellbore 1/5-5
    System.out.println("------------");
    System.out.println("All stratigraphy entries for well: 1/5-5");
    NpdWellboreStratigraphy wellbore = stratigraphyManager.getWellboreStratigraphy("1/5-5");
    List<NpdStratigraphy> stratigraphy = wellbore.getLithostratigraphy();
    for (NpdStratigraphy strat : stratigraphy)
      System.out.println(strat);

    // Print all entries for with BALDER formation
    System.out.println("------------");
    System.out.println("All stratigraphy entries with formation: BALDER");
    List<NpdStratigraphy> stratigraphy2 = stratigraphyManager.getStratigraphyByFormation("balder");
    for (NpdStratigraphy strat : stratigraphy2)
      System.out.println(strat);

    // Print all entries with NORDLAND group
    System.out.println("------------");
    System.out.println("All stratigraphy entries with group: NORDLAND");
    List<NpdStratigraphy> stratigraphy3 = stratigraphyManager.getStratigraphyByGroup("nordland");
    for (NpdStratigraphy strat : stratigraphy3)
      System.out.println(strat);

    try {
      // Null values
//      List<NpdStratigraphy> test1 = stratigraphyManager.getFormation(null);
//      List<NpdStratigraphy> test2 = stratigraphyManager.getGroup(null);
//      NpdWellboreStratigraphy test3 = stratigraphyManager.getWellbore(0);
//      NpdWellboreStratigraphy test4 = stratigraphyManager.getWellbore((String)null);

      // Wrong values
//      List<NpdStratigraphy> test5 = stratigraphyManager.getFormation("ABCD");
//      List<NpdStratigraphy> test6 = stratigraphyManager.getGroup("ABCD");
//      NpdWellboreStratigraphy test7 = stratigraphyManager.getWellbore(1111);
//      NpdWellboreStratigraphy test8 = stratigraphyManager.getWellbore("ABCD");

    }
    catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("Finished");

  }
}
