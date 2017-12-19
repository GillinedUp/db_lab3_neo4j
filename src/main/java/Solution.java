
public class Solution {

    private final GraphDatabase graphDatabase = GraphDatabase.createDatabase();

    public void databaseStatistics() {
        System.out.println(graphDatabase.runCypher("CALL db.labels()"));
        System.out.println(graphDatabase.runCypher("CALL db.relationshipTypes()"));
    }

    public void databaseSchema() {
        System.out.println(graphDatabase.runCypher("CALL db.schema()"));
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public String createBand(final String bandName, final int formationYear) {
        return graphDatabase.runCypher("CREATE (n:Band {name: \"" +
                bandName + "\", formation_year: " +
                formationYear + "})");
    }

    public String createCountry(final String countryName, final String continent) {
        return graphDatabase.runCypher("CREATE (n:Country {name: \"" +
                countryName + "\", continent: \"" +
                continent + "\"})");
    }

    public String createGenre(final String genreName,
                              final String vocals,
                              final String tempo,
                              final String distortion)
    {
        return graphDatabase.runCypher("CREATE (n:Genre {name: \"" + genreName + "\", " +
                "vocals: \"" + vocals + "\", " +
                "tempo: \"" + tempo + "\", " +
                "distortion: \"" + distortion + "\"})");
    }

    public String createPerson(final String personName, final int yearOfBirth) {
        return graphDatabase.runCypher("CREATE (n:Person {name: \"" + personName + "\", " +
                "year: {" + yearOfBirth + "})");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////

    public String createOriginRelationship(final String bandName, final String countryName) {
        return graphDatabase.runCypher("MATCH (b:Band {name: \"" + bandName + "\"}), " +
                "(c:Country {name:\"" + countryName + "\"})\n" +
                "CREATE (b)-[:ORIGIN]->(c)");
    }

    public String createPlaysRelationship(final String bandName, final String genreName) {
        return graphDatabase.runCypher("MATCH (b:Band {name: \"" + bandName + "\"}), " +
                "(c:Genre {name: \"" + genreName + "\"})\n" +
                "CREATE (b)-[:PLAYS]->(c)");
    }

    public String createIsMemberOfRelationship(final String personName,
                                               final String bandName,
                                               final int yearOfJoin,
                                               final String role)
    {
        return graphDatabase.runCypher("MATCH (p:Person {name:\"" + personName + "\"}), " +
                "(b:Band {name: \"" + bandName + "\"})\n" +
                "CREATE (p)-[:IS_MEMBER_OF {year_of_join: {" + yearOfJoin + "}, " +
                "role: \"" + role + "\"}]->(b)");
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    public String returnAllRelationshipsForBand(final String bandName) {
        return graphDatabase.runCypher("MATCH (:Band {name: \"" + bandName + "\"})-[r]-()\n" +
                "RETURN r");
    }

    public String returnAllRelationshipsForCountry(final String countryName) {
        return graphDatabase.runCypher("MATCH (:Country {name: \"" + countryName + "\"})-[r]-()\n" +
                "RETURN r");
    }

    public String returnAllRelationshipsForGenre(final String genreName) {
        return graphDatabase.runCypher("MATCH (:Genre {name: \"" + genreName + "\"})-[r]-()\n" +
                "RETURN r");
    }

    public String returnAllRelationshipsForPerson(final String personName) {
        return graphDatabase.runCypher("MATCH (:Person {name: \"" + personName + "\"})-[r]-()\n" +
                "RETURN r");
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    public String returnPathBetweenNodes(final String nodeType1,
                                         final String nodeType2,
                                         final String nodeName1,
                                         final String nodeName2)
    {
        return graphDatabase.runCypher("MATCH (n1:" + nodeType1 + " { name: \"" + nodeName1 + "\" })," +
                "(n2:" + nodeType2 + " { name: \"" + nodeName2 +"\" }), " +
                "p = shortestPath((n1)--(n2))\n" +
                "RETURN p");
    }

    public String returnPathBetweenBands(final String bandName1, final String bandName2) {
        return returnPathBetweenNodes("Band", "Band", bandName1, bandName2);
    }

    public String returnPathBetweenCountries(final String countryName1, final String countryName2) {
        return returnPathBetweenNodes("Country", "Country", countryName1, countryName2);
    }

    public String returnPathBetweenGenres(final String genreName1, final String genreName2) {
        return returnPathBetweenNodes("Genre", "Genre", genreName1, genreName2);
    }

    public String returnPathBetweenPersons(final String personName1, final String personName2) {
        return returnPathBetweenNodes("Person", "Band", personName1, personName2);
    }

    public String returnPathBetweenBandAndCountry(final String bandName, final String countryName) {
        return returnPathBetweenNodes("Band", "Country", bandName, countryName);
    }

    public String returnPathBetweenBandAndGenre(final String bandName, final String genreName) {
        return returnPathBetweenNodes("Band", "Genre", bandName, genreName);
    }

    public String returnPathBetweenPersonAndBand(final String personName, final String bandName) {
        return returnPathBetweenNodes("Person", "Band", personName, bandName);
    }

    public String returnPathBetweenPersonAndGenre(final String personName, final String genreName) {
        return returnPathBetweenNodes("Person", "Genre",  personName, genreName);
    }

    public String returnPathBetweenPersonAndCountry(final String personName, final String countryName) {
        return returnPathBetweenNodes("Person", "Country",  personName, countryName);
    }

    public String returnPathBetweenGenreAndCountry(final String genreName, final String countryName) {
        return returnPathBetweenNodes("Genre", "Country", genreName, countryName);
    }

    public void createNodes() {
        System.out.println(createBand("Gloryhammer", 2010));
        System.out.println(createBand("Tank", 1980));
        System.out.println(createBand("Judas Priest", 1970));
        System.out.println(createBand("Death Angel", 1982));
        System.out.println(createBand("Death", 1984));
        System.out.println(createBand("Candlemass", 1984));
        System.out.println(createBand("Anthrax", 1981));
        System.out.println(createBand("Blind Guardian", 1986));
        System.out.println(createBand("Gamma Ray", 1989));
        System.out.println(createBand("Diamond Head", 1976));
        System.out.println(createCountry("UK", "Europe"));
        System.out.println(createCountry("US", "North America"));
        System.out.println(createCountry("Switzerland", "Europe"));
        System.out.println(createCountry("Sweden", "Europe"));
        System.out.println(createCountry("Germany", "Europe"));
        System.out.println(createGenre("Heavy metal", "clean", "mid", "mild"));
        System.out.println(createGenre("Thrash metal", "harsh", "fast", "heavy"));
        System.out.println(createGenre("Death metal", "growl", "fast", "heavy"));
        System.out.println(createGenre("Doom metal", "clean", "slow", "heavy"));
        System.out.println(createGenre("Power metal", "clean", "fast", "mild"));
        System.out.println(createGenre("Speed metal", "clean", "fast", "mild"));
        System.out.println(createPerson("James Cartwright", 1986));
        System.out.println(createPerson("Ben Turk", -1));
        System.out.println(createPerson("Paul Templing", -1));
        System.out.println(createPerson("Christopher Bowes", 1986));
        System.out.println(createPerson("Thomas Winkler", 1985));
        System.out.println(createPerson("Algy Ward", 1959));
        System.out.println(createPerson("Mick Tucker", -1));
        System.out.println(createPerson("Cliff Evans", 1962));
        System.out.println(createPerson("Bruce Bisland", 1958));
        System.out.println(createPerson("Ian Hill", 1951));
        System.out.println(createPerson("Rob Halford", 1951));
        System.out.println(createPerson("Glenn Tipton", 1947));
        System.out.println(createPerson("Scott Travis", 1961));
        System.out.println(createPerson("Rob Cavestany", 1968));
        System.out.println(createPerson("Mark Osegueda", 1969));
        System.out.println(createPerson("Ted Aguilar", -1));
        System.out.println(createPerson("Damien Sisson", 1981));
        System.out.println(createPerson("Will Carroll", 1973));
        System.out.println(createPerson("Chuck Schuldiner", 1967));
        System.out.println(createPerson("Scott Clendenin", 1968));
        System.out.println(createPerson("Richard Christy", 1974));
        System.out.println(createPerson("Shannon Hamm", 1967));
        System.out.println(createPerson("Leif Edling", 1963));
        System.out.println(createPerson("Mats Björkman", -1));
        System.out.println(createPerson("Jan Lindh", -1));
        System.out.println(createPerson("Lars Johansson", -1));
        System.out.println(createPerson("Mats Levén", 1964));
        System.out.println(createPerson("Scott Ian", 1963));
        System.out.println(createPerson("Charlie Benante", 1962));
        System.out.println(createPerson("Frank Bello", 1965));
        System.out.println(createPerson("Joey Belladonna", 1960));
        System.out.println(createPerson("Jonathan Donais", 1979));
        System.out.println(createPerson("Hansi Kürsch", 1966));
        System.out.println(createPerson("André Olbrich", 1967));
        System.out.println(createPerson("Marcus Siepen", 1968));
        System.out.println(createPerson("Frederik Ehmke", 1978));
        System.out.println(createPerson("Kai Hansen", 1963));
        System.out.println(createPerson("Dirk Schlächter", 1965));
        System.out.println(createPerson("Henjo Richter", 1963));
        System.out.println(createPerson("Michael Ehré", 1971));
        System.out.println(createPerson("Frank Beck", 1969));
        System.out.println(createPerson("Brian Tatler", 1963));
        System.out.println(createPerson("Karl Wilcox", -1));
        System.out.println(createPerson("Andy Abberley", -1));
        System.out.println(createPerson("Rasmus Bom Andersen", 1984));
        System.out.println(createPerson("Dean Ashton", -1));
    }

    public void createRelationships() {
        System.out.println(createOriginRelationship("Judas Priest", "UK"));
        System.out.println(createOriginRelationship("Gloryhammer", "UK"));
        System.out.println(createOriginRelationship("Gloryhammer", "Switzerland"));
        System.out.println(createOriginRelationship("Tank", "UK"));
        System.out.println(createOriginRelationship("Death Angel", "US"));
        System.out.println(createOriginRelationship("Candlemass", "Sweden"));
        System.out.println(createOriginRelationship("Death", "US"));
        System.out.println(createOriginRelationship("Anthrax", "US"));
        System.out.println(createOriginRelationship("Diamond Head", "UK"));
        System.out.println(createOriginRelationship("Blind Guardian", "Germany"));
        System.out.println(createOriginRelationship("Gamma Ray", "Germany"));
        System.out.println(createPlaysRelationship("Judas Priest", "Heavy metal"));
        System.out.println(createPlaysRelationship("Gloryhammer", "Power metal"));
        System.out.println(createPlaysRelationship("Tank", "Heavy metal"));
        System.out.println(createPlaysRelationship("Tank", "Speed metal"));
        System.out.println(createPlaysRelationship("Death Angel", "Thrash metal"));
        System.out.println(createPlaysRelationship("Candlemass", "Doom metal"));
        System.out.println(createPlaysRelationship("Death", "Death metal"));
        System.out.println(createPlaysRelationship("Anthrax", "Thrash metal"));
        System.out.println(createPlaysRelationship("Diamond Head", "Heavy metal"));
        System.out.println(createPlaysRelationship("Blind Guardian", "Power metal"));
        System.out.println(createPlaysRelationship("Blind Guardian", "Speed metal"));
        System.out.println(createPlaysRelationship("Gamma Ray", "Power metal"));
        System.out.println(createIsMemberOfRelationship("James Cartwright", "Gloryhammer", 2010, "bass"));
        System.out.println(createIsMemberOfRelationship("Ben Turk", "Gloryhammer", 2010, "drums"));
        System.out.println(createIsMemberOfRelationship("Paul Templing", "Gloryhammer", 2010, "guitars"));
        System.out.println(createIsMemberOfRelationship("Christopher Bowes", "Gloryhammer", 2010, "keyboards"));
        System.out.println(createIsMemberOfRelationship("Thomas Winkler", "Gloryhammer", 2010, "vocals"));
        System.out.println(createIsMemberOfRelationship("Algy Ward", "Tank", 1980, "vocals, bass"));
        System.out.println(createIsMemberOfRelationship("Mick Tucker", "Tank", 1983, "guitars"));
        System.out.println(createIsMemberOfRelationship("Cliff Evans", "Tank", 1984, "guitars"));
        System.out.println(createIsMemberOfRelationship("Bruce Bisland", "Tank", 2001, "drums"));
        System.out.println(createIsMemberOfRelationship("Ian Hill", "Judas Priest", 1970, "bass"));
        System.out.println(createIsMemberOfRelationship("Rob Halford", "Judas Priest", 1973, "vocals"));
        System.out.println(createIsMemberOfRelationship("Glenn Tipton", "Judas Priest", 1974, "guitars"));
        System.out.println(createIsMemberOfRelationship("Scott Travis", "Judas Priest", 1989, "drums"));
        System.out.println(createIsMemberOfRelationship("Richie Faulkner", "Judas Priest", 2010, "guitars"));
        System.out.println(createIsMemberOfRelationship("Rob Cavestany", "Death Angel", 1982, "guitars"));
        System.out.println(createIsMemberOfRelationship("Mark Osequeda", "Death Angel", 1984, "vocals"));
        System.out.println(createIsMemberOfRelationship("Ted Aguilar", "Death Angel", 2001, "guitars"));
        System.out.println(createIsMemberOfRelationship("Damien Sisson", "Death Angel", 2009, "bass"));
        System.out.println(createIsMemberOfRelationship("Will Carroll", "Death Angel", 2009, "drums"));
        System.out.println(createIsMemberOfRelationship("Chuck Schuldiner", "Death", 1984, "guitars, vocals"));
        System.out.println(createIsMemberOfRelationship("Scott Clendenin", "Death", 1996, "bass"));
        System.out.println(createIsMemberOfRelationship("Richard Christy", "Death", 1996, "drums"));
        System.out.println(createIsMemberOfRelationship("Shannon Hamm", "Death", 1996, "guitars"));
        System.out.println(createIsMemberOfRelationship("Lief Edling", "Candlemass", 1984, "bass"));
        System.out.println(createIsMemberOfRelationship("Mats Björkman", "Candlemass", 1985, "guitars"));
        System.out.println(createIsMemberOfRelationship("Jan Lindh", "Candlemass", 1987, "drums"));
        System.out.println(createIsMemberOfRelationship("Mats Levén", "Candlemass", 2006, "vocals"));
        System.out.println(createIsMemberOfRelationship("Scott Ian", "Anthrax", 1981, "guitars, vocals"));
        System.out.println(createIsMemberOfRelationship("Charlie Benante", "Anthrax", 1983, "drums"));
        System.out.println(createIsMemberOfRelationship("Frank Bello", "Anthrax", 1984, "bass"));
        System.out.println(createIsMemberOfRelationship("Joey Belladonna", "Anthrax", 1984, "vocals"));
        System.out.println(createIsMemberOfRelationship("Jonathan Donais", "Anthrax", 2013, "guitars"));
        System.out.println(createIsMemberOfRelationship("Hansi Kürsch", "Blind Guardian", 1986, "vocals"));
        System.out.println(createIsMemberOfRelationship("André Olbrich", "Blind Guardian", 1986, "guitars"));
        System.out.println(createIsMemberOfRelationship("Marcus Siepen", "Blind Guardian", 1987, "guitars"));
        System.out.println(createIsMemberOfRelationship("Frederik Ehmke", "Blind Guardian", 2005, "drums"));
        System.out.println(createIsMemberOfRelationship("Kai Hansen", "Gamma Ray", 1989, "guitars, vocals"));
        System.out.println(createIsMemberOfRelationship("Dirk Schlächter", "Gamma Ray", 1990, "guitars, keyboards, bass"));
        System.out.println(createIsMemberOfRelationship("Henjo Richter", "Gamma Ray", 1989, "guitars, keyboards"));
        System.out.println(createIsMemberOfRelationship("Michael Ehré", "Gamma Ray", 1989, "drums"));
        System.out.println(createIsMemberOfRelationship("Frank Beck", "Gamma Ray", 1989, "vocals"));
        System.out.println(createIsMemberOfRelationship("Brian Tatler", "Diamond Head", 1976, "guitars"));
        System.out.println(createIsMemberOfRelationship("Karl Wilcox", "Diamond Head", 1992, "drums"));
        System.out.println(createIsMemberOfRelationship("Andy Abberley", "Diamond Head", 2006, "guitars"));
        System.out.println(createIsMemberOfRelationship("Rasmus Bom Andersen", "Diamond Head", 2014, "vocals"));
        System.out.println(createIsMemberOfRelationship("Dean Ashton", "Diamond Head", 2016, "bass"));
    }



}
