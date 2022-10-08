package com.example.backend.entity.ga;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class GeneticAlgorithm {

    private Data data;

    public GeneticAlgorithm(Data data) {
        this.data = data;
    }

    // Tiến hóa
    public Population evolve(Population population) {
        return mutatePopulation(crossoverPopulation(population));
    }

    // lai ghép quần thể, Tạo quần thể
    Population crossoverPopulation(Population population) {
        Population crossoverPopulation = new Population(population.getSchedules().size(), data);// khởi tạo quần thể
        // Instream.renge(0, 2) -> 0,1
        //set schedule đầu tiên vị trí 0 của crossover bằng schedule của population
        IntStream.range(0, Driver.NUMBER_OF_ELITE_SCHEDULE).forEach(x-> crossoverPopulation.getSchedules()
                .set(x, population.getSchedules().get(x))
        );


        IntStream.range(Driver.NUMBER_OF_ELITE_SCHEDULE, population.getSchedules().size()).forEach( x->{
            if(Driver.CROSSOVER_RATE > Math.random()){
                //Lựa chọn
                //  khởi tạo bố và khởi tạo mẹ bằng cách lấy schedule tốt nhất từ quần thể tournamentPopulation
                Schedule schedule1 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                Schedule schedule2 = selectTournamentPopulation(population).sortByFitness().getSchedules().get(0);
                // set lại giá trị cho cá thể schedule bằng con mới lai chéo giữa bố và mẹ
                crossoverPopulation.getSchedules().set(x, crossoverSchedule(schedule1, schedule2));
            }
            // trường hợp không tốt lấy lại giá trị cá thể của tập cá thể ban đầu
            else crossoverPopulation.getSchedules().set(x, population.getSchedules().get(x));
        });
        return crossoverPopulation;
    }

    //lai ghép 2 schedule(NST) bố và mẹ
    /**
     * crossoverSchecdule : A1, A3, A2, A4, A5
     * Shcedule bố:         A2, A1, A4, A5, A3
     * Shcedule mẹ:         A3, A1, A4, A5, A2
     * x : 0,1,2,3,4
     * x = 0 và > 0.5 A1 - > A2 gen con A2, A3, A2, A4, A5
     * x = 1 và > 0.5 A3 - > A2 gen con A2, A1, A2, A4, A5
     * x = 2 và < 0.5 A2 - > A4 gen con A2, A1, A4, A4, A5 ...
     * cuối cùng : tạo ra 1 cá thể (NST) schedule có các gen ( các lớp ) của bố và mẹ và các lớp
     */

    Schedule crossoverSchedule(Schedule schedule1, Schedule schedule2) {

        Schedule crossoverSchedule = new Schedule(data).initialize();// khởi tạo ngẫu nhiên 1 schedule
        IntStream.range(0, crossoverSchedule.getClasses().size()).forEach(x -> { // lấy ra size class và loop size int
            // random ngẫu nhiên trên 0.5, swap class vị trí x -> bằng giá trị của schedule 1 tại vịt rí x
            if(Math.random() > 0.5) crossoverSchedule.getClasses().set(x, schedule1.getClasses().get(x)) ;
            else crossoverSchedule.getClasses().set(x, schedule2.getClasses().get(x));

        });

        return crossoverSchedule;
    }

    //
    //
    Population mutatePopulation(Population population) {
        Population mutatePopulation = new Population(population.getSchedules().size(), data);
        ArrayList<Schedule> schedules = mutatePopulation.getSchedules();
        //Duyệt các cá thể ưu tú để đột biến
        IntStream.range(0, Driver.NUMBER_OF_ELITE_SCHEDULE).forEach(x ->
                // giữ nguyên các cá thể tốt nhất ưu tú nhất vào trong quần thể đột biến
                schedules.set(x, population.getSchedules().get(x)));
        // nhưng cá thể còn lại thì đột biến
        IntStream.range(Driver.NUMBER_OF_ELITE_SCHEDULE, population.getSchedules().size()).forEach(x ->{
            // đột biến là quá trình thay thế gen
            schedules.set(x, mutateSchedule(population.getSchedules().get(x)));
        });
        return mutatePopulation;
    }

    //đột biến cá thể
    // vd có cá thể đột biến vị trí gen thứ 0: A1 A2 A5 A3 - > A5 A2 A5 A3
    // class là gen
    Schedule mutateSchedule(Schedule mutateSchedule) {
        Schedule schedule = new Schedule(data).initialize();
        IntStream.range(0, mutateSchedule.getClasses().size()).forEach(x -> {
            if(Driver.MUTATION_RATE > Math.random()){
                mutateSchedule.getClasses().set(x, schedule.getClasses().get(x));
            }
        });
        return mutateSchedule;
    }

    // lấy ngẫu nhiên quần thể có n cá thể tournamentSize
    // Lựa chọn cá thể tốt nhất trong TournamentPopulation để làm bố mẹ lai ghép
    Population selectTournamentPopulation(Population population) {
        // Quần thể cạnh tranh:   tournamentPopulation có 3 cá thể schedule
        Population tournamentPopulation = new Population(Driver.TOURNAMENT_SELECTION_SIZE, data);
        // set từng schedule tournamentPopulation bằng schedule của population truyền vào
        IntStream.range(0, Driver.TOURNAMENT_SELECTION_SIZE).forEach(x -> {
            tournamentPopulation.getSchedules().set(x, population.getSchedules()
                    .get( (int) (Math.random() * population.getSchedules().size())));
        });
        return tournamentPopulation;
    }

}
