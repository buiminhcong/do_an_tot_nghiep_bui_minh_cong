package com.example.backend.entity.ga;

public class Driver {
    public static final int POPULATION_SIZE = 9; // Size của quần thể khởi tạo 1 thế hệ có 9 quần thể
    public static final double MUTATION_RATE = 0.1; // tỷ lệ đột biến
    public static final double CROSSOVER_RATE = 0.9; // tỷ lệ lai ghép
    // số lượng cá thể có trong quần thể cạnh tranh tournamentPopulation
    public static final int TOURNAMENT_SELECTION_SIZE = 3;
    public static final int NUMBER_OF_ELITE_SCHEDULE = 1; // số cá thể ưu tú
}
