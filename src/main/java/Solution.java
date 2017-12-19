
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
                "year: " + yearOfBirth + "})");
    }

    public String createPerson(final String personName) {
        return graphDatabase.runCypher("CREATE (n:Person {name: \"" + personName + "\"})");
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
                "CREATE (p)-[:IS_MEMBER_OF {year_of_join: " + yearOfJoin + ", " +
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
        return graphDatabase.runCypher("MATCH (n1:" + nodeType1 + " {name: \"" + nodeName1 + "\"})," +
                "(n2:" + nodeType2 + " {name: \"" + nodeName2 +"\"}), " +
                "p = shortestPath((n1)-[*]-(n2))\n" +
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
        return returnPathBetweenNodes("Person", "Person", personName1, personName2);
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

}
