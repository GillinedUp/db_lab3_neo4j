
public class Solution {

    private final GraphDatabase graphDatabase = GraphDatabase.createDatabase();

    public void databaseStatistics() {
        System.out.println(graphDatabase.runCypher("CALL db.labels()"));
        System.out.println(graphDatabase.runCypher("CALL db.relationshipTypes()"));
    }

    public void databaseSchema() {
        System.out.println(graphDatabase.runCypher("CALL db.schema()"));
    }

    public String createBand(final String bandName) {
        return graphDatabase.runCypher("CREATE (n:Band {name: \"" + bandName + "\"})");
    }

    public String createCountry(final String countryName) {
        return graphDatabase.runCypher("CREATE (n:Country {name: \"" + countryName + "\"})");
    }

    public String createGenre(final String genreName) {
        return graphDatabase.runCypher("CREATE (n:Genre {name: \"" + genreName + "\"})");
    }

    public String createOriginRelationship(final String bandName, final String countryName) {
        return graphDatabase.runCypher("MATCH (b:Band {name:\"" + bandName + "\"}), " +
                "(c:Country {name:\"" + countryName + "\"})\n" +
                "CREATE (b)-[:ORIGIN]->(c)");
    }

    public String createPlaysRelationship(final String bandName, final String genreName) {
        return graphDatabase.runCypher("MATCH (b:Band {name:\"" + bandName + "\"}), " +
                "(c:Genre {name:\"" + genreName + "\"})\n" +
                "CREATE (b)-[:PLAYS]->(c)");
    }

    public void createNodes() {
        System.out.println(createBand("Gloryhammer"));
        System.out.println(createBand("Tank"));
        System.out.println(createBand("Judas Priest"));
        System.out.println(createBand("Death Angel"));
        System.out.println(createBand("Death"));
        System.out.println(createBand("Candlemass"));
        System.out.println(createCountry("UK"));
        System.out.println(createCountry("US"));
        System.out.println(createCountry("Switzerland"));
        System.out.println(createCountry("Sweden"));
        System.out.println(createGenre("Heavy metal"));
        System.out.println(createGenre("Thrash metal"));
        System.out.println(createGenre("Death metal"));
        System.out.println(createGenre("Doom metal"));
        System.out.println(createGenre("Power metal"));
    }

    public void createRelationships() {
//        System.out.println(createOriginRelationship("Judas Priest", "UK"));
        System.out.println(createOriginRelationship("Gloryhammer", "UK"));
        System.out.println(createOriginRelationship("Gloryhammer", "Switzerland"));
        System.out.println(createOriginRelationship("Tank", "UK"));
        System.out.println(createOriginRelationship("Death Angel", "US"));
        System.out.println(createOriginRelationship("Candlemass", "Sweden"));
        System.out.println(createOriginRelationship("Death", "US"));
    }



}
