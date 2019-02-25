package com.example.codemonkeys.model;

import android.util.Log;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Universe {
    private Set<String> systems;
    private Set<Location> locations;
    private TechLevel[] actualLevels;
    private Resources[] actualResources;

    private SolarSystem[] solarSystems;


    private String[] SolarSystemNames =
            {"Acamar",
                    "Adahn",        // The alternate personality for The Nameless One in "Planescape: Torment"
                    "Aldea",
                    "Andevian",
                    "Antedi",
                    "Balosnee",
                    "Baratas",
                    "Brax",            // One of the heroes in Master of Magic
                    "Bretel",        // This is a Dutch device for keeping your pants up.
                    "Calondia",
                    "Campor",
                    "Capelle",        // The city I lived in while programming this game
                    "Carzon",
                    "Castor",        // A Greek demi-god
                    "Cestus",
                    "Cheron",
                    "Courteney",    // After Courteney Cox…
                    "Daled",
                    "Damast",
                    "Davlos",
                    "Deneb",
                    "Deneva",
                    "Devidia",
                    "Draylon",
                    "Drema",
                    "Endor",
                    "Esmee",        // One of the witches in Pratchett's Discworld
                    "Exo",
                    "Ferris",        // Iron
                    "Festen",        // A great Scandinavian movie
                    "Fourmi",        // An ant, in French
                    "Frolix",        // A solar system in one of Philip K. Dick's novels
                    "Gemulon",
                    "Guinifer",        // One way of writing the name of king Arthur's wife
                    "Hades",        // The underworld
                    "Hamlet",        // From Shakespeare
                    "Helena",        // Of Troy
                    "Hulst",        // A Dutch plant
                    "Iodine",        // An element
                    "Iralius",
                    "Janus",        // A seldom encountered Dutch boy's name
                    "Japori",
                    "Jarada",
                    "Jason",        // A Greek hero
                    "Kaylon",
                    "Khefka",
                    "Kira",            // My dog's name
                    "Klaatu",        // From a classic SF movie
                    "Klaestron",
                    "Korma",        // An Indian sauce
                    "Kravat",        // Interesting spelling of the French word for "tie"
                    "Krios",
                    "Laertes",        // A king in a Greek tragedy
                    "Largo",
                    "Lave",            // The starting system in Elite
                    "Ligon",
                    "Lowry",        // The name of the "hero" in Terry Gilliam's "Brazil"
                    "Magrat",        // The second of the witches in Pratchett's Discworld
                    "Malcoria",
                    "Melina",
                    "Mentar",        // The Psilon home system in Master of Orion
                    "Merik",
                    "Mintaka",
                    "Montor",        // A city in Ultima III and Ultima VII part 2
                    "Mordan",
                    "Myrthe",        // The name of my daughter
                    "Nelvana",
                    "Nix",            // An interesting spelling of a word meaning "nothing" in Dutch
                    "Nyle",            // An interesting spelling of the great river
                    "Odet",
                    "Og",            // The last of the witches in Pratchett's Discworld
                    "Omega",        // The end of it all
                    "Omphalos",        // Greek for navel
                    "Orias",
                    "Othello",        // From Shakespeare
                    "Parade",        // This word means the same in Dutch and in English
                    "Penthara",
                    "Picard",        // The enigmatic captain from ST:TNG
                    "Pollux",        // Brother of Castor
                    "Quator",
                    "Rakhar",
                    "Ran",            // A film by Akira Kurosawa
                    "Regulas",
                    "Relva",
                    "Rhymus",
                    "Rochani",
                    "Rubicum",        // The river Ceasar crossed to get into Rome
                    "Rutia",
                    "Sarpeidon",
                    "Sefalla",
                    "Seltrice",
                    "Sigma",
                    "Sol",            // That's our own solar system
                    "Somari",
                    "Stakoron",
                    "Styris",
                    "Talani",
                    "Tamus",
                    "Tantalos",        // A king from a Greek tragedy
                    "Tanuga",
                    "Tarchannen",
                    "Terosa",
                    "Thera",        // A seldom encountered Dutch girl's name
                    "Titan",        // The largest moon of Jupiter
                    "Torin",        // A hero from Master of Magic
                    "Triacus",
                    "Turkana",
                    "Tyrus",
                    "Umberlee",        // A god from AD&D, which has a prominent role in Baldur's Gate
                    "Utopia",        // The ultimate goal
                    "Vadera",
                    "Vagra",
                    "Vandor",
                    "Ventax",
                    "Xenon",
                    "Xerxes",        // A Greek hero
                    "Yew",            // A city which is in almost all of the Ultima games
                    "Yojimbo",        // A film by Akira Kurosawa
                    "Zalkon",
                    "Zuul"            // From the first Ghostbusters movie};
            };

    private final int MAXSOLARSYSTEM = 10;

    private static Universe universe = new Universe();

    public static Universe getInstance() {
        return universe;
    }

    private Universe() {
        systems = new HashSet<String>();
        locations = new HashSet<Location>();
    }

    public void generateUniverse() {
        generateSystemNames();
        scatterSystemLocations();
        generateTechLevels();
        generatePoliticalSystems();
        solarSystems = new SolarSystem[MAXSOLARSYSTEM];

        Iterator<String> it = systems.iterator();
        Iterator<Location> loc = locations.iterator();
        String content = "";
        int i = 0;
        while (it.hasNext()) {
            String sys = it.next();
            Location locat = loc.next();
            content += "System name: " + sys + "\n";
            content += "Location: " + locat + "\n";
            content += "Tech Level: " + actualLevels[i].getName() + "\n";
            content += "Resources: " + actualResources[i].getName() + "\n \n";
            solarSystems[i] = new SolarSystem(sys, locat,
                    actualLevels[i], actualResources[i]);
            i++;
        }
        largeLog("Universe", content);

    }

    public static void largeLog(String tag, String content) {
        if (content.length() > 4000) {
            Log.i(tag, content.substring(0, 4000));
            largeLog(tag, content.substring(4000));
        } else {
            Log.i(tag, content);
        }
    }

    public void generateTechLevels() {
        TechLevel[] possibleLevels = TechLevel.values();
        actualLevels = new TechLevel[MAXSOLARSYSTEM];
        for (int i = 0; i < MAXSOLARSYSTEM; i++) {
            actualLevels[i] = possibleLevels[(int) (Math.random() * (TechLevel.values().length))];
        }
    }

    public void generatePoliticalSystems() {
        Resources[] possibleResources = Resources.values();
        actualResources = new Resources[MAXSOLARSYSTEM];
        for (int i = 0; i < MAXSOLARSYSTEM; i++) {
            actualResources[i] = possibleResources[(int) (Math.random() * (Resources.values().length))];
        }
    }
    public void scatterSystemLocations() {
        int max = 150;
        int min = 0;
        while (locations.size() != MAXSOLARSYSTEM) {
            int x = (int) (Math.random() * ((max - min) + 1)) + min;
            int y = (int) (Math.random() * ((max - min) + 1)) + min;
            locations.add(new Location(x, y));
        }


    }

    public void generateSystemNames() {
        int max = SolarSystemNames.length - 1;
        int min = 0;
        while (systems.size() != MAXSOLARSYSTEM) {
            int num = (int) (Math.random() * ((max - min) + 1)) + min;
            systems.add(SolarSystemNames[num]);
        }
    }
}
