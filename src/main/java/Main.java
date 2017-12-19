import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
        Populator populator = new Populator(solution);
        populator.createNodes();
        populator.createRelationships();

        solution.databaseStatistics();
        solution.databaseSchema();

        System.out.println(solution.returnAllRelationshipsForPerson("Rob Halford"));
        System.out.println(solution.returnAllRelationshipsForBand("Judas Priest"));
        System.out.println(solution.returnAllRelationshipsForGenre("Heavy metal"));
        System.out.println(solution.returnAllRelationshipsForCountry("UK"));

        System.out.println(solution.returnPathBetweenBands("Judas Priest", "Gloryhammer"));
        System.out.println(solution.returnPathBetweenGenres("Thrash metal", "Death metal"));
        System.out.println(solution.returnPathBetweenCountries("Germany", "UK"));
        System.out.println(solution.returnPathBetweenPersons("Hansi Kürsch", "André Olbrich"));
        System.out.println(solution.returnPathBetweenBandAndCountry("Tank", "Switzerland"));
        System.out.println(solution.returnPathBetweenBandAndGenre("Gloryhammer", "Speed metal"));
        System.out.println(solution.returnPathBetweenPersonAndBand("Ian Hill", "Diamond Head"));
        System.out.println(solution.returnPathBetweenPersonAndCountry("Chuck Schuldiner", "US"));
        System.out.println(solution.returnPathBetweenPersonAndGenre("Ted Aguilar", "Death metal"));
        System.out.println(solution.returnPathBetweenGenreAndCountry("Doom metal", "Sweden"));
    }

}
